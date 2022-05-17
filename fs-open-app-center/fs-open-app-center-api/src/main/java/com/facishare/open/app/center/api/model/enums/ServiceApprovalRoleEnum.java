package com.facishare.open.app.center.api.model.enums;

/**
 * @describe: 服务号审批枚举
 * @author: xiaoweiwei
 * @date: 2016/10/18 15:13
 */
public enum ServiceApprovalRoleEnum {

    CREATOR(1,"申请人"),//待审批
    APPROVAL(2, "审批人"),//已通过
    COPY(3, "抄送人"),//抄送人
    OTHER(4, "其它"),//其它
    ;
    ServiceApprovalRoleEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    private Integer code;

    private String desc;



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static ServiceApprovalRoleEnum getByCode(Integer code) {
        for (ServiceApprovalRoleEnum c : ServiceApprovalRoleEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
