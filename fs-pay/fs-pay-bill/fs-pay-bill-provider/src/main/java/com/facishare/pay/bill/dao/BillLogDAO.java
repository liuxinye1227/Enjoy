package com.facishare.pay.bill.dao;

import java.util.Calendar;
import java.util.List;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillLogDo;
import com.facishare.pay.bill.model.BillLogResultDo;
import com.facishare.pay.bill.model.vo.BillLogVO;

/**
 * 账单记录流水查询
 * @author guom
 * */
public interface BillLogDAO {
    
    /**
     * 添加账单记录
     * @param BillLogDo billLogDo
     * @return int 结果
     * */
    int addBillLog(final BillLogDo billLogDo);
    
    /**
     * 查询账单记录
     * @param BillLogVO billLogVo 查询封装条件
     * @return List<BillLogDo> 结果集
     * */
    List<BillLogDo> queryBillLog(final BillLogVO billLogVo);
    
    /**
     * 分页统计对账结果
     * @param Pager<BillLogResultDo> page 查询封装条件
     * @return Pager<BillLogResultDo> 结果集
     * */
    Pager<BillLogResultDo> queryBillLogResult(Pager<BillLogResultDo> page, Calendar beginTime, Calendar endTime);

}
