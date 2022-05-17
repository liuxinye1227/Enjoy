package com.facishare.pay.business.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.dao.UserInfoDAO;
import com.facishare.pay.business.model.UserInfoDo;
import com.facishare.pay.business.service.UserInfoService;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.common.utils.MD5Util;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    public static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoDAO userInfoDao;
    
    @Override
    public ModelResult<Boolean> updateUserInfo(UserInfoDo userInfoDo) {
        /* 1.用户企业号或者用户id为空不通过
         * 2.没有要修改的字段不通过
         *  */
        if (StringUtils.isBlank(userInfoDo.getEnterpriseAccount()) || userInfoDo.getFsUserId() == null
                || (StringUtils.isBlank(userInfoDo.getUserName()) 
                         && userInfoDo.getStatus() == null 
                         && StringUtils.isBlank(userInfoDo.getFeatures())
                         && userInfoDo.getType() == null)) {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), false);
        }
        try {
            int result = userInfoDao.updateUserInfo(userInfoDo);
            if (result == 1) {
                return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(), true);
            }
            return new ModelResult<Boolean>(BusinessCodeEnum.UNEXIS_ERROR.getErrorCode(), BusinessCodeEnum.UNEXIS_ERROR.getErrorMessage(), false);
        } catch(Exception e) {
            logger.error("userInfoDo: {}, 系统异常: {}", userInfoDo.toString(),e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), false);
        }
    }

    @Override
    public ModelResult<Boolean> addUserInfo(UserInfoDo userInfoDo) {
        /**
         * 查询是否存着该用户
         * */
        if (!userInfoDo.validateParamNotNull()) {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), false);
        }
        try {
            UserInfoDo result = userInfoDao.queryUserInfo(userInfoDo.getEnterpriseAccount(), userInfoDo.getFsUserId());
            if (result == null) {
                // 二次加密
                userInfoDo.setPassword(MD5Util.MD5Encode(userInfoDo.getPassword(), "utf-8"));
                return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(), 
                        userInfoDao.addUserInfo(userInfoDo) == 1 ? true : false); 
            } else {
                return new ModelResult<Boolean>(BusinessCodeEnum.ADD_EXIST_USER.getErrorCode(), BusinessCodeEnum.ADD_EXIST_USER.getErrorMessage(), false);
            }
        } catch(Exception e) {
            logger.error("userInfoDo: {}, 系统异常: {}", userInfoDo.toString(),e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), false);
        }
    }

    @Override
    public ModelResult<Pager<UserInfoDo>> queryUserInfo(Pager<UserInfoDo> page,
            UserInfoDo userInfoDo) {
        try {
            return new ModelResult<Pager<UserInfoDo>>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(), 
                    userInfoDao.queryUserInfo(page, userInfoDo)); 
        } catch(Exception e) {
            logger.error("userInfoDo: {}, 系统异常: {}", userInfoDo.toString(),e);
            return new ModelResult<Pager<UserInfoDo>>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<Boolean> updateUserInfo(String enterpriseAccount,
            Long fsUserId, String oldPassword, String newPassword) {
        try {
            if (StringUtils.isBlank(enterpriseAccount)
                    || fsUserId == null
                    || StringUtils.isBlank(oldPassword)
                    || StringUtils.isBlank(newPassword)) {
                return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), false);
            }
            return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode(), BusinessCodeEnum.SUCCESS.getErrorMessage(), 
                    userInfoDao.updatePassword(enterpriseAccount, fsUserId
                            , MD5Util.MD5Encode(oldPassword, "utf-8")
                            , MD5Util.MD5Encode(newPassword, "utf-8")) == 1 ? true : false); 
        } catch(Exception e) {
            logger.error("系统异常: {}", e);
            return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(), BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), false);
        }
    }


}
