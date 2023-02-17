package com.example.goodchat;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TCChannelManage {
    /**
     * 存储每一个客户端接入进来时的channel对象
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 读锁
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    public static ConcurrentMap<String, Channel> userIdAndChannelMap = new ConcurrentHashMap<>();
    public static ConcurrentMap<Channel, String> channel_userid = new ConcurrentHashMap<>();

    public static void send(String senderId, String receiverId, String message, Channel senderChannel) {
        try {
            rwLock.readLock().lock();
            // 1.寻找receiverId的channel
            Channel receiverChannel = userIdAndChannelMap.get(receiverId);
            if (receiverChannel == null) {
                // 使用发送者的通道告知发送者，你要发的那个人不在线
                senderChannel.writeAndFlush(new TextWebSocketFrame(receiverId + "不在线"));
                return;
            }
            // 2.发送。A给B发，B若要收到消息，其实是通过B的channel给B发消息
            receiverChannel.writeAndFlush(new TextWebSocketFrame(senderId + "发来消息===》" + message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public static boolean hasChannel(String id) {
        Channel channel = userIdAndChannelMap.get(id);
        if (channel == null) {
            return false;
        } else {
            return true;
        }
    }

}
