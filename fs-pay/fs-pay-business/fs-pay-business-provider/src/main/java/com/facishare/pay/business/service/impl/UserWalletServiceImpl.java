package com.facishare.pay.business.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bank.model.UserWalletDO;
import com.facishare.pay.bank.model.UserWalletLogDO;
import com.facishare.pay.bank.model.vo.UserVO;
import com.facishare.pay.bank.model.vo.UserWalletLogVO;
import com.facishare.pay.bank.service.UserWalletReadService;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.service.UserWalletService;
import com.facishare.pay.common.result.ModelResult;

@Service
public class UserWalletServiceImpl implements UserWalletService {

    public static final Logger logger = LoggerFactory.getLogger(UserWalletServiceImpl.class);
    
    @Autowired
    UserWalletReadService userWalletReadService;
    
    @Override
    public ModelResult<UserWalletDO> queryUserWallet(String enterpriseAccount,
            Long fsUserId) {
        if (StringUtils.isBlank(enterpriseAccount) || fsUserId == null) {
            return new ModelResult<UserWalletDO>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(), BusinessCodeEnum.PARAMS_ERROR.getErrorMessage());
        }
        UserVO userVO = new UserVO();
        userVO.setEnterpriseAccount(enterpriseAccount);
        userVO.setFsUserId(fsUserId);
        return userWalletReadService.getUserWalletById(userVO);
    }

    @Override
    public ModelResult<Pager<UserWalletLogDO>> queryUserWallet(
            Pager<UserWalletLogDO> page, UserWalletLogVO userWalletLogVO) {
        if (page == null || userWalletLogVO == null || StringUtils.isBlank(userWalletLogVO.getEnterpriseAccount())) {
            return new ModelResult<>(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(),
                    BusinessCodeEnum.PARAMS_ERROR.getErrorMessage());
        }
        return userWalletReadService.queryUserWalletLogList(page, userWalletLogVO);
    }

}
