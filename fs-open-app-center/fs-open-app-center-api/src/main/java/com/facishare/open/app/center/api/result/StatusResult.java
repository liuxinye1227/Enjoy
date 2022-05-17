package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

/**
 * 处理true/false状态的传递
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class StatusResult extends BaseResult<Boolean> {

    private static final long serialVersionUID = -3141330418163261214L;

    public StatusResult() {
    }

    public StatusResult(boolean result) {
        super(result);
        if (result) {
            codeEnum(AppCenterCodeEnum.SUCCESS);
        } else {
            codeEnum(AppCenterCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    public StatusResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public StatusResult(BizException bizException) {
        super(bizException);
    }
}
