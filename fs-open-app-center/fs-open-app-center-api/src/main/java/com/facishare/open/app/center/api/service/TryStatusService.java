package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.TryStatusEnum;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

/**
 * 试用状态服务
 *
 * @author chenzengyong
 * @date on 2016/2/24.
 */
public interface TryStatusService {

    /**
     * 查询当前应用的试用状态
     * @param openAppDO
     * @param fsUserVO
     * @return
     */
    public BaseResult<TryStatusEnum> queryTryStatus(OpenAppDO openAppDO, FsUserVO fsUserVO);


}
