package com.facishare.open.manage.cons;

/**
 * Created by zhouq
 * on 2016/4/22.
 */
public interface CenterConstants {
    /**
     * 系统ID,用于登录时调用接口拉菜单.
     */
    Long SYSTEM_ID = 10L;


    /**
     * 发送的模板消息状态常量. 1.允许.0.禁止
     */
    String SEND_MESSAGE_STATUS_ENABLE = "1";

    /**
     * 发送的模板消息状态常量.1.允许.0.禁止
     */
    String SEND_MESSAGE_STATUS_DISABLE = "0";

    /**
     * 服务号统计指标 - 群发消息数
     */
    String SERVICE_STATISTIC_GROUP_SEND_MSG_COUNT = "groupSendMsgCount";
    /**
     * 服务号统计指标 - 群发消息服务号数
     */
    String SERVICE_STATISTIC_GROUP_SEND_MSG_SERVICE_COUNT = "groupSendMsgServiceCount";
    /**
     * 服务号统计指标 - 群发图文消息篇数
     */
    String SERVICE_STATISTIC_GROUP_SEND_IMAGE_TEXT_MSG_COUNT = "groupSendImageTextMsgCount";
    /**
     * 服务号统计指标 - 群发图文消息阅读数
     */
    String SERVICE_STATISTIC_GROUP_SEND_IMAGE_TEXT_MSG_READ_COUNT = "groupSendImageTextMsgReadCount";
    /**
     * 服务号统计指标 - 客服（人工回复）消息数
     */
    String SERVICE_STATISTIC_SERVICE_HUMAN_REPLY_COUNT = "serviceHumanReplyCount";
    /**
     * 服务号统计指标 - 客服（人工回复）消息服务号数
     */
    String SERVICE_STATISTIC_SERVICE_HUMAN_REPLY_SERVICE_COUNT = "serviceHumanReplyServiceCount";
    /**
     * 服务号统计指标 - 客服（自动回复）消息数
     */
    String SERVICE_STATISTIC_SERVICE_AUTO_REPLY_COUNT = "serviceAutoReplyCount";
    /**
     * 服务号统计指标 - 客服（人工回复）消息服务号数
     */
    String SERVICE_STATISTIC_SERVICE_AUTO_REPLY_SERVICE_COUNT = "serviceAutoReplyServiceCount";
    /**
     * 服务号统计指标 - 新增服务号数
     */
    String SERVICE_STATISTIC_NEW_SERVICE_COUNT = "newServiceCount";
    /**
     * 服务号统计指标 - 服务号总数
     */
    String SERVICE_STATISTIC_SERVICE_COUNT = "serviceCount";
    /**
     * 服务号统计指标 - 服务号企业总数
     */
    String SERVICE_STATISTIC_SERVICE_EA_COUNT = "serviceEaCount";

}
