package com.facishare.pay.business.dao;

import java.util.List;

import com.facishare.pay.business.model.UserCardInfoDo;


/**
 * 用户银行卡绑定信息
 * @author guom
 * @date 2015/10/13
 */
public interface UserCardInfoDAO {

    /**
     * 新增绑定银行卡
     * @param UserCardInfoDo userCardInfoDo 绑定信息
     * @return int 结果集
     * */
    int addUserCardInfo(UserCardInfoDo userCardInfoDo);
    
    /**
     * 取消绑定  修改状态 绑定银行卡状态 1 可用（绑定） 2 不可用（解绑）
     * @param Long userInfoId 用户id
     * @param String cardNo 卡号
     * @return int 结果集
     * */
    int unBoundUserCardInfo(final Long userInfoId, final String cardNo);
    
    /**
     * 查询用户绑定银行卡
     * @param Long userInfoId 用户id
     * @param String bankNo 银行卡账户
     * @return List<UserCardInfoDo> 结果集
     * */
    List<UserCardInfoDo> queryUserCardInfo(final Long userInfoId, final String cardNo);
    
    /**
     * 查询用户绑定银行卡
     * @param Long id 
     * @return UserCardInfoDo 结果集
     * */
    UserCardInfoDo queryUserCardInfoById(final Long id);
    
}
