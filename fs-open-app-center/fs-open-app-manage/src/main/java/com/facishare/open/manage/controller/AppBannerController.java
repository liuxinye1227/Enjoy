package com.facishare.open.manage.controller;

import com.facishare.open.app.ad.api.enums.AppAdBannerStatusEnum;
import com.facishare.open.app.ad.api.enums.AppAdBannerTypeEnum;
import com.facishare.open.app.ad.api.enums.AppAdTargetTypeEnum;
import com.facishare.open.app.ad.api.model.FsAdAppBannerDO;
import com.facishare.open.app.ad.api.service.AppCenterBannerService;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/open/manage/rest/app/banner")
public class AppBannerController extends BaseController {

    @Resource
    private AppCenterBannerService appCenterBannerService;
    @Resource
    private OpenAppService openAppService;

    @RequestMapping("/queryPager")
    @ResponseBody
    public AjaxResult queryPager(Pager<Map<String, Object>> pager) {
        Pager<FsAdAppBannerDO> param = new Pager<>();
        param.getParams().putAll(pager.getParams());
        param.setCurrentPage(pager.getCurrentPage());
        param.setPageSize(pager.getPageSize());
        com.facishare.open.common.result.BaseResult<Pager<FsAdAppBannerDO>> pagerBaseResult = appCenterBannerService.queryAppBannerPager(param);
        if (!pagerBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, pagerBaseResult, "??????banner??????????????????");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        pagerBaseResult.getResult().getData().forEach(fsAdAppBannerDO -> {
            Map<String, Object> map = getStringObjectMap(fsAdAppBannerDO);
            result.add(map);
        });
        pager.setData(result);
        pager.setRecordSize(pagerBaseResult.getResult().getRecordSize());
        return new AjaxResult(pager);
    }

    private Map<String, Object> getStringObjectMap(FsAdAppBannerDO fsAdAppBannerDO) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", fsAdAppBannerDO.getId());
        map.put("index", fsAdAppBannerDO.getIndex());
        map.put("bannerType", fsAdAppBannerDO.getBannerType());
        map.put("bannerTypeName", AppAdBannerTypeEnum.getByCode(fsAdAppBannerDO.getBannerType()).getDesc());
        map.put("description", fsAdAppBannerDO.getDescription());
        map.put("imageUrl", fsAdAppBannerDO.getImageUrl());
        map.put("targetUrl", fsAdAppBannerDO.getTargetUrl());
        map.put("targetType", fsAdAppBannerDO.getTargetType());
        map.put("status", fsAdAppBannerDO.getStatus());
        map.put("statusName", AppAdBannerStatusEnum.getByCode(fsAdAppBannerDO.getStatus()).getDesc());
        if (AppAdTargetTypeEnum.APP_DETAIL.getCode().equals(fsAdAppBannerDO.getTargetType())) {
            map.put("appId", fsAdAppBannerDO.getAppId());
            AppResult appResult = openAppService.loadOpenAppFast(fsAdAppBannerDO.getAppId());
            if (!appResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "??????????????????");
            }
            map.put("appName", appResult.getResult().getAppName());
        }
        map.put("gmtModified", fsAdAppBannerDO.getGmtModified());
        return map;
    }

    @RequestMapping("/loadAppBannerById")
    @ResponseBody
    public AjaxResult loadAppBannerById(@RequestParam(value = "id", required = false) String bannerId) {
        checkParam(bannerId, "??????????????????banner");
        com.facishare.open.common.result.BaseResult<FsAdAppBannerDO> baseResult = appCenterBannerService.queryBannerById(bannerId);
        if (!baseResult.isSuccess() || null == baseResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, "??????banner??????????????????");
        }
        Map<String, Object> map = getStringObjectMap(baseResult.getResult());
        return new AjaxResult(map);
    }

    @RequestMapping("/deleteBanner")
    @ResponseBody
    public AjaxResult deleteBanner(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner, "???????????????");
        checkParam(fsAdAppBanner.getId(), "??????????????????banner");
        FsAdAppBannerDO fsAdAppBannerDO = queryFsAdAppBannerById(fsAdAppBanner.getId());
        if (!AppAdBannerStatusEnum.DISABLE.getCode().equals(fsAdAppBannerDO.getStatus())) {
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "??????????????????????????????????????????");
        }
        BaseResult<Void> voidBaseResult = appCenterBannerService.deleteBanner(fsAdAppBanner.getId());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "????????????");
        }
        return SUCCESS;
    }

    private FsAdAppBannerDO queryFsAdAppBannerById(String id) {
        BaseResult<FsAdAppBannerDO> fsAdAppBannerDOBaseResult = appCenterBannerService.queryBannerById(id);
        if (!fsAdAppBannerDOBaseResult.isSuccess() || null == fsAdAppBannerDOBaseResult.getResult()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, fsAdAppBannerDOBaseResult, "banner?????????");
        }
        return fsAdAppBannerDOBaseResult.getResult();
    }

    @RequestMapping("/addBanner")
    @ResponseBody
    public AjaxResult addBanner(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner, "???????????????");
        checkFsAdAppBanner(fsAdAppBanner);
        String imageUrl = fsAdAppBanner.getImageUrl();
        // ????????? banner???https ???????????????.
        if (AppAdBannerTypeEnum.APP.getCode().equals(fsAdAppBanner.getBannerType()) && imageUrl.startsWith("https")) {
            fsAdAppBanner.setImageUrl("http" + imageUrl.substring(5));
        }

        setAppNameIfAppDtlBanner(fsAdAppBanner);

        BaseResult<String> voidBaseResult = appCenterBannerService.addBanner(fsAdAppBanner);
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "????????????");
        }
        return new AjaxResult(voidBaseResult.getResult());
    }

    private void checkFsAdAppBanner(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner.getBannerType(), "??????????????????banner??????");
        checkParam(fsAdAppBanner.getImageUrl(), "???????????????.");
        checkParam(fsAdAppBanner.getTargetType(), "??????????????????banner????????????");
        if (AppAdTargetTypeEnum.APP_DETAIL.getCode().equals(fsAdAppBanner.getTargetType())) {
            checkParam(fsAdAppBanner.getAppId(), "???????????????id");
            AppResult appResult = openAppService.loadOpenAppFast(fsAdAppBanner.getAppId());
            if (!appResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "??????????????????");
            }
        } else {
            checkParam(fsAdAppBanner.getDescription(), "?????????????????????.");
            checkParam(fsAdAppBanner.getTargetUrl(), "?????????????????????.");
        }
    }

    @RequestMapping("/updateBanner")
    @ResponseBody
    public AjaxResult updateBanner(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner, "???????????????");
        checkParam(fsAdAppBanner.getId(), "??????????????????banner");
        checkFsAdAppBanner(fsAdAppBanner);
        String imageUrl = fsAdAppBanner.getImageUrl();
        // ????????? banner???https ???????????????.
        if (AppAdBannerTypeEnum.APP.getCode().equals(fsAdAppBanner.getBannerType()) && imageUrl.startsWith("https")) {
            fsAdAppBanner.setImageUrl("http" + imageUrl.substring(5));
        }

        setAppNameIfAppDtlBanner(fsAdAppBanner);

        FsAdAppBannerDO fsAdAppBannerDO = queryFsAdAppBannerById(fsAdAppBanner.getId());
        if (!fsAdAppBannerDO.getBannerType().equals(fsAdAppBanner.getBannerType())) {
            BaseResult<Void> voidBaseResult = appCenterBannerService.bannerDisable(fsAdAppBanner.getId());
            if (!voidBaseResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "????????????");
            }
            voidBaseResult = appCenterBannerService.updateBannerIndex(fsAdAppBanner.getId(), null);
            if (!voidBaseResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "????????????");
            }
        }
        BaseResult<Void> voidBaseResult = appCenterBannerService.updateBanner(fsAdAppBanner);
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "????????????");
        }
        return SUCCESS;
    }

    private void setAppNameIfAppDtlBanner(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        if(AppAdTargetTypeEnum.APP_DETAIL.getCode().equals(fsAdAppBanner.getTargetType())){
            AppResult appResult = openAppService.loadOpenAppFast(fsAdAppBanner.getAppId());
            if (!appResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "??????????????????");
            }
            fsAdAppBanner.setDescription(appResult.getResult().getAppName());
        }
    }


    @RequestMapping("/bannerEnable")
    @ResponseBody
    public AjaxResult bannerEnable(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner, "???????????????");
        checkParam(fsAdAppBanner.getId(), "??????????????????banner");
        FsAdAppBannerDO fsAdAppBannerDO = queryFsAdAppBannerById(fsAdAppBanner.getId());
        if (null == fsAdAppBannerDO.getIndex()) {
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "????????????index");
        }
        BaseResult<Void> voidBaseResult = appCenterBannerService.bannerEnable(fsAdAppBanner.getId());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "banner????????????");
        }
        return SUCCESS;
    }

    @RequestMapping("/bannerDisable")
    @ResponseBody
    public AjaxResult bannerDisable(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner, "???????????????");
        checkParam(fsAdAppBanner.getId(), "??????????????????banner");
        FsAdAppBannerDO fsAdAppBannerDO = queryFsAdAppBannerById(fsAdAppBanner.getId());
        BaseResult<Void> voidBaseResult = appCenterBannerService.bannerDisable(fsAdAppBanner.getId());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "banner????????????");
        }
        voidBaseResult = appCenterBannerService.updateBannerIndex(fsAdAppBanner.getId(), null);
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "banner????????????");
        }
        return SUCCESS;
    }

    @RequestMapping("/updateBannerIndex")
    @ResponseBody
    public AjaxResult updateBannerIndex(@RequestBody FsAdAppBannerDO fsAdAppBanner) {
        checkParam(fsAdAppBanner, "???????????????");
        checkParam(fsAdAppBanner.getId(), "??????????????????banner");
        checkParam(fsAdAppBanner.getIndex(), "??????????????????");
        FsAdAppBannerDO fsAdAppBannerDO = queryFsAdAppBannerById(fsAdAppBanner.getId());
        AppAdBannerTypeEnum typeEnum = AppAdBannerTypeEnum.getByCode(fsAdAppBannerDO.getBannerType());
        BaseResult<FsAdAppBannerDO> fsAdAppBannerDOBaseResult = appCenterBannerService.queryBannerByIndex(typeEnum, fsAdAppBanner.getIndex());
        if (fsAdAppBannerDOBaseResult.isSuccess() && null != fsAdAppBannerDOBaseResult.getResult()) {
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "??????????????????.");
        }
        BaseResult<Void> voidBaseResult = appCenterBannerService.updateBannerIndex(fsAdAppBanner.getId(), fsAdAppBanner.getIndex());
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "banner????????????");
        }
        return SUCCESS;
    }
}
