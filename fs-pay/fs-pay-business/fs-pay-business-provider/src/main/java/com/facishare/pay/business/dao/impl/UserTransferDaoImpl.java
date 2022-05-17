package com.facishare.pay.business.dao.impl;

import com.facishare.pay.business.constant.TransferStatus;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.dao.UserTransferDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.UserTransferDo;
import com.facishare.pay.business.model.vo.TransferSearchVO;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;



/**
 * 用户转账流水查询
 * @author guom
 * @date 2015/10/13
 */
@Service
public class UserTransferDaoImpl extends BusinessBaseDAO<UserTransferDo> implements UserTransferDAO{

    @Override
    public Pager<UserTransferDo> queryUserTransfer(
            Pager<UserTransferDo> page, final TransferSearchVO transferSearchVo) {
        if (transferSearchVo != null) {
            if (StringUtils.isNotBlank(transferSearchVo.getOrderNo())) {
                page.addParam("orderNo", transferSearchVo.getOrderNo());
            }
            if (transferSearchVo.getBeginTime() != null) {
                page.addParam("beginTime", transferSearchVo.getBeginTime());
            }
            if (transferSearchVo.getEndTime() != null) {
                page.addParam("endTime", transferSearchVo.getEndTime());
            }
            if (StringUtils.isNotBlank(transferSearchVo.getFromEnterpriseAccount())) {
                page.addParam("fromEnterpriseAccount", transferSearchVo.getFromEnterpriseAccount());
            }
            if (transferSearchVo.getFromUserId() != null) {
                page.addParam("fromUserId", transferSearchVo.getFromUserId());
            }
            if (StringUtils.isNotBlank(transferSearchVo.getToEnterpriseAccount())) {
                page.addParam("toEnterpriseAccount", transferSearchVo.getToEnterpriseAccount());
            }
            if (transferSearchVo.getToUserId() != null) {
                page.addParam("toUserId", transferSearchVo.getToUserId());
            }
            if (transferSearchVo.getMaxAmount() != null) {
                page.addParam("maxAmount", transferSearchVo.getMaxAmount().doubleValue());
            }
            if (transferSearchVo.getMinAmount() != null) {
                page.addParam("minAmount", transferSearchVo.getMinAmount().doubleValue());
            }
            if (transferSearchVo.getStatus() != null) {
                page.addParam("status", transferSearchVo.getStatus().getIndex());
            }
        }
        return this.queryPage("queryUserTransferCount", "queryUserTransferPage", page);
    }

    @Override
    public UserTransferDo addUserTransferDo(UserTransferDo userTransferDo) {

        this.save("addUserTransferDo", userTransferDo);
        return userTransferDo;
    }

    @Override
    public boolean updateUserTransfer(String orderNo, TransferStatus status, Calendar updateTime,
        TransferStatus oldStatus) {
        Map<String, Object> map = new HashMap<String, Object>(4);
        map.put("orderNo", orderNo);
        map.put("status", status);
        map.put("updateTime",updateTime);
        map.put("oldStatus", oldStatus);
        return this.update("updateUserTransfer", map) == 1;
    }

    @Override
    public UserTransferDo queryUserTransferByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("orderNo", orderNo);
        return this.getUnique("queryUserTransferByOrderNo", params);
    }

    @Override
    public boolean updateTransferStatus(Long id, TransferStatus status) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("id", id);
        params.put("status", status.getIndex());
        return this.update("updateTransferStatus", params) == 1;
    }


}
