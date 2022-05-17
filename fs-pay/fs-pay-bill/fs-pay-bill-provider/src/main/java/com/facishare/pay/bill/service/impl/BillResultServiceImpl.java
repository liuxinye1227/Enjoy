package com.facishare.pay.bill.service.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.bill.constant.BillCodeEnum;
import com.facishare.pay.bill.constant.BillErrorResultStatus;
import com.facishare.pay.bill.dao.BillErrorResultDetailDAO;
import com.facishare.pay.bill.dao.BillLogDAO;
import com.facishare.pay.bill.dao.BillResultDAO;
import com.facishare.pay.bill.dao.BillResultDetailDAO;
import com.facishare.pay.bill.model.BillErrorResultDetailDo;
import com.facishare.pay.bill.model.BillLogResultDo;
import com.facishare.pay.bill.model.BillResultDetailDo;
import com.facishare.pay.bill.model.BillResultDo;
import com.facishare.pay.bill.service.BillResultService;
import com.facishare.pay.common.result.ModelResult;

@Service
public class BillResultServiceImpl implements BillResultService {

    private static final Logger logger = LoggerFactory.getLogger(BillResultServiceImpl.class);
    
    @Autowired
    private BillResultDAO billResultDao;
    
    @Autowired
    private BillLogDAO BillLogDao;
    
    @Autowired
    private BillResultDetailDAO BillResultDetailDao;
    
    @Autowired
    private BillErrorResultDetailDAO billErrorResultDetailDao;
    
    @Override
    public ModelResult<Pager<BillResultDo>> queryBillResult(
            Pager<BillResultDo> page, Calendar beginTime, Calendar endTime) {
        try {
            return new ModelResult<Pager<BillResultDo>>(BillCodeEnum.SUCCESS.getErrorCode(), BillCodeEnum.SUCCESS.getErrorMessage()
                    , billResultDao.queryBillResult(page, beginTime, endTime));
        } catch (Exception e) {
            logger.error("系统异常: {}", e);
            return new ModelResult<Pager<BillResultDo>>(BillCodeEnum.SYSTEM_ERROR.getErrorCode(), BillCodeEnum.SYSTEM_ERROR.getErrorMessage()
                    , null);
        }
    }

    @Override
    public ModelResult<Boolean> dealBill(Calendar beginTime, Calendar endTime, String remark, Integer status) {
        if (beginTime == null || endTime == null) {
            return new ModelResult<Boolean>(BillCodeEnum.PARAMS_ERROR.getErrorCode(), BillCodeEnum.PARAMS_ERROR.getErrorMessage(), false);
        } else {
            try {
                Pager<BillLogResultDo> pager = new Pager<BillLogResultDo>();
                pager.setPageSize(1000);
                // 成功个数
                long actualSucTotal = 0;
                // 失败个数
                long actualFailTotal = 0;
                // 同一天多次对账，删除前一次对账的记录,已经上上一次对账记录
                BillResultDetailDao.deleteBillResultDetail(beginTime, endTime);
                do {
                    pager = BillLogDao.queryBillLogResult(pager, beginTime, endTime);
                    List<BillLogResultDo> result = pager.getData();
                    if (result != null) {
                        for (BillLogResultDo billLogResult : result) {
                            BillResultDetailDo billResultDetail = BillResultDetailDao
                                    .queryBillResultDetail(billLogResult.getEnterpriseAccount(), billLogResult.getFsUserId());
                            if (billResultDetail != null) {
                                billResultDetail.setBalance(billLogResult.getActualBalance());
                                billResultDetail.setUpdateTime(Calendar.getInstance());
                                BillResultDetailDao.updateBillResultDetail(billResultDetail);
                            } else {
                                billResultDetail = new BillResultDetailDo();
                                billResultDetail.setBalance(billLogResult.getActualBalance());
                                billResultDetail.setEnterpriseAccount(billLogResult.getEnterpriseAccount());
                                billResultDetail.setFsUserId(billLogResult.getFsUserId());
                                billResultDetail.setStatus(1);
                                billResultDetail.setUpdateTime(Calendar.getInstance());
                                BillResultDetailDao.addBillResultDetail(billResultDetail);
                            }
                            // 错账
                            if (billLogResult.getActualBalance().doubleValue() != billLogResult.getRealBalance().doubleValue()) {
                                BillErrorResultDetailDo billErrorResultDetailDo = new BillErrorResultDetailDo();
                                billErrorResultDetailDo.setBalance(billLogResult.getRealBalance());
                                billErrorResultDetailDo.setActualBalance(billLogResult.getActualBalance());
                                billErrorResultDetailDo.setCreateTime(Calendar.getInstance());
                                billErrorResultDetailDo.setDeal("");
                                billErrorResultDetailDo.setEnterpriseAccount(billLogResult.getEnterpriseAccount());
                                billErrorResultDetailDo.setFsUserId(billLogResult.getFsUserId());
                                billErrorResultDetailDo.setStatus(BillErrorResultStatus.UNFINISH);
                                billErrorResultDetailDo.setRemark("");
                                billErrorResultDetailDao.addBillErrorResultDetail(billErrorResultDetailDo);
                                actualFailTotal++;
                            } else {
                                actualSucTotal++;
                            }
                        }
                    }
                    pager.setCurrentPage(pager.getCurrentPage() + 1);
                } while (pager.getCurrentPage() < pager.getPageTotal());
                // 添加对账记录
                BillResultDo billResult = new BillResultDo();
                billResult.setBeginTime(beginTime);
                billResult.setEndTime(endTime);
                billResult.setRemark(remark == null ? "" : remark);
                billResult.setStatus(status);
                billResult.setActualFailTotal(actualFailTotal);
                billResult.setActualSucTotal(actualSucTotal);
                billResult.setActualTotal(Long.valueOf(pager.getRecordSize()));
                billResult.setTotal(Long.valueOf(pager.getRecordSize()));
                billResultDao.addBillResult(billResult);
                
                return new ModelResult<Boolean>(BillCodeEnum.SUCCESS.getErrorCode(), BillCodeEnum.SUCCESS.getErrorMessage(), true);
            } catch (Exception e) {
                logger.error("对账出错，异常: {}", e);
                return new ModelResult<Boolean>(BillCodeEnum.SYSTEM_ERROR.getErrorCode(), BillCodeEnum.SYSTEM_ERROR.getErrorMessage(), false);
            }
        }
    }
    
}
