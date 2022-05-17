package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum.AppType;
import com.facishare.open.app.center.api.model.enums.AppStatus;
import com.facishare.open.app.center.api.model.vo.TryStatusAppVO;
import com.facishare.open.app.center.api.result.*;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.List;

/**
 * 应用相关的服务
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public interface OpenAppService {
    /**
     * 验证自定义应用或者服务号的名称是否存在重名
     * @param appName
     * @param fsEa
     * @param appType 仅限自定义应用服服务号
     * @return
     */
    BaseResult<Boolean> existsAppName(String appName, String fsEa, AppCenterEnum.AppType appType);

    /**
     * 验证开发者账号创建的应用/服务号的名称是否存在重名
     * @param appName
     * @param appType 基础应用、扩展应用、基础服务号、扩展服务号等
     * @param statusList 应用状态；管理后台创建时判断重名，除了"AppStatus.ON_LINE"，还要考虑其他类型
     * @return
     */
    BaseResult<Boolean> existsDevAppName(String appName, Integer appType, List<Integer> statusList);

    /**
     * 创建单个应用
     *
     * @param entity
     * @return
     */
    AppResult createOpenApp(OpenAppDO entity);

    /**
     * 更新单个应用
     *
     * @param entity
     * @return
     */
    AppResult updateOpenApp(OpenAppDO entity);

    /**
     * 加载单个应用
     *
     * @param appId 应用Id
     * @return
     */
    AppResult loadOpenApp(String appId);

    /**
     * 加载单个应用快速接口
     *
     * @param appId 应用Id
     * @return
     */
    AppResult loadOpenAppFast(String appId);

    /**
     * 批量查询app
     *
     * @param appIds
     * @return
     */
    AppListResult loadOpenAppByIds(List<String> appIds);


    /**
     * 批量查询app快速接口
     *
     * @param appIds
     * @return
     */
    AppListResult loadOpenAppByIdsFast(List<String> appIds);

    /**
     * 分页查询未绑定过的应用列表
     *
     * @param pager page
     * @return
     */
    AppPagerResult queryUnBindOpenAppByType(Pager<OpenAppDO> pager);

    /**
     * 分页查询的第三方应用列表
     *
     * @param pager page
     * @return
     */
    AppPagerResult queryOpenAppByDev(Pager<OpenAppDO> pager);

    /**
     * 分页查询服务号列表
     *
     * @param pager page
     * @return
     */
    AppPagerResult queryServiceByDev(Pager<OpenAppDO> pager);

    /**
     *查询第三方应用列表
     * @param pager
     * @return
     */
    BaseResult<Pager<TryStatusAppVO>> queryDevAppList(Pager<OpenAppDO> pager);

    /**
     * 删除单个自定义应用.
     *
     * @param fsAdminUser       管理员账号
     * @param appId             应用Id
     * @param enterpriseAccount 企业账号 E.xxx.001 中的xxx
     * @return
     */
    StatusResult deleteCustomApp(FsUserVO fsAdminUser, String appId, String enterpriseAccount);


    /**
     * 更新应用状态
     *
     * @param devId
     * @param appId
     * @return
     */
    StatusResult updateDevAppStatus(String devId, String appId, AppStatus appStatus);

    /**
     * 查询by 类型与状态
     *
     * @param statusList
     * @param appTypeList
     * @return
     */
    AppListResult queryAppListByStatusAndAppTypes(List<AppStatus> statusList, List<AppType> appTypeList);

    /**
     * 查询by 类型与状态
     *
     * @param statusList
     * @param appTypeList
     * @return
     */
    AppListResult queryAppListByStatusAndAppTypesOrderByAppName(List<AppStatus> statusList, List<AppType> appTypeList);

    /**
     * 根据应用状态和类型 分页查询
     *
     * @param pager
     * @return
     */
    BaseResult<Pager<OpenAppDO>> queryAppByStatusAndTypesByPager(Pager<OpenAppDO> pager);

    /**
     * 查询开放平台的应用,用于应用中心主页平台应用的展示.add by lambo @20160120.
     *
     * @param page
     * @return
     */
    BaseResult<Pager<OpenAppDO>> queryPlatFormAppList(Pager<OpenAppDO> page);

    /**
     * 查询企业自建应用列表
     * @param fsAdminUser 用户
     * @param openAppDO 应用类型
     * @return 应用列表
     */
    AppListResult queryCustomApps(FsUserVO fsAdminUser, OpenAppDO openAppDO);

    /**
     * 物理删除应用.仅用于数据迁移还原.
     * @param newAppId
     * @return
     */
    @Deprecated
    BaseResult<Void> deleteAppPhysical(String newAppId);

    /**
     * 查询当前企业的自建服务号数量
     * @param fsEa
     * @return
     */
    BaseResult<Long> queryServiceCount(String fsEa);

    /**
     * 查询企业所有服务号数(包括自建/基础/扩展服务号）
     * @param fsEa
     * @return
     */
    BaseResult<Integer> queryEaAllServiceCount(String fsEa);

}
