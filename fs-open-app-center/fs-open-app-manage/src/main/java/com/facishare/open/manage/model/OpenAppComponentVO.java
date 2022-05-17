package com.facishare.open.manage.model;

import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.vo.OpenComponentUrlGrayVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 应用组件
 *
 * @author zenglb
 * @date 2015年10月14日
 */
public class OpenAppComponentVO extends OpenAppComponentDO {
    private static final long serialVersionUID = 3797533212506742786L;

    /**
     * 应用组件 logo
     */
    private MultipartFile logoIconFile;

    /**
     * 跳转地址
     */
    private String loginUrl;

    /**
     * 应用标签名称
     */
    private String componentLabelName;

    /**
     * 组件logo地址
     */
    private String componentLogoUrl;

    /**
     * 组件地址和对应灰度的企业id,名称集合
     *
     */
    private OpenComponentUrlGrayVO openComponentUrlGrayVO;

    /**
     * 应用类型
     */
    private Integer appType;

    public MultipartFile getLogoIconFile() {
        return logoIconFile;
    }

    public void setLogoIconFile(MultipartFile logoIconFile) {
        this.logoIconFile = logoIconFile;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getComponentLabelName() {
        return componentLabelName;
    }

    public void setComponentLabelName(String componentLabelName) {
        this.componentLabelName = componentLabelName;
    }

    public String getComponentLogoUrl() {
        return componentLogoUrl;
    }

    public void setComponentLogoUrl(String componentLogoUrl) {
        this.componentLogoUrl = componentLogoUrl;
    }

    public OpenComponentUrlGrayVO getOpenComponentUrlGrayVO() {
        return openComponentUrlGrayVO;
    }

    public void setOpenComponentUrlGrayVO(OpenComponentUrlGrayVO openComponentUrlGrayVO) {
        this.openComponentUrlGrayVO = openComponentUrlGrayVO;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
