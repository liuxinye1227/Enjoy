package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.OpenDictDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

/**
 * 单个字典数据的返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class DictResult extends BaseResult<OpenDictDO> {

    private static final long serialVersionUID = -3643618564090848960L;

    public DictResult() {
    }

    public DictResult(OpenDictDO result) {
        super(result);
    }

    public DictResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public DictResult(BizException e) {
        super(e);
    }
}
