package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.vo.OpenAppVO;
import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.Collection;
import java.util.List;

/**
 * 应用管理员服务.
 *
 * @author xialf
 *         Created by xialf on 10/13/15.
 */
public interface OpenAppAdminService {
    /**
     * 判断用户是否是指定应用的管理员(包括服务号及应用).
     *
     * @param fsUserId 纷享账号(E.fs.1000)
     */
    BaseResult<Boolean> isAppAdmin(final String fsUserId, final String appId);

    /**
     * 判断用户是否服务号管理员.
     *
     * @param fsUserId 纷享账号
     */
    BaseResult<Boolean> isServiceAdmin(final String fsUserId);

    /**
     * 判断用户是否服务号管理员.
     * @param fsEa 企业账户
     * @param userId 用户ID
     * @param appTypes @see com.facishare.open.app.center.api.model.enums.AppCenterEnum.AppType
     * @return
     */
    BaseResult<Boolean> isServiceAdmin(String fsEa, Integer userId, List<Integer> appTypes);

    /**
     * 判断用户是否是应用管理员(不包括服务号).
     *
     * @param fsUserId 纷享账号
     */
    BaseResult<Boolean> isAppAdmin(final String fsUserId);

    /**
     * 查询用户作为应用管理员的所有应用id列表(不包括服务号).
     *
     * @param fsUserId 纷享账号
     */
    BaseResult<List<String>> queryAppIdList(final String fsUserId);

    /**
     * 查询用户作为服务号管理员的所有服务号id列表.
     *
     * @param fsUserId 纷享账号
     */
    BaseResult<List<String>> queryServiceIdList(final String fsUserId);

    /**
     * 查询在企业中可以管理指定应用的所有应用管理员账号id.
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     * @return 可以管理应用appId的所有应用管理员账号
     */
    BaseResult<List<String>> queryAppAdminIdList(final String fsEa, final String appId);

    /**
     * 更新企业中指定应用的应用管理员(多删少加).
     *
     * @param fsAdminUser 企业管理员
     * @param appId       应用id
     * @param appAdminIds 应用管理员id列表
     */
    BaseResult<Void> updateAppAdminIds(final FsUserVO fsAdminUser, final String appId, final Collection<String> appAdminIds);

    /**
     * 增加应用管理员.
     *
     * @param fsAdminUser      企业管理员
     * @param appId            应用id
     * @param appAdminIdsToAdd 新增加的应用管理员
     */
    BaseResult<Void> addAppAdminIds(final FsUserVO fsAdminUser, final String appId, final Collection<Integer> appAdminIdsToAdd);

    /**
     * 删除应用管理员.
     *
     * @param fsAdminUser         企业管理员
     * @param appId               应用id
     * @param appAdminIdsToRemove 删除的应用管理员
     * @return
     */
    BaseResult<Void> removeAppAdminIds(final FsUserVO fsAdminUser, final String appId, final Collection<Integer> appAdminIdsToRemove);

    /**
     * 分页查询带管理员的企业.
     * 特别注意本接口不提供.
     *
     * @param currentPage 当前页.
     * @param pageSize    页大小.
     */
    BaseResult<List<String>> queryPagerAllFsEas(Integer currentPage, Integer pageSize);

    /**
     * 批量查询企业下的所有的应用管理员.主要用于开发者管理后台使用.不作它用.
     *
     * @param fsEas       企业账号列表
     * @param appTypeList 应用类型列表(相应管理员类型列表),参照AppCenterEnum.AppType
     * @param appIds      应用列表
     */
    BaseResult<List<FsUserVO>> queryAppAdminByFsEas(List<String> fsEas, List<String> appTypeList, List<String> appIds);

    /**
     * 查询所有企业.
     */
    BaseResult<Long> queryCountAllFsEas();

    /**
     * 修改应用类型. 仅用于数据迁移.
     *
     * @param appId   应用id.
     * @param appType 应用类型.
     */
    @Deprecated
    BaseResult<Void> modifyAppType(String appId, AppCenterEnum.AppType appType);

    /**
     * 通过userId查找负责管理的服务号数.
     *
     * @param userId 用户id.
     */
    BaseResult<Integer> queryServiceCountByUserId(String userId);

    /**
     * 查询服务号
     * @param user
     * @param appTypes
     * @return
     */
    AppListResult queryServicesByAdminAndType(FsUserVO user, List<Integer> appTypes);

    /**
     * 查询服务号
     * @param user
     * @param appTypes
     * @return
     */
    BaseResult<List<OpenAppDO>> queryServicesByServiceAdmin(FsUserVO user, List<Integer> appTypes);

    /**
     * 查询<b>已启用</b>外联服务号列表(互联管理员)
     * @param fsEa
     * @param userId
     * @return BaseResult(List<OpenAppVO>)
     */
    BaseResult<List<OpenAppVO>> queryLinkServiceListByAdmin(String fsEa, Integer userId);

    /**
     * 查询<b>已启用</b>外联服务号列表(互联服务号管理员)
     * @param fsEa
     * @param userId
     * @return BaseResult(List<OpenAppVO>)
     */
    BaseResult<List<OpenAppVO>> queryLinkServiceListByServiceAdmin(String fsEa, Integer userId);

}
