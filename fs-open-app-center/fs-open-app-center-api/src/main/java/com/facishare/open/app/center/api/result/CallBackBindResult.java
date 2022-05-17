package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.vo.OpenAppVO;
import com.facishare.open.common.result.exception.BizException;

import java.util.List;

/**
 * Description: 组件跳转结果
 * User: zhouq
 * Date: 2016/7/21
 */
public class CallBackBindResult extends BaseResult<List<OpenAppVO>> {

    private static final long serialVersionUID = -2062891448782955987L;

    /**
     * 第三方回调登录url，包含code， md5
     */
    private String callBackLoginUrl;

    public CallBackBindResult(BizException e) {
        super(e);
    }

    public CallBackBindResult() {
    }

    public CallBackBindResult(AppCenterCodeEnum codeEnum) {

    }

    public CallBackBindResult(String callBackLoginUrl) {
        this.callBackLoginUrl = callBackLoginUrl;
    }

    public CallBackBindResult(int errCode, String errMessage) {
    }

    public String getCallBackLoginUrl() {
        return callBackLoginUrl;
    }

    public void setCallBackLoginUrl(String callBackLoginUrl) {
        this.callBackLoginUrl = callBackLoginUrl;
    }

    @Override
    public String toString() {
        return "CallBackBindResult{" +
                "callBackLoginUrl='" + callBackLoginUrl + '\'' +
                '}';
    }
}
