package com.facishare.pay.business.model;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facishare.pay.business.constant.UserStatus;
import com.facishare.pay.common.model.BaseDO;
import com.facishare.pay.common.model.ExtensionFeatures;
import com.google.common.base.MoreObjects;

/**
 * 用户信息表
 * @author guom
 * @date 2015/10/12 14:34
 */
public class UserInfoDo extends BaseDO implements ExtensionFeatures {

    private static final long serialVersionUID = 611310264978951928L;
    
    /**
     * 自增ID
     * */
    private Long id;

    /**
     * 企业号
     * */
    private String enterpriseAccount;

    /**
     * 纷享用户ID
     * */
    private Long fsUserId;

    /**
     * 用户状态
     * */
    private UserStatus status;

    /**
     * 用户名称
     * */
    private String userName;

    /**
     *  1 员工 2 企业帐号
     */
    private Integer type;
    
    /**
     * 密码
     * */
    private String password;
    /**
     * 扩展字段
     * */
    private JSONObject features = new JSONObject();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnterpriseAccount() {
        return enterpriseAccount;
    }

    public void setEnterpriseAccount(String enterpriseAccount) {
        this.enterpriseAccount = enterpriseAccount;
    }

    public Long getFsUserId() {
        return fsUserId;
    }

    public void setFsUserId(Long fsUserId) {
        this.fsUserId = fsUserId;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String getFeatures() {
        return JSON.toJSONString(features,SerializerFeature.UseISO8601DateFormat);
    }

    @Override
    public void setupFeature(String columnName, String value) {
        features.put(columnName, value);
    }

    @Override
    public void setupFeature(String columnName, Object value) {
        features.put(columnName, value);
    }

    @Override
    public void removeFeature(String columnName) {
        features.remove(columnName);
    }

    @Override
    public String getFeature(String columnName) {
        return features.getString(columnName);
    }

    @Override
    public <T> T getFeature(String columnName, Class<T> clz) {
        return features.getObject(columnName, clz);
    }

    public void setFeatures(String features) {
        this.features = JSONObject.parseObject(features,Feature.AllowISO8601DateFormat);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("enterpriseAccount", enterpriseAccount)
                .add("fsUserId", fsUserId)
                .add("status", status)
                .add("userName", userName)
                .add("type", type)
                .add("features", features.toJSONString()).toString();
    }

    @Override
    public boolean validateParamNotNull() {
        if (StringUtils.isBlank(enterpriseAccount)
                || fsUserId == null
                || status == null
                || userName == null
                || type == null
                || StringUtils.isBlank(password)) {
            return false;
        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int getFeaturesVersion() {
        return 0;
    }
    
}
