package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.AppNameVO;
import com.facishare.open.app.center.api.model.ComponentNameVO;
import com.facishare.open.app.center.api.model.vo.AppNameLogoVO;
import com.facishare.open.app.center.api.result.BaseResult;

import java.util.List;

/**
 * 应用名称与应用的组件名称服务.
 * Created by zenglb on 2016/1/19.
 */
public interface OpenAppOrComponentNameService {

    /**
     * 查询应用名称，仅用于汇集平台.
     * @param appIds
     * @return
     */
    BaseResult<List<AppNameVO>> queryAppNameByAppIds(List<String> appIds);

    /**
     *查询应用组件名称，仅用于汇集平台.
     * @param componentIds
     * @return
     */
    BaseResult<List<ComponentNameVO>> queryComponentNameByComponentIds(List<String> componentIds);

    /**
     * 查询应用名称与头像，仅用于消息中心展示.
     * @param appIds
     * @return
     */
    BaseResult<List<AppNameLogoVO>> queryAppNameLogoByAppIds(List<String> appIds);
}
