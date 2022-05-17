package com.facishare.open.manage.manager;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.msg.constant.MessageSendTypeEnum;

import java.util.List;

/**
 * Created by chenzs on 2016/10/12.
 */
public interface ServiceManager {

    /** 分页查询服务号
     * @param pager
     * @return
     */
    Pager<OpenDevAppVO> queryServiceByDev(Pager<OpenDevAppVO> pager);

    /** 通过应用类型查询服务号列表
     * @param appTypeList
     * @return
     */
    List<OpenAppDO> queryServicesByAppTypes(List<AppCenterEnum.AppType> appTypeList);

    /**
     * 统计自建服务号群发消息数
     * @param startTime
     * @param endTime
     * @return
     */
    long getGroupSendMsgCount(long startTime, long endTime);

    /**
     * 统计自建服务号群发消息的服务号数
     * @param startTime
     * @param endTime
     * @return
     */
    long getGroupSendMsgServiceCount(long startTime, long endTime);

    /**
     * 统计自建服务号群发图文消息数
     * @param startTime
     * @param endTime
     * @return
     */
    long getGroupSendImageTextMsgCount(long startTime, long endTime);

    /**
     * 统计自建服务号群发图文消息阅读数
     * @param startTime
     * @param endTime
     * @return
     */
    long getGroupSendImageTextMsgReadCount(long startTime, long endTime);

    /**
     * 统计自建服务号新建数
     * @param startTime
     * @param endTime
     * @return
     */
    long getNewServiceCount(long startTime, long endTime);

    /**
     * 统计自建服务号数(按截止时间)
     * @param endTime
     * @return
     */
    long getServiceCount(long endTime);

    /**
     * 统计自建服务号企业数(按截止时间)
     * @param endTime
     * @return
     */
    long getServiceEaCount(long endTime);

    /**
     * 统计消息回复数
     * @param sendTypeEnumList
     * @param startTime
     * @param endTime
     * @return
     */
    long countReplyMsgNum(List<MessageSendTypeEnum> sendTypeEnumList, long startTime, long endTime);

    /**
     * 统计回复消息的服务号数
     * @param sendTypeEnumList
     * @param startTime
     * @param endTime
     * @return
     */
    long countReplyMsgService(List<MessageSendTypeEnum> sendTypeEnumList,long startTime, long endTime);
}
