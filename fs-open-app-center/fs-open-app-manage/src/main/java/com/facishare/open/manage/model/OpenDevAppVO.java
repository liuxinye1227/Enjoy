package com.facishare.open.manage.model;

import com.facishare.open.app.center.api.model.OpenAppScopeOrderDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangwz on 2015/9/11.
 */
public class OpenDevAppVO implements Serializable {
    private static final long serialVersionUID = -2179086077343690412L;

    /**
     * 应用Id
     */
    private String appId;

    /**
     * app secret
     */
    private String appSecret;

    /**
     * 应用名称
     */
    private String appName;
    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * 应用类型 参数 AppType
     */
    private Integer appType;

    /**
     * 应用类型 名称 AppType
     */
    private String appTypeName;

    /**
     * 应用创建者
     */
    private String appCreater;

    /**
     * 开发者名称
     */
    private String devName;

    /**
     * 应用分类
     */
    private Integer appClass;
    /**
     * 应用分类名称
     */
    private String appClassName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态名
     */
    private String statusName;
    /**
     * 回调消息通知地址
     */
    private String callBackMsgUrl;

    /**
     * 回调域名
     */
    private String callBackDomain;

    /**
     * 应用权限
     */
    private List<OpenAppScopeOrderDO> scopes = new ArrayList<OpenAppScopeOrderDO>();

    /**
     * 应用权限组
     */
    private String[] scopeGroup;

    /**
     * 应用 logo
     */
    private MultipartFile logoIconFile;

    /**
     * 应用logo地址
     */
    private String appLogoUrl;

    /**
     * 应用组件
     */
    private List<OpenAppComponentVO> appComponents = new ArrayList<OpenAppComponentVO>();

    /**
     * token密钥
     */
    private String token;

    /**
     * 加密密钥
     */
    private String encodingAesKey;

    /**
     * 上线时间
     */
    private Date onLineDate;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 付费类型 @com.facishare.open.app.center.api.model.enums.PayTypeEnum
     */
    private Integer payType;

    /**
     * 付费类型名称
     */
    private String payTypeName;

    //------------------------添加灰度状态 &lambo 20151201
    /**
     * 可见状态 1.未启用，2.全部，3.部分可见
     */
    private Integer visibleStatus;
    /**
     * 可见状态名称 1.未配置，2.全部，3.部分可见
     */
    private String visibleStatusName;

    /**
     * 应用详情 图片urls
     */
    private List<String> detailImageUrls = new ArrayList<>();

    /**
     * 应用一句话描述
     */
    private String appIntro;

    /**
     * 试用类型
     * @com.facishare.open.app.center.api.model.enums.TryTypeEnum
     */
    private Integer tryType;

    /**
     * 是否需要授权
     */
    private Integer needAuth; //1：不需要授权  2：需要授权

    /**
     * 是否是官方应用
     */
    private Integer isOfficialApp;//1：官方   2：非官方

    /**
     * 是否推荐应用
     */
    private Integer isRecommendedApp; // 1：推荐  2：非推荐

    /**
     * 应用付费说明.
     */
    private String payDesc;

    /**
     * 试用天数.
     */
    private Integer trialDays;

    /**
     * 是否支持在线购买.
     */
    private Integer purchaseOnline;

    /**
     * app后台服务ip白名单，限制最大100个 ipv4 地址.
     */
    private String[] ipWhiteList;

    /**
     * 是否有关联的服务号
     */
    private Integer hasRefService;  // 1、有   2、没有

    /**
     * 关联的服务号的类型
     * @com.facishare.open.app.center.api.model.enums.AppCenterEnum.AppType
     */
    private Integer refServiceAppType;

    /**
     * 关联的服务号的名
     */
    private String refServiceAppName;

    /**
     * 关联的服务号的id
     */
    private String refServiceAppId;

    public Integer getNeedAuth() {
        return needAuth;
    }

    public void setNeedAuth(Integer needAuth) {
        this.needAuth = needAuth;
    }

    public Integer getIsOfficialApp() {
        return isOfficialApp;
    }

    public void setIsOfficialApp(Integer isOfficialApp) {
        this.isOfficialApp = isOfficialApp;
    }

    public Integer getIsRecommendedApp() { return isRecommendedApp; }

    public void setIsRecommendedApp(Integer isRecommendedApp) { this.isRecommendedApp = isRecommendedApp; }

    public Integer getTryType() {
        return tryType;
    }

    public void setTryType(Integer tryType) {
        this.tryType = tryType;
    }

    public String getAppIntro() {
        return appIntro;
    }

    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }

    public List<String> getDetailImageUrls() {
        return detailImageUrls;
    }

    public void setDetailImageUrls(List<String> detailImageUrls) {
        this.detailImageUrls = detailImageUrls;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppCreater() {
        return appCreater;
    }

    public void setAppCreater(String appCreater) {
        this.appCreater = appCreater;
    }

    public String getCallBackMsgUrl() {
        return callBackMsgUrl;
    }

    public void setCallBackMsgUrl(String callBackMsgUrl) {
        this.callBackMsgUrl = callBackMsgUrl;
    }

    public void setScopes(List<OpenAppScopeOrderDO> scopes) {
        this.scopes = scopes;
    }

    public List<OpenAppScopeOrderDO> getScopes() {
        return scopes;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCallBackDomain() {
        return callBackDomain;
    }

    public void setCallBackDomain(String callBackDomain) {
        this.callBackDomain = callBackDomain;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public String[] getScopeGroup() {
        return scopeGroup;
    }

    public void setScopeGroup(String[] scopeGroup) {
        this.scopeGroup = scopeGroup;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public Integer getAppClass() {
        return appClass;
    }

    public void setAppClass(Integer appClass) {
        this.appClass = appClass;
    }

    public String getAppClassName() {
        return appClassName;
    }

    public void setAppClassName(String appClassName) {
        this.appClassName = appClassName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Date getOnLineDate() {
        return onLineDate;
    }

    public void setOnLineDate(Date onLineDate) {
        this.onLineDate = onLineDate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public MultipartFile getLogoIconFile() {
        return logoIconFile;
    }

    public void setLogoIconFile(MultipartFile logoIconFile) {
        this.logoIconFile = logoIconFile;
    }

    public List<OpenAppComponentVO> getAppComponents() {
        return appComponents;
    }

    public void setAppComponents(List<OpenAppComponentVO> appComponents) {
        this.appComponents = appComponents;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getAppLogoUrl() {
        return appLogoUrl;
    }

    public void setAppLogoUrl(String appLogoUrl) {
        this.appLogoUrl = appLogoUrl;
    }

    public Integer getVisibleStatus() {
        return visibleStatus;
    }

    public void setVisibleStatus(Integer visibleStatus) {
        this.visibleStatus = visibleStatus;
    }

    public String getVisibleStatusName() {
        return visibleStatusName;
    }

    public void setVisibleStatusName(String visibleStatusName) {
        this.visibleStatusName = visibleStatusName;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public Integer getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(Integer trialDays) {
        this.trialDays = trialDays;
    }

    public Integer getPurchaseOnline() {
        return purchaseOnline;
    }

    public void setPurchaseOnline(Integer purchaseOnline) {
        this.purchaseOnline = purchaseOnline;
    }

    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName(String appTypeName) {
        this.appTypeName = appTypeName;
    }

    public String[] getIpWhiteList() {
        return ipWhiteList;
    }

    public void setIpWhiteList(String[] ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    public String getRefServiceAppId() {
        return refServiceAppId;
    }

    public void setRefServiceAppId(String refServiceAppId) {
        this.refServiceAppId = refServiceAppId;
    }

    public Integer getRefServiceAppType() {
        return refServiceAppType;
    }

    public void setRefServiceAppType(Integer refServiceAppType) {
        this.refServiceAppType = refServiceAppType;
    }

    public String getRefServiceAppName() {
        return refServiceAppName;
    }

    public void setRefServiceAppName(String refServiceAppName) {
        this.refServiceAppName = refServiceAppName;
    }

    public Integer getHasRefService() {
        return hasRefService;
    }

    public void setHasRefService(Integer hasRefService) {
        this.hasRefService = hasRefService;
    }
}
