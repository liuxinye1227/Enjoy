package com.facishare.open.app.center.adapter.model.manager.impl;

import com.facishare.open.app.center.adapter.model.manager.AppManager;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.app.center.api.service.QueryAppAdminService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xialf on 7/20/16.
 *
 * @author xialf
 */
@Slf4j
@Service
public class AppManagerImpl implements AppManager {
    @Resource
    private QueryAppAdminService queryAppAdminService;

    @Resource
    private OpenAppAdminService openAppAdminService;

    @Override
    public List<Integer> queryAppAdmins(String fsEa, String appId) {
        BaseResult<List<FsUserVO>> appAdminsResult = queryAppAdminService.findAppAdminListByAppId(fsEa, appId);
        if (!appAdminsResult.isSuccess()) {
            log.warn("failed to call queryAppAdminService.findAppAdminListByAppId, fsEa[{}],appId[{}],result[{}]",
                    fsEa, appId, appAdminsResult);
            throw new BizException(appAdminsResult);
        }
        return appAdminsResult.getResult().stream().map(FsUserVO::getUserId).collect(Collectors.toList());
    }

    @Override
    public void addAppAdmins(FsUserVO fsUser, String appId, List<Integer> appAdmins) {
        final com.facishare.open.app.center.api.result.BaseResult<Void> result = openAppAdminService.addAppAdminIds(fsUser, appId, appAdmins);
        if (!result.isSuccess()) {
            log.warn("fail to call openAppAdminService.addAppAdminIds: fsUser[{}], appId[{}], appAdmins[{}], result[{}]",
                    fsUser, appId, appAdmins, result);
            throw new BizException(result);
        }
    }

    @Override
    public void removeAppAdmins(FsUserVO fsUser, String appId, List<Integer> appAdmins) {
        final com.facishare.open.app.center.api.result.BaseResult<Void> result = openAppAdminService.removeAppAdminIds(fsUser, appId, appAdmins);
        if (!result.isSuccess()) {
            log.warn("fail to call openAppAdminService.removeAppAdminIds: fsUser[{}], appId[{}], appAdmins[{}], result[{}]",
                    fsUser, appId, appAdmins, result);
            throw new BizException(result);
        }
    }

    @Override
    public boolean isAppAdmin(FsUserVO fsUser, String appId) {
        final com.facishare.open.app.center.api.result.BaseResult<Boolean> result = openAppAdminService.isAppAdmin(fsUser.asStringUser(), appId);
        if (!result.isSuccess()) {
            log.warn("fail to openAppAdminService.isAppAdmin: fsUser[{}], appId[{}], result[{}]",
                    fsUser, appId, result);
            throw new BizException(result);
        }
        return result.getResult();
    }
}
