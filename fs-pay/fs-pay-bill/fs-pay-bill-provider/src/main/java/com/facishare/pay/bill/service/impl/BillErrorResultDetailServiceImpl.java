package com.facishare.pay.bill.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.constant.BillCodeEnum;
import com.facishare.pay.bill.dao.BillErrorResultDetailDAO;
import com.facishare.pay.bill.model.BillErrorResultDetailDo;
import com.facishare.pay.bill.model.vo.BillErrorResultVO;
import com.facishare.pay.bill.service.BillErrorResultDetailService;
import com.facishare.pay.common.result.ModelResult;

@Service
public class BillErrorResultDetailServiceImpl implements
        BillErrorResultDetailService {

    private static final Logger logger = LoggerFactory.getLogger(BillErrorResultDetailServiceImpl.class);
    
    @Autowired
    private BillErrorResultDetailDAO billErrorResultDetailDao;
    
    @Override
    public ModelResult<Pager<BillErrorResultDetailDo>> queryBillErrorResultDetail(
            Pager<BillErrorResultDetailDo> page,
            BillErrorResultVO billErrorResultVo) {
        try {
            return new ModelResult<Pager<BillErrorResultDetailDo>>(BillCodeEnum.SUCCESS.getErrorCode(), BillCodeEnum.SUCCESS.getErrorMessage()
                    , billErrorResultDetailDao.queryBillErrorResultDetail(page, billErrorResultVo));
        } catch (Exception e) {
            logger.error("billErrorResultVo: {}, 系统异常: {}", billErrorResultVo.toString(), e);
            return new ModelResult<Pager<BillErrorResultDetailDo>>(BillCodeEnum.SYSTEM_ERROR.getErrorCode(), BillCodeEnum.SYSTEM_ERROR.getErrorMessage()
                    , null);
        }
    }

    @Override
    public ModelResult<Boolean> updateErrorBillStatus(BillErrorResultDetailDo billErrorResultDetailDo) {
        try {
            return new ModelResult<Boolean>(BillCodeEnum.SUCCESS.getErrorCode(), BillCodeEnum.SUCCESS.getErrorMessage()
                    , billErrorResultDetailDao.updateBillErrorResultDetail(billErrorResultDetailDo) == 1 ? true : false);
        } catch (Exception e) {
            logger.error(" billErrorResultDetailDo: {}, 系统异常: {}", billErrorResultDetailDo.toString(), e);
            return new ModelResult<Boolean>(BillCodeEnum.SYSTEM_ERROR.getErrorCode(), BillCodeEnum.SYSTEM_ERROR.getErrorMessage()
                    , false);
        }
    }

}
