package com.facishare.open.app.center.api.service.template;

import com.facishare.open.app.center.api.model.template.TemplateDO;
import com.facishare.open.app.center.api.model.vo.template.TemplateVO;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.app.center.api.result.TemplateListResult;
import com.facishare.open.app.center.api.result.TemplatePagerResult;
import com.facishare.open.app.center.api.result.TemplateResult;
import com.facishare.open.common.storage.mysql.dao.Pager;

/**
 * @author dingc
 * @Description: 模板操作类
 * @date 2015年8月27日 下午3:04:21
 */
public interface TemplateService {

    /**
     * 添加模板
     *
     * @param templateVO
     */
    TemplateResult addTemplate(TemplateVO templateVO);

    /**
     * 更新模板
     *
     * @param templateDO
     */
    StatusResult updateTemplate(TemplateDO templateDO);

    /**
     * 删除模板
     *
     * @param id
     */
    StatusResult deleteTemplate(long id);

    /**
     * 查询模板列表
     *
     * @param appId
     * @return
     */
    TemplateListResult queryTemplateListByAppId(String appId);

    /**
     * 查询模板
     *
     * @param templateId
     * @param status
     * @return
     */
    TemplateResult getTemplateByParams(String templateId, int status);

    /**
     * 查询模板
     *
     * @param id
     * @return
     */
    TemplateResult getTemplateById(long id);

    /**
     * 查询模板列表   分页
     *
     * @param pager
     * @return
     */
    TemplatePagerResult queryTemplateListByPager(Pager<TemplateDO> pager);
}
