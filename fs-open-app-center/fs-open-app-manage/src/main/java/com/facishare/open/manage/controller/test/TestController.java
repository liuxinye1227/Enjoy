package com.facishare.open.manage.controller.test;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.AppViewDO;
import com.facishare.open.app.center.api.model.EmployeeRange;
import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.enums.AppAccessTypeEnum;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.center.api.model.vo.OpenAppVO;
import com.facishare.open.app.center.api.model.vo.UserCanViewListVO;
import com.facishare.open.app.center.api.result.AppListResult;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.*;
import com.facishare.open.app.pay.api.model.QuotaRecordVo;
import com.facishare.open.app.pay.api.model.QuotaVo;
import com.facishare.open.app.pay.api.service.EmployeeTrialService;
import com.facishare.open.app.pay.api.service.QuotaService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.controller.BaseController;
import com.facishare.open.manage.manager.TestManager;
import com.facishare.open.manage.model.DevConcernedAppVO;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.notify.proxy.service.external.QyWechatAuthService;
import com.fxiaoke.common.PasswordUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by xialf on 5/17/16.
 *
 * @author xialf
 */
@RestController
@RequestMapping("/open/manage/test/")
public class TestController extends BaseController {
    @Resource
    private OpenFsUserAppViewService openFsUserAppViewService;

    @Resource
    private QuotaService quotaService;

    @Resource
    private OpenQuotaService openQuotaService;

    @Resource
    private EmployeeTrialService employeeTrialService;

    @Resource
    private OpenAppService openAppService;

    @Resource
    private OpenAppComponentService openAppComponentService;

    @Resource
    private AppIconService appIconService;

    @Resource
    private QyWechatAuthService qyWechatAuthService;

    @Resource
    private TestManager testManager;

    /**
     * 检查是否打开测试后台接口.
     */
    @ModelAttribute
    public void checkTestIsOn() {
        if (!ConfigCenter.isTestOn()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), "test url is off");
        }
    }

    /**
     * 取消授权.
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     */
    @RequestMapping(value = "deauth/{fsEa:[\\w]+}/{appId:[\\w]+}", method = RequestMethod.POST)
    public AjaxResult deauth(@PathVariable final String fsEa, @PathVariable final String appId) {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "deauth cannot used in foneshare environment");
        }
        testManager.deauth(fsEa, appId);
        return SUCCESS;
    }

    /**
     * 获取可见组件列表.
     *
     * @param fsEa   企业账号
     * @param userId 员工id
     */
    @RequestMapping(value = "viewComponents/{fsEa:[\\w]+}/{userId:[\\d]+}", method = RequestMethod.GET)
    public AjaxResult viewComponents(@PathVariable final String fsEa, @PathVariable final Integer userId) {
        final BaseResult<List<UserCanViewListVO>> webComponentsResult
                = openFsUserAppViewService.queryComponentsByFsUser(new FsUserVO(fsEa, userId), AppAccessTypeEnum.WEB);
        if (!webComponentsResult.isSuccess()) {
            logger.warn("fail to queryComponentsByFsUser on WEB, fsEa[{}],userId[{}],result[{}]",
                    fsEa, userId, webComponentsResult);
            throw new BizException(webComponentsResult);
        }

        final List<UserCanViewListVO> webComponents = webComponentsResult.getResult();
        webComponents.forEach(openCanViewCompVO -> {
            BaseResult<String> iconUrlResult =
                    appIconService.queryAppIconUrl(openCanViewCompVO.getComponentId(), IconType.WEB);
            if (!iconUrlResult.isSuccess()) {
                logger.warn("fail to appIconService.queryAppIconUrl, componentId[{}], iconType[{}], result[{}]",
                        openCanViewCompVO.getComponentId(), IconType.WEB, iconUrlResult);
                throw new BizException(iconUrlResult);
            }
            openCanViewCompVO.setImageUrl(iconUrlResult.getResult());
        });

        final BaseResult<List<UserCanViewListVO>> iOsComponentsResult
                = openFsUserAppViewService.queryComponentsByFsUser(new FsUserVO(fsEa, userId), AppAccessTypeEnum.IOS);
        if (!iOsComponentsResult.isSuccess()) {
            logger.warn("fail to queryComponentsByFsUser on APP, fsEa[{}],userId[{}],result[{}]",
                    fsEa, userId, webComponentsResult);
            throw new BizException(iOsComponentsResult);
        }

        Map<String, Object> components = Maps.newHashMap();
        components.put("web", webComponentsResult.getResult());
        components.put("app", iOsComponentsResult.getResult());
        return new AjaxResult(components);
    }

    /**
     * 获取企业管理员的可管理的应用列表.
     *
     * @param fsEa 企业账号
     */
    @RequestMapping(value = "apps/{fsEa:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult getApps(@PathVariable final String fsEa) {
        List<OpenDevAppVO> openDevAppVOs = testManager.getAppsByAdmin(fsEa);
        return new AjaxResult(openDevAppVOs);
    }

    /**
     * 组件的可见范围.
     *
     * @param fsEa        企业账号
     * @param componentId 组件id
     */
    @RequestMapping(value = "componentView/{fsEa:[\\w]+}/{componentId:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult componentView(@PathVariable final String fsEa, @PathVariable final String componentId) {
        final FsUserVO fakeAdmin = new FsUserVO(fsEa, true);
        final BaseResult<OpenAppComponentDO> componentResult =
                openAppComponentService.loadOpenAppComponentById(fakeAdmin, componentId);
        if (!componentResult.isSuccess()) {
            logger.warn("fail to loadOpenAppComponentById, componentId[{}], result[{}]",
                    componentId, componentResult);
            throw new BizException(componentResult);
        }
        final AppComponentTypeEnum componentType = AppComponentTypeEnum.getByCode(componentResult.getResult().getComponentType());
        final BaseResult<AppViewDO> viewResult =
                openFsUserAppViewService.loadAppViewByType(fakeAdmin, componentId, componentType);
        if (!viewResult.isSuccess()) {
            logger.warn("fail to loadAppViewByType, fsEa[{}],componentId[{}],result[{}]",
                    fsEa, componentId, viewResult);
            throw new BizException(viewResult);
        }

        return new AjaxResult(viewResult.getResult());
    }

    /**
     * CRM可见性.
     *
     * @param fsEa   企业账号
     * @param userId 员工id
     * @return bool 是否可见
     */
    @RequestMapping(value = "crmAvailable/{fsEa:[\\w]+}/{userId:[\\d]+}", method = RequestMethod.GET)
    public AjaxResult cmrAvailable(@PathVariable final String fsEa, @PathVariable final Integer userId) {
        final BaseResult<Boolean> canAccessResult =
                openFsUserAppViewService.canAccessComponent(new FsUserVO(fsEa, userId), ConfigCenter.getCrmComponentId());
        if (!canAccessResult.isSuccess()) {
            logger.warn("fail to canAccessComponent, fsEa[{}],userId[{}],result[{}]",
                    fsEa, userId, canAccessResult);
            throw new BizException(canAccessResult);
        }
        return new AjaxResult(canAccessResult.getResult());
    }

    /**
     * 推送改变到终端.
     *
     * @param fsEa 企业账号
     * @param view 推送目标
     */
    @RequestMapping(value = "push/{fsEa:[\\w]+}", method = RequestMethod.POST)
    public AjaxResult push(@PathVariable final String fsEa, @RequestBody EmployeeRange view) {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "push cannot used in foneshare environment");
        }
        testManager.push(fsEa, view);
        return SUCCESS;
    }

    /**
     * 清空企业缓存.
     *
     * @param fsEa 企业账号
     */
    @RequestMapping(value = "cache/{fsEa:[\\w]+}", method = RequestMethod.DELETE)
    public AjaxResult clearCache(@PathVariable final String fsEa) {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "clearCache cannot used in foneshare environment");
        }
        testManager.clearCache(fsEa);
        return SUCCESS;
    }

    @RequestMapping(value = "reset/{fsEa:[\\w]+}/{appId:[\\w]+}", method = RequestMethod.DELETE)
    public AjaxResult resetEaApp(@PathVariable final String fsEa, @PathVariable final String appId) {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "reset cannot used in foneshare environment");
        }
        //删除个人试用记录
        com.facishare.open.common.result.BaseResult<Void> deleteTrialsResult =
                employeeTrialService.deleteTrials(appId, fsEa);
        if (!deleteTrialsResult.isSuccess()) {
            logger.warn("fail to employeeTrialService.deleteTrials,appId[{}],fsEa[{}],result[{}]",
                    appId, fsEa, deleteTrialsResult);
            throw new BizException(deleteTrialsResult);
        }

        //删除配额记录
        com.facishare.open.common.result.BaseResult<List<QuotaRecordVo>> quotaRecordsResult =
                quotaService.queryQuotaRecords(fsEa, appId);
        if (!quotaRecordsResult.isSuccess()) {
            logger.warn("fail to quotaService.queryQuotaRecords,fsEa[{}},appId[{}],result[{}]",
                    fsEa, appId, quotaRecordsResult);
            throw new BizException(quotaRecordsResult);
        }
        quotaRecordsResult.getResult().forEach(qr -> {
            com.facishare.open.common.result.BaseResult<Void> deleteResult =
                    quotaService.deleteQuotaRecord(qr.getId());
            if (!deleteResult.isSuccess()) {
                logger.warn("fail to quotaService.deleteQuotaRecord, quotaId[{}],result[{}}",
                        qr.getId(), deleteResult);
            }
        });
        quotaRecordsResult = quotaService.queryQuotaRecords(fsEa, appId);
        if (!quotaRecordsResult.getResult().isEmpty()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), "fail to delete quota");
        }
        //清空缓存
        testManager.clearCache(fsEa);

        //取消授权
        testManager.deauth(fsEa, appId);

        //调用接口判断结果(比如配额, 管理员可见列表等)
        List<OpenDevAppVO> appsByAdmin = testManager.getAppsByAdmin(fsEa);
        if (appsByAdmin.stream().anyMatch(appVo -> appVo.getAppId().equals(appId))) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), "fail to deauth");
        }

        //推送
        final EmployeeRange view = new EmployeeRange();
        view.getDepartment().add(CommonConstant.COMPANY_DEPARTMENT_CODE);
        testManager.push(fsEa, view);
        return SUCCESS;
    }

    @RequestMapping(value = "quotaInfo/{fsEa:[\\w]+}/{appId:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult getQuotaInfo(@PathVariable final String fsEa, @PathVariable final String appId) {
        com.facishare.open.common.result.BaseResult<QuotaVo> quotaVoResult = quotaService.queryQuotaInfo(fsEa, appId);
        if (!quotaVoResult.isSuccess()) {
            logger.warn("fail to quotaService.queryQuotaInfo, fsEa[{}],appId[{}],result[{}]",
                    fsEa, appId, quotaVoResult);
            throw new BizException(quotaVoResult);
        }
        return new AjaxResult(toMapWithAppInfo(quotaVoResult.getResult()));
    }

    @RequestMapping(value = "employeetrials/{fsEa:[\\w]+}/{userId:[\\d]+}", method = RequestMethod.GET)
    public AjaxResult getEmployeeTrial(@PathVariable final String fsEa, @PathVariable final Integer userId) {
        com.facishare.open.common.result.BaseResult<List<String>> appIdsResult =
                employeeTrialService.queryTrialApps(new FsUserVO(fsEa, userId));
        if (!appIdsResult.isSuccess()) {
            logger.warn("fail to employeeTrialService.queryTrialApps, fsEa[{}],userId[{}],result[{}]",
                    fsEa, userId, appIdsResult);
            throw new BizException(appIdsResult);
        }
        if (appIdsResult.getResult().isEmpty()) {
            return new AjaxResult(new OpenAppVO[0]);
        } else {
            AppListResult appListResult = openAppService.loadOpenAppByIds(appIdsResult.getResult());
            return new AjaxResult(appListResult.getResult().stream()
                    .map(openApp -> {
                        OpenDevAppVO openDevAppVO = TestManager.openApp2DevApp(openApp);
                        openDevAppVO.setAppLogoUrl(testManager.getAppIconUrl(openApp.getAppId(), IconType.WEB));
                        return openDevAppVO;
                    })
                    .collect(Collectors.toList()));
        }
    }

    @RequestMapping(value = "quotarecords/{fsEa:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult getQuotas(@PathVariable final String fsEa) {
        List<QuotaRecordVo> quotaRecordVos = testManager.queryQuotaRecords(fsEa, null);
        return new AjaxResult(quotaRecordVos.stream()
                .map(this::toMapWithAppInfo)
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "quotarecords/{fsEa:[\\w]+}/{appId:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult getQuotas(@PathVariable final String fsEa, @PathVariable final String appId) {
        List<QuotaRecordVo> quotaRecordVos = testManager.queryQuotaRecords(fsEa, appId);
        return new AjaxResult(quotaRecordVos.stream()
                .map(this::toMapWithAppInfo)
                .collect(Collectors.toList()));
    }

    @RequestMapping(value = "appsForDevQuery", method = RequestMethod.GET)
    public AjaxResult getAppsForDevQuery() {
        String appIds = ConfigCenter.getAppIdsForDevQuery();
        List<DevConcernedAppVO> appVOs = testManager.queryDevConcernedApps(appIds.split(";"));
        return new AjaxResult(appVOs);
    }

    /**
     * 付费应用：试用/购买.
     */
    @RequestMapping(value = "addquota", method = RequestMethod.POST)
    public AjaxResult addQuota(@RequestBody QuotaRecordVo quotaRecordVo) {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "addQuota cannot used in foneshare environment");
        }
        //参数验证
        if (StringUtils.isBlank(quotaRecordVo.getFsEa()) ||
                StringUtils.isBlank(quotaRecordVo.getAppId()) ||
                quotaRecordVo.getGmtBegin() == null ||
                quotaRecordVo.getGmtEnd() == null) {
            return new AjaxResult(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION);
        }
        //添加纪录
        com.facishare.open.common.result.BaseResult<Long> addQuotaRecordResult
                = openQuotaService.addQuotaRecord(quotaRecordVo.getFsEa(), quotaRecordVo.getAppId(), quotaRecordVo.getQuota(),
                quotaRecordVo.getQuotaType().getCode(), quotaRecordVo.getGmtBegin(), quotaRecordVo.getGmtEnd());
        //添加纪录的结果
        if (!addQuotaRecordResult.isSuccess()) {
            logger.warn("fail to openQuotaService.addQuotaRecord, quotaRecordVo[{}] result[{}]",quotaRecordVo, addQuotaRecordResult);
            throw new BizException(addQuotaRecordResult);
        } else {
            return new AjaxResult(addQuotaRecordResult.getResult());
        }
    }

    @RequestMapping(value = "jobStatus", method = RequestMethod.GET)
    public AjaxResult getJobStatus() {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "getJobStatus cannot used in foneshare environment");
        }
        final Map<String, String> jobStatus = testManager.getJobStatus();
        return new AjaxResult(jobStatus);
    }

    @RequestMapping(value = "triggerWillExpireJob")
    public AjaxResult triggerWillExpireJob() {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "triggerWillExpireJob cannot used in foneshare environment");
        }
        testManager.triggerWillExpireJob();
        return SUCCESS;
    }

    @RequestMapping(value = "triggerLoadJob")
    public AjaxResult triggerLoadJob() {
        //限制, 只能是测试环境使用
        if (!isTestEnv()) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "triggerLoadJob cannot used in foneshare environment");
        }
        testManager.triggerLoadJob();
        return SUCCESS;
    }

    /**
     * 删除仍在可见范围的离职员工
     * 暂时只为CRM提供
     * @param fsEa 企业账号
     * @param componentId 组件ID
     * @param userId 要移除的员工ID
     * @return 移除结果
     */
    @RequestMapping(value = "removeView/{fsEa:[\\w]+}/{componentId:[\\w]+}/{userId:[\\d]+}", method = RequestMethod.DELETE)
    public AjaxResult removeView(@PathVariable(value = "fsEa") String fsEa,
                                 @PathVariable(value = "componentId") String componentId,
                                 @PathVariable(value = "userId") Integer userId) {
        if (!ConfigCenter.getCrmComponentId().equals(componentId)) {
            throw new  BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(),
                    "现阶段只允许移除CRM的可见范围");
        }
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(fsEa), "fsEa is blank");
            Preconditions.checkArgument(StringUtils.isNotBlank(componentId), "componentId is blank");
            Preconditions.checkArgument(userId != null && userId > 0, "userId is illegal");
        } catch (IllegalArgumentException e) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), e.getMessage());
        }
        testManager.removeView(fsEa, componentId, userId);
        return SUCCESS;
    }

    @RequestMapping(value = "removeEnterpriseViewCache/{fsEa:[\\w]+}/{appId:[\\w]+}", method = RequestMethod.DELETE)
    public AjaxResult removeEnterpriseViewCache(@PathVariable(value = "fsEa") String fsEa,
                                                @PathVariable(value = "appId") String appId) {
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(fsEa), "fsEa is blank");
            Preconditions.checkArgument(StringUtils.isNotBlank(appId), "appId is blank");
        } catch (IllegalArgumentException e) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), e.getMessage());
        }
        testManager.removeEnterpriseViewCache(fsEa, appId);
        return SUCCESS;
    }

    @RequestMapping(value = "token//{fsEa:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult queryToken(@PathVariable(value = "fsEa") String fsEa) {
        try {
            Preconditions.checkArgument(StringUtils.isNoneBlank(fsEa, "fsEa is blank"));
            final com.facishare.open.common.result.BaseResult<String> tokenResult = qyWechatAuthService.getAccessTokenByFsEa(fsEa);
            if (!tokenResult.isSuccess()) {
                throw new BizException(tokenResult);
            }
            return new AjaxResult(tokenResult.getResult());
        } catch (IllegalArgumentException e) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), e.getMessage());
        }
    }

    @RequestMapping(value = "decode/{encodedPass}", method = RequestMethod.GET)
    public AjaxResult decodePass(@PathVariable String encodedPass) {
        try {
            return new AjaxResult(PasswordUtil.decode(encodedPass));
        } catch (Exception e) {
            throw new BizException(AjaxCode.PARAM_ERROR, "参数错误");
        }
    }

    @RequestMapping(value = "encode/{originalPass}", method = RequestMethod.GET)
    public AjaxResult encodePass(@PathVariable String originalPass) {
        try {
            return new AjaxResult(PasswordUtil.encode(originalPass));
        } catch (Exception e) {
            throw new BizException(AjaxCode.PARAM_ERROR, "参数错误");
        }
    }

    @RequestMapping(value = "pushCrmPoll//{fsEa:[\\w]+}", method = RequestMethod.GET)
    public AjaxResult pushCrmPoll(@PathVariable(value = "fsEa") String fsEa) {
        try {
            Preconditions.checkArgument(StringUtils.isNoneBlank(fsEa, "fsEa is blank"));
            testManager.pushCrmPoll(fsEa);
            return SUCCESS;
        } catch (IllegalArgumentException e) {
            throw new BizException(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), e.getMessage());
        }
    }

    @RequestMapping("/queryEnterpriseInfo")
    public AjaxResult queryEnterpriseInfo(@RequestParam(value = "fsEa", required = false) String fsEa,
                                          @RequestParam(value = "fsEi", required = false) String fsEi) {
        if (!StringUtils.isBlank(fsEi)) {
            return new AjaxResult(testManager.queryEnterpriseInfoByEi(Integer.valueOf(fsEi)));
        }
        if (!StringUtils.isBlank(fsEa)) {
            return new AjaxResult(testManager.queryEnterpriseInfoByEa(fsEa));
        }
        throw new BizException(AjaxCode.BIZ_EXCEPTION, "fsEa or fsEi required.");
    }

    @RequestMapping("/queryCrmInfoByEa")
    public AjaxResult queryCrmInfoByEa(@RequestParam(value = "fsEa", required = false) String fsEa) {
        checkParam(fsEa, "fsEa required");
        Map<String, Object> map = new HashMap<>();
        map.put("eaInfo", testManager.queryEnterpriseInfoByEa(fsEa));

        //配额信息
        AjaxResult quotaInfoResult = getQuotaInfo(fsEa, ConfigCenter.getCrmAppId());
        map.put("quotaInfo", quotaInfoResult.getData());

        //配额记录(除去已过期)
        List<QuotaRecordVo> quotaRecordVos = testManager.queryQuotaRecords(fsEa, ConfigCenter.getCrmAppId());
        map.put("quotaRecordsWithoutExpired", quotaRecordVos.stream().filter(vo -> vo.getGmtEnd().after(new Date()))
                .map(this::toMapWithAppInfo).collect(Collectors.toList()));

        //组件可见范围
        AjaxResult componentViewResult = componentView(fsEa, ConfigCenter.getCrmComponentId());
        map.put("crmComponentView", componentViewResult.getData());

        //应用管理员
        map.put("crmAppAdmin", testManager.queryAppAdminIdList(fsEa));
        //系统管理员
        map.put("fsAdmin", testManager.queryFsAdminList(fsEa));

        return new AjaxResult(map);
    }

    @RequestMapping("/checkCopyQuota")
    public AjaxResult checkCopyQuota(@RequestParam(value = "fromEa", required = false) String fromEa,
                                     @RequestParam(value = "toEa", required = false) String toEa) {
        checkParam(fromEa, "fromEa required");
        checkParam(toEa, "toEa required");
        Map<String, Object> map = new HashMap<>();
        map.put("fromEa", fromEa);
        map.put("toEa", toEa);

        //CRM配额信息对比
        boolean checkCrmQuota = testManager.isCrmQuotaEqual(fromEa, toEa);
        map.put("isCrmQuotaEqual", checkCrmQuota);
        //CRM应用管理员对比
        boolean checkCrmAppAdmin = testManager.isCrmAppAdminEqual(fromEa, toEa);
        map.put("isCrmAppAdminEqual", checkCrmAppAdmin);
        //目的企业是否包含模板企业的CRM可见范围
        boolean checkCrmView = testManager.isCrmViewIncluded(fromEa, toEa);
        map.put("isCrmViewIncluded", checkCrmView);
        //系统管理员对比
        boolean checkFsAdmin = testManager.isFsAdminEqual(fromEa, toEa);
        map.put("isFsAdminEqual", checkFsAdmin);

        return new AjaxResult(map);
    }

    private Map<String, Object> toMapWithAppInfo(final Object obj) {
        Map<String, Object> map = introspect(obj);
        final String appId = map.get("appId").toString();
        AppResult appResult = openAppService.loadOpenAppFast(appId);
        if (!appResult.isSuccess()) {
            logger.warn("fail to openAppService.loadOpenAppFast, appId[{}],result[{}]",
                    appId, appResult);
        } else {
            map.put("appName", appResult.getResult().getAppName());
            final String appIconUrl = testManager.getAppIconUrl(appId, IconType.WEB);
            map.put("appLogoUrl", appIconUrl);
        }
        return map;
    }

    private static Map<String, Object> introspect(Object obj) {
        Map<String, Object> result = new HashMap<>();
        try {
            BeanInfo info = Introspector.getBeanInfo(obj.getClass());
            for (final PropertyDescriptor pd : info.getPropertyDescriptors()) {
                Method reader = pd.getReadMethod();
                if (reader != null && !"class".equals(pd.getName())) {
                    result.put(pd.getName(), reader.invoke(obj));
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //判断当前是否是测试环境
    private boolean isTestEnv() {
        String env = System.getProperty("spring.profiles.active");
        List<String> testEnv = Lists.newArrayList(ConfigCenter.getTestEnv().split(";"));
        return testEnv.contains(env);
/*
        final String crmAppId = ConfigCenter.getCrmAppId();
        final String crmComponentId = ConfigCenter.getCrmComponentId();
        return crmAppId != null && !crmAppId.equals(FONESHARE_CRM_APP_ID) &&
                crmComponentId != null && !crmComponentId.equals(FONESHARE_CRM_COMPONENT_ID);*/
    }
}