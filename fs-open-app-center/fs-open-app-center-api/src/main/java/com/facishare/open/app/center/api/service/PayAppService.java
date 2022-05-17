package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.enums.PayTypeEnum;
import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.result.AppResult;

/**
 * 付费应用服务
 * Created by chenzengyong on 15/10/14.
 */
public interface PayAppService {

    /**
     * 更新app的付费状态
     *
     * @param appId
     * @param payTypeEnum
     * @return
     */
    AppResult updatePayApp(String appId, PayTypeEnum payTypeEnum);

    /**
     * 查询免费/付费 应用
     *
     * @param payTypeEnum
     * @return
     */
    AppListResult queryByPayType(PayTypeEnum payTypeEnum);
}
