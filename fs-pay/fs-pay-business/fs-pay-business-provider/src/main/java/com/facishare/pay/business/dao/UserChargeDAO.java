package com.facishare.pay.business.dao;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.business.model.UserChargeDo;
import com.facishare.pay.business.model.vo.ChargeSearchVO;
import com.facishare.pay.business.model.vo.UpdateChargeStatusVO;

/**
 * 充值流水
 * @author guom
 * @date 2015/10/13
 */
public interface UserChargeDAO {

    /**
     * 查询用户充值流水
     * @param page 分页标签
     * @param chargeSearchVo 查询条件
     * @return Pager<UserChargeDo> 结果集
     * */
    Pager<UserChargeDo> queryUserCharge(Pager<UserChargeDo> page, final ChargeSearchVO chargeSearchVo);

    /**
     * 添加用户充值流水
     * @param userChargeDo 用户充值流水
     * @return
     */
    UserChargeDo addUserCharge(UserChargeDo userChargeDo);

    /**
     * 查询用户充值流水
     * @param userChargeDo
     * @return
     */
    UserChargeDo getUserChargeByOrderNoAndStatus(UserChargeDo userChargeDo);

    /**
     * 查询用户充值流水
     * @param orderNo
     * @return
     */
    UserChargeDo getUserChargeByOrderNo(String orderNo);

    /**
     * 更新用户充值流水
     * @param updateChargeStatusVO 修改充值流水状态
     * @return
     */
    boolean updateUserCharge(UpdateChargeStatusVO updateChargeStatusVO);
    
    /**
     * 修改充值状态（后台使用）
     * @param Long id
     * @param ChargeStatus status
     * @return boolean
     * */
    boolean updateChargeStatus(Long id, ChargeStatus status);
}
