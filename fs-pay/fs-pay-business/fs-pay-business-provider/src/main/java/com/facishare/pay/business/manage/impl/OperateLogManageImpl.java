package com.facishare.pay.business.manage.impl;

import java.util.List;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.manage.OperateLogManage;
import com.facishare.pay.business.model.vo.OperateLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.facishare.pay.business.constant.BusinessCodeEnum;
import com.facishare.pay.business.dao.OperateLogDAO;
import com.facishare.pay.business.model.OperateLogDo;
import com.facishare.pay.common.result.ModelResult;
import com.facishare.pay.common.utils.ThreadPoolUtils;

@Service
public class OperateLogManageImpl implements OperateLogManage {
    
    public static final Logger logger = LoggerFactory.getLogger(OperateLogManageImpl.class);

    @Autowired
    private OperateLogDAO operateLogDao;

    @Override
    public ModelResult<Pager<OperateLogDo>> queryOperateLog(
        Pager<OperateLogDo> page, OperateLogVO operateLogVO) {
        try {
            return new ModelResult<Pager<OperateLogDo>>(BusinessCodeEnum.SUCCESS.getErrorCode(),
                BusinessCodeEnum.SUCCESS.getErrorMessage(), operateLogDao.queryOperateLog(page, operateLogVO));
        } catch(Exception e) {
            logger.error("operateLogVO: {}, 查询异常: {}", operateLogVO.toString(), e);
            return new ModelResult<Pager<OperateLogDo>>(BusinessCodeEnum.SYSTEM_ERROR.getErrorCode(),
                BusinessCodeEnum.SYSTEM_ERROR.getErrorMessage(), null);
        }
    }


    public void addOperateLog(OperateLogDo operateLogDo) {
        try {
            ThreadPoolUtils.execute(new Runnable() {
                public void run() {
                    operateLogDao.addOperateLog(operateLogDo);
                }
            });
        } catch (Exception e) {
            logger.error("操作日志异常:{}" ,e);
        }
    }

}
