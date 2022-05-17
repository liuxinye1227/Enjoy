package com.facishare.open.app.center.adapter.model.manager.impl;

import com.facishare.open.app.center.adapter.model.manager.CrmManager;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmEmployeeTrialInfos;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmQuotaRecords;
import com.facishare.open.app.center.adapter.model.util.ConfigCenter;
import com.facishare.open.app.center.api.model.AppViewDO;
import com.facishare.open.app.center.api.model.EmployeeRange;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.service.OpenFsUserAppViewService;
import com.facishare.open.app.center.api.service.externals.AppViewBizService;
import com.facishare.open.app.pay.api.model.EmployeeTrialVo;
import com.facishare.open.app.pay.api.model.QuotaRecordVo;
import com.facishare.open.app.pay.api.model.QuotaVo;
import com.facishare.open.app.pay.api.service.EmployeeTrialService;
import com.facishare.open.app.pay.api.service.QuotaService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xialf on 7/7/16.
 *
 * @author xialf
 */
@Slf4j
@Service
public class CrmManagerImpl implements CrmManager {
    @Resource
    private QuotaService quotaService;

    @Resource
    private EmployeeTrialService employeeTrialService;

    @Resource
    private OpenFsUserAppViewService openFsUserAppViewService;

    @Resource
    private AppViewBizService appViewBizService;

    @Override
    public void saveUsers(String fsEa, Collection<Integer> userIds) {
        final BaseResult<Integer> addResult = appViewBizService.addEmployeeView(ConfigCenter.getCrmComponentId(), fsEa, Lists.newArrayList(userIds));
        if (!addResult.isSuccess()) {
            log.warn("fail to appViewBizService.addEmployeeView: componentId[{}], fsEa[{}], userIds[{}], result[{}]",
                    ConfigCenter.getCrmComponentId(), fsEa, userIds, addResult);
            throw new BizException(addResult);
        }
    }

    @Override
    public void removeUsers(String fsEa, Collection<Integer> userIds) {
        //从可见范围中删除userIds

        final BaseResult<Integer> result = appViewBizService.removeEmployeeView(ConfigCenter.getCrmComponentId(), fsEa, Lists.newArrayList(userIds));
        if (!result.isSuccess()) {
            log.warn("fail to appViewBizService.removeEmployeeView: componentId[{}], fsEa[{}], userIds[{}], result[{}]",
                    ConfigCenter.getCrmComponentId(), fsEa, userIds, result);
            throw new BizException(result);
        }
    }

    @Override
    public List<Integer> queryUsers(String fsEa) {
        return getView(fsEa).getMember();
    }

    @Override
    public int queryQuota(String fsEa) {
        final BaseResult<Integer> quotaResult = quotaService.queryQuota(fsEa, ConfigCenter.getCrmAppId());
        if (!quotaResult.isSuccess()) {
            log.warn("fail to quotaService.queryQuota, fsEa[{}], appId[{}], result[{}]",
                    fsEa, ConfigCenter.getCrmAppId(), quotaResult);
            throw new BizException(quotaResult);
        }
        return quotaResult.getResult();
    }

    @Override
    public int queryStatus(String fsEa) {
        final BaseResult<QuotaVo> quotaInfoResult = quotaService.queryQuotaInfo(fsEa, ConfigCenter.getCrmAppId());
        if (!quotaInfoResult.isSuccess()) {
            log.warn("fail to quotaService.queryQuotaInfo: fsEa[{}], appId[{}], result[{}]",
                    fsEa, ConfigCenter.getCrmAppId(), quotaInfoResult.getResult());
            throw new BizException(quotaInfoResult);
        }
        return quotaInfoResult.getResult().getPayStatus().getCode();
    }

    @Override
    public List<QueryCrmQuotaRecords.QuotaRecordItem> queryCrmQuotaRecords(String fsEa) {
        final BaseResult<List<QuotaRecordVo>> quotaRecordsResult = quotaService.queryQuotaRecords(fsEa, ConfigCenter.getCrmAppId());
        if (!quotaRecordsResult.isSuccess()) {
            log.warn("fail to quotaService.queryQuotaRecords: fsEa[{}], appId[{}], result[{}]",
                    fsEa, ConfigCenter.getCrmAppId(), quotaRecordsResult);
            throw new BizException(quotaRecordsResult);
        }
        return quotaRecordsResult.getResult().stream().map(QueryCrmQuotaRecords.QuotaRecordItem::from).collect(Collectors.toList());
    }

    @Override
    public List<QueryCrmEmployeeTrialInfos.CrmEmployeeTrialItem> queryCrmEmployeeTrialInfos(String fsEa) {
        final BaseResult<List<EmployeeTrialVo>> employeeTrialsResult = employeeTrialService.queryTrialInfos(ConfigCenter.getCrmAppId(), fsEa);
        if (!employeeTrialsResult.isSuccess()) {
            log.warn("fail to employeeTrialService.queryTrialInfos: fsEa[{}], appId[{}], result[{}]",
                    fsEa, ConfigCenter.getCrmAppId(), employeeTrialsResult);
            throw new BizException(employeeTrialsResult);
        }
        return employeeTrialsResult.getResult().stream().map(QueryCrmEmployeeTrialInfos.CrmEmployeeTrialItem::from).collect(Collectors.toList());
    }

    private EmployeeRange getView(final String fsEa) {
        final FsUserVO fakeAdmin = new FsUserVO(fsEa, true);
        final com.facishare.open.app.center.api.result.BaseResult<AppViewDO> componentViewResult =
                openFsUserAppViewService.loadAppViewByType(fakeAdmin, ConfigCenter.getCrmComponentId(), AppComponentTypeEnum.APP);
        if (!componentViewResult.isSuccess()) {
            log.warn("fail to openFsUserAppViewService.loadAppViewByType, admin[{}], componentId[{}], result[{}]",
                    fakeAdmin, ConfigCenter.getCrmComponentId(), componentViewResult);
            throw new BizException(componentViewResult);
        }
        return EmployeeRange.fromAppView(componentViewResult.getResult());
    }
}
