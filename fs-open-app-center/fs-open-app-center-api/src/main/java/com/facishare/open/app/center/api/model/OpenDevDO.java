package com.facishare.open.app.center.api.model;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用开发者信息
 *
 * @author zenglb
 * @date 2015年8月17日
 */
public class OpenDevDO extends BaseDO {

    private static final long serialVersionUID = -244072057894511582L;

    /**
     * 开发者名称
     */
    private String devName;
    /**
     * 营业执照编号
     */
    private String businessLicenseId;
    /**
     * 营业执照图片地址
     */
    private String masterLicenseId;
    /**
     * 组织机构代码
     */
    private String orgNo;
    /**
     * 公司地址
     */
    private String orgAddress;
    /**
     * 公司网站（非必选）
     */
    private String orgWebsite;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 电话号码
     */
    private String phoneNo;
    /**
     * 邮箱地址
     */
    private String mailAddress;

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OpenDevDO openDevDO = (OpenDevDO) o;

        if (devName != null ? !devName.equals(openDevDO.devName) : openDevDO.devName != null) {
            return false;
        }
        if (businessLicenseId != null ? !businessLicenseId
                .equals(openDevDO.businessLicenseId) : openDevDO.businessLicenseId != null) {
            return false;
        }
        if (masterLicenseId != null ? !masterLicenseId
                .equals(openDevDO.masterLicenseId) : openDevDO.masterLicenseId != null) {
            return false;
        }
        if (orgNo != null ? !orgNo.equals(openDevDO.orgNo) : openDevDO.orgNo != null) {
            return false;
        }
        if (orgAddress != null ? !orgAddress.equals(openDevDO.orgAddress) : openDevDO.orgAddress != null) {
            return false;
        }
        if (orgWebsite != null ? !orgWebsite.equals(openDevDO.orgWebsite) : openDevDO.orgWebsite != null) {
            return false;
        }
        if (contactName != null ? !contactName.equals(openDevDO.contactName) : openDevDO.contactName != null) {
            return false;
        }
        if (phoneNo != null ? !phoneNo.equals(openDevDO.phoneNo) : openDevDO.phoneNo != null) {
            return false;
        }
        if (mailAddress != null ? !mailAddress.equals(openDevDO.mailAddress) : openDevDO.mailAddress != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = devName != null ? devName.hashCode() : 0;
        result = 31 * result + (businessLicenseId != null ? businessLicenseId.hashCode() : 0);
        result = 31 * result + (masterLicenseId != null ? masterLicenseId.hashCode() : 0);
        result = 31 * result + (orgNo != null ? orgNo.hashCode() : 0);
        result = 31 * result + (orgAddress != null ? orgAddress.hashCode() : 0);
        result = 31 * result + (orgWebsite != null ? orgWebsite.hashCode() : 0);
        result = 31 * result + (contactName != null ? contactName.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + (mailAddress != null ? mailAddress.hashCode() : 0);
        return result;
    }

    public String getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(String businessLicenseId) {
        this.businessLicenseId = businessLicenseId;
    }

    public String getMasterLicenseId() {
        return masterLicenseId;
    }

    public void setMasterLicenseId(String masterLicenseId) {
        this.masterLicenseId = masterLicenseId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
