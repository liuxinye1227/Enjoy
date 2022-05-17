package com.facishare.pay.business.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.business.model.UserGetMoneyDo;
import com.facishare.pay.business.model.result.GetMoneyResult;
import com.facishare.pay.business.model.result.GetMoneyUpdateResult;
import com.facishare.pay.business.model.vo.GetMoneySearchVO;
import com.facishare.pay.business.model.vo.GetMoneyUpdateVO;
import com.facishare.pay.business.model.vo.UserGetMoneyVO;
import com.facishare.pay.common.result.ModelResult;

/**
 * 提现流水
 * @author guom
 * @date 2015/10/13
 */
public interface UserGetMoneyService {

    /**
     * 查询用户提现流水
     * @param Pager<UserGetMoneyLogDo> page 分页标签
     * @param GetMoneySearchVO userGetMoneyLogVo查询条件
     * @return ModelResult<Pager<UserGetMoneyLogDo>>结果集
     * */
    ModelResult<Pager<UserGetMoneyDo>> queryUserGetMoney(Pager<UserGetMoneyDo> page, final GetMoneySearchVO userGetMoneyLogVo);


    /**
     * 提现申请   申请出错只能重做
     * @param userGetMoneyVO
     * @return
     */
    ModelResult<GetMoneyResult> getMoneyApply(UserGetMoneyVO userGetMoneyVO);


    /**
     * 提现审核 修改状态
     * @param getMoneyUpdateVO
     * @return
     */
    ModelResult<GetMoneyUpdateResult> updateGetMoneyAuditor(GetMoneyUpdateVO getMoneyUpdateVO);
    
    /**
     * 提现出纳 修改状态
     * @param getMoneyUpdateVO
     * @return
     */
    ModelResult<GetMoneyUpdateResult> updateGetMoneyTeller(GetMoneyUpdateVO getMoneyUpdateVO);
    
    /**
     * 已提现 修改状态
     * @param getMoneyUpdateVO
     * @return
     */
    ModelResult<GetMoneyUpdateResult> updateGetMoneyFinish(GetMoneyUpdateVO getMoneyUpdateVO);
    
    /**
     * 查询用户提现流水
     * @param Long id
     * @return ModelResult<UserGetMoneyDo>
     * */
    ModelResult<UserGetMoneyDo> queryUserGetMoneyById(Long id);

    /**
     * 查询用户提现流水
     * @param String orderNo
     * @return ModelResult<UserGetMoneyDo>
     * */
    ModelResult<UserGetMoneyDo> queryUserGetMoneyByOrderNo(String orderNo);
    
    /**
     * 修改用户提现状态 （后台使用）
     * @param Long id
     * @param GetMoneyStatus status
     * @return ModelResult<Boolean>
     * */
    ModelResult<Boolean> updateGetMoney(Long id, GetMoneyStatus status);
    
}
