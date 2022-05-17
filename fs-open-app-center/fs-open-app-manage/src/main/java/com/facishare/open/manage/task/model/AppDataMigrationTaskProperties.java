package com.facishare.open.manage.task.model;

import java.util.List;

/**
 * 数据迁移任务的其它属性.
 * Created by zenglb on 2016/6/29.
 */
public class AppDataMigrationTaskProperties {

    /**
     * 需要处理的应用id.
     */
    private List<String> appIds;

    /**
     * 需要处理的企业id.
     */
    private List<String> fsEas;

    public List<String> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<String> appIds) {
        this.appIds = appIds;
    }

    public List<String> getFsEas() {
        return fsEas;
    }

    public void setFsEas(List<String> fsEas) {
        this.fsEas = fsEas;
    }
}
