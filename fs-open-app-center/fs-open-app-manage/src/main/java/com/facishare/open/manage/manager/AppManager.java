package com.facishare.open.manage.manager;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import com.facishare.open.app.center.api.model.OpenComponentUrlGrayDO;
import com.facishare.open.app.center.api.model.OpenDictDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.vo.OpenComponentUrlGrayVO;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.model.OpenAppComponentVO;
import com.facishare.open.manage.model.OpenAppScopeVO;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.oauth.model.enums.ScopeOwnerEnum;

import java.util.List;

/**
 * Created by wangwz on 2015/9/15.
 */
public interface AppManager {
    /**
     * 判断当前应用能否使用新名称
     * @param appId
     * @param newAppName
     * @return true 可以  false 不可以
     */
    boolean checkAppName(String appId, String newAppName);

    /**
     * 判断是否存在开发者创建的重名的应用/服务号（基础应用、扩展应用、基础服务号、扩展服务号）
     * @param appName 应用名称
     * @param appType
     * @return
     */
    boolean existsDevAppName(String appName, Integer appType);

    /**
     * 创建一个第三方应用
     *
     * @return
     */
    void createDevApp(OpenDevAppVO appForm);

    /**
     * 修改一个第三方应用
     *
     * @param appForm
     */
    void updateDevApp(OpenDevAppVO appForm);

    /**
     * 修改一个第三方应用基础信息
     *
     * @param appForm
     */
    void updateDevAppBase(OpenDevAppVO appForm);


    /**
     * 修改一个第三方应用，第三方信息
     *
     * @param appForm
     */
    void updateDevAppDev(OpenDevAppVO appForm);

    /**
     * 查询第三方应用。
     *
     * @param pager
     * @return
     */
    Pager<OpenAppDO> queryOpenAppByDev_ddd(Pager<OpenAppDO> pager);

    /**
     * 加载应用
     *
     * @param appId
     * @return
     */
    OpenDevAppVO loadOpenApp(String appId);

    /**
     * 删除应用
     *
     * @param devId
     * @param appId
     */
    void deleteDevApp(String devId, String appId);

    /**
     * 添加应用/服务号，获取权限
     * @param scopeOwn @oauth com.facishare.open.oauth.model.enums.ScopeOwnerEnum  1：应用、2：服务号
     * @return
     */
    List<OpenAppScopeOrderDO> listScope(ScopeOwnerEnum scopeOwn);

    /**
     * 申请上线
     *
     * @param devId
     * @param appId
     */
    void applyForOnline(String devId, String appId);

    /**
     * 查询所有的应用分类
     *
     * @return
     */
    List<OpenDictDO> listAppClass();

    /**
     * @param pager
     * @return
     */
    Pager<OpenDevAppVO> queryOpenAppByDev(Pager<OpenDevAppVO> pager);

    /**
     * 应用标签
     *
     * @return
     */
    List<OpenDictDO> listAppLabel();

    /**
     * 创建应用组件
     *
     * @param form
     */
    void createAppComponent(String appId, Integer appType, OpenAppComponentVO openAppComponentVO);

    /**
     * 更新应用组件
     *
     * @param form
     */
    void updateAppComponent(String componentId, OpenAppComponentVO openAppComponentVO);

    /**
     * 根据id查询单个应用组件
     *
     * @param componentId 组件ID
     * @return OpenAppComponentVO
     */
    OpenAppComponentVO loadAppComponentById(String componentId);

    /**
     * 保存组件灰度地址
     * @param form 组件灰度地址
     */
    void saveOpenComponentUrlGray(OpenComponentUrlGrayVO form);

    /**
     * 根据id查询单个应用组件的灰度地址
     *
     * @param componentId 组件ID
     * @return OpenComponentUrlGrayVO
     */
    OpenComponentUrlGrayVO loadOpenComponentUrlGrayById(String componentId);

    /**
     * 根据id删除单个应用组件
     *
     * @param componentId 组件ID
     */
    void deleteAppComponentById(String componentId);

    /**
     * 更新应用权限信息
     * @param scopeOwn @oauth com.facishare.open.oauth.model.enums.ScopeOwnerEnum  1：应用、2：服务号
     */
    void updateAppScopes(OpenAppScopeVO openAppScopeVO, ScopeOwnerEnum scopeOwn);

    /**
     * 更新应用的状态，只有开发人员才能操作.
     * @param entity 应用信息
     * @return boolean
     */
    boolean updateAppStatus(OpenAppDO entity);

    /**
     * 应用绑定服务号：添加绑定关系
     * @param appId
     * @param serviceId
     */
    void addServiceBind(String appId, String serviceId);

    /**
     * 应用绑定服务号：删除绑定关系
     * @param appId
     * @param serviceId
     */
    void deleteServiceBind(String appId, String serviceId);

    /**
     * 应用绑定服务号：修改绑定关系
     * @param appId
     * @param serviceId
     * @param newServiceId
     */
    void modifyServiceBind(String appId, String serviceId, String newServiceId);
}
