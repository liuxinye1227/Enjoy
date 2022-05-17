package com.facishare.pay.business.dao;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.UserInfoDo;


/**
 * 用户信息
 * @author guom
 * @date 2015/10/13
 */
public interface UserInfoDAO {

    /**
     * 修改用户信息
     * @param userInfoDo 用户名
     * @return ModelResult<Boolean>结果集
     * */
    int updateUserInfo(final UserInfoDo userInfoDo);
    
    /**
     * 修改用户秘密
     * @param String userName 用户名
     * @return ModelResult<Boolean>结果集
     * */
    int updatePassword(String enterpriseAccount, Long fsUserId, String oldPassword, String newPassword);
    
    /**
     * 添加用户信息
     * @param userInfoDo
     * @return int 
     * */
    int addUserInfo(final UserInfoDo userInfoDo);
    
    /**
     * 查询用户信息
     * @param enterpriseAccount 企业号
     * @param fsUserId 用户id
     * @return UserInfoDo
     * */
    UserInfoDo queryUserInfo(final String enterpriseAccount, final Long fsUserId);
    
    /**
     * 分页查询用户列表
     * @param userInfoDo
     * @param page
     * @return Pager<UserInfoDo>
     * */
    Pager<UserInfoDo> queryUserInfo(Pager<UserInfoDo> page, final UserInfoDo userInfoDo);
}
