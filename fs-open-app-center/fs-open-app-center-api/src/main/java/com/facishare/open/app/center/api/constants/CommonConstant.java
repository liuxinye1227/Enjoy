package com.facishare.open.app.center.api.constants;

/**
 * 公共常量
 *
 * @author zenglb
 * @date 2015年7月31日
 */
public interface CommonConstant {
    /**
     * 1 判断 1.是2.否
     */
    int YES = 1;
    /**
     * 2 判断 1.是2.否
     */
    int NO = 2;

    /**
     * 1 状态 1.有效 2.无效
     */
    int VALID = 1;
    /**
     * 2 状态 1.有效 2.无效
     */
    int INVALID = 2;

    /**
     * 成功.
     */
    int SUCCESS = 1;

    /**
     * 失败.
     */
    int FAILED = 2;

    /**
     * 应用绑定状态 1.启用
     */
    int APP_BIND_STATUS_ON = 1;
    /**
     * 应用绑定状态 2.停用
     */
    int APP_BIND_STATUS_OFF = 2;

    /**
     * 1.纷享账号创建应用
     */
    int APP_CREATER_FS_ACCOUNT = 1;
    /**
     * 2.开发者账号创建应用
     */
    int APP_CREATER_DEV_ACCOUNT = 2;

    /**
     * 模板状态 启用
     */
    int APP_TEMPLATE_ENABLE_STATUS = 1;
    /**
     * 模板状态 停用
     */
    int APP_TEMPLATE_DISABLE_STATUS = 0;

    /**
     * open_app_app_class 字典中的应用分类的key
     */
    String DICT_TYPE_APP_CLASS = "open_app_app_class";
    /**
     * open_app_app_label 字典中的应用标签的key
     */
    String DICT_TYPE_APP_LABEL = "open_app_app_label";
    /**
     * open_app_app_mode 字典中的应用模式的key
     */
    String DICT_TYPE_APP_MODE = "open_app_app_mode";
    /**
     * open_app_app_status 字典中的应用模式的key
     */
    String DICT_TYPE_APP_STATUS = "open_app_status";

    /**
     * 3.自定义应用Label 4 其他
     */
    int APP_LABEL_CUSTOM = 3;

    /**
     * 4 其他.
     */
    int APP_LABEL_OTHER = 4;
    /**
     * 1 普通模式 2.开发者模式
     */
    int APP_MODE_COMMON = 1;

    /**
     * 3 自定义应用的class 1 企业系统的class
     */
    int APP_CLASS_CUSTOM = 3;

    int APP_CLASS_ENTERPRISE = 1;

    /**
     * 999999 应用可见范围中部门参数包含 这个code表示全公司 。
     */
    int COMPANY_DEPARTMENT_CODE = 999999;

    /**
     * 更新CRM可见性.
     */
    int UPDATE_USER_PROPERTIES_CRM_AVAIL = 9;

    /**
     * 可见修改.使用msgSessionService.updateUserPropertiesBatch方法时传送的key
     */
    int UPDATE_USER_PROPERTIES_VIEW_MODIFIED = 100;

    /**
     * 通知手机侧服务号通讯录的事件key.使用msgSessionService.updateUserPropertiesBatch方法时传送的key
     */
    int UPDATE_USER_PROPERTIES_SERVICE_NUMBER_MODIFIED = 20;

    /**
     * 外联服务号修改.使用msgSessionService.updateUserPropertiesBatch方法时传送的key
     */
    int UPDATE_USER_PROPERTIES_OUTER_SERVICE_MODIFIED = 21;

    /**
     * 模板停用状态
     */
    int TEMPLATE_STATUS_DISABLE = 0;

    /**
     * 模板启用状态
     */
    int TEMPLATE_STATUS_ENABLE = 1;
    /**
     * 1 灰度发布应用相对于企业的可见，1,包含，2，排除
     */
    int APP_EA_VISIBLE_INCLUDE = 1;
    /**
     * 2 灰度发布应用相对于企业的可见，1,包含，2，排除
     */
    int APP_EA_VISIBLE_EXCLUDE = 2;
    /**
     * 应用的logo图片大小 5m
     */
    int APP_LOGO_IMAGE_MAX_SIZE = 5 * 1024 * 1024;

    /**
     * 关键词类型
     */
    int TEMPLATE_KEYWORD_TYPE = 0;

    /**
     * 图片类型
     */
    int TEMPLATE_MEDIAWORD_PICTURE_TYPE = 1;

    /**
     * 示例应用的模板创建id.
     */
    String DEMO_APP_CREATE_TEMPLATE_ID = "771500c6fd8e4101868813d8edd326cd";

    //服务号相关常量
    /**
     * 空白应用模板.
     */
    String BLANK_APP_CREATE_TEMPLATE_ID = "2f52b5e407654ca388dd8d391956f648";

    /**
     * 快捷入口应用模板
     */
    String FAST_APP_CREATE_TEMPLATE_ID = "42cf5c7c1dfe4c8e86c5e8de2be3e2b3";

    /**
     * 服务号组件跳转地址标识.
     */
    String SERVICE_LOGIN_URL = "SERVICE_COMPONENT_TYPE";

    int SERVICE_NUMBER_ON = 1;//开启服务号开关
    int SERVICE_NUMBER_OFF = 0;//关闭服务号开关

    int WORK_ORDER_ON = 1; //开启工单  1.开启
    int WORK_ORDER_OFF = 0; //关闭工单 0：关闭

    int QUESTIONNAIRE_ON = 1; //开启问卷   1.开启
    int QUESTIONNAIRE_OFF = 0; //关闭问卷  0：关闭

    int CUSTOMMENU_ON = 1; //开启自定义菜单   1.开启
    int CUSTOMMENU_OFF = 0; //关闭自定义菜单  0：关闭

    int AUTOREPLY_ON = 1;  //开启自动回复   1.开启
    int AUTOREPLY_OFF = 0; //关闭自动回复   0：关闭

    int UPDATE_LOGO = 1;  //修改Logo
    int UPDATE_NAME = 1;  //修改名称
    int UPDATE_FUNCTION_INTRODUCTION = 1;  //修改功能介绍
    int UPDATE_SUBSCRIPTION_RANGE = 1;     //修改订阅范围
    int UPDATE_INNER_SUBSCRIPTION_RANGE = 2; //修改互联服务号内部可见范围
    int UPDATE_OUTER_SUBSCRIPTION_RANGE = 3; //修改互联服务号外部可见范围
    int UPDATE_SAFETY_SET = 1;             //修改安全设置

    int UPDATE_OFFICIAL_COMP_APP_RANGE = 1;     //修改官方应用app组件订阅范围
    int UPDATE_OFFICIAL_COMP_WEB_RANGE = 2;     //修改官方应用web组件订阅范围

    int DEVELOPER_MODE_ON = 1;            //开启开发者模式
    int DEVELOPER_MODE_UPDATE = 2;        //修改开发者模式参数
    int DEVELOPER_MODE_OFF = 0;           //关闭开发者模式

    int DELETE_APP_MODE = 1;            //删除应用模式
    int DELETE_SERVICE_MODE = 2;        //删除服务号模式

    int CANCEL_AUTHORIZATION_OUTER_SERVICE = 2;        //取消外联服务号授权

    int UPDATE_APP_COM_LOGO = 1;           //app修改应用入口Logo
    int UPDATE_APP_COM_URL = 1;            //app修改入口URL
    int UPDATE_APP_COM_LOGIN = 1;          //app修改应用入口
    int UPDATE_APP_COM_VIEW = 1;           //app修改可见范围
    int UPDATE_WEB_COM_LOGO = 1;           //app修改WEB入口Logo
    int UPDATE_WEB_COM_URL = 1;            //app修改WEB入口URL
    int UPDATE_WEB_COM_LOGIN = 1;          //app修改WEB入口
    int UPDATE_WEB_COM_VIEW = 1;           //app修改WEB可见范围

    int UPDATE_APP_CALLBACK_MSG_URL = 1;       //修改安全设置
    int UPDATE_APP_CALLBACK_DOMAIN = 1;        //修改安全设置
    int UPDATE_APP_IPWHITES = 1;              //修改安全设置

    int ACCESSPERMISSION_INTERNAL = 1;   //
    String SAFETY_SET_INFO_1 = "仅订阅范围内分享，不允许分享到外部社交平台";
    int ACCESSPERMISSION_OPEN = 2;
    String SAFETY_SET_INFO_2 = "分享到外部社交平台，比如微信，朋友圈，QQ等";

    int WECHAT_IS_UNAUTHERIZED = -1;    //未认证的服务号，订阅号
    int WECHAT_IS_AUTHERIZED = 0;       //认证的服务号，订阅号
    int WECHAT_SERVICE = 2;           //微信服务号

    int UNBIND_FROM_OUTER_SERVICE = 1;       //通过外联服务号取消授权
    int UNBIND_FROM_WECHAT = 2;    //通过微信公众号取消授权
    int UNBIND_FROM_UPGRADE_TO_CUSTOMER_SERVICE = 3;    //升级为专业客服系统导致取消授权

    int IS_SERVICE = 0; //服务号标识
    int IS_OUTER_SERVICE = 1; //外联服务号标识
    int IS_LINK_SERVICE = 2; //互联服务号标识
}
