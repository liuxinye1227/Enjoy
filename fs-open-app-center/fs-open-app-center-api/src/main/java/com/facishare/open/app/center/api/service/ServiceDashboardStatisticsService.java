package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.ServiceDashboardStatisticsDO;
import com.facishare.open.common.result.BaseResult;

/**
 * 服务号统计数据服务
 * Created by liqiulin on 2016/8/9.
 */
public interface ServiceDashboardStatisticsService {
    /**
     * 查询服务号工作台服务号统计数据
     * @param appId
     * @param fsEa
     * @return
     */
    BaseResult<ServiceDashboardStatisticsDO> queryDashboardStatisticsByAppId(String appId, String fsEa);
}
