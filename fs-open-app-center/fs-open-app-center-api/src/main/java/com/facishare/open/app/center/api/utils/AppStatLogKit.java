package com.facishare.open.app.center.api.utils;

import com.facishare.open.common.enums.MessageSendStatType;
import com.facishare.open.common.enums.MonitorTypeEnum;
import com.facishare.open.common.logger.MonitorLogger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 应用统计日志上报服务.
 * <p>
 * Created by zenglb on 2015/12/3.
 */
public class AppStatLogKit {

    /**
     * 日志格式.
     */
    private static final String MONITOR_LOG_FORMATTER = "DT={}||AID={}||CID={}||TYPE={}||EA={}||UID={}||APPTP={}||SENDTP={}||MSGTP={}";

    /**
     * 日志时间格式.
     */
    private static final String MONITOR_DATE_FORMATTER = "yyyyMMddHHmmss";

    /**
     * 指定时区
     */
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+8");

    /**
     * 记录日志.
     *
     * @param appId           应用id.
     * @param componentId     组件id.
     * @param monitorTypeEnum 类型.
     * @param fsEa            企业账号.
     * @param userId          用户id.
     */
    public static final void log(String appId, String componentId, MonitorTypeEnum monitorTypeEnum, String fsEa, Integer userId, Integer appType) {
        MonitorLogger.info(MONITOR_LOG_FORMATTER, getNowDate(), appId, componentId, monitorTypeEnum.value(), fsEa, userId, appType,"","");
    }

    private static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MONITOR_DATE_FORMATTER);
        simpleDateFormat.setTimeZone(TIME_ZONE);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 记录日志.
     *
     * @param appId           应用id.
     * @param monitorTypeEnum 类型.
     * @param fsEa            企业账号.
     * @param userId          用户id.
     * @param msgType         消息类型.
     */
    public static final void logSendMsg(String appId, MonitorTypeEnum monitorTypeEnum, String fsEa, Integer userId, Integer appType, MessageSendStatType sendType, String msgType) {
        MonitorLogger.info(MONITOR_LOG_FORMATTER, getNowDate(), appId, "", monitorTypeEnum.value(), fsEa, userId, appType, sendType.getType(), msgType);
    }
}
