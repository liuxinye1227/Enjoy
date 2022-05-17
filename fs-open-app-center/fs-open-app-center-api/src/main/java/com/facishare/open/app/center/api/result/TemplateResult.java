package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.template.TemplateDO;

/**
 * 模板信息结果
 *
 * @author songk
 * @date 2015年9月1日
 */
public class TemplateResult extends BaseResult<TemplateDO> {

    private static final long serialVersionUID = -5789398037960343832L;

    public TemplateResult() {
    }

    public TemplateResult(TemplateDO result) {
        super(result);
    }

    public TemplateResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }
}
