package com.facishare.open.manage.manager.impl;

import com.facishare.converter.EIEAConverter;
import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.addressbook.api.EnterpriseService;
import com.facishare.open.addressbook.model.EnterpriseSimpleInfo;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.app.center.api.model.EmployeeRange;
import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.app.center.api.service.*;
import com.facishare.open.app.pay.api.model.QuotaRecordVo;
import com.facishare.open.app.pay.api.model.QuotaVo;
import com.facishare.open.app.pay.api.service.QuotaService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.TestManager;
import com.facishare.open.manage.model.DevConcernedAppVO;
import com.facishare.open.manage.model.DevConcernedComponentVO;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.oauth.model.enums.AccessTypeEnum;
import com.facishare.open.oauth.result.CommonResult;
import com.facishare.open.oauth.service.AuthService;
import com.facishare.polling.api.arg.UpdatePollingDataArg;
import com.facishare.polling.api.enums.PollingNotifyFlag;
import com.facishare.polling.api.enums.PollingOSType;
import com.facishare.polling.api.util.PollingMessageProducer;
import com.facishare.polling.api.util.RangeBuilder;
import com.github.jedis.support.MergeJedisCmd;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import javafx.util.Pair;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xialf on 6/6/16.
 *
 * @author xialf
 */
@Service
public class TestManagerImpl implements TestManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestManagerImpl.class);
    private static final String CRM_AVAIL_POLL_KEY = "crm_availability";

    @Resource
    private OpenFsUserAppViewService openFsUserAppViewService;

    @Resource
    private OpenFsUserBindAppService openFsUserBindAppService;

    @Resource
    private QuotaService quotaService;

    @Resource
    private AppIconService appIconService;

    @Resource
    private MergeJedisCmd jedis;

    @Resource
    private OpenAppService openAppService;

    @Resource
    private OpenAppComponentService openAppComponentService;

    @Resource
    private AuthService authService;

    @Resource
    private PollingMessageProducer pollingMessageProducer;

    @Resource
    private OpenAppAdminService openAppAdminService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private CrmViewService crmViewService;

    @Resource
    private EnterpriseService enterpriseService;

    @Resource
    private EIEAConverter eieaConverter;

    @Override
    public void deauth(String fsEa, String appId) {
        final StatusResult deauthResult = openFsUserBindAppService.saveFsUserBindAppCancelAuth(new FsUserVO(fsEa, true), appId, fsEa);
        if (!deauthResult.isSuccess()) {
            LOGGER.warn("fail to deauth, fsEa[{}],appId[{}],result[{}]", fsEa, appId, deauthResult);
            throw new BizException(deauthResult);
        }
    }

    @Override
    public List<OpenDevAppVO> getAppsByAdmin(String fsEa) {
        final AppListResult appListResult =
                openFsUserBindAppService.queryAppListByFsEnterpriseAccount(new FsUserVO(fsEa, true), fsEa);
        if (!appListResult.isSuccess()) {
            LOGGER.warn("fail to queryAppListByFsEnterpriseAccount,fsEa[{}],result[{}]", fsEa, appListResult);
            throw new BizException(appListResult);
        }

        return appListResult.getResult().stream()
                .map(openApp -> {
                    OpenDevAppVO openDevAppVO = TestManager.openApp2DevApp(openApp);
                    openDevAppVO.setAppLogoUrl(getAppIconUrl(openApp.getAppId(), IconType.WEB));
                    return openDevAppVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void push(String fsEa, EmployeeRange view) {
        final BaseResult<Void> notifyResult =
                openFsUserAppViewService.notifyEndUsers(new FsUserVO(fsEa, true), ConfigCenter.getCrmAppId(), view); //todo
        if (!notifyResult.isSuccess()) {
            LOGGER.warn("fail to notify, fsEa[{}],view[{}],result[{}]", fsEa, view, notifyResult);
            throw new BizException(notifyResult);
        }
    }

    @Override
    public void clearCache(String fsEa) {
        //清空应用付费的缓存
        final Set<String> allKeys = Sets.newHashSet();
        allKeys.addAll(jedis.keys(String.format("open.app.pay.data.quotaVo.%s.%s", fsEa, "*")));
        allKeys.addAll(jedis.keys(String.format("open.app.pay.data.employeeTrialVo.%s.%s.%s", fsEa, "*", "*")));
        allKeys.addAll(jedis.keys(String.format("open.app.pay.data.keys.employeeTrialVo.%s.%s", fsEa, "*")));
        allKeys.addAll(jedis.keys(String.format("open.app.pay.data.employeeTrialApps.%s.%s", fsEa, "*")));

        allKeys.add(String.format("open.app.pay.data.keys.employeeTrialApps.%s", fsEa));
        allKeys.add(String.format("open.app.pay.data.off.apps.set.%s", fsEa));

        //清空应用中心的缓存
        allKeys.addAll(jedis.keys(String.format("acc.e.a.sta.%s.%s", fsEa, "*")));
        allKeys.addAll(jedis.keys(String.format("acc.e.a.eid.uni.id.%s.%s", fsEa, "*")));
        allKeys.addAll(jedis.keys(String.format("acc.e.a.sta.%s.%s", fsEa, "*")));
        allKeys.addAll(jedis.keys(String.format("acc.e.c.user.sta.%s.%s.%s", fsEa, "*", "*")));

        jedis.del(allKeys.toArray(new String[allKeys.size()]));
    }

    @Override
    public List<QuotaRecordVo> queryQuotaRecords(String fsEa, String appId) {
        com.facishare.open.common.result.BaseResult<List<QuotaRecordVo>> quotasResult =
                quotaService.queryQuotaRecords(fsEa, appId);
        if (!quotasResult.isSuccess()) {
            LOGGER.warn("fail to quotaService.queryQuotaRecords, fsEa[{}],appId[{}],result[{}]",
                    fsEa, null, quotasResult);
            throw new BizException(quotasResult);
        }
        return quotasResult.getResult();
    }

    @Override
    public String getAppIconUrl(final String appOrComponentId, IconType type) {
        BaseResult<String> iconUrlResult = appIconService.queryAppIconUrl(appOrComponentId, type);
        if (!iconUrlResult.isSuccess()) {
            LOGGER.warn("queryAppIconUrl failed, appIdOrComponentId[{}], type[{}], result[{}] : ",
                    appOrComponentId, type, iconUrlResult);
        }
        return iconUrlResult.getResult();
    }

    @Override
    public List<DevConcernedAppVO> queryDevConcernedApps(String... appIds) {
        if (appIds == null || appIds.length == 0) {
            return new ArrayList<>(0);
        }
        AppListResult appListResult = openAppService.loadOpenAppByIds(Arrays.asList(appIds));
        if (!appListResult.isSuccess()) {
            LOGGER.warn("queryDevConcernedApps failed, appId={}, errCode={}, errMsg={}", appIds, appListResult.getErrCode(),
                    appListResult.getErrMessage());
            throw new BizException(appListResult);
        }
        List<OpenAppDO> openAppDOs = appListResult.getResult();
        List<DevConcernedAppVO> devConcernedAppVOs = openAppDOs.stream().map(appDo ->
                new DevConcernedAppVO(appDo.getAppId(), appDo.getAppName(),
                        getAppIconUrl(appDo.getAppId(), IconType.WEB), appDo.getAppType())).collect(Collectors.toList());

        devConcernedAppVOs.stream().forEach(devConcernedAppVO -> {
            BaseResult<List<OpenAppComponentDO>> componentDosResult =
                    openAppComponentService.queryAppComponentListByAppId(null, devConcernedAppVO.getAppId());
            if (!componentDosResult.isSuccess()) {
                LOGGER.warn("queryAppComponentListByAppId failed, appId={}, errCode={}, errMsg={}", devConcernedAppVO.getAppId(),
                        componentDosResult.getErrCode(), componentDosResult.getErrMessage());
            } else {
                List<OpenAppComponentDO> componentDOs = componentDosResult.getResult();
                componentDOs.stream().map(componentDO -> new DevConcernedComponentVO(componentDO.getComponentId(),
                        componentDO.getComponentName(), componentDO.getComponentType(),
                        getAppIconUrl(componentDO.getComponentId(), IconType.WEB)))
                        .forEach(devConcernedAppVO.getComponents()::add);
            }
        });
        return devConcernedAppVOs;
    }



    private static final String REDIS_KEY_PREFIX = "open.app.pay";
    private static final String REDIS_JOB_KEY_PREFIX = REDIS_KEY_PREFIX + ".job";
    private static final String REDIS_WILL_EXPIRE_LOCK = REDIS_JOB_KEY_PREFIX + ".lock.willExpire";
    private static final String REDIS_WILL_EXPIRE_KEYS_SET = REDIS_JOB_KEY_PREFIX + ".keys.willExpire";

    private static final String REDIS_LOAD_EXPIRED_LOCK = REDIS_JOB_KEY_PREFIX + ".lock.expired.load";
    private static final String REDIS_EXPIRED_WRITTEN_KEYS_SET = REDIS_JOB_KEY_PREFIX + ".keys.expired.written";

    private static final String REDIS_HANDLE_EXPIRED_QUOTA_LOCK = REDIS_JOB_KEY_PREFIX + ".lock.handle.expired.quota";
    private static final String REDIS_HANDLE_EXPIRED_EMP_TRIAL_LOCK = REDIS_JOB_KEY_PREFIX + ".lock.handle.expired.trial";

    @Override
    public Map<String, String> getJobStatus() {
        Map<String, String> statusMap = Maps.newHashMap();

        StringBuilder sb = new StringBuilder();
        final Boolean isWillJobLocked = jedis.exists(REDIS_WILL_EXPIRE_LOCK);
        sb.append(isWillJobLocked ? "任务已锁定" : "任务未锁定").append(";");
        final Boolean isWillJobHandled = jedis.sismember(REDIS_WILL_EXPIRE_KEYS_SET, DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        sb.append(isWillJobHandled ? "今日任务已完成" : (isWillJobLocked ? "今日任务执行中" : "今日任务未完成"));
        statusMap.put("将要到期任务", sb.toString());

        sb.setLength(0);
        //加载配额已到期数据任务
        final Boolean isLoadJobLocked = jedis.exists(REDIS_LOAD_EXPIRED_LOCK);
        sb.append(isLoadJobLocked ? "任务已锁定" : " 任务未锁定").append(";");
        final Boolean isLoadJobHandled = jedis.sismember(REDIS_EXPIRED_WRITTEN_KEYS_SET, DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        sb.append(isLoadJobHandled ? "今日任务已完成" : (isLoadJobLocked ? "今日任务执行中" : "进入任务未完成"));
        statusMap.put("加载配额到期数据任务", sb.toString());

        //加载个人试用已到期任务
        statusMap.put("加载个人试用到期数据任务", sb.toString());
        sb.setLength(0);

        //处理配额已到期任务
        final Boolean isQuotaJobLocked = jedis.exists(REDIS_HANDLE_EXPIRED_QUOTA_LOCK);
        sb.append(isQuotaJobLocked ? "任务已锁定" : "任务未锁定").append(";");
        sb.append(isQuotaJobLocked ? "任务执行中" : "任务暂停中");
        statusMap.put("处理配额已到期任务", sb.toString());

        sb.setLength(0);
        //处理个人试用已到期任务
        final Boolean isTrialJobLocked = jedis.exists(REDIS_HANDLE_EXPIRED_EMP_TRIAL_LOCK);
        sb.append(isTrialJobLocked ? "任务已锁定" : "任务未锁定").append(";");
        sb.append(isTrialJobLocked ? "任务执行中" : "任务暂停中");
        statusMap.put("处理个人试用已到期任务", sb.toString());

        return statusMap;
    }

    @Override
    public void triggerWillExpireJob() {
        jedis.srem(REDIS_WILL_EXPIRE_KEYS_SET, DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        jedis.del(REDIS_WILL_EXPIRE_LOCK);
    }

    @Override
    public void triggerLoadJob() {
        jedis.srem(REDIS_EXPIRED_WRITTEN_KEYS_SET, DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
        jedis.del(REDIS_LOAD_EXPIRED_LOCK);
    }

    @Override
    public void removeView(String fsEa, String componentId, Integer userId) {
        BaseResult<EmployeeRange> viewResult = openFsUserAppViewService.loadComponentView(new FsUserVO(fsEa, true),
                componentId);
        if (!viewResult.isSuccess()) {
            LOGGER.warn("removeView failed, fsEa=[{}], appId[{}], userId[{}], result[{}]", fsEa, componentId, userId,
                    viewResult);
            throw new BizException(viewResult);
        }
        EmployeeRange view = viewResult.getResult();
        List<Integer> viewMembers = view.getMember();
        if (viewMembers.contains(userId)) {
            List<String> toBeRemoved = new ArrayList<>(1);
            toBeRemoved.add(new FsUserVO(fsEa, userId).asStringUser());
            CommonResult commonResult = authService.batchDeleteFsAuth(new FsUserVO(fsEa, true).asStringUser(), null,
                    componentId, toBeRemoved, AccessTypeEnum.APP);
            if (!commonResult.isSuccess()) {
                LOGGER.warn("authService.batchDeleteFsAuth failed, fsEa[{}], componentId[{}], toBeRemoved[{}], " +
                                "result[{}]", fsEa, componentId, toBeRemoved, commonResult);
                throw new BizException(commonResult);
            }
            LOGGER.info("Manually removed user[{}] from CRM view for ea[{}]", userId, fsEa);
        }
    }

    @Override
    public void removeEnterpriseViewCache(String fsEa, String appId) {
        BaseResult<Void> result = openFsUserAppViewService.cleanViewCache(fsEa, appId);
        if (!result.isSuccess()) {
            LOGGER.warn("openFsUserAppViewService.cleanViewCache failed, fsEa[{}], appId[{}], result[{}]", fsEa, appId,
                    result);
            throw new BizException(result);
        }
    }

    @Override
    public void pushCrmPoll(String fsEa) {
        UpdatePollingDataArg arg = new UpdatePollingDataArg();
        arg.setKey(CRM_AVAIL_POLL_KEY);
        arg.setVersion(System.currentTimeMillis());
        arg.setOsType(PollingOSType.WEB);
        arg.setRange(RangeBuilder.buildEnterprisesRange(Lists.newArrayList(fsEa)));

        try {
            // 通过rocketMq发送消息
            pollingMessageProducer.sendMessage(PollingNotifyFlag.UPDATE_ENTERPRISES, arg);
            LOGGER.info("pollingMessageProducer.sendMessage success: fsEa[{}], arg[{}]", fsEa, arg);
        } catch (Exception e) {
            LOGGER.warn("fail to pollingMessageProducer.sendMessage: fsEa[{}], arg[{}]", fsEa, arg);
        }
    }

    @Override
    public List<String> queryAppAdminIdList(String fsEa) {
        final com.facishare.open.common.result.BaseResult<List<String>> appAdminResult =
                openAppAdminService.queryAppAdminIdList(fsEa, ConfigCenter.getCrmAppId());
        if (!appAdminResult.isSuccess()) {
            LOGGER.warn("fail to call openAppAdminService.queryAppAdminIdList, fsEa[{}], appId[{}], result[{}]",
                    fsEa, ConfigCenter.getCrmAppId(), appAdminResult);
            throw new BizException(appAdminResult);
        }
        return appAdminResult.getResult();
    }

    @Override
    public List<Integer> queryFsAdminList(String fsEa) {
        final ListResult<Integer> fsAdminResult = employeeService.getAdminIds(fsEa);
        if (!fsAdminResult.isSuccess()) {
            LOGGER.warn("fail to call employeeService.getAdminIds, fsEa={}, result={}",
                    fsEa, fsAdminResult);
            throw new BizException(fsAdminResult);
        }
        return fsAdminResult.getResult();
    }

    @Override
    public boolean isCrmQuotaEqual(String fromEa, String toEa) {
        com.facishare.open.common.result.BaseResult<QuotaVo> fromQuotaVoResult =
                quotaService.queryQuotaInfo(fromEa, ConfigCenter.getCrmAppId());
        if (!fromQuotaVoResult.isSuccess()) {
            LOGGER.warn("fail to call quotaService.queryQuotaInfo. fsEa[{}], appId[{}]",
                    fromEa, ConfigCenter.getCrmAppId());
            throw new BizException(fromQuotaVoResult);
        }

        com.facishare.open.common.result.BaseResult<QuotaVo> toQuotaVoResult =
                quotaService.queryQuotaInfo(toEa, ConfigCenter.getCrmAppId());
        if (!toQuotaVoResult.isSuccess()) {
            LOGGER.warn("fail to call quotaService.queryQuotaInfo. fsEa[{}], appId[{}]",
                    toEa, ConfigCenter.getCrmAppId());
            throw new BizException(toQuotaVoResult);
        }

        final QuotaVo fromVo = fromQuotaVoResult.getResult();
        final QuotaVo toVo = toQuotaVoResult.getResult();

        try {
            Class clazz = fromVo.getClass();
            Field[] fields = fromVo.getClass().getDeclaredFields();
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(fromVo);
                Object o2 = getMethod.invoke(toVo);

                if (field.getName().equals("fsEa")) {
                    continue;
                }
                if (o1 != null && o2 != null) {
                    if (!o1.toString().equals(o2.toString())) {
                        return false;
                    }
                }
                else if (Optional.ofNullable(o1).isPresent() ^ Optional.ofNullable(o2).isPresent()) {
                    return false;
                }
            }
        } catch (Exception e) {
            LOGGER.warn(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean isCrmAppAdminEqual(String fromEa, String toEa) {
        com.facishare.open.common.result.BaseResult<List<String>> fromAppAdminResult =
                openAppAdminService.queryAppAdminIdList(fromEa, ConfigCenter.getCrmAppId());
        if (!fromAppAdminResult.isSuccess()) {
            LOGGER.warn("fail to call openAppAdminService.queryAppAdminIdList. fsEa[{}], appId[{}]",
                    fromEa, ConfigCenter.getCrmAppId());
            throw new BizException(fromAppAdminResult);
        }

        com.facishare.open.common.result.BaseResult<List<String>> toAppAdminResult =
                openAppAdminService.queryAppAdminIdList(toEa, ConfigCenter.getCrmAppId());
        if (!fromAppAdminResult.isSuccess()) {
            LOGGER.warn("fail to call openAppAdminService.queryAppAdminIdList. fsEa[{}], appId[{}]",
                    toEa, ConfigCenter.getCrmAppId());
            throw new BizException(toAppAdminResult);
        }

        List<Integer> fromAppAdminIdList = fromAppAdminResult.getResult().stream()
                .map(FsUserVO::getUserId).collect(Collectors.toList());

        List<Integer> toAppAdminIdList = toAppAdminResult.getResult().stream()
                .map(FsUserVO::getUserId).collect(Collectors.toList());

        return contrastList(fromAppAdminIdList, toAppAdminIdList);
    }

    @Override
    public boolean isCrmViewIncluded(String fromEa, String toEa) {
        com.facishare.open.common.result.BaseResult<List<Pair<Integer, Long>>> fromUsersResult =
                crmViewService.queryUsers(fromEa);
        if (!fromUsersResult.isSuccess()) {
            LOGGER.warn("fail to call crmViewService.queryUsers. fsEa[{}], usersResult[{}]", fromEa, fromUsersResult);
            throw new BizException(fromUsersResult);
        }

        com.facishare.open.common.result.BaseResult<List<Pair<Integer, Long>>> toUsersResult =
                crmViewService.queryUsers(toEa);
        if (!toUsersResult.isSuccess()) {
            LOGGER.warn("fail to call crmViewService.queryUsers. fsEa[{}], usersResult[{}]", toEa, toUsersResult);
            throw new BizException(toUsersResult);
        }

        List<Integer> fromUsers = fromUsersResult.getResult().stream().map(Pair::getKey).collect(Collectors.toList());
        List<Integer> toUsers = toUsersResult.getResult().stream().map(Pair::getKey).collect(Collectors.toList());

        return toUsers.containsAll(fromUsers);
    }

    @Override
    public boolean isFsAdminEqual(String fromEa, String toEa) {
        List<Integer> fromFsAdminList = queryFsAdminList(fromEa);
        List<Integer> toFsAdminList = queryFsAdminList(toEa);
        return contrastList(fromFsAdminList, toFsAdminList);
    }

    @Override
    public EnterpriseSimpleInfo queryEnterpriseInfoByEi(Integer fsEi) {
        ListResult<EnterpriseSimpleInfo> result = enterpriseService.getEnterpriseSimpleList(Arrays.asList(fsEi));
        if (!result.isSuccess()) {
            LOGGER.warn("fail to call enterpriseService.getEnterpriseSimpleList. fsEi[{}], result[{}]", fsEi, result);
            throw new BizException(result);
        }
        return result.getResult().get(0);
    }

    @Override
    public EnterpriseSimpleInfo queryEnterpriseInfoByEa(String fsEa) {
        Integer fsEi = eieaConverter.enterpriseAccountToId(fsEa);
        if (!Optional.ofNullable(fsEi).isPresent()) {
            LOGGER.warn("fail to call eieaConverter.enterpriseAccountToId. fsEa[{}]", fsEa);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "fail to call eieaConverter.enterpriseAccountToId. fsEa[{}]", fsEa);
        }
        return queryEnterpriseInfoByEi(fsEi);
    }

    private boolean contrastList(List<Integer> fromList, List<Integer> toList) {
        if (fromList.size() != toList.size()) {
            return false;
        }

        Collections.sort(fromList);
        Collections.sort(toList);
        for(int i = 0; i < fromList.size(); i++) {
            if (!Objects.equals(fromList.get(i), toList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
