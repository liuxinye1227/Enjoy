package com.facishare.open.app.center.external.utils;

import com.github.autoconf.ConfigFactory;
import com.github.autoconf.api.IConfigFactory;

/**
 * 配置中心
 * <p>
 * Author: chenzengyong
 * Create Time: 16.1.12
 */

public class ConfigCenter {
    private static IConfigFactory factory = ConfigFactory.getInstance();

    public static Integer WE_CHAT_MAX_REPLY_TIMEOUT = 48 * 3600000;
    public static String HINT_MSG_WE_CHAT_MAX_REPLY_TIMEOUT = "发送失败，超过48小时未对话，会话已过期";

    static {
        factory.getConfig("fs-open-app-center-external-biz", config -> {
            WE_CHAT_MAX_REPLY_TIMEOUT = config.getInt("WE_CHAT_MAX_REPLY_TIMEOUT", WE_CHAT_MAX_REPLY_TIMEOUT);
            HINT_MSG_WE_CHAT_MAX_REPLY_TIMEOUT = config.get("HINT_MSG_WE_CHAT_MAX_REPLY_TIMEOUT", HINT_MSG_WE_CHAT_MAX_REPLY_TIMEOUT);
        });
    }
}
