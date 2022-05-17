package com.facishare.open.app.center.api.service;

import com.facishare.open.common.result.BaseResult;

import java.util.Date;

/**
 * Created by huanghl on 2016/7/25.
 *
 * @author huanghl
 */
public interface OpenQuotaService {

    BaseResult<Long> addQuotaRecord(String fsEa, String appId, int quota, int quotaType, Date beginTime, Date endTime);

    /**
     * 添加配额(依赖QuotaBizService).
     *
     * @param fsEa      企业账号
     * @param appId     应用id
     * @param quota     配额
     * @param quotaType 配额类型(1购买，2试用)
     * @param beginTime 配额开始时间
     * @param endTime   配额结束时间
     * @param bizAction 业务动作{@link com.facishare.open.app.pay.api.cons.BizAction}
     */
    BaseResult<Long> addQuotaRecord(String fsEa, String appId, int quota, int quotaType, Date beginTime, Date endTime, int bizAction);

    /**
     * 添加配额(依赖QuotaBizService).
     *
     * @param fsEa      企业账号
     * @param appId     应用id
     * @param quota     配额
     * @param quotaType 配额类型(1购买，2试用)
     * @param beginTime 配额开始时间
     * @param endTime   配额结束时间
     * @param attribute 配额属性
     * @return 新增配额的id
     */
    BaseResult<Long> addQuotaRecord(String fsEa, String appId, int quota, int quotaType, Date beginTime, Date endTime, int bizAction, int attribute);
}
