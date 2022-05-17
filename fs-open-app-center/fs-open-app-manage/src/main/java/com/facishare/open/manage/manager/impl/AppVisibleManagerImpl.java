package com.facishare.open.manage.manager.impl;

import com.facishare.open.app.center.api.model.enums.VisibleEnum;
import com.facishare.open.app.center.api.model.vo.AppEaVisibleVO;
import com.facishare.open.app.center.api.model.vo.FsEaVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.app.center.api.service.AppEaVisibleService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.AppVisibleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zenglb
 * @date 2015年9月1日
 */
@Service
public class AppVisibleManagerImpl implements AppVisibleManager {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AppEaVisibleService appEaVisibleService;

    @Override
    public void save(String appId, VisibleEnum visibleEnum, List<FsEaVO> fsEaVOs) {
        logger.info("appId[{}] visibleEnum[{}] fsEaVOs[{}] ", appId, visibleEnum, fsEaVOs);
        FsUserVO admin = new FsUserVO("admin",1000);
        StatusResult statusResult = appEaVisibleService.saveVisible(admin,appId, visibleEnum ,fsEaVOs);
        if (!statusResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, statusResult, statusResult.getErrDescription());
        }
    }

    @Override
    public AppEaVisibleVO queryEaVisibleDOByAppId(String appId) {
        BaseResult<AppEaVisibleVO> baseResult = appEaVisibleService.queryEaVisibleDOByAppId(appId);
        if (!baseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, baseResult, baseResult.getErrDescription());
        }

        return baseResult.getResult();
    }

    @Override
    public List<String> queryAppIdVisibleConfig(List<String> appIds) {
        if (CollectionUtils.isEmpty(appIds)) {
            return  new ArrayList<>();
        }
        BaseResult<List<String>> listBaseResult = appEaVisibleService.queryAppIdVisibleConfig(appIds);
        if(!listBaseResult.isSuccess()){
            throw  new BizException(AjaxCode.BIZ_EXCEPTION,listBaseResult,listBaseResult.getErrDescription());
        }
        return listBaseResult.getResult();
    }
}
