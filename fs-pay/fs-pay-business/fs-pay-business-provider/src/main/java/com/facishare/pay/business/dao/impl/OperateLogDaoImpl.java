package com.facishare.pay.business.dao.impl;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.dao.OperateLogDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.business.model.vo.OperateLogVO;

@Service
public class OperateLogDaoImpl extends BusinessBaseDAO<OperateLogDo> implements OperateLogDAO {

    @Override
    public int addOperateLog(OperateLogDo operateLog) {
        if (operateLog.getCreateTime() == null) {
            operateLog.setCreateTime(Calendar.getInstance());
        }
        return this.save("addOperateLog", operateLog);
    }

    @Override
    public Pager<OperateLogDo> queryOperateLog(Pager<OperateLogDo> page, OperateLogVO operateLogVO) {
        if (operateLogVO != null) {
            if (StringUtils.isNotBlank(operateLogVO.getOrderNo())) {
                page.addParam("orderNo", operateLogVO.getOrderNo());
            }
            if (operateLogVO.getBeginTime() != null) {
                page.addParam("beginTime", operateLogVO.getBeginTime());
            }
            if (operateLogVO.getEndTime() != null) {
                page.addParam("endTime", operateLogVO.getEndTime());
            }
            if (operateLogVO.getBusiNo() != null) {
                page.addParam("busiNo", operateLogVO.getBusiNo().getIndex());
            }
            if (operateLogVO.getOperateType() != null) {
                page.addParam("operateType", operateLogVO.getOperateType().getIndex());
            }
            if (operateLogVO.getTransType() != null) {
                page.addParam("transType", operateLogVO.getTransType().getIndex());
            }
        }
        return this.queryPage("queryOperateLogCount", "queryOperateLogPager", page);
    }

}
