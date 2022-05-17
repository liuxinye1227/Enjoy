package com.facishare.pay.business.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.dao.UserCardInfoDAO;
import com.facishare.pay.business.model.UserCardInfoDo;
import com.facishare.pay.business.service.UserCardInfoService;
import com.facishare.pay.common.result.ModelResult;

@Service
public class UserCardInfoServiceImpl implements UserCardInfoService {
    
    public static final Logger logger = LoggerFactory.getLogger(UserCardInfoServiceImpl.class);

    @Autowired
    private UserCardInfoDAO userCardInfoDAO;

    @Override
    public ModelResult<Boolean> addUserCardInfo(UserCardInfoDo userCardInfoDo) {
        if (userCardInfoDo.validateParamNotNull()) {
            try {
                // 是否已绑定
                List<UserCardInfoDo> list = userCardInfoDAO.queryUserCardInfo(userCardInfoDo.getUserInfoId(), userCardInfoDo.getCardNo());
                if (list == null || list.size() == 0) {
                    userCardInfoDo.setUpdateTime(Calendar.getInstance());
                    return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode()
                            , BusinessCodeEnum.SUCCESS.getErrorMessage(), userCardInfoDAO.addUserCardInfo(userCardInfoDo) == 1 ? true : false);
                } else {
                    return new ModelResult<Boolean>(BusinessCodeEnum.REPEAT_ERROR.getErrorCode()
                            , BusinessCodeEnum.REPEAT_ERROR.getErrorMessage(), false);
                }
            } catch(Exception e) {
                logger.error("UserCardInfoDo: {}, 系统异常: {}", userCardInfoDo.toString(), e);
                return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode()
                        , BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), false);
            }
        } else {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), false);
        }
    }

    @Override
    public ModelResult<List<UserCardInfoDo>> queryUserCardInfoByUserInfoId(
            final Long userInfoId) {
        try {
            return new ModelResult<List<UserCardInfoDo>>(BusinessCodeEnum.SUCCESS.getErrorCode()
                    , BusinessCodeEnum.SUCCESS.getErrorMessage(), userCardInfoDAO.queryUserCardInfo(userInfoId, null));
        } catch(Exception e) {
            logger.error("userInfoId: {}, 系统异常: {}", userInfoId, e);
            return new ModelResult<List<UserCardInfoDo>>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode()
                    , BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }

    @Override
    public ModelResult<Boolean> unBoundUserCardInfo(Long userInfoId,
            String cardNo) {
        if (userInfoId != null && StringUtils.isNotBlank(cardNo)) {
            try {
                return new ModelResult<Boolean>(BusinessCodeEnum.SUCCESS.getErrorCode()
                        , BusinessCodeEnum.SUCCESS.getErrorMessage(), userCardInfoDAO.unBoundUserCardInfo(userInfoId, cardNo) == 1 ? true : false);
            } catch(Exception e) {
                logger.error("userInfoId: {}, cardNo: {}, 系统异常: {}", userInfoId, cardNo, e.getMessage());
                return new ModelResult<Boolean>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode()
                        , BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), false);
            }
        } else {
            return new ModelResult<Boolean>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode()
                    , BusinessCodeEnum.PARAMS_ERROR.getErrorMessage(), false);
        }
    }

}
