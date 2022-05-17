package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;


/**
 * 用于Integer的返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class IntegerResult extends BaseResult<Integer> {

    private static final long serialVersionUID = -3141330418163261214L;

    public IntegerResult() {
    }

    public IntegerResult(Integer result) {
        super(result);
    }

    public IntegerResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public IntegerResult(BizException e) {
        super(e);
    }
}
