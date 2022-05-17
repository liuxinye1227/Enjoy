package com.facishare.wechat.access.manager;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接管理器
 * Created by lif on 2015/9/30.
 */
public class ConnectionManager{


    /**
     * 存放所有客户端登陆连接
     */
    private static Map<String, Channel> connMap = new ConcurrentHashMap<String, Channel>();

    /**
     * 添加连接
     *
     * @param userId
     * @param channel
     */
    public static void addConn(String userId, Channel channel) {
        connMap.put(userId, channel);
        System.out.println("-------------------addConn------------------------------");
        for (Map.Entry<String, Channel> entry : connMap.entrySet()) {
            System.out.println("addConn: " + entry.getKey());

        }
        System.out.println("-------------------addConn end------------------------------");

    }

    /**
     * 移除连接
     *
     * @param userId
     */
    public static void removeConn(String userId) {
        if (connMap.containsKey(userId)) {
            connMap.remove(userId).close();
        }
    }


    /**
     * 获取用户Channel
     *
     * @param userId
     * @return
     */
    public static Channel getConn(String userId) {
        System.out.println("-------------------getConn------------------------------");
        for (Map.Entry<String, Channel> entry : connMap.entrySet()) {
            System.out.println("addConn: " + entry.getKey());

        }
        System.out.println("-------------------getConn end------------------------------");
        return connMap.get(userId);
    }

}
