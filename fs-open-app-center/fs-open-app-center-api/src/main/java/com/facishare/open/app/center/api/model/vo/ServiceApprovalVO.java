package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe: 服务号审批VO
 * @author: xiaoweiwei
 * @date: 2016/10/18 10:34
 */
public class ServiceApprovalVO implements Serializable {

    private static final long serialVersionUID = 7126768508634993069L;
    /**
     * 审批id,uuid
     */
    private String approvalId;

    /**
     * 服务号logo
     */
    private String serviceLogo;
    /**
     * 服务号名称
     */
    private String serviceName;
    /**
     * 服务号描述
     */
    private String serviceDesc;
    /**
     * 服务号管理员
     */
    private List<Integer> serviceAdmins;
    /**
     * 服务号管理员id:name键值对
     */
    private Map<Integer, String> serviceAdminsInfo;
    /**
     * 服务号订阅范围
     */
    private String serviceView;
    /**
     * 服务号订阅范围id:name键值对。部门id:部门名称和员工id:名称
     */
    private List<Map<Integer, String>> serviceViewInfo;
    /**
     * 服务号模版id
     */
    private String templateId;
    /**
     * 企业
     */
    private String fsEa;
    /**
     * 抄送人列表 .json 同service_admins
     */
    private List<Integer> copies;

    /**
     * 抄送人id:name键值对
     */
    private Map<Integer, String> copiesInfo;
    /**
     * 创建者的用户id
     */
    private Integer creatorUserId;
    /**
     * 创建者名称
     */
    private String creatorUserName;
    /**
     * 创建人的部门名称
     */
    private String creatorUserDepartmentName;
    /**
     * 审批用户id
     */
    private Integer approvalUserId;
    /**
     * 审批id和名称键值对
     */
    private String approvalUserName;
    /**
     * 前置审批id
     */
    private String preApprovalId;
    /**
     * 审批状态 1.待审批  2.审批通过 3.审批不通过
     */
    private Integer status;
    /**
     * 审批信息,主要用于放不通过消息.
     */
    private String approvalMsg;
    /**
     * 创建时间
     */
    private Long gmtCreate;
    /**
     * 修改时间
     */
    private Long gmtModified;

    /**
     * 申请理由.
     */
    private String applyReasons;


    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getServiceLogo() {
        return serviceLogo;
    }

    public void setServiceLogo(String serviceLogo) {
        this.serviceLogo = serviceLogo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public List<Integer> getServiceAdmins() {
        return serviceAdmins;
    }

    public void setServiceAdmins(List<Integer> serviceAdmins) {
        this.serviceAdmins = serviceAdmins;
    }

    public List<Integer> getCopies() {
        return copies;
    }

    public void setCopies(List<Integer> copies) {
        this.copies = copies;
    }

    public String getServiceView() {
        return serviceView;
    }

    public void setServiceView(String serviceView) {
        this.serviceView = serviceView;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getFsEa() {
        return fsEa;
    }

    public void setFsEa(String fsEa) {
        this.fsEa = fsEa;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Integer getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(Integer approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public String getPreApprovalId() {
        return preApprovalId;
    }

    public void setPreApprovalId(String preApprovalId) {
        this.preApprovalId = preApprovalId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApprovalMsg() {
        return approvalMsg;
    }

    public void setApprovalMsg(String approvalMsg) {
        this.approvalMsg = approvalMsg;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Map<Integer, String> getServiceAdminsInfo() {
        return serviceAdminsInfo;
    }

    public void setServiceAdminsInfo(Map<Integer, String> serviceAdminsInfo) {
        this.serviceAdminsInfo = serviceAdminsInfo;
    }

    public List<Map<Integer, String>> getServiceViewInfo() {
        return serviceViewInfo;
    }

    public void setServiceViewInfo(List<Map<Integer, String>> serviceViewInfo) {
        this.serviceViewInfo = serviceViewInfo;
    }

    public Map<Integer, String> getCopiesInfo() {
        return copiesInfo;
    }

    public void setCopiesInfo(Map<Integer, String> copiesInfo) {
        this.copiesInfo = copiesInfo;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public void setCreatorUserName(String creatorUserName) {
        this.creatorUserName = creatorUserName;
    }

    public String getCreatorUserDepartmentName() {
        return creatorUserDepartmentName;
    }

    public void setCreatorUserDepartmentName(String creatorUserDepartmentName) {
        this.creatorUserDepartmentName = creatorUserDepartmentName;
    }

    public String getApprovalUserName() {
        return approvalUserName;
    }

    public void setApprovalUserName(String approvalUserName) {
        this.approvalUserName = approvalUserName;
    }

    public String getApplyReasons() {
        return applyReasons;
    }

    public void setApplyReasons(String applyReasons) {
        this.applyReasons = applyReasons;
    }

    @Override
    public String toString() {
        return "ServiceApprovalVO{" +
                "approvalId='" + approvalId + '\'' +
                ", serviceLogo='" + serviceLogo + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", serviceDesc='" + serviceDesc + '\'' +
                ", serviceAdmins=" + serviceAdmins +
                ", serviceAdminsInfo=" + serviceAdminsInfo +
                ", serviceView='" + serviceView + '\'' +
                ", serviceViewInfo=" + serviceViewInfo +
                ", templateId='" + templateId + '\'' +
                ", fsEa='" + fsEa + '\'' +
                ", copies=" + copies +
                ", copiesInfo=" + copiesInfo +
                ", creatorUserId=" + creatorUserId +
                ", creatorUserName='" + creatorUserName + '\'' +
                ", creatorUserDepartmentName='" + creatorUserDepartmentName + '\'' +
                ", approvalUserId=" + approvalUserId +
                ", approvalUserName='" + approvalUserName + '\'' +
                ", preApprovalId='" + preApprovalId + '\'' +
                ", status=" + status +
                ", approvalMsg='" + approvalMsg + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", applyReasons='" + applyReasons + '\'' +
                '}';
    }
}
