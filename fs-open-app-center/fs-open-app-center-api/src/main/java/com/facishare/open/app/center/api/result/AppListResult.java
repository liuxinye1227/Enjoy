package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

import java.util.List;

/**
 * 应用list的返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class AppListResult extends BaseResult<List<OpenAppDO>> {

    private static final long serialVersionUID = -3407747763849450234L;

    public AppListResult() {
    }

    public AppListResult(List<OpenAppDO> result) {
        super(result);
    }

    public AppListResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public AppListResult(BizException e) {
        super(e);
    }
}
