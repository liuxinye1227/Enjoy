package com.facishare.pay.bill.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.constant.BillErrorResultStatus;
import com.facishare.pay.bill.model.BillErrorResultDetailDo;
import com.facishare.pay.bill.model.vo.BillErrorResultVO;
import com.facishare.pay.common.result.ModelResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BillErrorResultDetailServiceImplTest {
    
    @Autowired
    private BillErrorResultDetailServiceImpl billErrorResultDetailServiceImpl;

    @Test
    public void testQueryBillErrorResultDetail() {
        Pager<BillErrorResultDetailDo> page = new Pager<BillErrorResultDetailDo>();
        BillErrorResultVO vo = new BillErrorResultVO();
        ModelResult<Pager<BillErrorResultDetailDo>> result = billErrorResultDetailServiceImpl.queryBillErrorResultDetail(page, vo);
        assertNotNull(result.getResult());
        for (BillErrorResultDetailDo detailDo : result.getResult().getData()) {
            System.out.println(detailDo.toString());
        }
    }

    @Test
    public void testUpdateErrorBillStatus() {
        BillErrorResultDetailDo billErrorResultDetailDo = new BillErrorResultDetailDo();
        billErrorResultDetailDo.setId(16l);
        billErrorResultDetailDo.setStatus(BillErrorResultStatus.FINISH);
        billErrorResultDetailDo.setDeal("fs.102");
        ModelResult<Boolean> result = billErrorResultDetailServiceImpl.updateErrorBillStatus(billErrorResultDetailDo);
        assertTrue(result.getResult());
    }

}
