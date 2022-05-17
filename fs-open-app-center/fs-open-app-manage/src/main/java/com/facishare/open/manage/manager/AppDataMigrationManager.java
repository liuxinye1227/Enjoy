package com.facishare.open.manage.manager;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.common.model.FsUserVO;

/**
 * 数据迁移代码.
 * Created by lambo on 2015/9/9.
 */
public interface AppDataMigrationManager {

    /**
     * 查询单个应用信息.
     *
     * @param appId
     * @return
     */
    OpenAppDO loadOpenAppFast(String appId);

    /**
     * 修改应用类型.
     *
     * @param appId
     * @param service
     */
    void modifyAppType(String appId, AppCenterEnum.AppType service);

    /**
     * 修改应用组件的所属于应用id.
     *
     * @param fsAdminUser
     * @param appComponentId
     * @param newAppId
     */
    void updateOpenAppComponent(FsUserVO fsAdminUser, String appComponentId, String newAppId);
}
