package com.facishare.open.app.center.external.manager;

import com.facishare.open.common.model.FsUserVO;

import java.util.List;

/**
 * 应用管理员查询服务.
 * Created by zenglb on 2016/10/12.
 */
public interface OpenAppAdminManager {

    /**
     * 查询当前用户是否为用户对象.
     *
     * @param fsUserVO 用户.
     * @param appId    应用id.
     * @return 是否应用管理员. 判断时需要先调用baseResult.isSuccess判断是否请求成功.
     */
    boolean isAppAdmin(FsUserVO fsUserVO, String appId);

    /**
     * 查询当前应用的所有管理员
     *
     * @param fsEa  企业账号.
     * @param appId 应用id.
     * @return 是否应用管理员. 判断时需要先调用baseResult.isSuccess判断是否请求成功.
     */
    List<FsUserVO> findAppAdminListByAppId(String fsEa, String appId);
}
