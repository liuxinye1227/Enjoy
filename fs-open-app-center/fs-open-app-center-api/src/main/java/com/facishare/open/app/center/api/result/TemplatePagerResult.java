package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.template.TemplateDO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;

/**
 * @author songk
 * @date 2015年9月24日
 */
public class TemplatePagerResult extends BaseResult<Pager<TemplateDO>> {

    private static final long serialVersionUID = 4842335370562030748L;

    public TemplatePagerResult() {
    }

    public TemplatePagerResult(Pager<TemplateDO> result) {
        super(result);
    }

    public TemplatePagerResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public TemplatePagerResult(BizException e) {
        super(e);
    }
}
