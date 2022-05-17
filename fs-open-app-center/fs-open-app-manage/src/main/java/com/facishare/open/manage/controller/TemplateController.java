package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.template.TemplateDO;
import com.facishare.open.app.center.api.model.vo.template.CellVO;
import com.facishare.open.app.center.api.model.vo.template.TemplateVO;
import com.facishare.open.app.center.api.result.TemplateResult;
import com.facishare.open.app.center.api.service.template.TemplateService;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/open/manage/rest/template")
public class TemplateController extends BaseController {

    @Autowired
    private TemplateService templateService;

    /**
     * 模板创建
     *
     * @param template
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "saveTemplate", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult saveTemplate(@RequestBody TemplateVO template, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        TemplateVO templateVO = template;
        List<CellVO> cellList = templateVO.getCellsList();

        checkParam(templateVO.getTitle(), "标题不能够为空！！！");
        // 校验标题
        if (templateVO.getTitle().length() < 1 || templateVO.getTitle().length() > 60) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "标题必须为2到30个汉字！！！");
        }
        // 校验关键词
        if (null != cellList && 0 < cellList.size()) {
            for (CellVO item : cellList) {
                if ("".equals(item.getCellName())) {
                    return new AjaxResult(AjaxCode.PARAM_ERROR, "关键词不能够为空！！！");
                } else {
                    if (item.getCellName().length() > 30) {
                        return new AjaxResult(AjaxCode.PARAM_ERROR, "关键词不能超出15个汉字！！！");
                    }
                }
            }
        }

        TemplateResult templateResult = null;
        
        //默认设置2张图片
        CellVO cellVO1 = new CellVO();
        cellVO1.setType(CommonConstant.TEMPLATE_MEDIAWORD_PICTURE_TYPE);
        cellVO1.setCellValue("$mediaword1.URL$");
        cellList.add(cellVO1);
        CellVO cellVO2 = new CellVO();
        cellVO1.setType(CommonConstant.TEMPLATE_MEDIAWORD_PICTURE_TYPE);
        cellVO1.setCellValue("$mediaword2.URL$");
        cellList.add(cellVO2);
        templateVO.setCellsList(cellList);
        
        // 保存数据
        templateResult = templateService.addTemplate(templateVO);

        // 异常信息
        if (!templateResult.isSuccess()) {
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, templateResult.getErrDescription());
        }
        return SUCCESS;
    }

    @RequestMapping("queryTemplateById")
    @ResponseBody
    public AjaxResult queryTemplateById(@RequestParam(value = "id", required = false) Long id) {
        checkParam(id, "请输入Id");

        AjaxResult result = null;
        TemplateResult templateResult = templateService.getTemplateById(id);
        if (!templateResult.isSuccess()) {
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, templateResult.getErrDescription());
        }

        result = new AjaxResult(templateResult.getResult());
        //TemplateDO templateDO = templateResult.getResult();
        return result;
    }

    /**
     * 模板列表
     *
     * @param map
     * @return
     */
    /*@RequestMapping("templateList")
    @ResponseBody
	public AjaxResult list(@RequestParam(value = "appId", required = false) String appId) {
		checkParam(appId, "请选择应用");
		TemplateListResult templateListResult = templateService.queryTemplateListByAppId(appId);
		if (!templateListResult.isSuccess()) {
			return new AjaxResult(AjaxCode.BIZ_EXCEPTION, templateListResult.getErrDescription());
		}
		List<TemplateDO> list = templateListResult.getTemplateDOList();
		return new AjaxResult(list);
	}*/
    @RequestMapping("templateList")
    @ResponseBody
    public AjaxResult queryTemplatePager(Pager<TemplateDO> pager, @RequestParam(value = "appId", required = false) String appId) {

        //checkParam(appId, "请选择应用");
        if (null == pager) {
            pager = new Pager<TemplateDO>();
        }

        if (StringUtils.isNotBlank(appId)) {
            pager.addParam("appId", appId);
        }

        Pager<TemplateDO> result = templateService.queryTemplateListByPager(pager).getResult();
        result.setParams(null);

        return new AjaxResult(result);
    }

    /**
     * 模板删除
     *
     * @param id 模板seqid
     * @return
     */
    @RequestMapping("templateDelete")
    @ResponseBody
    public AjaxResult templateDelete(@RequestParam(value = "id", required = false) Integer id) {
        checkParam(id, "请选择模板ID");
        templateService.deleteTemplate(id);
        return SUCCESS;
    }
}
