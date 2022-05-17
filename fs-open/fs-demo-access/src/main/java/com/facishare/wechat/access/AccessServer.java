package com.facishare.wechat.access;

import com.facishare.fcp.client.FcpClient;
import com.facishare.open.addressbook.api.CircleService;
import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.open.warehouse.api.FileService;
import com.facishare.uc.api.service.UserAccountService;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.utils.FcpClientHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * access bootstrap
 * Created by lif on 2015/9/30.
 */
public class AccessServer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.start();

        CircleService circleService = (CircleService) context.getBean("circleService");
        EmployeeService employeeService = (EmployeeService) context.getBean("employeeService");
        RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
        FcpClientHelper fcpClientHelper = (FcpClientHelper)context.getBean("fcpClientHelper");

        UserAccountService userAccountService = (UserAccountService)context.getBean("userAccountService");
        FcpClient fcpClient = (FcpClient)context.getBean("fcpClient2");

        MsgServer.getIntance().setUserAccountService(userAccountService);
        MsgServer.getIntance().setFcpClient(fcpClient);
        MsgServer.getIntance().setCircleService(circleService);
        MsgServer.getIntance().setEmployeeService(employeeService);
        MsgServer.getIntance().setRedisTemplate(redisTemplate);
        MsgServer.getIntance().setFcpClientHelper(fcpClientHelper);
        MsgServer.getIntance().start(29312);
    }
}
