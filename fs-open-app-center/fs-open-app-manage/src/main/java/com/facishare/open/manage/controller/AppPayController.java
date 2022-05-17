package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.PayTypeEnum;
import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.app.center.api.service.PayAppService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 付费相关
 */
@Controller
@RequestMapping("/open/manage/payApp")
public class AppPayController extends BaseController {


    private static final String SPLIT = ",";

    @Autowired
    private PayAppService payAppService;

    @RequestMapping("/toEdit")
    public String toEdit(Model model) {
        return "/appCharge/edit";
    }

    @RequestMapping("/updateAppToCharge")
    public String updateAppToCharge(Model model, @RequestParam(value = "appId", required = false) String appId, @RequestParam(value = "payType", required = false) int payType) {

        if (StringUtils.isBlank(appId)) {
            model.addAttribute("errMsg", "appId不能为空");
            return "/appCharge/list";
        }

        payAppService.updatePayApp(appId, PayTypeEnum.getByPayType(payType));


        AppListResult appListResult = payAppService.queryByPayType(PayTypeEnum.CHARGE);
        List<OpenAppDO> result = new ArrayList<>();
        if (appListResult.isSuccess()) {
            result = appListResult.getResult();
        }
        model.addAttribute("result", result);
        return "/appCharge/list";
    }

    @RequestMapping("/save")
    public String save(Model model,
                       @RequestParam(value = "payAppIds", required = false) String payAppIds) {

        if (org.apache.commons.lang.StringUtils.isBlank(payAppIds)) {
            model.addAttribute("errMsg", "appId必填");
            return "/appCharge/edit";
        }

        try {
            String[] appIds = null;
            appIds = payAppIds.split(SPLIT);
            for (int i = 0; i < appIds.length; i++) {
                payAppService.updatePayApp(appIds[i], PayTypeEnum.CHARGE);
            }
        } catch (Exception e) {
            model.addAttribute("errMsg", e);
            return "/appCharge/edit";
        }


        AppListResult appListResult = payAppService.queryByPayType(PayTypeEnum.CHARGE);
        List<OpenAppDO> result = new ArrayList<>();
        if (appListResult.isSuccess()) {
            result = appListResult.getResult();
        }
        model.addAttribute("result", result);
        return "/appCharge/list";
    }


}
