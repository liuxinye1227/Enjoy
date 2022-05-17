package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>互联服务号-管理列表-根据平台组接口CustomerServiceAuthVo组装数据VO</p>
 * @dateTime 2017/7/28 19:11
 * @author yinyang yiny@fxiaoke.com
 * @version 1.0 
 */
public class OpenLinkServiceVO implements Serializable {

    private static final long serialVersionUID = 6161180661661608612L;

    /** from EnterpriseSimpleVo */
    private String enterpriseAccount;
    private String enterpriseName;
    private String enterpriseShortName;
    private String enterpriseShortNameSpell;

    /** from CustomerServiceAuthVo */
    private String downstreamEa;
    private List<String> customServiceIds;

    private List<OpenAppVO> openAppVOList;

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseShortName() {
        return enterpriseShortName;
    }

    public void setEnterpriseShortName(String enterpriseShortName) {
        this.enterpriseShortName = enterpriseShortName;
    }

    public String getEnterpriseShortNameSpell() {
        return enterpriseShortNameSpell;
    }

    public void setEnterpriseShortNameSpell(String enterpriseShortNameSpell) {
        this.enterpriseShortNameSpell = enterpriseShortNameSpell;
    }

    public String getDownstreamEa() {
        return downstreamEa;
    }

    public void setDownstreamEa(String downstreamEa) {
        this.downstreamEa = downstreamEa;
    }

    public List<String> getCustomServiceIds() {
        return customServiceIds;
    }

    public void setCustomServiceIds(List<String> customServiceIds) {
        this.customServiceIds = customServiceIds;
    }

    public List<OpenAppVO> getOpenAppVOList() {
        return openAppVOList;
    }

    public void setOpenAppVOList(List<OpenAppVO> openAppVOList) {
        this.openAppVOList = openAppVOList;
    }

    @Override
    public String toString() {
        return "OpenLinkServiceVO{" +
                "enterpriseAccount='" + enterpriseAccount + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseShortName='" + enterpriseShortName + '\'' +
                ", enterpriseShortNameSpell='" + enterpriseShortNameSpell + '\'' +
                ", downstreamEa='" + downstreamEa + '\'' +
                ", customServiceIds=" + customServiceIds +
                ", openAppVOList=" + openAppVOList +
                '}';
    }
}
