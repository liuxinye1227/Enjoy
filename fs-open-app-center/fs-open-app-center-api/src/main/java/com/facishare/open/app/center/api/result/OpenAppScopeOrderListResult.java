package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

import java.util.List;

/**
 * 应用的权限数据
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class OpenAppScopeOrderListResult extends BaseResult<List<OpenAppScopeOrderDO>> {

    private static final long serialVersionUID = 7944916786819965663L;

    public OpenAppScopeOrderListResult() {
    }

    public OpenAppScopeOrderListResult(List<OpenAppScopeOrderDO> result) {
        super(result);
    }

    public OpenAppScopeOrderListResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public OpenAppScopeOrderListResult(BizException e) {
        super(e);
    }
}
