package Core.Handlers;

import Core.Factory.FactoryUtils;
import Core.Annotation.ServiceHandlerMapping;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * extends this class to be the ServiceHandler (Controller)
 */
public abstract class ServiceHandler {
    public  void prepareHandler(ChannelHandlerContext ctx, FullHttpRequest req,String path){

        //获取request中的参数
        Map<String,Object>map=ServiceHandler.parse(req);

        HttpResponseHandler res=new HttpResponseHandler(ctx);
        HttpRequestHandler  r=new HttpRequestHandler(req,map);

        //getClass()方法获取的是当前类的本类，一定不是父类
        Method[]methods=this.getClass().getMethods();
        for(Method m:methods){
            ServiceHandlerMapping serviceHandlerMapping=m.getAnnotation(ServiceHandlerMapping.class);
            if(serviceHandlerMapping==null){
                continue;
            }
            String match= serviceHandlerMapping.value();
            if(match.equals(path)){
                handler(m,map,res,r);
                return;
            }
        }
        HttpResponseHandler.write("Resources not found",ctx);
    }

    //在该controller类中搜索符合path的注解的方法，并执行该方法。（该方法，请求参数，看该方法是否需要传res或者req的参）
    public void handler(Method m,Map<String,Object> map,HttpResponseHandler res,HttpRequestHandler req){
        try {
            Parameter[]p=m.getParameters();
            Object[]args=new Object[p.length];
            int arg_index=0;
            if(p.length>0){
                for (Parameter parameter : p) {

                    Class<?> c = parameter.getType();
                    if (c.getName().equals(HttpResponseHandler.class.getName())) {
                        args[arg_index++] = res;
                        continue;
                    } else if (c.getName().equals(HttpRequestHandler.class.getName())) {
                        args[arg_index++] = req;
                        continue;
                    }
                    //若参数既不是res，也不是req，则创建一实例并传参给该实例
                    Object instance = c.getDeclaredConstructor().newInstance();
                    Field[] f = instance.getClass().getDeclaredFields();

                    for (Field field : f) {
                        String name = field.getName();
                        if (map.containsKey(name)) {
                            field.setAccessible(true);
                            //将instance的field成员设置为map.get(name)的值
                            FactoryUtils.set(field,instance,map.get(name));
                        }
                    }

                    args[arg_index++] = instance;

                }
            }
            //调用this的方法m，参数为args
            m.invoke(this,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> parse(FullHttpRequest req){
        HttpMethod method = req.method();

        Map<String, Object> parmMap = new HashMap<>();

        if (HttpMethod.GET == method) {
            // 是GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
            decoder.parameters().forEach((key, value) -> {
                // entry.getValue()是一个List, 只取第一个元素
                parmMap.put(key, value.get(0));
            });
        } else if (HttpMethod.POST == method) {
            // 是POST请求
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(req);
            decoder.offer(req);

            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

            for (InterfaceHttpData parm : parmList) {

                Attribute data = (Attribute) parm;
                try {
                    parmMap.put(data.getName(), data.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return parmMap;
    }
}
