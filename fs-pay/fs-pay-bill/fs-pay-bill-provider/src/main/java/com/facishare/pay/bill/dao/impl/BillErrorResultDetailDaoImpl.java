package com.facishare.pay.bill.dao.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.dao.BillErrorResultDetailDAO;
import com.facishare.pay.bill.dao.base.BillBaseDAO;
import com.facishare.pay.bill.model.BillErrorResultDetailDo;
import com.facishare.pay.bill.model.vo.BillErrorResultVO;

@Service
public class BillErrorResultDetailDaoImpl extends BillBaseDAO<BillErrorResultDetailDo> implements BillErrorResultDetailDAO {

    @Override
    public int addBillErrorResultDetail(
            BillErrorResultDetailDo billErrorResultDetailDo) {
        return this.save("addBillErrorResultDetail", billErrorResultDetailDo);
    }

    @Override
    public Pager<BillErrorResultDetailDo> queryBillErrorResultDetail(
            Pager<BillErrorResultDetailDo> page,
            BillErrorResultVO billErrorResultVo) {
        if (billErrorResultVo != null) {
            if (StringUtils.isNotBlank(billErrorResultVo.getEnterpriseAccount())) {
                page.addParam("enterpriseAccount", billErrorResultVo.getEnterpriseAccount());
            }
            if (billErrorResultVo.getStatus() != null) {
                page.addParam("status", billErrorResultVo.getStatus().getIndex());
            }
            if (billErrorResultVo.getFsUserId() != null) {
                page.addParam("fsUserId", billErrorResultVo.getFsUserId());
            }
            if (billErrorResultVo.getBeginTime() != null) {
                page.addParam("beginTime", billErrorResultVo.getBeginTime());
            }
            if (billErrorResultVo.getEndTime() != null) {
                page.addParam("endTime", billErrorResultVo.getEndTime());
            }
        }
        return this.queryPage("queryBillErrorResultDetailCount", "queryBillErrorResultDetailPage", page);
    }

    @Override
    public int updateBillErrorResultDetail(BillErrorResultDetailDo billErrorResultDetailDo) {
        return this.update("updateBillErrorResultDetail", billErrorResultDetailDo);
    }


}
