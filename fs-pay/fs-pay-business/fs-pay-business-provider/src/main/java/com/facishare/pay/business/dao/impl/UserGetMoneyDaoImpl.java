package com.facishare.pay.business.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.GetMoneyStatus;
import com.facishare.pay.business.dao.UserGetMoneyDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.UserGetMoneyDo;
import com.facishare.pay.business.model.vo.GetMoneySearchVO;
import com.facishare.pay.business.model.vo.GetMoneyUpdateVO;



/**
 * 用户提现流水查询
 * @author guom
 * @date 2015/10/13
 */
@Service
public class UserGetMoneyDaoImpl extends BusinessBaseDAO<UserGetMoneyDo> implements UserGetMoneyDAO {

    @Override
    public Pager<UserGetMoneyDo> queryUserGetMoney(
            Pager<UserGetMoneyDo> page, final GetMoneySearchVO getMoneySearchVo) {
        if (getMoneySearchVo != null) {
            if (StringUtils.isNotBlank(getMoneySearchVo.getAuditor())) {
                page.addParam("auditor", getMoneySearchVo.getAuditor());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getBusiTradeNo())) {
                page.addParam("busiTradeNo", getMoneySearchVo.getBusiTradeNo());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getCity())) {
                page.addParam("city", getMoneySearchVo.getCity());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getEnterpriseAccount())) {
                page.addParam("enterpriseAccount", getMoneySearchVo.getEnterpriseAccount());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getInvoice())) {
                page.addParam("invoice", getMoneySearchVo.getInvoice());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getOrderNo())) {
                page.addParam("orderNo", getMoneySearchVo.getOrderNo());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getProvince())) {
                page.addParam("province", getMoneySearchVo.getProvince());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getBankBranchName())) {
                page.addParam("bankBranchName", getMoneySearchVo.getBankBranchName());
            }
            if (StringUtils.isNotBlank(getMoneySearchVo.getTeller())) {
                page.addParam("teller", getMoneySearchVo.getTeller());
            }
            if (getMoneySearchVo.getBankType() != null) {
                page.addParam("bankNo", getMoneySearchVo.getBankType().getIndex());
            }
            if (getMoneySearchVo.getBeginTime() != null) {
                page.addParam("beginTime", getMoneySearchVo.getBeginTime());
            }
            if (getMoneySearchVo.getEndTime() != null) {
                page.addParam("endTime", getMoneySearchVo.getEndTime());
            }
            if (getMoneySearchVo.getOperBeginTime() != null) {
                page.addParam("operBeginTime", getMoneySearchVo.getOperBeginTime());
            }
            if (getMoneySearchVo.getOperEndTime() != null) {
                page.addParam("operEndTime", getMoneySearchVo.getOperEndTime());
            }
            if (getMoneySearchVo.getFsUserId() != null) {
                page.addParam("fsUserId", getMoneySearchVo.getFsUserId());
            }
            if (getMoneySearchVo.getMaxAmount() != null) {
                page.addParam("maxAmount", getMoneySearchVo.getMaxAmount().doubleValue());
            }
            if (getMoneySearchVo.getMinAmount() != null) {
                page.addParam("minAmount", getMoneySearchVo.getMinAmount().doubleValue());
            }
            if (getMoneySearchVo.getStatus() != null) {
                page.addParam("status", getMoneySearchVo.getStatus().getIndex());
            }
        }
        return this.queryPage("queryUserGetMoneyCount", "queryUserGetMoneyPage", page);
    }

    @Override
    public int addUserGetMoney(UserGetMoneyDo userGetMoneyDo) {
        return this.save("addUserGetMoney", userGetMoneyDo);
    }

    @Override
    public int updateUserGetMoney(GetMoneyUpdateVO getMoneyUpdateVO) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (getMoneyUpdateVO != null) {
            params.put("orderNo", getMoneyUpdateVO.getOrderNo());
            if (getMoneyUpdateVO.getGetMoneyStatus() != null) {
                params.put("status", getMoneyUpdateVO.getGetMoneyStatus().getIndex());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getAuditor())) {
                params.put("auditor", getMoneyUpdateVO.getAuditor());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getBatchId())) {
                params.put("batchId", getMoneyUpdateVO.getBatchId());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getBatchNo())) {
                params.put("batchNo", getMoneyUpdateVO.getBatchNo());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getBusiTradeNo())) {
                params.put("busiTradeNo", getMoneyUpdateVO.getBusiTradeNo());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getFailReason())) {
                params.put("failReason", getMoneyUpdateVO.getFailReason());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getInvoice())) {
                params.put("invoice", getMoneyUpdateVO.getInvoice());
            }
            if (StringUtils.isNotBlank(getMoneyUpdateVO.getTeller())) {
                params.put("teller", getMoneyUpdateVO.getTeller());
            }
            params.put("operTime", getMoneyUpdateVO.getOperTime() == null ? Calendar.getInstance() : getMoneyUpdateVO.getOperTime());
            return this.update("updateUserGetMoneyStatus", params);
        }
        return 0;
        
    }

    @Override
    public UserGetMoneyDo queryUserGetMoneyById(Long id) {
        return this.get("queryUserGEtMoneyById", id);
    }

    @Override
    public UserGetMoneyDo queryUserGetMoneyByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return null;
        }
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("orderNo", orderNo);
        return this.getUnique("queryUserGetMoneyByOrderNo", params);
    }

    @Override
    public boolean updateGetMoneyStatus(Long id, GetMoneyStatus status) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("id", id);
        params.put("status", status.getIndex());
        return this.update("updateGetMoneyStatus", params) == 1;
    }

}
