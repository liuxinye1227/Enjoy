package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.model.vo.AppEaVisibleVO;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.manager.AppVisibleManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/open/manage/rest/appVisible")
public class AppVisibleController extends BaseController {
    @Resource
    private AppVisibleManager appVisibleManager;

    @RequestMapping("/loadAppEaVisible")
    @ResponseBody
    public AjaxResult loadAppEaVisible(@RequestParam(value = "appId", required = false) String appId) {
        checkParam(appId, "请填写应用");
        AppEaVisibleVO appEaVisibleVo = appVisibleManager.queryEaVisibleDOByAppId(appId);
        return new AjaxResult(appEaVisibleVo);
    }
}
