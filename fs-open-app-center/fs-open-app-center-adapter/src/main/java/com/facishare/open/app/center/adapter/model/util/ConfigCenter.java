package com.facishare.open.app.center.adapter.model.util;

import com.github.autoconf.ConfigFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by xialf on 7/7/16.
 *
 * @author xialf
 */
@Slf4j
public final class ConfigCenter {
    private static final String COMMON_CONFIG_FILE_NAME = "fs-open-app-center-common";

    private static String CRM_APP_ID;
    private static String CRM_COMPONENT_ID;

    public static String getCrmAppId() {
        return CRM_APP_ID;
    }

    public static String getCrmComponentId() {
        return CRM_COMPONENT_ID;
    }

    static {
        ConfigFactory.getInstance().getConfig(COMMON_CONFIG_FILE_NAME, config -> {
            CRM_APP_ID = config.get("CRM_APP_ID");
            if (CRM_APP_ID == null) {
                log.error("crm app id is null, please set CRM_APP_ID in " + COMMON_CONFIG_FILE_NAME);
            }

            CRM_COMPONENT_ID = config.get("CRM_COMPONENT_ID");
            if (CRM_COMPONENT_ID == null) {
                log.error("crm component id is null, please set CRM_COMPONENT_ID in " + COMMON_CONFIG_FILE_NAME);
            }
        });
    }
}
