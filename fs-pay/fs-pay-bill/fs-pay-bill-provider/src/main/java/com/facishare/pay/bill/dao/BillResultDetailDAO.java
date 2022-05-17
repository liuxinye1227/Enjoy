package com.facishare.pay.bill.dao;

import java.util.Calendar;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.model.BillResultDetailDo;

/**
 * 账单结果详情查询
 * @author guom
 * */
public interface BillResultDetailDAO {

    /**
     * 添加对账结果详情
     * @param BillResultDetailDo billResultDetailDo添加对象
     * @return int 结果
     * */
    int addBillResultDetail(final BillResultDetailDo billResultDetailDo);
    
    /**
     * 更新对账结果详情
     * @param BillResultDetailDo billResultDetailDo添加对象
     * @return int 结果
     * */
    int updateBillResultDetail(final BillResultDetailDo billResultDetailDo);
    
    /**
     * 查询对账结果详情
     * @param Pager<BillResultDetailDo> page分页对象
     * @param String enterpriseAccount企业号
     * @param Long fsUserId用户id
     * @return Pager<BillResultDetailDo> 结果
     * */
    Pager<BillResultDetailDo> queryBillResultDetail(Pager<BillResultDetailDo> page, final String enterpriseAccount, final Long fsUserId);
    
    /**
     * 查询对账结果详情
     * @param String enterpriseAccount企业号
     * @param Long fsUserId用户id
     * @return BillResultDetailDo 结果
     * */
    BillResultDetailDo queryBillResultDetail(final String enterpriseAccount, final Long fsUserId);
    
    
    /**
     * 删除对账信息，删除小于beginTime,大于endTime时间段数据
     * @param Calendar beginTime 开始时间
     * @param Calendar endTime 结束时间
     * @return  int删除行数
     * */
    int deleteBillResultDetail(Calendar beginTime, Calendar endTime);
}
