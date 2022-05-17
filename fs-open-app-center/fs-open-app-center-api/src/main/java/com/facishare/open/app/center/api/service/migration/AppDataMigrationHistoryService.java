package com.facishare.open.app.center.api.service.migration;

import com.facishare.open.app.center.api.model.AppDataMigrationHistoryDO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.Collection;

/**
 * 应用数据迁移服务历史信息保存.
 * Created by zenglb on 2016/6/27.
 */
public interface AppDataMigrationHistoryService {

    /**
     * 批量执行历史.
     *
     * @param historyList
     * @return
     */
    BaseResult<Void> saveBatch(Collection<AppDataMigrationHistoryDO> historyList);

    /**
     * 查询单个应用的执行历史.
     * @param appId
     * @return
     */
    BaseResult<AppDataMigrationHistoryDO> queryHistoryByAppId(String appId);

    /**
     *  历史信息表.
     * @param pager
     * @return
     */
    BaseResult<Pager<AppDataMigrationHistoryDO>> queryPager(Pager<AppDataMigrationHistoryDO> pager);

    /**
     * 删除数据迁移历史.
     * @param appId
     * @return
     */
    BaseResult<Void> deleteHistory(String appId);
}
