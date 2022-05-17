package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.template.TemplateDO;

import java.util.List;

public class TemplateListResult extends BaseResult<TemplateDO> {

    private static final long serialVersionUID = -5789398037960343832L;

    /**
     * 模板信息列表
     */
    public List<TemplateDO> templateDOList;

    public TemplateListResult() {
    }

    public TemplateListResult(TemplateDO result) {
        super(result);
    }

    public TemplateListResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public List<TemplateDO> getTemplateDOList() {
        return templateDOList;
    }

    public void setTemplateDOList(List<TemplateDO> templateDOList) {
        this.templateDOList = templateDOList;
    }
}
