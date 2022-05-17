package com.facishare.open.app.center.api.model.enums;

/**
 * 试用状态
 *
 * @author chenzengyong
 * @date on 2016/2/23.
 */
public enum TryStatusEnum {

    //============admin 针对企业管理员
    ADMIN_ADDED(1, "已添加", "已添加", TargetTypeEnum.GRAY, TryUrlEnum.VOID),//已添加,不可跳转
    ADMIN_ABLE_TO_ADD(2, "可添加", "添加", TargetTypeEnum.URL, TryUrlEnum.AUTH_URL),//添加,跳转到企业授权页面 1
    ADMIN_ABLE_TO_TRY(3, "可试用", "试用", TargetTypeEnum.URL, TryUrlEnum.TRY_URL),//试用,跳转到试用页面 2
    ADMIN_NOT_ABLE_TO_TRY(4, "企业试用机会已经用完，不能再试用", "试用", TargetTypeEnum.ADMIN_UNABLE_TRY, TryUrlEnum.VOID),//试用,弹出提示框（管理员）
    ADMIN_NOT_TRY_APP(5, "该应用不允许企业试用", "", TargetTypeEnum.HIDE, TryUrlEnum.VOID),//"" 不展示文字
    ADMIN_TRYING(6, "企业试用中", "试用中", TargetTypeEnum.GRAY, TryUrlEnum.VOID),//试用中,不可跳转
    BOUGHT(7, "已购买", "已购买", TargetTypeEnum.GRAY, TryUrlEnum.VOID),//已购买,不可跳转
    ADMIN_THIRD_APP_ABLE_TO_TRY(8, "第三方可企业试用", "试用", TargetTypeEnum.URL, TryUrlEnum.AUTH_URL),//第三方企业试用 2
    PURCHASE_EXPIRED(9, "购买已到期", "购买已到期", TargetTypeEnum.GRAY, TryUrlEnum.VOID), //购买已到期, 不可跳转
    ENTERPRISE_TRIAL_EXPIRED(10, "企业试用已到期", "试用已到期", TargetTypeEnum.GRAY, TryUrlEnum.VOID), //企业试用已到期, 不可跳转


    //===========employee 针对普通员工
//    EMPLOYEE_ADDED(20, "已添加"),
    EMPLOYEE_ABLE_TO_ADD(21, "可添加", "添加", TargetTypeEnum.URL, TryUrlEnum.NOTIFY_ADMIN_URL),//添加,跳转到通知管理员开通 3
    EMPLOYEE_ABLE_TO_TRY(22, "可试用", "试用", TargetTypeEnum.URL, TryUrlEnum.TRY_URL),//试用,跳转到试用页面 2
    EMPLOYEE_NOT_ABLE_TO_TRY(23, "个人试用机会已经用完，不能再试用", "试用", TargetTypeEnum.EMPLOYEE_UNABLE_TRY, TryUrlEnum.VOID),//试用,弹出提示框（个人）
    EMPLOYEE_NOT_TRY_APP(24, "该应用不允许个人试用", "", TargetTypeEnum.HIDE, TryUrlEnum.VOID),//"" 不展示文字
    EMPLOYEE_TRYING(25, "个人试用中", "试用中", TargetTypeEnum.GRAY, TryUrlEnum.VOID),//试用中,不可跳转
    EMPLOYEE_THIRD_APP_NOT_ABLE_TO_TRY(26, "非官方应用不允许个人试用", "试用", TargetTypeEnum.URL, TryUrlEnum.NOTIFY_ADMIN_URL),//试用，通知管理员开通 3
    EMPLOYEE_TRIAL_EXPIRED(27, "个人试用已到期", "试用已到期", TargetTypeEnum.GRAY, TryUrlEnum.VOID), //个人试用已到期, 不可跳转
    EMPLOYEE_DENIED_BY_ENTERPRISE_EXPIRED(28, "企业已到期导致个人不能试用", "已到期", TargetTypeEnum.GRAY, TryUrlEnum.VOID) //企业购买或试用到期, 导致个人不能试用

    ;

    private int code;

    private String desc;

    /**
     * 按钮名称.
     */
    private String text;

    /**
     * 按钮类型.
     */
    private TargetTypeEnum targetTypeEnum;

    /**
     * 跳转地址.
     */
    private TryUrlEnum tryUrlEnum;

    TryStatusEnum(int code, String desc, String text,
                  TargetTypeEnum targetTypeEnum, TryUrlEnum tryUrlEnum) {
        this.code = code;
        this.desc = desc;
        this.text = text;
        this.targetTypeEnum = targetTypeEnum;
        this.tryUrlEnum = tryUrlEnum;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getText() {
        return text;
    }

    public TargetTypeEnum getTargetTypeEnum() {
        return targetTypeEnum;
    }

    public TryUrlEnum getTryUrlEnum() {
        return tryUrlEnum;
    }
}
