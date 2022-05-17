package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.vo.OpenAppVO;
import com.facishare.open.common.result.exception.BizException;

import java.util.List;

/**
 * AppVo的返回,目前用于返回 应用可见接口(20150824)
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class AppVOListResult extends BaseResult<List<OpenAppVO>> {

    private static final long serialVersionUID = -3407747763849450234L;

    public AppVOListResult() {
    }

    public AppVOListResult(List<OpenAppVO> result) {
        super(result);
    }

    public AppVOListResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public AppVOListResult(BizException e) {
        super(e);
    }
}