package com.facishare.open.app.center.api.service.migration;

import com.facishare.open.app.center.api.model.AppDataMigrationConfigDO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.Collection;

/**
 * 应用数据迁移配置信息的服务.
 * Created by zenglb on 2016/6/24.
 */
public interface AppDataMigrationConfigService {

    /**
     * 批量保存应用数据迁移信息.
     * @param entities 信息列表.
     * @return
     */
    BaseResult<Void> saveBatch(Collection<AppDataMigrationConfigDO> entities);

    /**
     *  查询应用数据迁移的配置数据.
     * @param pager
     * @return
     */
    BaseResult<Pager<AppDataMigrationConfigDO>> queryPager(Pager<AppDataMigrationConfigDO> pager);
}
