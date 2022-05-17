package com.facishare.open.app.center.api.model.enums;

/**
 * Created by liangk on 2017/7/27.
 */
public enum OpenAppCreateTemplateTypeEnum {
    /**
     * 示例应用模板
     */
    DEMO_APP(1),

    /**
     * 自建服务号模板
     */
    CUSTOM_SERVICE(2),

    /**
     * 快捷入口模板
     */
    QUICK_ENTER(3),

    /**
     * 空白应用模板
     */
    WHITE_APP(4),

    /**
     * 企业互联服务号模板
     */
    LINK_SERVICE (5);

    private int value;

    OpenAppCreateTemplateTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "OpenAppCreateTemplateTypeEnum" + "value+" + value;
    }


}
