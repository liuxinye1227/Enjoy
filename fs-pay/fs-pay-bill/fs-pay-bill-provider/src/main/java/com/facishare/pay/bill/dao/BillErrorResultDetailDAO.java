package com.facishare.pay.bill.dao;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillErrorResultDetailDo;
import com.facishare.pay.bill.model.vo.BillErrorResultVO;

/**
 * 错误账单结果详情查询
 * @author guom
 * */
public interface BillErrorResultDetailDAO {

    /**
     * 添加错误对账结果详情
     * @param BillErrorResultDetailDAO billErrorResultDetailDAO添加对象
     * @return int 结果
     * */
    int addBillErrorResultDetail(final BillErrorResultDetailDo billErrorResultDetailDo);
    
    /**
     * 查询错误对账结果详情
     * @param Pager<BillErrorResultDetailDo> page分页对象
     * @param BillErrorResultVO billErrorResultVo 封装条件
     * @return Pager<BillErrorResultDetailDo> 结果
     * */
    Pager<BillErrorResultDetailDo> queryBillErrorResultDetail(Pager<BillErrorResultDetailDo> page, final BillErrorResultVO billErrorResultVo);
    
    /**
     * 更新错误对账结果详情
     * @param BillErrorResultDetailDo billErrorResultDetailDo对象
     * @return int 结果
     * */
    int updateBillErrorResultDetail(final BillErrorResultDetailDo billErrorResultDetailDo);
}
