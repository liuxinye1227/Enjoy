package com.facishare.pay.business.dao;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.business.model.UserGetMoneyDo;
import com.facishare.pay.business.model.vo.GetMoneySearchVO;
import com.facishare.pay.business.model.vo.GetMoneyUpdateVO;

/**
 * 提现流水
 * @author guom
 * @date 2015/10/13
 */
public interface UserGetMoneyDAO {

    /**
     * 查询用户提现流水
     * @param Pager<UserGetMoneyDo> page 分页标签
     * @param GetMoneySearchVO userGetMoneyLogVo查询条件
     * @return Pager<UserGetMoneyDo>结果集
     * */
    Pager<UserGetMoneyDo> queryUserGetMoney(Pager<UserGetMoneyDo> page, final GetMoneySearchVO getMoneySearchVo);
    
    /**
     * 生成用户提现流水
     * @param UserGetMoneyDo userGetMoneyDo 分页标签
     * @return int
     * */
    int addUserGetMoney(UserGetMoneyDo userGetMoneyDo);
    
    /**
     * 更新提现订单
     * @param GetMoneyUpdateVO getMoneyUpdateVO
     * @return int
     * */
    int updateUserGetMoney(GetMoneyUpdateVO getMoneyUpdateVO);
    
    /**
     * 查询用户提现流水
     * @param Long id
     * @return UserGetMoneyDo
     * */
    UserGetMoneyDo queryUserGetMoneyById(final Long id);
    
    /**
     * 查询用户提现流水
     * @param String orderNo
     * @return UserGetMoneyDo
     * */
    UserGetMoneyDo queryUserGetMoneyByOrderNo(final String orderNo);
    
    /**
     * 修改用户提现状态 （后台使用）
     * @param Long id
     * @param GetMoneyStatus status
     * @return Boolean
     * */
    boolean updateGetMoneyStatus(Long id, GetMoneyStatus status);
    
}
