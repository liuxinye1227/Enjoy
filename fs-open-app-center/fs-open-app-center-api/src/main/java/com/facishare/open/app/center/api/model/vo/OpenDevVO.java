package com.facishare.open.app.center.api.model.vo;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangwz on 2015/9/8.
 */
public class OpenDevVO implements Serializable {

    private static final long serialVersionUID = -244072057894511572L;


    /**
     * 开发商Id
     */
    private Long id;
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

    /**
     * 应用个数
     */
    private String appNum;

    /**
     * 服务号个数
     */
    private String serviceNum;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 图片地址
     */
    private CommonsMultipartFile masterLicenseFile;

    /**
     * 介绍
     */
    private String intro;

    /**
     * 手机号码 add by lambo@20160121
     */
    private String mobileNum;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getAppNum() {
        return appNum;
    }

    public void setAppNum(String appNum) {
        this.appNum = appNum;
    }

    public String getServiceNum() { return serviceNum; }

    public void setServiceNum(String serviceNum) { this.serviceNum = serviceNum; }

    public CommonsMultipartFile getMasterLicenseFile() {
        return masterLicenseFile;
    }

    public void setMasterLicenseFile(CommonsMultipartFile masterLicenseFile) {
        this.masterLicenseFile = masterLicenseFile;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getBusinessLicenseId() {
        return businessLicenseId;
    }

    public void setBusinessLicenseId(String businessLicenseId) {
        this.businessLicenseId = businessLicenseId;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgWebsite() {
        return orgWebsite;
    }

    public void setOrgWebsite(String orgWebsite) {
        this.orgWebsite = orgWebsite;
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

    @Override
    public String toString() {
        return "OpenDevVO [devName=" + devName + ", businessLicenseId=" + businessLicenseId + ", businessLicenseId="
                + businessLicenseId + ", orgNo=" + orgNo + "]";
    }


    public String getMasterLicenseId() {
        return masterLicenseId;
    }

    public void setMasterLicenseId(String masterLicenseId) {
        this.masterLicenseId = masterLicenseId;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }
}
