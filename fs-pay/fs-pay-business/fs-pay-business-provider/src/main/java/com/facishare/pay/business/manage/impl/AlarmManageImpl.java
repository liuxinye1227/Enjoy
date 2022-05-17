package com.facishare.pay.business.manage.impl;

import com.facishare.open.msg.constant.MessageTypeEnum;
import com.facishare.open.msg.model.SendTextMessageVO;
import com.facishare.open.msg.service.SendMessageService;
import com.facishare.pay.business.dao.AlarmLogDAO;
import com.facishare.pay.business.manage.AlarmManage;
import com.facishare.pay.business.model.AlarmLogDo;
import com.facishare.pay.business.utils.ExecutorUtils;
import com.facishare.pay.business.utils.LevelType;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.utils.DateUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangtao on 2015/10/30.
 */
@Service
public class AlarmManageImpl implements AlarmManage {

    public static final Logger logger = LoggerFactory.getLogger(AlarmManageImpl.class);

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private AlarmLogDAO alarmLogDAO;

    @Value("${fs.pay.notify.appid}")
    private String appId;

    @Value("${fs.pay.notify.enterpriseAccount}")
    private String enterpriseAccount;

    @Value("${fs.pay.notify.touser}")
    private String toUser;

    @Override
    public void notifyError(String orderNo, LevelType levelType, TransTypeEnum transType, String errorMsg, String
        requestInfo, Calendar currTime) {
        try {
            StringBuffer content = new StringBuffer();
            content.append("告警级别:[").append(levelType.getType()).append("]")
                .append("时间:[").append(DateUtils.getDateFormat(currTime)).append("]")
                .append("orderNo:[").append(orderNo).append("] 交易类型:[")
                .append(transType.getCode())
                .append(transType.getDesc()).append("]" )
                .append("错误信息:[").append(errorMsg).append("] 请求内容:[")
                .append(requestInfo).append("]")
                ;
            // 异步操作日志


            ExecutorUtils.execute(new Runnable() {
                @Override
                public void run() {
                    // alarmLogDAO
                    try {
                        AlarmLogDo alarmLogDo = new AlarmLogDo();
                        alarmLogDo.setCreateTime(currTime);
                        alarmLogDo.setErrorMsg(errorMsg);
                        alarmLogDo.setOrderNo(orderNo);
                        alarmLogDo.setRequestInfo(requestInfo);
                        alarmLogDo.setStatus(0);
                        alarmLogDo.setTransType(transType.getCode()+"");
                        alarmLogDo.setType(levelType.getType());
                        alarmLogDAO.addAlarmLog(alarmLogDo);
                    } catch (Exception e) {

                        logger.error("addAlarmLog error:{}", e);
                    }

                    try {
                        SendTextMessageVO sendTextMessageVO = new SendTextMessageVO();
                        sendTextMessageVO.setAppId(appId);
                        sendTextMessageVO.setEnterpriseAccount(enterpriseAccount);
                        sendTextMessageVO.setPostId(UUID.randomUUID().toString());
                        sendTextMessageVO.setType(MessageTypeEnum.TEXT);
                        String[] toUsers = toUser.split(",");
                        List toUserList = new ArrayList();
                        for (String userid : toUsers) {
                            toUserList.add(userid);
                        }
                        sendTextMessageVO.setToUserList(toUserList);
                        sendTextMessageVO.setContent(content.toString());

                        sendMessageService.sendTextMessage(sendTextMessageVO);
                    } catch (Exception e) {
                        logger.error("sendTextMessage error:{}", e);
                    }
                }
               }
            );
        } catch (Exception e) {
            logger.error("notifyError :{}", e);
        }
    }
}
