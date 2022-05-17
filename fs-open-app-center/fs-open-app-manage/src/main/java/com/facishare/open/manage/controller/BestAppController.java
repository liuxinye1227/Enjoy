package com.facishare.open.manage.controller;

import com.facishare.open.app.ad.api.model.FsAdBestAppDO;
import com.facishare.open.app.ad.api.service.AppCenterBestAppService;
import com.facishare.open.app.center.api.model.enums.AppStatus;
import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.service.AppIconService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/open/manage/rest/best/app")
public class BestAppController extends BaseController {

    @Resource
    private AppCenterBestAppService appCenterBestAppService;
    @Resource
    private OpenAppService openAppService;
    @Autowired
    private AppIconService appIconService;

    @RequestMapping("/queryAppBestApps")
    @ResponseBody
    public AjaxResult queryAppBestApps() {
        BaseResult<List<FsAdBestAppDO>> listBaseResult = appCenterBestAppService.queryAppBestApps();
        if (!listBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, listBaseResult, "查询banner推荐位失败。");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        listBaseResult.getResult().forEach(fsAdAppBannerDO -> {
            Map<String, Object> map = getStringObjectMap(fsAdAppBannerDO);
            result.add(map);
        });

        return new AjaxResult(result);
    }

    private Map<String, Object> getStringObjectMap(FsAdBestAppDO fsAdAppBannerDO) {
        Map<String, Object> result = new HashMap<>();
        result.put("appId", fsAdAppBannerDO.getAppId());
        result.put("imageUrl", queryAppIconUrl(fsAdAppBannerDO.getAppId(), IconType.WEB));
        result.put("gmtCreate", fsAdAppBannerDO.getGmtCreate());
        result.put("index", fsAdAppBannerDO.getIndex());
        result.put("id", fsAdAppBannerDO.getId());
        AppResult appResult = openAppService.loadOpenAppFast(fsAdAppBannerDO.getAppId());
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "查询应用失败");
        }
        result.put("appName", appResult.getResult().getAppName());
        result.put("appStatus", appResult.getResult().getStatus());
        for (AppStatus appStatus : AppStatus.values()) {
            if (appStatus.getStatus() == appResult.getResult().getStatus()) {
                result.put("appStatusName", appStatus.getDesc());
                break;
            }
        }
        return result;
    }

    @RequestMapping("/deleteBestApp")
    @ResponseBody
    public AjaxResult deleteBestApp(@RequestBody FsAdBestAppDO fsAdBestApp) {
        checkParam(fsAdBestApp, "请填写表单");
        checkParam(fsAdBestApp.getId(), "请选择指定的精品应用");
        BaseResult<Void> voidBaseResult = appCenterBestAppService.deleteBestApp(fsAdBestApp.getId());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "删除失败");
        }
        return SUCCESS;
    }

    @RequestMapping("/addBestApp")
    @ResponseBody
    public AjaxResult addBestApp(@RequestBody FsAdBestAppDO fsAdBestApp) {
        checkParam(fsAdBestApp, "请填写表单");
        checkParam(fsAdBestApp.getAppId(), "请选择应用.");
        checkParam(fsAdBestApp.getIndex(), "请填写序号");
        AppResult appResult = openAppService.loadOpenAppFast(fsAdBestApp.getAppId());
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "查询应用失败");
        }
        BaseResult<FsAdBestAppDO> fsAdBestAppDOBaseResult = appCenterBestAppService.queryByIndex(fsAdBestApp.getIndex());
        if (fsAdBestAppDOBaseResult.isSuccess() && null != fsAdBestAppDOBaseResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, fsAdBestAppDOBaseResult, "序号已存在.");
        }

        BaseResult<String> voidBaseResult = appCenterBestAppService.addBestApp(fsAdBestApp.getAppId(), fsAdBestApp.getIndex());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "添加失败");
        }
        return new AjaxResult(voidBaseResult.getResult());
    }


    @RequestMapping("/updateBestAppIndex")
    @ResponseBody
    public AjaxResult updateBestAppIndex(@RequestBody FsAdBestAppDO fsAdBestApp) {
        checkParam(fsAdBestApp, "请填写表单");
        checkParam(fsAdBestApp.getId(), "请选择指定的应用");
        checkParam(fsAdBestApp.getIndex(), "请填写排序号");

        BaseResult<FsAdBestAppDO> fsAdBestAppDOBaseResult = appCenterBestAppService.queryByIndex(fsAdBestApp.getIndex());
        if (fsAdBestAppDOBaseResult.isSuccess() && null != fsAdBestAppDOBaseResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, fsAdBestAppDOBaseResult, "序号已存在.");
        }
        BaseResult<Void> voidBaseResult = appCenterBestAppService.updateBestAppIndex(fsAdBestApp.getId(), fsAdBestApp.getIndex());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "设置排序号失败");
        }
        return SUCCESS;
    }

    /**
     * 查询iconUrl
     * @param appIdOrComponentId 应用或者组件ID
     * @param type 图片类型
     * @return iconUrl
     */
    public String queryAppIconUrl(String appIdOrComponentId, IconType type){
        BaseResult<String> iconUrlResult = appIconService.queryAppIconUrl(appIdOrComponentId, type);
        if (!iconUrlResult.isSuccess()) {
            logger.warn("queryAppIconUrl failed, appIdOrComponentId[{}], type[{}], result[{}] : " + appIdOrComponentId, type, iconUrlResult);
        }
        return iconUrlResult.getResult();
    }
}
