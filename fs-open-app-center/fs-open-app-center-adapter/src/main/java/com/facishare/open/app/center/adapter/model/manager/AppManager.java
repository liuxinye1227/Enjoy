package com.facishare.open.app.center.adapter.model.manager;

import com.facishare.open.common.model.FsUserVO;

import java.util.List;

/**
 * Created by xialf on 7/20/16.
 *
 * @author xialf
 */
public interface AppManager {
    /**
     * 获取应用管理员.
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     * @return 应用管理员列表
     */
    List<Integer> queryAppAdmins(final String fsEa, final String appId);

    /**
     * 增加应用管理员.
     */
    void addAppAdmins(final FsUserVO fsUser, final String appId, List<Integer> appAdmins);

    /**
     * 删除应用管理员.
     */
    void removeAppAdmins(final FsUserVO fsUser, final String appId, List<Integer> appAdmins);

    /**
     * 是否是应用管理员.
     *
     * @param fsUser 员工账号
     * @param appId  应用id
     * @return true if fsUser is app admin for the appId
     */
    boolean isAppAdmin(final FsUserVO fsUser, final String appId);
}
