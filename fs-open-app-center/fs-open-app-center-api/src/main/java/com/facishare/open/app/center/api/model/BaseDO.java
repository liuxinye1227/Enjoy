package com.facishare.open.app.center.api.model;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基本DO
 *
 * @author chenzengyong
 * @date on 2015/6/15.
 */
public class BaseDO implements Serializable {

    private static final long serialVersionUID = -8680655901699178180L;

    protected Long id;

    /**
     * 状态消息
     */
    protected Integer status;

    /**
     * 创始时间
     */
    protected Date gmtCreate;

    /**
     * 最后修改时间
     */
    protected Date gmtModified;

    protected String properties;

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getProperty(String key) {

        if (StringUtils.isEmpty(properties)) {
            return null;
        }

        Map<String, String> map = new Gson().fromJson(properties, new TypeToken<Map<String, String>>() {
        }.getType());

        return map.get(key);
    }

    public void setProperty(String key, String value) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(properties)) {
            map.put(key, value);
        } else {
            map = new Gson().fromJson(properties, new TypeToken<Map<String, String>>() {
            }.getType());
            map.put(key, value);
        }

        setProperties(new Gson().toJson(map));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
