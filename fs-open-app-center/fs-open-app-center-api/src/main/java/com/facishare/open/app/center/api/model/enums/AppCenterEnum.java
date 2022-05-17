package com.facishare.open.app.center.api.model.enums;

import java.io.Serializable;

/**
 * 公共枚举，用于将取值少的枚举包装起来.
 *
 * @author zenglb
 * @date 2015年8月17日
 */
public class AppCenterEnum implements Serializable {
    private static final long serialVersionUID = -7900045711649303604L;

    /**
     * 应用可见范围枚举.
     *
     * @author zenglb
     * @date 2015年8月24日
     */
    public enum ViewType {
        WEB, APP
    }

    /**
     * 应用类型.
     *
     * @author zenglb
     * @date 2015年8月24日
     */
    public enum AppType {
        /**
         * 互联服务号
         */
        LINK_SERVICE(11),

        /**
         * 外联服务号(公司自定义)
         */
        OUT_SERVICE_APP(9),
        /**
         * 服务号.
         * 运营服务号.
         */
        OPERATION_SERVICE_APP(8),
        /**
         * 扩展服务号.
         */
        EXT_SERVICE_APP(7),
        /**
         * 基础服务号.
         */
        BASE_SERVICE_APP(6),
        /**
         * 自定义服务号.
         */
        SERVICE(5),
        /**
         * 测试应用.仅用于后台接口测试，现在应用中心不使用，主要与oauth维持一致.
         */
        TEST_APP(4),
        /**
         * 基础应用.
         */
        BASE_APP(3),
        /**
         * 第三方应用.
         */
        DEV_APP(2),
        /**
         * 自定义应用.
         */
        CUSTOM_APP(1),;

        private int value;

        /**
         * 类型.
         *
         * @param value
         */
        AppType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

        public static AppType from(Integer code) {
            if (code == null) {
                throw new IllegalArgumentException("Null app type code is not allowed");
            }
            for (AppType t : values()) {
                if (t.value() == code) {
                    return t;
                }
            }
            throw new IllegalArgumentException("App type code is illegal");
        }
    }
}
