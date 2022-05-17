package com.facishare.pay.business.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.business.model.UserChargeDo;
import com.facishare.pay.business.model.vo.ChargeSearchVO;
import com.facishare.pay.business.model.vo.UpdateChargeStatusVO;
import com.facishare.pay.business.model.vo.UserChargeVO;
import com.facishare.pay.common.result.ModelResult;

/**
 * 充值流水
 * @author guom
 * @date 2015/10/13
 */
public interface UserChargeService {

    /**
     * 查询用户充值流水
     * @param  page 分页标签
     * @param  chargeSearchVO 查询条件
     * @return ModelResult<Pager<UserChargeLogDo>> 结果集
     * */
    ModelResult<Pager<UserChargeDo>> queryUserCharge(Pager<UserChargeDo> page, final ChargeSearchVO chargeSearchVO);


    /**
     * 用户充值请求
     * @param userChargeVO
     * @return
     */
    ModelResult<UserChargeDo> charge(UserChargeVO userChargeVO);


    /**
     * 第三方响应成功，修改充值状态
     * @param updateChargeStatusVO
     * @return
     */
    ModelResult<Boolean> updateChargeStatus(UpdateChargeStatusVO updateChargeStatusVO);

    /**
     * 获取查询状态
     * @param orderNo
     * @return
     */
    ModelResult<UserChargeDo> getChargeByOrderNo(String orderNo);
    
    /**
     * 修改充值状态（后台使用）
     * @param Long id
     * @param ChargeStatus status
     * @return ModelResult<Boolean>
     * */
    ModelResult<Boolean> updateChargeStatus(Long id, ChargeStatus status);

}
