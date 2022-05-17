package com.facishare.wechat.access.client;


import com.facishare.wechat.access.client.codec.JsonDecoder;
import com.facishare.wechat.access.client.codec.JsonEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by lif on 2015/9/28.
 */
public class MsgClient {
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public void connect(String host, int port) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("MsgDecoder", new JsonDecoder());// 解码器
                pipeline.addLast("MsgEncoder", new JsonEncoder());// 编码器
                ch.pipeline().addLast(new MsgClientHandler());
                //ch.pipeline().addLast(new HeartBeatHandler());
                ch.pipeline().addLast(new IdleStateHandler(60, 0, 0));
            }
        });

        ChannelFuture future;
        try {
            future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            //断线重连操作
//            executor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(5);
//                        System.out.println("检查到断线，发起重连");
//                        connect(host, port);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });

            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
       new MsgClient().connect("localhost", 29312);
       // new MsgClient().connect("172.31.105.112", 29312);
    }
}
