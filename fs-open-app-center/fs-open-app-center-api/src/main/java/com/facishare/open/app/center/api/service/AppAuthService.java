package com.facishare.open.app.center.api.service;

import java.util.Collection;

/**
 * Created by xialf on 3/18/16.
 *
 * @author xialf
 */
public interface AppAuthService {
    /**
     * 授权应用.
     * 幂等, 且只能为开平开发者使用, 用户操作不能调用该接口.
     *
     * @param fsEa        企业账号
     * @param appId       应用id
     * @param appAdminIds 应用管理员(null则不设置且不修改)
     */
    void authApp(final String fsEa, final String appId, final Collection<Integer> appAdminIds);

    /**
     * 取消授权.
     * 幂等, 且只能为开平开发者使用, 用户操作不能调用该接口.
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     */
    void deauthApp(final String fsEa, final String appId);
}
