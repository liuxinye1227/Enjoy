package com.facishare.open.manage.utils;

import com.facishare.open.app.center.api.model.OpenDevDO;
import com.facishare.open.app.center.api.model.property.OpenDevProperties;
import com.facishare.open.app.center.api.model.vo.OpenDevVO;
import org.apache.commons.lang.StringUtils;

/**
 * @author chenzengyong
 * @date on 2016/1/8.
 */
public class ManagerCommonUtils {

    public static OpenDevVO openDevDOToVo(OpenDevDO dev){
        OpenDevVO openDevVO = new OpenDevVO();
        openDevVO.setBusinessLicenseId(dev.getBusinessLicenseId());
        openDevVO.setMasterLicenseId(dev.getMasterLicenseId());
        openDevVO.setContactName(dev.getContactName());
        openDevVO.setDevName(dev.getDevName());
        openDevVO.setId(dev.getId());
        openDevVO.setMailAddress(dev.getMailAddress());
        openDevVO.setOrgAddress(dev.getOrgAddress());
        openDevVO.setOrgNo(dev.getOrgNo());
        openDevVO.setOrgWebsite(dev.getOrgWebsite());
        openDevVO.setPhoneNo(dev.getPhoneNo());

        if(StringUtils.isNotEmpty(dev.getProperties())){
            OpenDevProperties openDevProperties = OpenDevProperties.fromJson(dev.getProperties());
            if(StringUtils.isNotEmpty(openDevProperties.getIntro())){
                openDevVO.setIntro(openDevProperties.getIntro());
                openDevVO.setMobileNum(openDevProperties.getMobileNum());
            }
        }

        return openDevVO;
    }

}
