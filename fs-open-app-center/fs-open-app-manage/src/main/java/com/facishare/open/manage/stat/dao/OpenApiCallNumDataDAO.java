package com.facishare.open.manage.stat.dao;

/**
 * 查询应用的api调用次数.
 * Created by zenglb on 2016/6/28.
 */
public interface OpenApiCallNumDataDAO {

    /**
     * 查询应用的调用次数.
     *
     * @param appId 应用id..
     * @return
     */
    Integer queryByAppId(String appId);

}
