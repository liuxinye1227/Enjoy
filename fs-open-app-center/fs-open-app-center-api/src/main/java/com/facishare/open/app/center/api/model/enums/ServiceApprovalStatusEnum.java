package com.facishare.open.app.center.api.model.enums;

/**
 * @describe: 服务号审批枚举
 * @author: xiaoweiwei
 * @date: 2016/10/18 15:13
 */
public enum ServiceApprovalStatusEnum {

    PENDING(1,"待审批"),//待审批
    APPROVAL(2, "已通过"),//已通过
    REJECT(3, "未通过"),//
    ;
    ServiceApprovalStatusEnum(Integer code, String desc) {
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

    public static ServiceApprovalStatusEnum getByCode(Integer code) {
        for (ServiceApprovalStatusEnum c : ServiceApprovalStatusEnum.values()) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}
