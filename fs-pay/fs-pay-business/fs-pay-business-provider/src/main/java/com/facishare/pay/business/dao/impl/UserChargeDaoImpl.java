package com.facishare.pay.business.dao.impl;

import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.constant.ChargeStatus;
import com.facishare.pay.business.exception.PayBusinessExcrption;
import com.facishare.pay.business.model.vo.UpdateChargeStatusVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.dao.UserChargeDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.UserChargeDo;
import com.facishare.pay.business.model.vo.ChargeSearchVO;

import java.util.HashMap;
import java.util.Map;



/**
 * 用户充值流水查询
 * @author guom
 * @date 2015/10/13
 */
@Service
public class UserChargeDaoImpl extends BusinessBaseDAO<UserChargeDo> implements UserChargeDAO{

    @Override
    public Pager<UserChargeDo> queryUserCharge(
            Pager<UserChargeDo> page, final ChargeSearchVO chargeSearchVo) {
        if (chargeSearchVo != null) {
            if (StringUtils.isNotBlank(chargeSearchVo.getChargeNo())) {
                page.addParam("chargeNo", chargeSearchVo.getChargeNo());
            }
            if (StringUtils.isNotBlank(chargeSearchVo.getEnterpriseAccount())) {
                page.addParam("enterpriseAccount", chargeSearchVo.getEnterpriseAccount());
            }
            if (StringUtils.isNotBlank(chargeSearchVo.getOrderNo())) {
                page.addParam("orderNo", chargeSearchVo.getOrderNo());
            }
            if (chargeSearchVo.getChannelId() != null) {
                page.addParam("channelId", chargeSearchVo.getChannelId());
            }
            if (chargeSearchVo.getChargeWay() != null) {
                page.addParam("chargeWay", chargeSearchVo.getChargeWay());
            }
            if (chargeSearchVo.getFsUserId() != null) {
                page.addParam("fsUserId", chargeSearchVo.getFsUserId());
            }
            if (chargeSearchVo.getMaxAmount() != null) {
                page.addParam("maxAmount", chargeSearchVo.getMaxAmount().doubleValue());
            }
            if (chargeSearchVo.getMinAmount() != null) {
                page.addParam("minAmount", chargeSearchVo.getMinAmount().doubleValue());
            }
            if (chargeSearchVo.getRequestBeginTime() != null) {
                page.addParam("requestBeginTime", chargeSearchVo.getRequestBeginTime());
            }
            if (chargeSearchVo.getRequestEndTime() != null) {
                page.addParam("requestEndTime", chargeSearchVo.getRequestEndTime());
            }
            if (chargeSearchVo.getResponseBeginTime() != null) {
                page.addParam("responseBeginTime", chargeSearchVo.getResponseBeginTime());
            }
            if (chargeSearchVo.getResponseEndTime() != null) {
                page.addParam("responseEndTime", chargeSearchVo.getResponseEndTime());
            }
            if (chargeSearchVo.getStatus() != null) {
                page.addParam("status", chargeSearchVo.getStatus().getIndex());
            }
        }
        return this.queryPage("queryUserChargeCount", "queryUserChargePage", page);
    }

    @Override
    public UserChargeDo addUserCharge(UserChargeDo userChargeDo) {

        int result = this.save("addUserCharge", userChargeDo);
        if (result == 1) {
            return  userChargeDo;
        }
        return null;
    }

    @Override
    public UserChargeDo getUserChargeByOrderNoAndStatus(UserChargeDo userChargeDo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", userChargeDo.getOrderNo());
        map.put("status", userChargeDo.getStatus());

        return this.getUnique("getUserChargeByOrderNoAndStatus", map);
    }

    @Override
    public UserChargeDo getUserChargeByOrderNo(String orderNo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderNo", orderNo);

        return this.getUnique("getUserChargeByOrderNo", map);
    }

    @Override
    public boolean updateUserCharge(UpdateChargeStatusVO updateChargeStatusVO) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (updateChargeStatusVO == null) {
            throw new PayBusinessExcrption(BusinessCodeEnum.PARAMS_ERROR.getErrorCode(),
                BusinessCodeEnum.PARAMS_ERROR.getErrorMessage());
        }
        map.put("orderNo", updateChargeStatusVO.getOrderNo());
        map.put("chargetPayNo", updateChargeStatusVO.getChargePayNo());
        map.put("chargeStatus", updateChargeStatusVO.getChargeStatus());
        map.put("failReturnUrl", updateChargeStatusVO.getFailReturnUrl());
        map.put("responseInfo", updateChargeStatusVO.getResponseInfo());
        map.put("responseTime", updateChargeStatusVO.getResponseTime());
        map.put("sucReturnUrl", updateChargeStatusVO.getSucReturnUrl());
        map.put("transType", updateChargeStatusVO.getTransType());
        return this.update("updateUserCharge", map) == 1;
    }

    @Override
    public boolean updateChargeStatus(Long id, ChargeStatus status) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("id", id);
        params.put("status", status.getIndex());
        return this.update("updateChargeStatus", params) == 1;
    }
}
