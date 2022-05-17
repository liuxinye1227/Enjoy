package com.facishare.pay.business.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bank.model.UserWalletDO;
import com.facishare.pay.bank.model.UserWalletLogDO;
import com.facishare.pay.bank.model.vo.UserWalletLogVO;
import com.facishare.pay.common.result.ModelResult;


/**
 * 用户钱包信息
 * @author guom
 * @date 2015/11/2
 */
public interface UserWalletService {

    /**
     * 查询用户钱包
     * @param final String enterpriseAccount 企业号（必填）
     * @param final Long fsUserId （必填）
     * @return ModelResult<UserWalletDO>结果集
     * */
    ModelResult<UserWalletDO> queryUserWallet(final String enterpriseAccount, final Long fsUserId);
    
    /**
     * 查询用户钱包流水
     * @param Pager<UserWalletLogDO> page
     * @param Pager<UserWalletLogDO> page
     * @return ModelResult<Pager<UserWalletLogDO>>
     * */
    ModelResult<Pager<UserWalletLogDO>> queryUserWallet(Pager<UserWalletLogDO> page, UserWalletLogVO userWalletLogVO);
    
}
