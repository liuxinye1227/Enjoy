package com.facishare.open.app.center.external.service.impl;

import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.service.QueryAppAdminService;
import com.facishare.open.app.center.external.manager.OpenAppAdminManager;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * impl.
 * Created by zenglb on 2016/3/8.
 */
@Service("queryAppAdminServiceImpl")
public class QueryAppAdminServiceImpl implements QueryAppAdminService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private OpenAppAdminManager openAppAdminManager;

    @Override
    public BaseResult<Boolean> isAppAdmin(FsUserVO fsUserVO, String appId) {
        if (null == fsUserVO || !FsUserVO.isFsUserString(fsUserVO.asStringUser()) || StringUtils.isBlank(appId)) {
            return new com.facishare.open.app.center.api.result.BaseResult<>(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION);
        }
        try {
            return new BaseResult<>(openAppAdminManager.isAppAdmin(fsUserVO, appId));
        } catch (BizException e) {
            logger.warn("failed to call isAppAdmin", e);
            return new BaseResult<>(e);
        } catch (Exception e) {
            logger.error("failed to call isAppAdmin", e);
            return new BaseResult<>(AppCenterCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    @Override
    public BaseResult<List<FsUserVO>> findAppAdminListByAppId(String fsEa, String appId) {
        if (StringUtils.isBlank(fsEa) || StringUtils.isBlank(appId)) {
            return new com.facishare.open.app.center.api.result.BaseResult<>(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION);
        }
        try {
            return new BaseResult<>(openAppAdminManager.findAppAdminListByAppId(fsEa, appId));
        } catch (BizException e) {
            logger.warn("failed to call findAppAdminListByAppId", e);
            return new com.facishare.open.app.center.api.result.BaseResult<>(e);
        } catch (Exception e) {
            logger.error("failed to call findAppAdminListByAppId", e);
            return new com.facishare.open.app.center.api.result.BaseResult<>(AppCenterCodeEnum.SYSTEM_EXCEPTION);
        }
    }
}
