package com.facishare.wechat.access.netty;

import com.facishare.fcp.client.FcpClient;
import com.facishare.open.addressbook.api.CircleService;
import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.open.warehouse.api.FileService;
import com.facishare.uc.api.service.UserAccountService;
import com.facishare.wechat.access.netty.codec.JsonEncoder;
import com.facishare.wechat.access.netty.codec.JsonDecoder;
import com.facishare.wechat.access.netty.handler.HeartBeatHandler;
import com.facishare.wechat.access.utils.FcpClientHelper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.net.InetSocketAddress;

/**
 * Created by lif on 2015/9/28.
 */
public class MsgServer {
    private final Logger Log = LoggerFactory.getLogger(getClass());

    private static MsgServer instance = new MsgServer();

    public static MsgServer getIntance(){
        return instance;
    }

    private CircleService circleService;

    private EmployeeService employeeService;

    private RabbitTemplate rabbitTemplate;

    private FileService fileService;

    private RedisTemplate redisTemplate;

    private FcpClientHelper fcpClientHelper;

    private FcpClient fcpClient;
    private UserAccountService userAccountService;

    public FcpClientHelper getFcpClientHelper() {
        return fcpClientHelper;
    }

    public void setFcpClientHelper(FcpClientHelper fcpClientHelper) {
        this.fcpClientHelper = fcpClientHelper;
    }

    public FileService getFileService() {
        return fileService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public CircleService getCircleService() {
        return circleService;
    }

    public void setCircleService(CircleService circleService) {
        this.circleService = circleService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public FcpClient getFcpClient() {
        return fcpClient;
    }

    public void setFcpClient(FcpClient fcpClient) {
        this.fcpClient = fcpClient;
    }

    public UserAccountService getUserAccountService() {
        return userAccountService;
    }

    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public void start(int port) {
        System.out.println("准备启动netty server");
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        bootstrap.childOption(ChannelOption.SO_LINGER, 0);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new LoggingHandler(LogLevel.INFO));

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("MsgDecoder", new JsonDecoder());// 解码器
                pipeline.addLast("MsgEncoder", new JsonEncoder());// 编码器
                ch.pipeline().addLast(new MsgServerHandler());
                ch.pipeline().addLast(new IdleStateHandler(60,0,0));
            }
        });

        InetSocketAddress addr = new InetSocketAddress(port);

        bootstrap.bind(addr).addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future)
                    throws Exception {
                if (future.isSuccess()) {
                    Log.info("info:Netty服务器,启动成功");
                } else {
                    Log.info("info:Netty服务器,启动失败");
                }
            }
        });
    }

    public static void main(String[] args) {
        MsgServer msgServer = new MsgServer();
        msgServer.start(29301);
    }

}
