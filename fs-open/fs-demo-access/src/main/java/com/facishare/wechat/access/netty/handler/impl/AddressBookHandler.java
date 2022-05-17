package com.facishare.wechat.access.netty.handler.impl;

import com.facishare.open.addressbook.api.CircleService;
import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.addressbook.model.Circle;
import com.facishare.open.addressbook.model.Employee;
import com.facishare.open.addressbook.model.MetaParam;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.Constants.LoginKey;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import com.facishare.wechat.access.utils.CommonUtil;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.zip.CRC32;

/**
 * 登录消息处理器
 * Created by lif on 2015/9/30.
 */
public class AddressBookHandler implements MsgHandler {


    private final Logger Log = LoggerFactory.getLogger(getClass());


    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {

        try {
            String token = msg.getHeader().get(BusiKey.HEADER_KEY_TOKEN);

            EmployeeService employeeService = MsgServer.getIntance().getEmployeeService();

            MetaParam param = new MetaParam();
            Log.debug("emplyeeId : {}", CommonUtil.getLoginId(ctx.channel()));
//            param.setCurrentEmployeeId(Integer.parseInt(CommonUtil.getLoginId(ctx.channel())));
//            param.setEnterpriseAccount(CommonUtil.getLoginEa(ctx.channel()));
            String userId = MsgServer.getIntance().getRedisTemplate().get(token, "");
            if (StringUtils.isBlank(userId)) {
                TransData transData = new TransData();

                transData.setHeader(msg.getHeader());
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.TOKEN_TIMEOUT);
                transData.setData(dataMap);

                RouterManager.routeSuccess(ctx.channel(), transData);
            }
            Log.debug("userId:{}", userId);
            String[] users = userId.split("\\.");
            param.setCurrentEmployeeId(Integer.parseInt(users[2]));
            param.setEnterpriseAccount(users[1]);

            //
            ListResult<Integer> list = employeeService.getAllEmployeeIds(param, 2);


            ListResult<Employee> employeeList = employeeService.getEmployees(param, list.getResult());
            ListResult<Employee> employeeListTmp = new ListResult<>();
            List<Employee> l = new ArrayList<>();
            for (Employee e : employeeList.getResult()) {
                 if (e.getAccount().contains("1881111")) {
                     e.setName(e.getMobile());
                     e.setProfileImage("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3637558653,1735813698&fm=58");
                     l.add(e);
                 }
            }
            employeeListTmp.setResult(l);
            employeeListTmp.setAddressBookResultCodeEnum(employeeList.getAddressBookResultCodeEnum());

            TransData result = new TransData();
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("errorCode", CommonConstant.LOGIN_PASSWORD_SUCCESS);
            resultMap.put("employeeList", new Gson().toJson(employeeListTmp));
            result.setData(resultMap);
            result.setHeader(msg.getHeader());

            // 返回登录信息
            RouterManager.routeLoginSuccess(ctx.channel(), result);

        } catch (Exception e) {
            Log.error("error:" + e.getMessage(), e);
            TransData result = new TransData();
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("errorCode", "-2");
            result.setData(resultMap);
            result.setHeader(msg.getHeader());
            RouterManager.routeLoginSuccess(ctx.channel(), result);

        }

    }

}
