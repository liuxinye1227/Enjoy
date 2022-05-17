package com.facishare.open.app.center.api.model.property;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * openAppDO 的 properties属性
 *
 * @author chenzengyong
 * @date on 2016/1/11.
 */
public class OpenAppProperties implements Serializable{

    private static final long serialVersionUID = -8857847488683731511L;
    /**
     * 详情图片url
     */
    private List<String> detailImageUrl = new ArrayList<>();

    /**
     * 应用一句话介绍
     */
    private String appIntro;

    /**
     * 是否需要授权
     */
    private Integer needAuth; //1 不需要授权； 2 需要授权

    /**
     * 是否是官方应用
     */
    private Integer isOfficialApp;//1 官方； 2 非官方

    /**
     * 是否推荐应用
     */
    private Integer isRecommendedApp; // 1 推荐； 2 非推荐

    /**
     * 试用天数.
     */
    private Integer trialDays;

    /**
     * 是否支持在线购买.
     */
    private Integer purchaseOnline; // 1支持, 2不支持

    /**
     * 创建应用的模板id.
     */
    private String createTemplateId;

    /**
     * 应用付费说明.
     */
    private String payDesc;

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

    public String getAppIntro() {
        return appIntro;
    }

    public void setAppIntro(String appIntro) {
        this.appIntro = appIntro;
    }

    public List<String> getDetailImageUrl() {
        return detailImageUrl;
    }

    public void setDetailImageUrl(List<String> detailImageUrl) {
        this.detailImageUrl = detailImageUrl;
    }

    public String getCreateTemplateId() {
        return createTemplateId;
    }

    public void setCreateTemplateId(String createTemplateId) {
        this.createTemplateId = createTemplateId;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public  String getJsonString() {
        return new Gson().toJson(this);
    }

    public static OpenAppProperties fromJson(String json) {
        if(StringUtils.isEmpty(json)){
            return null;
        }

        return new Gson().fromJson(json, OpenAppProperties.class);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OpenAppProperties that = (OpenAppProperties) o;

        return !(detailImageUrl != null ? !detailImageUrl.equals(that.detailImageUrl) : that.detailImageUrl != null);

    }

    @Override
    public int hashCode() {
        return detailImageUrl != null ? detailImageUrl.hashCode() : 0;
    }
}
