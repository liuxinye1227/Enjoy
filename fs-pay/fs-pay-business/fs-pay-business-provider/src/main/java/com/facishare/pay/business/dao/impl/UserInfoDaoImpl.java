package com.facishare.pay.business.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.dao.UserInfoDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.UserInfoDo;

@Service
public class UserInfoDaoImpl extends BusinessBaseDAO<UserInfoDo> implements UserInfoDAO {

    @Override
    public int updateUserInfo(UserInfoDo userInfoDo) {
        return this.update("updateUserInfo", userInfoDo);
    }

    @Override
    public int addUserInfo(UserInfoDo userInfoDo) {
        return this.save("addUserInfo", userInfoDo);
    }

    @Override
    public UserInfoDo queryUserInfo(String enterpriseAccount, final Long fsUserId) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("enterpriseAccount", enterpriseAccount);
        params.put("fsUserId", fsUserId);
        return this.getUnique("queryUserInfo", params);
    }

    @Override
    public Pager<UserInfoDo> queryUserInfo(Pager<UserInfoDo> page,
            UserInfoDo userInfoDo) {
        if (userInfoDo != null) {
            if (StringUtils.isNotBlank(userInfoDo.getEnterpriseAccount())) {
                page.addParam("enterpriseAccount", userInfoDo.getEnterpriseAccount());
            }
            if (userInfoDo.getFsUserId() != null) {
                page.addParam("fsUserId", userInfoDo.getFsUserId());
            }
            if (userInfoDo.getStatus() != null) {
                page.addParam("status", userInfoDo.getStatus().getIndex());
            }
            if (userInfoDo.getType() != null) {
                page.addParam("type", userInfoDo.getType());
            }
            if (StringUtils.isNotBlank(userInfoDo.getUserName())) {
                page.addParam("userName", userInfoDo.getUserName());
            }
        }
        return this.queryPage("queryUserInfoAccount", "queryUserInfoPage", page);
    }

    @Override
    public int updatePassword(String enterpriseAccount, Long fsUserId,
            String oldPassword, String newPassword) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        params.put("enterpriseAccount", enterpriseAccount);
        params.put("fsUserId", fsUserId);
        params.put("oldPassword", oldPassword);
        params.put("newPassword", newPassword);
        return this.update("updateUserInfo", params);
    }
}
