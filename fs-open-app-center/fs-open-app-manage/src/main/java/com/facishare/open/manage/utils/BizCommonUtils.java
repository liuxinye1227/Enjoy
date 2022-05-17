package com.facishare.open.manage.utils;


import com.fxiaoke.common.release.GrayRelease;
import org.springframework.stereotype.Service;


/**
 * 公用工具类
 *
 * @author chenzengyong
 * @date on 2016/2/25.
 */
@Service
public class BizCommonUtils {

    /**
     * 添加数据迁移的灰度.
     * @param fsEa 企业账号
     * @return
     */
    public static boolean isAllowAppDateMigration(final String fsEa) {
        return GrayRelease.isAllow("app-center", "appDateMigration", fsEa);
    }

    /**
     * 添加数据迁移的应用过滤灰度.
     * @param appId
     * @return
     */
    public static boolean isAllowAppDateMigrationAppIds(String appId) {
        return GrayRelease.isAllow("app-center", "appDateMigrationAppIds", appId);
    }

    /**
     * 应用能否删除绑定的服务号的应用过滤灰度.
     * @param appId
     * @return
     */
    public static boolean isAllowAppDeleteRefService(String appId) {
        return GrayRelease.isAllow("app-center", "appDeleteRefService", appId);
    }

    /**
     * 应用能否修改绑定的服务号的应用过滤灰度.
     * @param appId
     * @return
     */
    public static boolean isAllowAppModifyRefService(String appId) {
        return GrayRelease.isAllow("app-center", "appModifyRefService", appId);
    }
}
