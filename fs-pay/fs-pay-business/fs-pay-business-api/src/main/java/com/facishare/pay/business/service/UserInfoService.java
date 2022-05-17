package com.facishare.pay.business.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.common.result.ModelResult;


/**
 * 用户信息
 * @author guom
 * @date 2015/10/13
 */
public interface UserInfoService {

    /**
     * 修改用户信息
     * @param UserInfoDo userInfoDo 用户信息
     * @return ModelResult<Boolean>结果集
     * */
    ModelResult<Boolean> updateUserInfo(final UserInfoDo userInfoDo);
    
    /**
     * 修改用户密码
     * @param String enterpriseAccount 企业号
     * @param Long fsUserId 用户id
     * @param String oldPassword 旧密码
     * @param String newPassword 新密码
     * @return ModelResult<Boolean>结果集
     * */
    ModelResult<Boolean> updateUserInfo(String enterpriseAccount, Long fsUserId, String oldPassword, String newPassword);
    
    /**
     * 添加用户信息
     * @param UserInfoDo userInfoDo
     * @return ModelResult<Boolean> 
     * */
    ModelResult<Boolean> addUserInfo(final UserInfoDo userInfoDo);
    
    /**
     * 分页查询用户列表
     * @param UserInfoDo userInfoDo
     * @param Pager<UserInfoDo> page
     * @return ModelResult<Pager<UserInfoDo>>
     * */
    ModelResult<Pager<UserInfoDo>> queryUserInfo(Pager<UserInfoDo> page, final UserInfoDo userInfoDo);
    
}
