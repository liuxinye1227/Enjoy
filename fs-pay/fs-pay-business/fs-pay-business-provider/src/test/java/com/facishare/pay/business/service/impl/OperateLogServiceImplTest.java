package com.facishare.pay.business.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.constant.OperateType;
import com.facishare.pay.business.manage.impl.OperateLogManageImpl;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.vo.OperateLogVO;
import com.facishare.pay.common.constants.BizTypeEnum;
import com.facishare.pay.common.constants.TransTypeEnum;
import com.facishare.pay.common.result.ModelResult;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class OperateLogServiceImplTest {
    
    @Autowired
    private com.facishare.pay.business.manage.impl.OperateLogManageImpl OperateLogServiceImpl;
    
    @Autowired
    private OperateLogManageImpl operateLogManageImpl;
    
    @Test
    public void testQueryOperateLogByOrderNo() {
        OperateLogVO oDo = new OperateLogVO();
        oDo.setOrderNo("201510301034370000001001");
        ModelResult<Pager<OperateLogDo>> result = OperateLogServiceImpl.queryOperateLog(new Pager<OperateLogDo>(), oDo);
        assertNotNull(result.getResult());
        for (OperateLogDo operateLogDo : result.getResult().getData()) {
            System.out.println(operateLogDo.toString());
        }
    }

    @Test
    public void testAddOperateLog() {
        operateLogManageImpl.addOperateLog(new OperateLogDo("987654321", BizTypeEnum.CHARGE, TransTypeEnum.CHARGE,
                OperateType.CH_BACK_THIRD, "a=a", Calendar.getInstance()));
        try {
            Thread.sleep(14000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
