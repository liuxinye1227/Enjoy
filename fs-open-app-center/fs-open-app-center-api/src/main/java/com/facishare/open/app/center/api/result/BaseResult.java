package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

/**
 * 基本dubbo返回
 *
 * @param <T>
 * @author zenglb
 * @date 2015年8月24日
 */
public class BaseResult<T> extends com.facishare.open.common.result.BaseResult<T> {

    private static final long serialVersionUID = 6039777006120900996L;

    public BaseResult(T result) {
        this();
        this.result = result;
        if (null == result) {
            codeEnum(AppCenterCodeEnum.RESULT_IS_NULL);
        }
    }

    public BaseResult(AppCenterCodeEnum codeEnum) {
        codeEnum(codeEnum);
    }

    public BaseResult() {
        codeEnum(AppCenterCodeEnum.SUCCESS);
    }

    public BaseResult(BizException e) {
        bizException(e);
    }

    @Override
    public String toString() {
        return "result [errCode=" + errCode + ", errMessage=" + errMessage + ", errDescription=" + errDescription
                + ", result=" + result + "]";
    }
}
