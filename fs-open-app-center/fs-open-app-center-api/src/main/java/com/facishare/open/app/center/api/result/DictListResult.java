package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.OpenDictDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;

import java.util.List;

/**
 * 多个字典数据的返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class DictListResult extends BaseResult<List<OpenDictDO>> {

    private static final long serialVersionUID = -3407747763849450234L;

    public DictListResult() {
    }

    public DictListResult(List<OpenDictDO> result) {
        super(result);
    }

    public DictListResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public DictListResult(BizException e) {
        super(e);
    }
}
