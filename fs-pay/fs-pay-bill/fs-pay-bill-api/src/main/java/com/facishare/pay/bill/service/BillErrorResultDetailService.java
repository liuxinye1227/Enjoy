package com.facishare.pay.bill.service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillErrorResultDetailDo;
import com.facishare.pay.bill.model.vo.BillErrorResultVO;
import com.facishare.pay.common.result.ModelResult;


/**
 * 查询错误的对账
 * @author guom
 * @date 2015/10/21
 */
public interface BillErrorResultDetailService {

    
    /**
     * 查询错误的对账
     * @param Pager<BillErrorResultDetailDo> page 分页查询
     * @param BillErrorResultVO billErrorResultVo 查询条件
     * @return ModelResult<Pager<BillErrorResultDetailDo>> 结果集
     * */
    ModelResult<Pager<BillErrorResultDetailDo>> queryBillErrorResultDetail(Pager<BillErrorResultDetailDo> page, BillErrorResultVO billErrorResultVo);
    
    /**
     * 修改错误对账状态
     * @param BillErrorResultDetailDo billErrorResultDetaildo 条件
     * @return ModelResult<Pager<BillErrorResultDetailDo>> 结果集
     * */
    ModelResult<Boolean> updateErrorBillStatus(BillErrorResultDetailDo billErrorResultDetaildo);
}
