package com.facishare.open.manage.manager;

import com.facishare.portal.model.UserDO;

/**
 * 主要是进行当前用户的用户数据获取。
 *
 * @author zhouq
 * @date 2016年5月17日
 */
public interface WebAuthManager {
    /**
     * 加载当前登录的用户信息
     *
     * @param portalUserId 用户ID
     * @param portalToken  token
     * @return UserDO
     */
    UserDO loadWebFsUser(String portalUserId, String portalToken);

    /**
     * 获取当前用户拥有系统菜单权限
     *
     * @param user     用户
     * @param systemId 系统ID
     * @return UserDO
     */
    UserDO queryUserPermission(UserDO user, Long systemId);

    /**
     * 查询用户在该系统是否具有此url的权限
     *
     * @param portalUserId 用户ID
     * @param systemId     系统ID
     * @param url          访问url
     * @return boolean
     */
    boolean verifyUserPermission(String portalUserId, Long systemId, String url);
}