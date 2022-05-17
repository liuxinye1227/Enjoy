package com.facishare.open.manage.controller;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.manager.OperationLogManager;
import com.facishare.open.operating.center.api.model.OperationLogVO;
import com.facishare.open.operating.center.api.model.QueryOperationLogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/open/manage/operationLog")
public class OperationLogController extends BaseController {

    @Autowired
    private OperationLogManager operationLogManager;

    @RequestMapping(value = "/findOperationLogs", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult findOperationLogs(QueryOperationLogVO queryOperationLogVO) {
        if (null == queryOperationLogVO || StringUtils.isBlank(queryOperationLogVO.getEnterpriseAccount())) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "搜索企业号不能为空");
        }
        if (null != queryOperationLogVO.getOperatorId()) {
            if (queryOperationLogVO.getOperatorId().intValue() < 0 || queryOperationLogVO.getOperatorId().intValue() > 999999) {
                return new AjaxResult(AjaxCode.PARAM_ERROR, "员工id不在范围内");
            }
        }
        if (null != queryOperationLogVO.getStartTime() && null != queryOperationLogVO.getEndTime()) {
            if (queryOperationLogVO.getStartTime().longValue() > queryOperationLogVO.getEndTime().longValue()) {
                return new AjaxResult(AjaxCode.PARAM_ERROR, "查询时间范围有误");
            }
        }
        Pager<OperationLogVO> pager = new Pager<>();
        pager.setCurrentPage(null == queryOperationLogVO.getCurrentPage() ? 1 : queryOperationLogVO.getCurrentPage().intValue());
        pager.setPageSize(null == queryOperationLogVO.getPageSize() ? 10 : queryOperationLogVO.getPageSize().intValue());
        Pager<OperationLogVO> operationLog = operationLogManager.findOperationLog(pager, queryOperationLogVO);
        return new AjaxResult(operationLog);
    }
}
