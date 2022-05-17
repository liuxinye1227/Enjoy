package com.facishare.pay.business.service;

import java.util.List;

import com.facishare.pay.business.model.UserCardInfoDo;
import com.facishare.pay.common.result.ModelResult;

/**
 * 用户银行卡绑定
 * @author guom
 * @date 2015/10/16
 */
public interface UserCardInfoService {

    /**
     * 新增绑定银行卡
     * @param UserCardInfoDo userCardInfoDo 绑定信息
     * @return ModelResult<Boolean> 结果集
     * */
    ModelResult<Boolean> addUserCardInfo(UserCardInfoDo userCardInfoDo);
    
    /**
     * 查询用户绑定银行卡
     * @param Long userInfoId 查询条件
     * @return ModelResult<List<UserCardInfoDo>> 结果集
     * */
    ModelResult<List<UserCardInfoDo>> queryUserCardInfoByUserInfoId(final Long userInfoId);
    
    /**
     * 取消绑定
     * @param Long userInfoId 用户id
     * @param String cardNo 卡号
     * @return ModelResult<Boolean> 结果集
     * */
    ModelResult<Boolean> unBoundUserCardInfo(final Long userInfoId, final String cardNo);
    
}
