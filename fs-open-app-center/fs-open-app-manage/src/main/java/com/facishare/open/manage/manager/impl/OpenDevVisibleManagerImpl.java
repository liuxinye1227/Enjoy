package com.facishare.open.manage.manager.impl;

import com.facishare.open.app.center.api.model.OpenDevDO;
import com.facishare.open.app.center.api.model.vo.OpenDevVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.app.center.api.service.OpenDevService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.OpenDevVisibleManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangwz on 2015/9/9.
 */
@Service
public class OpenDevVisibleManagerImpl implements OpenDevVisibleManager {

    private OpenDevService openDevService;

    @Resource
    public void setOpenDevService(OpenDevService openDevService) {
        this.openDevService = openDevService;
    }

    @Override
    public Pager<OpenDevVO> listAllOpenDevVisible(Pager<OpenDevVO> pager) {
        BaseResult<Pager<OpenDevVO>> baseResult = openDevService.queryOpenDevVisibleByPage(pager);
        if (!baseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, baseResult.getErrDescription());
        }
        return baseResult.getResult();
    }

    @Override
    public void deleteByOpenDevId(String id) {
        StatusResult statusResult = openDevService.deleteByOpenDevId(id);
        if (!statusResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, statusResult, statusResult.getErrDescription());
        }
    }

    @Override
    public OpenDevDO loadOpenDevByDevId(String devId) {
        BaseResult<OpenDevDO> baseResult = openDevService.loadOpenDevByDevId(devId);
        if (!baseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, baseResult.getErrDescription());
        }
        return baseResult.getResult();
    }

}
