package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenDevDO;
import com.facishare.open.app.center.api.model.property.OpenDevProperties;
import com.facishare.open.app.center.api.model.vo.OpenDevVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenDevService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.manager.AppManager;
import com.facishare.open.manage.manager.OpenDevVisibleManager;
import com.facishare.open.manage.utils.ManagerCommonUtils;
import com.facishare.open.warehouse.api.FileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

/**
 * Created by wangwz on 2015/9/6.
 */
@Controller
@RequestMapping("/open/manage/rest/openDev")
public class OpenDevController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OpenDevController.class);

    @Autowired
    private OpenDevService openDevService;
    @Autowired
    private OpenDevVisibleManager openDevVisibleManager;
    @Autowired
    private AppManager appManager;
    @Autowired
    private FileService fileService;

    /**
     * 获取开发商列表
     *
     * @return
     */
    @RequestMapping("/queryPager")
    @ResponseBody
    public AjaxResult queryPager(Pager<OpenDevVO> pager) {
        if (null == pager) {
            pager = new Pager<OpenDevVO>();
        }
        pager = openDevVisibleManager.listAllOpenDevVisible(pager);
        pager.setParams(null);
        return new AjaxResult(pager);
    }


    /**
     * 查询一个开发者
     *
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/loadOpenDevByDevId", method = {RequestMethod.GET})
    @ResponseBody
    public AjaxResult loadOpenDevByDevId(@RequestParam(value = "devId", required = false) String devId) {
        checkParam(devId, "请填写开发者ID");
        OpenDevDO dev = openDevVisibleManager.loadOpenDevByDevId(devId);
        return new AjaxResult(ManagerCommonUtils.openDevDOToVo(dev));
    }

    /**
     * 保存开发商信息
     *
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/createOpenDev", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult createOpenDev(OpenDevVO form) {
        CommonsMultipartFile file = form.getMasterLicenseFile();
        if (null == file) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "请上传扫描件");
        }
        return updateOpenDev(form);
    }

    /**
     * 保存开发商信息
     *
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/updateOpenDev", method = {RequestMethod.POST})
    @ResponseBody
    public AjaxResult updateOpenDev(OpenDevVO form) {
        checkParam(form, "请填写表单");
        checkParam(form.getDevName(), "开发商名称不能够为空");
        checkParam(form.getBusinessLicenseId(), "营业执照号不能够为空");
        // 保存数据
        try {
            OpenDevDO dev = new OpenDevDO();
            CommonsMultipartFile file = form.getMasterLicenseFile();
            if (null != file && file.getSize() > 0) {
                String ext = "";
                String fileName = file.getOriginalFilename();
                int lastIndex = fileName.lastIndexOf(".");
                if (lastIndex > -1) {
                    ext = fileName.substring(lastIndex + 1);
                }
                com.facishare.open.warehouse.result.BaseResult<String> uploadResult = fileService.upload(file.getBytes(), ext);
                if (!uploadResult.isSuccess()) {
                    throw new BizException(AjaxCode.BIZ_EXCEPTION, uploadResult, "上传扫描件失败");
                }
                dev.setMasterLicenseId(uploadResult.getResult());
            }

            dev.setDevName(form.getDevName());
            dev.setBusinessLicenseId(form.getBusinessLicenseId());

            dev.setOrgAddress(form.getOrgAddress());
            dev.setOrgNo(form.getOrgNo());
            dev.setOrgWebsite(form.getOrgWebsite());

            dev.setContactName(form.getContactName());
            dev.setPhoneNo(form.getPhoneNo());
            dev.setMailAddress(form.getMailAddress());
            dev.setGmtModified(new Date());
            BaseResult<OpenDevDO> openDevResult = null;
            if (null == form.getId()) {
                dev.setGmtCreate(new Date());
                dev.setStatus(CommonConstant.VALID);
                OpenDevProperties newProperties = new OpenDevProperties();
                if (StringUtils.isNotEmpty(form.getIntro())) {
                    newProperties.setIntro(form.getIntro());
                    newProperties.setMobileNum(form.getMobileNum());
                }
                dev.setProperties(newProperties.getJsonString());
                openDevResult = openDevService.createOpenDev(dev);
            } else {
                dev.setId(form.getId());
                if (StringUtils.isNotEmpty(form.getIntro())) {
                    BaseResult<OpenDevDO> openDevDOBaseResult = openDevService.loadOpenDevByDevId(dev.getId() + "");
                    OpenDevDO result = openDevDOBaseResult.getResult();
                    OpenDevProperties oldProperties = OpenDevProperties.fromJson(result.getProperties());
                    if (oldProperties == null) {
                        oldProperties = new OpenDevProperties();
                    }
                    oldProperties.setIntro(form.getIntro());
                    oldProperties.setMobileNum(form.getMobileNum());
                    dev.setProperties(oldProperties.getJsonString());
                }
                openDevResult = openDevService.updateOpenDev(dev);
            }
            if (!openDevResult.isSuccess()) {
                return new AjaxResult(openDevResult.getErrCode(), openDevResult.getErrDescription());
            }
            return new AjaxResult(null);
        } catch (Exception e) {
            logger.error("saveOpenDev exception. form : " + JsonKit.object2json(form), e);
            return new AjaxResult(AjaxCode.PARAM_ERROR, "添加开发者异常");
        }
    }

    /** 删除开发商
     * @return
     */
    @RequestMapping("/deleteByDevId")
    @ResponseBody
    public AjaxResult deleteByDevId(@RequestParam(value = "devId", required = false) String devId) {
        checkParam(devId, "请选择开发商");
//		openDevVisibleManager.deleteByOpenDevId(devId);
        return SUCCESS;
    }
}
