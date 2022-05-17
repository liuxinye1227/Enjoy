package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

/**
 * appDo的返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class AppResult extends BaseResult<OpenAppDO> {

    private static final long serialVersionUID = -3643618564090848960L;

    public AppResult() {
    }

    public AppResult(OpenAppDO result) {
        super(result);
    }

    public AppResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public AppResult(BizException e) {
        super(e);
    }
}
