package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.OpenAppScopeOrderListResult;
import com.facishare.open.app.center.api.result.StatusResult;

import java.util.List;

/**
 * 查询应用已经获取了哪些权限 .
 *
 * @author zenglb
 * @date 2015年8月20日
 */
public interface OpenAppScopeOrderService {
    /**
     * 权限应用id,查询应用已经获取了哪些权限 .
     *
     * @param appId 应用Id
     * @return
     */
    OpenAppScopeOrderListResult getAppScopeByAppId(String appId);

    /**
     * 保存应用权限.
     *
     * @param appId
     * @param scopeList
     * @return
     */
    StatusResult saveAppScopes(String appId, List<OpenAppScopeOrderDO> scopeList);


    /**
     * 删除应用权限.仅用于数据迁移.
     *
     * @param appId
     * @return
     */
    @Deprecated
    BaseResult<Void> deleteAppScopesPhysical(String appId);
}
