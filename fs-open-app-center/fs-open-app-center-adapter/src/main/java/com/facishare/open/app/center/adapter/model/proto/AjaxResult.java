package com.facishare.open.app.center.adapter.model.proto;

import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.ErrCode;
import com.facishare.open.common.result.exception.BizException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by xialf on 2017/04/19.
 *
 * @author xialf
 * @since 2017/04/19 4:37 PM
 */
@Getter
@Setter
@ToString
public class AjaxResult {
    private Integer errCode;
    private String errMsg;
    private Object data = null;

    public AjaxResult() {
    }

    public AjaxResult(ErrCode errCode) {
        this.errCode = errCode.getErrCode();
        this.errMsg = errCode.getErrMessage();
    }

    public AjaxResult(BizException e) {
        this.errCode = e.getErrCode();
        this.errMsg = e.getErrMessage();
    }

    public AjaxResult(BaseResult result) {
        this.errCode = result.getErrCode();
        this.errMsg = result.getErrMessage();
        this.data = result.getResult();
    }

    public AjaxResult(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public AjaxResult(Object data) {
        this(0, "OK");
        this.data = data;
    }


}
