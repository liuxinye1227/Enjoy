package com.facishare.pay.bill.dao.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.dao.BillLogDAO;
import com.facishare.pay.bill.dao.base.BillBaseDAO;
import com.facishare.pay.bill.model.BillLogDo;
import com.facishare.pay.bill.model.BillLogResultDo;
import com.facishare.pay.bill.model.vo.BillLogVO;

@Service
public class BillLogDaoImpl extends BillBaseDAO implements BillLogDAO {

    @Override
    public int addBillLog(BillLogDo billLogDo) {
        return this.save("addBillLog", billLogDo);
    }

    /**
     * 内部对账用，企业号，用户号，起止时间必填
     * */
    @Override
    public List<BillLogDo> queryBillLog(BillLogVO billLogVo) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (billLogVo.getFsUserId() != null) {
            params.put("fsUserId", billLogVo.getFsUserId());
        }
        if (StringUtils.isNotBlank(billLogVo.getEnterpriseAccount())) {
            params.put("enterpriseAccount", billLogVo.getEnterpriseAccount());
        }
        if (billLogVo.getBeginTime() != null) {
            params.put("beginTime", billLogVo.getBeginTime());
        }
        if (billLogVo.getEndTime() != null) {
            params.put("endTime", billLogVo.getEndTime());
        }
        return this.getList("queryBillLog", params);
    }

    @Override
    public Pager<BillLogResultDo> queryBillLogResult(Pager<BillLogResultDo> page, Calendar beginTime, Calendar endTime) {
        page.addParam("beginTime", beginTime);
        page.addParam("endTime", endTime);
        return this.queryPage("accountBillLogResultCount", "accountBillLogResult", page);
    }

}
