package com.facishare.open.manage.controller;

import com.facishare.open.app.ad.api.enums.ModuleKeyEnum;
import com.facishare.open.app.ad.api.service.CheckAppUpdatedService;
import com.facishare.open.manage.ajax.result.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 触发终端更新“我的应用”
 *
 * @author chenzengyong
 * @date on 2016/1/22.
 */
@Controller
@RequestMapping("/open/manage/myapp/")
public class MyAppController extends BaseController {


    @Resource
    private CheckAppUpdatedService checkAppUpdatedService;

    @RequestMapping("/updateMyApp")
    @ResponseBody
    public AjaxResult updateMyApp() {

        checkAppUpdatedService.resetTagByModuleKey(null,ModuleKeyEnum.COMPONENTS);
        return new AjaxResult(null);

    }
}
