package com.facishare.pay.bill.dao;

import java.util.Calendar;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillResultDo;

/**
 * 账单记录查询
 * @author guom
 * */
public interface BillResultDAO {
    
    /**
     * 添加账单记录
     * @param BillResultDo billResultDo添加对象
     * @return int 结果
     * */
    int addBillResult(final BillResultDo billResultDo);

    /**
     * 查询账单记录
     * @param Pager<BillResultDo> page 分页查询
     * @param Calendar beginTime 开始时间
     * @param Calendar endTime 结束时间
     * @return Pager<BillResultDo> 结果
     * */
    Pager<BillResultDo> queryBillResult(Pager<BillResultDo> page, final Calendar beginTime, final Calendar endTime);
}
