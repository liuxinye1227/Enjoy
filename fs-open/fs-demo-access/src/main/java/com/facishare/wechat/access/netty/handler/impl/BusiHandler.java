package com.facishare.wechat.access.netty.handler.impl;

import com.facishare.fcp.FcpCodec;
import com.facishare.fcp.client.FcpClient;
import com.facishare.fcp.protocol.*;
import com.facishare.wechat.access.Constants.BusiKey;
import com.facishare.wechat.access.Constants.CommonConstant;
import com.facishare.wechat.access.manager.ConnectionManager;
import com.facishare.wechat.access.manager.RouterManager;
import com.facishare.wechat.access.model.TransData;
import com.facishare.wechat.access.netty.MsgServer;
import com.facishare.wechat.access.netty.handler.MsgHandler;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangtao on 2015/11/18.
 */
public class BusiHandler implements MsgHandler {
    private static Logger logger = LoggerFactory.getLogger(BusiHandler.class);


    @Override
    public void process(ChannelHandlerContext ctx, TransData msg) {

        // 获取查询 msg
        String token = msg.getHeader().get(BusiKey.HEADER_KEY_TOKEN);

        String queryName = msg.getHeader().get(BusiKey.HEADER_KEY_QUERY);

        //String body = new Gson().toJson(msg.getData());
        String body = new Gson().toJson(msg.getData());

        FcpClient client = MsgServer.getIntance().getFcpClientHelper().getClient();


        try {
            FcpRequest req = new FcpRequest(FcpRequestMethod.Query);
            req.addHeader(FcpHeaderType.V3QueryName, queryName);

            req.addHeader(FcpHeaderType.CallId, "" + UUID.randomUUID().toString());
            req.addHeader(FcpHeaderType.Cseq, "" + 0);

            // 需要获取用户信息 用token 去换取
            String userId = MsgServer.getIntance().getRedisTemplate().get(token, "");
            logger.debug("userId: [{}]", userId);
            if (StringUtils.isBlank(userId)) {
                TransData transData = new TransData();

                transData.setHeader(msg.getHeader());
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.TOKEN_TIMEOUT);
                transData.setData(dataMap);

                RouterManager.routeSuccess(ctx.channel(), transData);
                // userId = "E.fsfte2.10007";
            }

//            req.addHeader(FcpHeaderType.V3UserInfo, "E.fsfte2.10001");
            req.addHeader(FcpHeaderType.V3UserInfo, userId);

            FcpHeader fcp = new FcpHeader(FcpHeaderType.ContentType);
            fcp.setInt64Value(FcpMessage.FcpContentType.Json);

            req.addHeader(fcp);
            if (StringUtils.isNotBlank(body)) {
                req.addBody(body.getBytes("UTF-8"));
            }

            FcpResponse response = client.send(req);

            Iterator<FcpBody> it = response.getBodies().iterator();
            while (it.hasNext()) {
                FcpBody fcpBody = it.next();
                System.out.println(
                    "------string--" + IOUtils.toString(fcpBody.getValue(), Charsets.UTF_8.displayName()));
            }
            String result = FcpCodec.decode(FcpMessage.FcpContentType.Json, String.class, response.mergeBodies());
            logger.debug("result:[{}]", result);
            client.shutDown();

            TransData transData = new TransData();
            transData.setHeader(msg.getHeader());
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.SUCCESS);

            // result 去掉 “
//            result.replaceAll("\"", "");
            resultMap.put("body", result);
            transData.setData(resultMap);
            RouterManager.routeSuccess(ctx.channel(), transData);
        } catch (Exception e) {
            logger.error("error:{}", e);
            TransData transData = new TransData();
            transData.setHeader(msg.getHeader());
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put(CommonConstant.DATA_KEY_ERRORCODE, CommonConstant.BUSI_ERROR);
            transData.setData(resultMap);

            RouterManager.routeSuccess(ctx.channel(), transData);

        }
    }
}
