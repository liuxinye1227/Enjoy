package com.facishare.open.app.center.api.model.enums;

/**
 * 应用中心埋点的serivceName.
 * Created by zenglb on 2016/5/13.
 */
public enum BuriedPointBizEnum {

    /**
     * 自定义应用的启用.
     */
    CUSTOM_APP_ENABLE(),

    /**
     * 自定义应用的停用.
     */
    CUSTOM_APP_DISABLE(),

    /**
     * 服务号的启用.
     */
    SERVICE_ENABLE(),

    /**
     * 服务号的停用.
     */
    SERVICE_DISABLE(),

    /**
     * 取消授权.
     */
    APP_CANCEL_AUTH(),

    /**
     * 删除自定义应用.
     */
    DELETE_CUSTOM_APP,

    /**
     * 根据模板创建自定义应用.
     */
    CREATE_CUSTOM_APP_BY_TEMPLATE,

    /**
     * 根据模板创建服务号.
     */
    CREATE_SERVICE_NUM_BY_TEMPLATE,

    /**
     * 群发文本消息.
     */
    GROUP_SEND_TEXT_MSG,

    /**
     * 群发图文消息.
     */
    GROUP_SEND_IMAGE_TEXT_MSG,
    /**
     * 发模板消息.
     */
    APP_SEND_TEMPLATE_MSG,

    /**
     * 修改应用的管理员列表.
     */
    MODIFY_APP_ADMINS,

    /**
     * 自定义应用创建组件.
     */
    CREATE_CUSTOM_APP_COMPONENT,

    /**
     * 自定义应用开启自定义菜单.
     */
    CUSTOM_APP_ENABLE_MENU,

    /**
     * 自定义应用关闭自定义菜单.
     */
    CUSTOM_APP_DISABLE_MENU,

    /**
     * 自定义菜单设置数据.
     */
    CUSTOM_APP_MENU_DATA,

    /**
     * 自定义应用开启开发模式.
     */
    CUSTOM_APP_ENABLE_DEV_MODE,

    /**
     * 自定义应用关闭开发模式.
     */
    CUSTOM_APP_DISABLE_DEV_MODE,

    /**
     * 开启自动回复.
     */
    ENABLE_AUTO_REPLY,

    /**
     * 关闭自动回复功能.
     */
    DISABLE_AUTO_REPLY,

    /**
     * 创建默认自动回复.
     */
    CREATE_DEFAULT_AUTO_REPLY,

    /**
     * 创建关键字自动回复.
     */
    CREATE_KEY_WORD_AUTO_REPLY,

    /**
     * 企业管理员添加扩展应用.
     */
    ADMIN_ADD_EXTEND_APP,

    /**
     * 企业管理员试用(公司试用).
     */
    ADMIN_TRY_EXTEND_APP,

    /**
     * 普通用户试用.
     */
    USER_TRY_EXTEND_APP,

    /**
     * 拉应用列表功能点.
     */
    QUERY_COMPONENTS_BY_FS_USER;


    public String getAction(){
        return this.name();
    }

    /**
     * 服务名.
     * @return
     */
    public static String serviceName() {
        return "APP_CENTER_BIZ";
    }
}
