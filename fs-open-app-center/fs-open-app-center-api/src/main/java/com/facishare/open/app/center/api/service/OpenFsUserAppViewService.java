package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.AppViewDO;
import com.facishare.open.app.center.api.model.EmployeeRange;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppAccessTypeEnum;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.model.vo.NotifyUserViewVO;
import com.facishare.open.app.center.api.model.vo.UserCanViewListVO;
import com.facishare.open.app.center.api.result.AppVOListResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 组件可见范围的服务.
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public interface OpenFsUserAppViewService {

    /**
     * 保存fs用户与应用的可见关系.
     *
     * @param fsAdminUser 管理员账号信息
     * @param componentId 应用组件ID
     * @param viewType    WEB可见或app可见
     * @param view        可见范围取值
     * @return
     */
    BaseResult<Void> saveFsUserAppViewList(FsUserVO fsAdminUser, String componentId, AppComponentTypeEnum viewType,
                                           AppViewDO view);

    /**
     * 保存组件的可见范围, 并且不受配额限制(数据迁移时临时使用, 有问题请咨询夏林锋).
     *
     * @param fsEa        企业账号
     * @param componentId 组件id
     * @param view        可见范围
     */
    BaseResult<Void> saveViewWithoutQuota(final String fsEa, final String componentId, final AppViewDO view);

    /**
     * 清空应用中所有组件的可见范围.
     *
     * @param fsAdmin 管理员账号
     * @param appId   应用id
     * @return
     */
    BaseResult<Void> clearView(final FsUserVO fsAdmin, final String appId);

    /**
     * 根据用户和viewType获取可见组件列表
     * Web组件按照其所属应用的授权时间排序
     * App组件按照Label排序.
     *
     * @param fsUser            分享用户
     * @param appAccessTypeEnum 访问类型
     * @return 可见的组件列表
     */
    BaseResult<List<UserCanViewListVO>> queryComponentsByFsUser(final FsUserVO fsUser,
                                                                AppAccessTypeEnum appAccessTypeEnum);

    /**
     * 加载组件在指定企业的可见范围.
     *
     * @param fsAdminUser 管理员账号信息
     * @param componentId 组件ID
     * @param viewType    组件类型
     * @return
     */
    BaseResult<AppViewDO> loadAppViewByType(FsUserVO fsAdminUser, String componentId, AppComponentTypeEnum viewType);

    /**
     * 根据fsUserId, viewType 获取可见应用.
     *
     * @param fsUser   普通用户
     * @param viewType 可见类型
     * @return
     */
    @Deprecated
    AppVOListResult queryAppListByFsUserId(FsUserVO fsUser, AppCenterEnum.ViewType viewType);

    /**
     * 仅仅保存个人可见,并通知终端拉取列表.
     *
     * @param fsAdminUser
     * @param componentId
     * @param viewType
     * @param insertUserList
     * @param deleteUserList
     * @return
     */
    @Deprecated
    BaseResult<Void> saveOnlyUserComponentViewList(FsUserVO fsAdminUser, String componentId, AppComponentTypeEnum viewType,
                                                   List<Integer> insertUserList, List<Integer> deleteUserList);

    /**
     * 保存某个appId下所有组件个人可见,并通知终端拉取列表.
     *
     * @param fsAdminUser
     * @param appId
     * @param insertUserList
     * @param deleteUserList
     * @return
     */
    @Deprecated
    BaseResult<Void> saveOnlyUserAllComponentViewList(FsUserVO fsAdminUser, String appId, List<Integer> insertUserList,
                                                      List<Integer> deleteUserList);

    /**
     * 加载组件在公司的可见范围.
     *
     * @param user        纷享用户
     * @param componentId 组件id
     * @return 组件在公司的可见范围
     */
    BaseResult<EmployeeRange> loadComponentView(final FsUserVO user, final String componentId);

    /**
     * 通知终端用户.
     *
     * @param user  纷享用户
     * @param appId 应用id
     * @param view  可见范围
     * @return 空
     */
    BaseResult<Void> notifyEndUsers(final FsUserVO user, final String appId, final EmployeeRange view);

    /**
     * 查询用户可见的应用自定义应用列表,用于应用中心主页企业自建的展示.add by lambo @20160120.
     *
     * @param user
     * @return
     */
    BaseResult<List<OpenAppDO>> queryVisibleAppList(FsUserVO user);

    /**
     * 判断用户是否可以访问指定应用的组件.(包括可见范围和试用)
     * 当前主要是为了兼容A/B版.
     *
     * @param fsUser      纷享用户
     * @param componentId 应用的组件id
     * @return 是否可以访问
     */
    BaseResult<Boolean> canAccessComponent(final FsUserVO fsUser, final String componentId);

    /**
     * 查询可以使用该应用的用户数(各个组件的并集).
     *
     * @param fsUser 纷享用户
     * @param appId  应用id
     * @return 用户数
     */
    BaseResult<Integer> queryUsersCount(final FsUserVO fsUser, final String appId);

    /**
     * 查询可以使用该应用的用户(各个组件的并集).
     *
     * @param fsUser 纷享用户
     * @param appId  应用id
     * @return 用户数
     */
    BaseResult<Set<Integer>> queryUsers(final FsUserVO fsUser, final String appId);

    /**
     * 查询组件可见范围人数 (各个组件独立计算)
     *
     * @param fsUserVO
     * @param appId
     * @return 组件ID:人数 作为键值对的Map
     */
    BaseResult<Map<String, Integer>> queryComponentUsersCount(FsUserVO fsUserVO, String appId);


    /**
     * 通知终端展示/隐藏crm
     *
     * @param componentId
     * @param fsUserVOs
     * @return
     */
    @Deprecated
    BaseResult<Void> notifyCrmShow(final String componentId, Set<FsUserVO> fsUserVOs);

    /**
     * 删除可见缓存
     *
     * @param fsUserVO
     * @param appId
     * @return
     */
    @Deprecated
    BaseResult<Void> deleteViewCache(FsUserVO fsUserVO, String appId);


    /**
     * 通知终端更新可见列表
     *
     * @param fsEa
     * @param appId
     * @param fsUserVOs
     * @return
     */
    @Deprecated
    BaseResult<Void> notifyUserView(String fsEa, String appId, Set<FsUserVO> fsUserVOs);

    /**
     * 通知终端,更新可见范围
     *
     * @param fsUserVO
     * @param appId
     * @param isOff
     * @return
     */
    BaseResult<Void> updateAndNotifyApp(FsUserVO fsUserVO, String appId, Boolean isOff);

    /**
     * 通知终端更新应用列表和CRM展示.
     *
     * @param fsUserVO       纷享用户
     * @param appId          应用id
     * @param insertSessions 插入的用户
     * @param delSessions    删除的用户
     * @return
     */
    BaseResult<Void> updateAndNotifyApp(FsUserVO fsUserVO, String appId, Set<Integer> insertSessions, Set<Integer> delSessions);

    /**
     * 根据配额限制可见范围
     * @param fsEa 企业账号
     * @param appId 应用ID
     * @param quota 应用配额
     * @return Void
     */
    BaseResult<Void> restrictView(String fsEa, String appId, int quota);

    /**
     * 删除可见范围的企业级缓存（仅供app-pay使用）
     * @param fsEa 企业账号
     * @param appId 应用ID
     * @return Void
     */
    BaseResult<Void> cleanViewCache(String fsEa, String appId);

    /**
     * 通知终端更新（企信通知和checkAppUpdated方式）
     *
     * @param fsUser
     * @param notifyUserViewVO
     * @return
     */
    BaseResult<Void> notifyUserViewAsync(final FsUserVO fsUser, NotifyUserViewVO notifyUserViewVO);
}
