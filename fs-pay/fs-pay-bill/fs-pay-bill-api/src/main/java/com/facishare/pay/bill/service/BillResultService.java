package com.facishare.pay.bill.service;

import java.util.Calendar;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillResultDo;
import com.facishare.pay.common.result.ModelResult;


/**
 * 对账记录查询
 * @author guom
 * @date 2015/10/21
 */
public interface BillResultService {

    /**
     * 查询历史对账
     * @param Pager<BillResultDo> page 分页查询
     * @param Calendar BeginTime 起止时间
     * @param Calendar endTime 起止时间
     * @return ModelResult<Pager<BillResultDo>> 结果集
     * */
    ModelResult<Pager<BillResultDo>> queryBillResult(Pager<BillResultDo> page, Calendar beginTime, Calendar endTime);
    
    /**
     * 
     * 机器断电等原因，对账未进行，后台人工进行对账，暂没有进行多线程处理，后台界面进行disabled控制
     * 
     * 进行对账
     * @param Calendar BeginTime 起止时间
     * @param Calendar endTime 起止时间
     * @param Calendar remark 备注
     * @param Integer status  1  自动对账完成 2 人工对账完成
     * 
     * @return ModelResult<Boolean> 结果集
     * */
    ModelResult<Boolean> dealBill(Calendar beginTime, Calendar endTime, String remark, Integer billResultStatus);
    
}
