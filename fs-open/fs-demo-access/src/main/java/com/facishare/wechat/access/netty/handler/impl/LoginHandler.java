package com.facishare.wechat.access.netty.handler.impl;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.Tag;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.facishare.common.fsi.CanProto;
import com.facishare.fcp.FcpCodec;
import com.facishare.fcp.client.FcpClient;
import com.facishare.fcp.protocol.*;
import com.facishare.open.common.storage.redis.RedisTemplate;
import com.facishare.uc.api.model.useraccount.arg.GetEnterpriseListByMobileArg;
import com.facishare.uc.api.model.useraccount.result.GetEnterpriseListByMobileResult;
import com.facishare.uc.api.service.UserAccountService;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.Constants.LoginKey;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import com.facishare.wechat.access.utils.CommonUtil;
import com.facishare.wechat.access.utils.EnterpriseSummary;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.krb5.internal.crypto.crc32;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.zip.CRC32;

/**
 * 登录消息处理器
 * Created by lif on 2015/9/30.
 */
public class LoginHandler implements MsgHandler {


    private final Logger Log = LoggerFactory.getLogger(getClass());

    private String enterpriseGetFromPhoneUrl = "OPG/Authorize/GetAvaliableEnterprises";


    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {

        try {
            Map<String, String> data = msg.getData();
            String ea = data.get(LoginKey.BODY_KEY_EA); // 企业号
            String userName = data.get(LoginKey.BODY_KEY_USERNAME);// 手机号
            String password = data.get(LoginKey.BODY_KEY_PASSWORD);// 密码

            TransData result = new TransData();
            result.setHeader(msg.getHeader());

            //登录失败
            if (StringUtils.isEmpty(password) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(ea)) {
                // 返回登录失败，用户名或者密码不正确
                Map<String, String> resultMap = new HashMap<String, String>();
                resultMap.put("errorCode", CommonConstant.LOGIN_PASSWORD_ERROR);
                result.setData(resultMap);
                RouterManager.routeLoginFail(ctx.channel(), result);
                return;
            }
            List<EnterpriseSummary> list = this.getEnterpriseByPhone(userName, password);
            if (list == null || list.size()<=0) {
                // 返回登录失败，用户名或者密码不正确
                Map<String, String> resultMap = new HashMap<>();
                resultMap.put("errorCode", CommonConstant.LOGIN_PASSWORD_ERROR);
                result.setData(resultMap);
                RouterManager.routeLoginFail(ctx.channel(), result);
                return;
            }
            EnterpriseSummary summary = list.get(0);

            for (EnterpriseSummary summary1 : list) {
                 if (summary1.getEnterpriseAccount().equals(ea) ) {
                     summary = summary1;
                 }
            }
            Long userId = (long)summary.getEmployeeId();


            // 判断是否有旧链接
            Channel oldChannel = ConnectionManager.getConn(userId + "");
            if (oldChannel != null) {
                ConnectionManager.removeConn(userId + "");
            }

            // 添加连接
            ConnectionManager.addConn(userId + "", ctx.channel());

            // 生成一个token，登录结果放到redis
            String token = UUID.randomUUID().toString();
            RedisTemplate redisTemplate =  MsgServer.getIntance().getRedisTemplate();
            redisTemplate.set(token, "E." + summary.getEnterpriseAccount() + "." + userId);
            redisTemplate.set("E." + summary.getEnterpriseAccount() + "." + userId, token);
            redisTemplate.expire(token, 30 * 60 * 16);
            redisTemplate.expire("E." + summary.getEnterpriseAccount() + "." + userId, 30*60);
            Log.debug("---------------------------------------------");
            Log.debug("redis token [{}] :[{}]", token, redisTemplate.get(token,""));
            Log.debug("---------------------------------------------");

            CommonUtil.setLoginInfo(ctx.channel(), userId + "", ea, userName, token );

            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("errorCode", CommonConstant.LOGIN_PASSWORD_SUCCESS);
            resultMap.put("userId", userId + "");
            resultMap.put("userName", userName);
            resultMap.put("enterpriseAccount", summary.getEnterpriseAccount());
            resultMap.put("type", "1");
            resultMap.put("token", token);
            resultMap.put("ea", ea);
            result.setData(resultMap);


            // 返回登录信息
            RouterManager.routeLoginSuccess(ctx.channel(), result);

        } catch (Exception e) {
            Log.error("login error:" + e.getMessage(), e);
        }
    }

    public List<EnterpriseSummary> getEnterpriseByPhone(String phone, String password) {
        try {
            Log.debug("getEnterpriseByPhone phone：{} password:{}", phone, password);

            GetEnterpriseListByMobileResult result = this.getEnterpriseListByMobile(phone);

            GetAvaliableEnterprisesArg arg = new GetAvaliableEnterprisesArg();
            arg.setPhoneNumber(phone);
            arg.setPassword(password);
            ArrayList<Integer> list = new ArrayList<Integer>(2);
            for (Integer temp : result.getEnterpriseIdList()) {
                list.add(temp);
            }
            arg.setEnterpriseIds(list);

            GetAvaliableEnterprisesResult ret = new GetAvaliableEnterprisesResult();

            FcpRequest request = new FcpRequest(FcpRequestMethod.V5RPC, UUID.randomUUID().toString(), 1);
            request.addHeader(FcpHeaderType.V3QueryName, enterpriseGetFromPhoneUrl);
            request.addHeader(FcpHeaderType.ContentType, FcpMessage.FcpContentType.ProtoBuf);
            request.addBody(FcpCodec.encode(FcpMessage.FcpContentType.ProtoBuf, arg));
            FcpResponse response = MsgServer.getIntance().getFcpClient().send(request);
            ret = FcpCodec.decode(FcpMessage.FcpContentType.ProtoBuf, GetAvaliableEnterprisesResult.class, response.mergeBodies());


            return ret.items;
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }

    public GetEnterpriseListByMobileResult getEnterpriseListByMobile(String phone) {
        GetEnterpriseListByMobileArg arg = new GetEnterpriseListByMobileArg();
        arg.setMobile(phone);
        GetEnterpriseListByMobileResult result = MsgServer.getIntance().getUserAccountService().getEnterpriseListByMobile(arg);
        return result;
    }

    private static class GetAvaliableEnterprisesArg implements CanProto, Serializable {
        private static final Schema<GetAvaliableEnterprisesArg> schema = RuntimeSchema.getSchema(GetAvaliableEnterprisesArg.class);

        @Tag(1)
        public String PhoneNumber;

        @Tag(2)
        public String Password;

        @Tag(3)
        public ArrayList<Integer> EnterpriseIds = new ArrayList<Integer>(2);

        @Override
        public byte[] toProto() {
            return ProtobufIOUtil.toByteArray(this, schema, LinkedBuffer.allocate(256));
        }

        @Override
        public void fromProto(byte[] bytes) {
            ProtobufIOUtil.mergeFrom(bytes, this, schema);
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            PhoneNumber = phoneNumber;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public ArrayList<Integer> getEnterpriseIds() {
            return EnterpriseIds;
        }

        public void setEnterpriseIds(ArrayList<Integer> enterpriseIds) {
            EnterpriseIds = enterpriseIds;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("GetAvaliableEnterprisesArg{");
            sb.append("PhoneNumber=").append(PhoneNumber);
            sb.append("Password=").append(Password);
            sb.append("EnterpriseIds=").append(EnterpriseIds);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class GetAvaliableEnterprisesResult implements CanProto,Serializable {

        private static final Schema<GetAvaliableEnterprisesResult> schema = RuntimeSchema.getSchema(GetAvaliableEnterprisesResult.class);


        @Tag(1)
        public ArrayList<EnterpriseSummary> items = new ArrayList<EnterpriseSummary>(2);

        public ArrayList<EnterpriseSummary> getItems() {
            return items;
        }

        public void setItems(ArrayList<EnterpriseSummary> items) {
            this.items = items;
        }

        @Override
        public byte[] toProto() {
            return ProtobufIOUtil.toByteArray(this, schema, LinkedBuffer.allocate(256));
        }

        @Override
        public void fromProto(byte[] bytes) {
            ProtobufIOUtil.mergeFrom(bytes,this,schema);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("GetAvaliableEnterprisesResult{");
            sb.append("items=").append(items);
            sb.append('}');
            return sb.toString();
        }
    }

}
