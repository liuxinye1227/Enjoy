package com.facishare.pay.bill.dao.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.dao.BillResultDAO;
import com.facishare.pay.bill.dao.base.BillBaseDAO;
import com.facishare.pay.bill.model.BillResultDo;

@Service
public class BillResultDaoImpl extends BillBaseDAO<BillResultDo> implements BillResultDAO {

    @Override
    public int addBillResult(BillResultDo billResultDo) {
        return this.save("addBillResult", billResultDo);
    }

    @Override
    public Pager<BillResultDo> queryBillResult(Pager<BillResultDo> page,
            Calendar beginTime, Calendar endTime) {
        if (beginTime != null) {
            page.addParam("beginTime", beginTime);
        }
        if (endTime != null) {
            page.addParam("endTime", endTime);
        }
        return this.queryPage("queryBillResultCount", "queryBillResultPage", page);
    }

}
