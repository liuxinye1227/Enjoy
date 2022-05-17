package com.facishare.open.manage.manager.impl;

import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.WebAuthManager;
import com.facishare.portal.common.ModelResult;
import com.facishare.portal.model.UserDO;
import com.facishare.portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebAuthManagerImpl implements WebAuthManager {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserService userService;

    @Override
    public UserDO loadWebFsUser(String portalUserId, String portalToken) {
        // 获取当前用户信息

        ModelResult<UserDO> modelResult = userService.queryUserById(Long.parseLong(portalUserId));

        if (!modelResult.isSuccess()){
            logger.info("userService.queryUserById , portalUserId[{}], resultUser[{}]", portalUserId, modelResult);
            throw new BizException(AjaxCode.USER_NOT_LOGIN, "验证用户登录信息失败");
        }
        UserDO userDO = modelResult.getResult();
        if (null == userDO){
            throw new BizException(AjaxCode.USER_NOT_LOGIN, "验证用户登录信息失败");
        }
        return userDO;
    }

    @Override
    public UserDO queryUserPermission(UserDO user, Long systemId) {
        ModelResult<UserDO> modelResult = userService.queryUserPermission(user.getId(), systemId);
        if (!modelResult.isSuccess()){
            logger.info("userService.queryUserById , userId[{}], systemId[{}], resultUser[{}]", user.getId(), systemId, modelResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "获取用户菜单权限失败");
        }
        UserDO userDO = modelResult.getResult();
        if (null == userDO){
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "获取用户菜单权限失败");
        }
        return userDO;
    }

    public boolean verifyUserPermission(String portalUserId, Long systemId, String url) {
        ModelResult<Boolean> modelResult = userService.verifyUserPermission(Long.parseLong(portalUserId), systemId, url);
        if (!modelResult.isSuccess()){
            logger.info("verifyUserPermission failed, userId[{}], systemId[{}], url[{}], modelResult[{}]", portalUserId, systemId, url, modelResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "查询用户在该系统是否具有此url的权限失败");
        }
        return modelResult.getResult();
    }

}