package com.facishare.pay.bill.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.dao.BillResultDetailDAO;
import com.facishare.pay.bill.dao.base.BillBaseDAO;
import com.facishare.pay.bill.model.BillResultDetailDo;

@Service
public class BillResultDetailDaoImpl extends BillBaseDAO<BillResultDetailDo> implements BillResultDetailDAO {

    @Override
    public int addBillResultDetail(BillResultDetailDo billResultDetailDo) {
        return this.save("addBillResultDetail", billResultDetailDo);
    }

    @Override
    public Pager<BillResultDetailDo> queryBillResultDetail(
            Pager<BillResultDetailDo> page, String enterpriseAccount,
            Long fsUserId) {
        if (StringUtils.isNotBlank(enterpriseAccount)) {
            page.addParam("enterpriseAccount", enterpriseAccount);
        }
        if (fsUserId != null) {
            page.addParam("fsUserId", fsUserId);
        }
        return this.queryPage("queryBillResultDetailCount", "queryBillResultDetailPage", page);
    }

    @Override
    public BillResultDetailDo queryBillResultDetail(String enterpriseAccount,
            Long fsUserId) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("enterpriseAccount", enterpriseAccount);
        params.put("fsUserId", fsUserId);
        return this.getUnique("queryBillResultDetail", params);
    }

    @Override
    public int updateBillResultDetail(BillResultDetailDo billResultDetailDo) {
        return this.update("updateBillResultDetail", billResultDetailDo);
    }

    @Override
    public int deleteBillResultDetail(Calendar beginTime, Calendar endTime) {
        if (beginTime == null || endTime == null) {
            return 0;
        }
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        return this.delete("deleteBillResultDetail", params);
    }
}
