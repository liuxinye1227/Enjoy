package com.facishare.open.manage.stat.manager.impl;

import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenAppComponentService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.DateKit;
import com.facishare.open.manage.stat.dao.AppStatDAO;
import com.facishare.open.manage.stat.manager.AppStatManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppStatManagerImpl implements AppStatManager {

    @Autowired
    private AppStatDAO appStatDAO;
    @Autowired
    private OpenAppService openAppService;
    @Autowired
    private OpenAppComponentService openAppComponentService;

    @Override
    public Pager<Map<String, Object>> queryAllAppStat4Search(Pager<Map<String, Object>> pager) {
        Map<String, Object> parameters = pager.params();
        parameters.put("statDate", DateKit.addDay(new Date(), -1));
        Long recordSize = appStatDAO.queryAllAppStat4SearchCount(parameters);
        if (null != recordSize) {
            pager.setRecordSize(recordSize.intValue());
            if (recordSize.intValue() > 0 && pager.getPageTotal() >= pager.getCurrentPage()) {
                List<Map<String, Object>> lst = appStatDAO.queryAllAppStat4SearchList(parameters);
                if (null != lst) {
                    List<String> appIds = new ArrayList<String>();
                    String appId = null;
                    for (Map<String, Object> map : lst) {
                        appId = (String) map.get("appId");
                        if (StringUtils.isNotBlank(appId)) {
                            appIds.add(appId);
                        }
                    }
                    AppListResult appListResult = openAppService.loadOpenAppByIdsFast(appIds);
                    if (appListResult.isSuccess() && !CollectionUtils.isEmpty(appListResult.getResult())) {
                        for (Map<String, Object> map : lst) {
                            appId = (String) map.get("appId");
                            if (StringUtils.isNotBlank(appId)) {
                                for (OpenAppDO app : appListResult.getResult()) {
                                    if (appId.equals(app.getAppId())) {
                                        map.put("appName", app.getAppName());
                                    }
                                }
                            }
                        }
                    }
                    pager.setData(lst);
                }
            }
        }
        return pager;
    }

    @Override
    public List<Map<String, Object>> queryDtlByAppId(String appId, Date startDate, Date endDate) {
        Date start = DateKit.date(startDate);
        Date end = DateKit.date(endDate);
        AppResult appResult = openAppService.loadOpenApp(appId);
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用不存在");
        }
        BaseResult<List<OpenAppComponentDO>> componentListResult = openAppComponentService.queryAppComponentListByAppId(null, appId);
        if (!componentListResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, componentListResult, "查询应用组件失败");
        }
        List<String> componentIdList = componentListResult.getResult().stream().map(OpenAppComponentDO::getComponentId).collect(Collectors.toList());
        List<Map<String, Object>> result = appStatDAO.queryDtlByAppIdAndStatDate(appId, start, end);
        final List<Map<String, Object>> componentList = new ArrayList<>();

        if (!componentIdList.isEmpty()) {
            componentList.addAll(appStatDAO.queryDtlByComponentIdsAndStatDate(componentIdList, start, end));
        }

        String appName = appResult.getResult().getAppName();
        result.forEach(line -> {
            Date statDate = (Date) line.get("statDate");
            line.put("appName", appName);
            for (Map<String, Object> map : componentList) {
                if (statDate.equals(map.get("statDate"))) {
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> components = (List<Map<String, Object>>) line.get("components");
                    if (null == components) {
                        components = new ArrayList<>();
                    }
                    componentListResult.getResult().stream().filter(component -> component.getComponentId().equals(map.get("componentId"))).forEach(component -> {
                        String componentName = component.getComponentName();
                        componentName += "(" + (component.getComponentType() == AppComponentTypeEnum.APP.getType() ? "App" : "Web") + ")";
                        map.put("componentName", componentName);
                    });

                    map.put("cumulativeOpenEaNum", line.get("cumulativeOpenEaNum"));
                    components.add(map);
                    line.put("components", components);
                }
            }
        });

        return result;
    }

}
