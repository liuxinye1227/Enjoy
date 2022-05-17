package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.AppViewDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

/**
 * 应用可见返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class AppViewResult extends BaseResult<AppViewDO> {

    private static final long serialVersionUID = -3643618564090848960L;

    public AppViewResult() {
    }

    public AppViewResult(AppViewDO result) {
        super(result);
    }

    public AppViewResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public AppViewResult(BizException bizException) {
        super(bizException);
    }
}
