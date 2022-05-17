package com.facishare.open.manage.manager;

import com.facishare.open.addressbook.model.EnterpriseSimpleInfo;
import com.facishare.open.app.center.api.model.EmployeeRange;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.pay.api.model.QuotaRecordVo;
import com.facishare.open.manage.model.DevConcernedAppVO;
import com.facishare.open.manage.model.OpenDevAppVO;

import java.util.List;
import java.util.Map;

/**
 * Created by xialf on 6/6/16.
 *
 * @author xialf
 */
public interface TestManager {
    void deauth(final String fsEa, final String appId);

    List<OpenDevAppVO> getAppsByAdmin(final String fsEa);

    void push(final String fsEa, final EmployeeRange view);

    void clearCache(final String fsEa);

    List<QuotaRecordVo> queryQuotaRecords(final String fsEa, final String appId);

    String getAppIconUrl(final String appOrComponentId, IconType type);

    Map<String, String> getJobStatus();

    void triggerWillExpireJob();

    void triggerLoadJob();

    static OpenDevAppVO openApp2DevApp(final OpenAppDO openApp) {
        OpenDevAppVO result = new OpenDevAppVO();
        result.setAppCreater(openApp.getAppCreater());
        if (null != openApp.getOpenDevDO()) {
            result.setDevName(openApp.getOpenDevDO().getDevName());
        }
        result.setGmtCreate(openApp.getGmtCreate());
        result.setAppId(openApp.getAppId());
        result.setAppDesc(openApp.getAppDesc());
        result.setAppName(openApp.getAppName());
        Integer appType = openApp.getAppType();
        result.setAppType(appType);
        if (AppCenterEnum.AppType.DEV_APP.value() == appType) {
            result.setAppTypeName("扩展应用");
        } else if (AppCenterEnum.AppType.BASE_APP.value() == appType) {
            result.setAppTypeName("基础应用");
        }
        result.setStatus(openApp.getStatus());
        return result;
    }

    List<DevConcernedAppVO> queryDevConcernedApps(String... appIds);

    void removeView(String fsEa, String componentId, Integer userId);

    void removeEnterpriseViewCache(String fsEa, String appId);

    /**
     * 推送CRM可见性到前端/终端轮询中心.
     *
     * @param fsEa 企业账号
     */
    void pushCrmPoll(String fsEa);

    List<String> queryAppAdminIdList(String fsEa);

    List<Integer> queryFsAdminList(String fsEa);

    //CRM配额信息是否相同
    boolean isCrmQuotaEqual(String fromEa, String toEa);
    //CRM应用管理员是否相同
    boolean isCrmAppAdminEqual(String fromEa, String toEa);
    //目的企业是否包含模板企业的CRM可见范围
    boolean isCrmViewIncluded(String fromEa, String toEa);
    //系统管理员是否相同
    boolean isFsAdminEqual(String fromEa, String toEa);

    EnterpriseSimpleInfo queryEnterpriseInfoByEi(Integer fsEi);
    EnterpriseSimpleInfo queryEnterpriseInfoByEa(String fsEa);
}
