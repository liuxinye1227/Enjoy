package com.facishare.open.app.center.api.service;

import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * 查询应用管理员，用于官方应用开发者调用（非应用中心查询）
 * Created by zenglb on 2016/3/8.
 */
public interface QueryAppAdminService {

    /**
     * 查询当前用户是否为用户对象.
     *
     * @param fsUserVO 用户.
     * @param appId    应用id.
     * @return 是否应用管理员. 判断时需要先调用baseResult.isSuccess判断是否请求成功.
     */
    BaseResult<Boolean> isAppAdmin(FsUserVO fsUserVO, String appId);

    /**
     * 查询当前应用的所有管理员
     *
     * @param fsEa  企业账号.
     * @param appId 应用id.
     * @return 是否应用管理员. 判断时需要先调用baseResult.isSuccess判断是否请求成功.
     */
    BaseResult<List<FsUserVO>> findAppAdminListByAppId(String fsEa, String appId);
}
