package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.model.BaseDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.TryStatusEnum;

/**
 * 试用状态vo
 *
 * @author chenzengyong
 * @date on 2016/2/24.
 */
public class TryStatusAppVO extends BaseDO {

    private static final long serialVersionUID = 8313497067166733857L;

    private OpenAppDO openAppDO;

    private TryStatusEnum tryStatusEnum;

    public OpenAppDO getOpenAppDO() {
        return openAppDO;
    }

    public void setOpenAppDO(OpenAppDO openAppDO) {
        this.openAppDO = openAppDO;
    }

    public TryStatusEnum getTryStatusEnum() {
        return tryStatusEnum;
    }

    public void setTryStatusEnum(TryStatusEnum tryStatusEnum) {
        this.tryStatusEnum = tryStatusEnum;
    }
}
