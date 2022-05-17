package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.result.IntegerResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.Collection;

/**
 * 绑定关系更新.
 *
 * @author zenglb
 * @date 2015年8月10日
 */
public interface OpenFsUserBindAppService {

    /**
     * 为企业绑定应用(授权应用).
     *
     * @param fsAdminUser       管理员账号信息
     * @param enterpriseAccount 企业账号 E.xxx.001 中的xxx
     * @param appId             应用 id
     * @return
     */
    StatusResult saveFsUserBindApp(FsUserVO fsAdminUser, String enterpriseAccount, String appId);

    /**
     * 查询当前企业绑定的应用.
     *
     * @param fsAdminUser       管理员账号信息
     * @param enterpriseAccount 企业账号 E.xxx.001 中的xxx
     * @return
     */
    AppListResult queryAppListByFsEnterpriseAccount(FsUserVO fsAdminUser, String enterpriseAccount);

    /**
     * 取消授权.
     *
     * @param fsAdminUser       管理员账号信息
     * @param appId             应用 id
     * @param enterpriseAccount 企业账号 E.xxx.001 中的xxx
     * @return
     */
    StatusResult saveFsUserBindAppCancelAuth(FsUserVO fsAdminUser, String appId, String enterpriseAccount);

    /**
     * 自定义应用的停用启用.
     *
     * @param fsAdminUser       管理员账号信息
     * @param appId             应用 id
     * @param enterpriseAccount 企业账号 E.xxx.001 中的xxx
     * @param status            1启用，2停用
     * @return
     */
    StatusResult updateAppBindOnOff(FsUserVO fsAdminUser, String appId, String enterpriseAccount, Integer status);

    /**
     * 服务号的停用启用.
     *
     * @param fsAdminUser       管理员账号信息
     * @param appId             应用 id
     * @param enterpriseAccount 企业账号 E.xxx.001 中的xxx
     * @param status            1启用，2停用
     * @return
     */
    StatusResult updateServiceBindOnOff(FsUserVO fsAdminUser, String appId, String enterpriseAccount, Integer status);

    /**
     * 查询应用的绑定状态.
     *
     * @param fsAdminUser 管理员账号信息
     * @param appId       应用 id
     * @return
     */
    IntegerResult queryAppBindStatus(FsUserVO fsAdminUser, String appId);

    /**
     * 查询应用的绑定状态.
     * @param enterpriseAccount ea信息
     * @param appId 应用 id
     * @return
     */
    IntegerResult queryAppBindStatus(String enterpriseAccount, String appId);

    /**
     * 添加第三方应用
     * @param  fsUser 企业账号
     * @param  appId 应用ID
     * @param  adminIds 应用管理员列表
     * @return **/
    StatusResult addDevApp(String fsUser, String appId, Collection<Integer> adminIds);

    /**
     * 微企信专用（如果企业没有对应用授权则授权，否则添加应用管理员）
     * @param fsUser
     * @param appId
     * @param adminIds
     * @return
     */
    @Deprecated
    StatusResult addDevAppOrAdmins(String fsUser, String appId, Collection<Integer> adminIds);
}
