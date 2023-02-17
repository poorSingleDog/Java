package com.example.goodchat;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.Resource;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TCWebSocketHandler extends SimpleChannelInboundHandler<Object> {
    // 用于服务器端web套接字打开和关闭握手
    private WebSocketServerHandshaker handshaker;

    private static final String WEB_SOCKET_URL = "/websocket";

    public static SqlSessionFactory factory;



    static {
        try {
            factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void channelRegistered(ChannelHandlerContext ctx)throws Exception{
//        System.out.println("register, remoteAddress:"+ctx.channel().remoteAddress());
//    }
//
//    @Override
//    public void channelUnregistered(ChannelHandlerContext ctx)throws Exception{
//        System.out.println("unregister, remoteAddress:"+ctx.channel().remoteAddress());
//    }


    //客户端与服务端创建连接的时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        TCChannelManage.group.add(ctx.channel());
        //用户上线代码
        System.out.println("客户端与服务端连接开启，客户端remoteAddress：" + ctx.channel().remoteAddress());
    }

    //客户端与服务端断开连接的时候调用
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TCChannelManage.group.remove(ctx.channel());
        //判断是不是ws的channel
        if(TCChannelManage.channel_userid.containsKey(ctx.channel())) {
            String userid = TCChannelManage.channel_userid.remove(ctx.channel());
            TCChannelManage.userIdAndChannelMap.remove(userid);
            System.out.println(userid + " 下线了！");
        }
        System.out.println("客户端与服务端连接关闭..."+ ctx.channel().remoteAddress());
    }

    //服务端接收客户端发送过来的数据结束之后调用
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    //工程出现异常的时候调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    //服务端处理客户端请求的核心方法
    protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {

        /**传统的HTTP接入（采用http处理方式）
         * 第一次握手请求消息由HTTP协议承载，所以它是一个HTTP消息，
         * 握手成功后，数据就直接从 TCP 通道传输，与 HTTP 无关了。
         * 执行handleHttpRequest方法来处理WebSocket握手请求。
         */

        // FullHttpRequest是完整的 HTTP请求，协议头和Form数据是在一起的，不用分开读
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(context, (FullHttpRequest) msg);
        }
        /**
         *  WebSocket接入（采用socket处理方式）
         *  提交请求消息给服务端，
         *  WebSocketServerHandler接收到的是已经解码后的WebSocketFrame消息。
         */
        else if (msg instanceof WebSocketFrame) {
            handWebsocketFrame(context, (WebSocketFrame) msg);
        }
        /**
         * Websocket的数据传输是frame形式传输的，比如会将一条消息分为几个frame，按照先后顺序传输出去。这样做会有几个好处：
         *
         * 1）大数据的传输可以分片传输，不用考虑到数据大小导致的长度标志位不足够的情况。
         *
         * 2）和http的chunk一样，可以边生成数据边传递消息，即提高传输效率。
         */
    }

    /**
     * 处理客户端与服务端之前的websocket业务
     *
     * @param ctx
     * @param frame
     */
    private void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {


        //判断是否是关闭websocket的指令
        if (frame instanceof CloseWebSocketFrame) {
            System.out.println("处理关闭websocket请求，" + "客户："+ctx.channel().remoteAddress());
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }

        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            System.out.println("处理websocket的ping消息，" + "客户："+ctx.channel().remoteAddress());
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        //判断是否是ws的text桢
        if (frame instanceof TextWebSocketFrame) {
            System.out.println("处理websocket的前端聊天消息，" + "客户："+ctx.channel().remoteAddress());
            // 返回应答消息
            String requestMsg = ((TextWebSocketFrame) frame).text();
            System.out.println("收到客户端" + ctx.channel().remoteAddress() + "的消息==》" + requestMsg);
            String[] array = requestMsg.split(",");
            // 先判断通道管理器中是否存在该通道，没有则添加进去
            if (!TCChannelManage.hasChannel(array[0])) {
                TCChannelManage.userIdAndChannelMap.put(array[0], ctx.channel());   //《发送者id-通道》
                TCChannelManage.channel_userid.put(ctx.channel(),array[0]);         //《通道-发送者id》
                System.out.println(array[0]+" 上线了！");
            }

            if (array[0].length() != 0 && array[1].length() != 0) {
                TCChannelManage.send(array[0], array[1], array[2], ctx.channel());
            } else if (array[0].length() != 0 && array[1].length() == 0) {
                //如果没有指定接收者表示群发array.length() = 2
                System.out.println("用户" + array[0] + "群发了一条消息：" + array[2]);
                TCChannelManage.group.writeAndFlush(new TextWebSocketFrame("用户" + array[0] + "群发了一条消息：" + array[2]));
            } else {
                //如果没有指定发送者与接收者表示向服务端发送array.length() = 1
                System.out.println("服务端接收用户" + ctx.channel().remoteAddress() + "消息，不再发送出去");
                ctx.writeAndFlush(new TextWebSocketFrame("你向服务端发送了消息==》" + array[2]));
            }
        }
    }


    /**
     * 处理客户端向服务端发起http握手请求的业务
     *（增加处理post请求的方法）
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws IOException {

        System.out.println("处理http请求，http方法==>>" + req.method() + ",客户：" + ctx.channel().remoteAddress() + " 请求http地址==>>" + req.uri());
        Map<String, String> parmMap = new HashMap<>();
        try {
            //解析请求参数
            parmMap = parse(req);
            for (Map.Entry<String, String> entry : parmMap.entrySet()) {
                System.out.println("请求参数：" + entry.getKey() + "--" + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 如果是post请求，判断消息是否正确，否则重定向
         */
        if (req.method() == HttpMethod.POST) {
            if (req.uri().startsWith("/static/chat")) {
                if (parmMap.containsKey("username") && parmMap.containsKey("password")) {
                    String username = parmMap.get("username");
                    String password = parmMap.get("password");
                    try (SqlSession sqlSession = factory.openSession()) {
                        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
                        User user = userMapper.getUser(username, password);
                        if (user == null) {      //用户名和密码不正确--》重定向到登录界面
                            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
                            response.headers().set(HttpHeaderNames.LOCATION, "/static/login.html");
                            ChannelFuture future = ctx.channel().writeAndFlush(response);
                            future.addListener(ChannelFutureListener.CLOSE);
                            return;
                        } else {
                            //百里香叶渲染html界面
                            TemplateEngine engine = new TemplateEngine();
                            ClassLoaderTemplateResolver r = new ClassLoaderTemplateResolver();
                            engine.setTemplateResolver(r);
                            Context context = new Context();
                            context.setVariable("username", parmMap.get("username"));
                            context.setVariable("senderId", parmMap.get("username"));
                            context.setVariable("file_sender", parmMap.get("username"));
                            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                            //将用户id作为会话id存入浏览器
                            response.headers().add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.encode("SessionId",user.getUsername()));

                            response.content().writeBytes(engine.process("/static/chat.html", context).getBytes());
                            ctx.channel().writeAndFlush(response);
                            ctx.channel().close();
                            return;
                        }
                    }

                }
            } else if (req.uri().startsWith("/file-action")) {
                System.out.println("body:  " + req.content());
                if (req.uri().equals("/file-action")) {
                    MultipartRequest MultipartBody = getMultipartBody(req);
                    Map<String, FileUpload> fileUploads = MultipartBody.getFileUploads();
                    //输出文件信息
                    for (String key : fileUploads.keySet()) {
                        //获取文件对象
                        FileUpload fileupload = fileUploads.get(key);
                        System.out.println("fileName is: " + fileupload.getFile().getName());
                        //获取文件流
                        InputStream in = new FileInputStream(fileupload.getFile());
                        //写文件到resources文件夹下
                        String file = fileupload.getFile().getName();
                        String path = this.getClass().getClassLoader().getResource("").getPath();
                        String file_name = file.substring(file.lastIndexOf("_") + 1);
                        OutputStream out = new FileOutputStream(path + "/" + file_name);
                        IOUtils.copy(in, out);


                    }
                    //输出参数信息，前端表单的 name-value
                    JSONObject params = MultipartBody.getParams();
                    //输出文件信息
                    System.out.println(JSONObject.toJSONString(params));

                }

                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK); //设置重定向响应码 （临时重定向、永久重定向）
//                HttpHeaders headers = response.headers();
//                headers.set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "x-requested-with,content-type");
//                headers.set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "POST,GET");
//                headers.set(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//                headers.set(HttpHeaderNames.LOCATION, "/static/login.html"); //重定向URL设置
//                ctx.channel().writeAndFlush(response)
//                        .addListener(ChannelFutureListener.CLOSE);//解决写入完成后，客户端断开会报异常的问题

                //返回json对象到前端
                String s="{\"success\":true}";
                response.content().writeBytes(s.getBytes());
                ctx.channel().writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
                return;

            }

        } else if (req.method() == HttpMethod.GET) {     //处理websocket握手，或者请求页面
            if (req.uri().equals("/websocket") && ("websocket".equals(req.headers().get("Upgrade")))) {
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                        WEB_SOCKET_URL, null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {
                    // 通过它构造握手响应消息返回给客户端，
                    // 同时将WebSocket相关的编码和解码类动态添加到ChannelPipeline中，用于WebSocket消息的编解码，
                    // 添加WebSocketEncoder和WebSocketDecoder之后，服务端就可以自动对WebSocket消息进行编解码了
                    handshaker.handshake(ctx.channel(), req);
                }
                return;
            } else if (!req.uri().equals("/static/login.html") && !req.uri().equals("/favicon.ico")) {     //重定向到登陆界面
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
                response.headers().set(HttpHeaderNames.LOCATION, "/static/login.html");
                ChannelFuture future = ctx.channel().writeAndFlush(response);
                future.addListener(ChannelFutureListener.CLOSE);
                return;
            } else {                                                 //返回登录界面

                String cookieStr = (String) req.headers().get("Cookie");
                if(cookieStr != null) {
                    Set<Cookie> cookies = ServerCookieDecoder.decode(cookieStr);
                    for (Cookie cookie : cookies) {
                        System.out.println("cookie值：" + cookie.value());
                    }
                }

                String path = req.uri().substring(1);
                if (path.contains("?")) {
                    path = path.substring(0, path.indexOf("?"));
                }
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
                //如果是请求静态资源，则呈现网页，加载网页过程中会发送upgrade=websocket的http请求，建立ws连接。
                if (in != null) {
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                    response.content().writeBytes(bytes);
                    ctx.channel().writeAndFlush(response);
                    ctx.channel().close();
                    return;
                }
            }
        }

    }

    /**
     * 服务端向客户端响应消息
     *
     * @param ctx
     * @param req
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 解析GET、POST请求参数
     * @return 包含所有请求参数的键值对, 如果没有参数, 则返回空Map
     *
     * @throws IOException
     */
    public Map<String, String> parse(FullHttpRequest fullReq) throws IOException {

        HttpMethod method = fullReq.method();

        Map<String, String> parmMap = new HashMap<>();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(fullReq.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), fullReq);
            List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();
            for(InterfaceHttpData data:postData){
                if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    parmMap.put(attribute.getName(), attribute.getValue());
                }
            }
        } else {
            // 不支持其它方法
            System.out.println("不支持其他方法提交的参数");
        }

        return parmMap;
    }

    private static MultipartRequest getMultipartBody(FullHttpRequest request) {
        try {
            //创建HTTP对象工厂
            HttpDataFactory factory = new DefaultHttpDataFactory(true);
            //使用HTTP POST解码器
            HttpPostRequestDecoder httpDecoder = new HttpPostRequestDecoder(factory, request);
            httpDecoder.setDiscardThreshold(0);
            if (httpDecoder != null) {
                //获取HTTP请求对象
                final HttpContent chunk = (HttpContent) request;
                //加载对象到加吗器。
                httpDecoder.offer(chunk);
                if (chunk instanceof LastHttpContent) {
                    //自定义对象bean
                    MultipartRequest multipartRequest = new MultipartRequest();
                    //存放文件对象
                    Map<String, FileUpload> fileUploads = new HashMap<>();
                    //存放参数对象
                    JSONObject body = new JSONObject();
                    //通过迭代器获取HTTP的内容
                    java.util.List<InterfaceHttpData> InterfaceHttpDataList = httpDecoder.getBodyHttpDatas();
                    for (InterfaceHttpData data : InterfaceHttpDataList) {
                        //如果数据类型为文件类型，则保存到fileUploads对象中
                        if (data != null && InterfaceHttpData.HttpDataType.FileUpload.equals(data.getHttpDataType())) {
                            FileUpload fileUpload = (FileUpload) data;
                            fileUploads.put(data.getName(), fileUpload);
                        }
                        //如果数据类型为参数类型，则保存到body对象中
                        if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                            Attribute attribute = (Attribute) data;
                            body.put(attribute.getName(), attribute.getValue());
                        }
                    }
                    //存放文件信息
                    multipartRequest.setFileUploads(fileUploads);
                    //存放参数信息
                    multipartRequest.setParams(body);

                    return multipartRequest;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
