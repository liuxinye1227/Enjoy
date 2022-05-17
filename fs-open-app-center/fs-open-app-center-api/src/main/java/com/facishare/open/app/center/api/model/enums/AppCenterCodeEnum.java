package com.facishare.open.app.center.api.model.enums;


import com.facishare.open.common.result.ErrCode;

/**
 * 接口的返回码。
 *
 * @author zenglb
 * @date 2015年8月6日
 */
public enum AppCenterCodeEnum implements ErrCode{

    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS", "成功"),


    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(70001, "SYSTEM_EXCEPTION", "系统异常"),

    /**
     * 返回值为空
     */
    RESULT_IS_NULL(70002, "RESULT_IS_NULL", "返回值为空"),

    /**
     * 参数不合法
     */
    PARAM_ILLEGAL_EXCEPTION(70400, "PARAM_ILLEGAL_EXCEPTION", "参数不合法"),

    /**
     * 应用不存在
     */
    APP_NOT_EXISTS(75001, "APP_NOT_EXISTS", "应用不存在"),

    /**
     * 非自定义应用不能删除
     */
    ONLY_CUSTOM_APP_CAN_DELETE(75002, "ONLY_CUSTOM_APP_CAN_DELETE", "只有自定义应用可以删除"),

    /**
     * 添加app失败
     */
    ADD_APP_ERROR(75003, "ADD_APP_ERROR", "添加app失败"),

    /**
     * 添加域名失败
     */
    ADD_NAMESPACE_ERROR(75004, "ADD_NAMESPACE_ERROR", "添加域名失败"),

    /**
     * 后台保存异常
     */
    SAVE_EXCEPTION(75005, "SAVE_EXCEPTION", "后台保存异常"),

    /**
     * 不可以添加一个无效的应用
     */
    CAN_NOT_ADD_INVALID_APP(75006, "CAN_NOT_ADD_INVALID_APP", "不可以添加一个无效的应用"),

    /**
     * 非第三方应用不可取消授权
     */
    NOT_AUTH_IF_NOT_DEV_APP(75008, "NOT_AUTH_IF_NOT_DEV_APP", "非第三方应用不可取消授权"),

    /**
     * 应用已启用
     */
    APP_IS_NORMAL(75009, "APP_IS_NORMAL", "应用已启用"),

    /**
     * 应用已停用
     */
    APP_IS_STOP(75010, "APP_IS_STOP", "应用已停用"),

    /**
     * 应用未授权
     */
    APP_IS_NOT_AUTHED(75011, "APP_IS_NOT_AUTHED", "应用未授权"),

    /**
     * 应用取消授权失败
     */
    CANCEL_EA_AUTH(75012, "CANCEL_EA_AUTH", "应用取消授权失败"),

    /**
     * 图片保存异常
     */
    SAVE_IMAGE_EXCETION(75013, "SAVE_IMAGE_EXCETION", "图片保存异常"),

    /**
     * 应用名称已存在
     */
    APP_NAME_EXISTS(75014, "APP_NAME_EXISTS", "应用名称已存在"),

    /**
     * 必须设置应用的app可见范围
     */
    MUST_SET_APP_VIEW(75015, "MUST_SET_APP_VIEW", "必须设置应用的app可见范围"),

    /**
     * 必须设置应用的web可见范围
     */
    MUST_SET_WEB_VIEW(75016, "MUST_SET_WEB_VIEW", "必须设置应用的web可见范围"),

    /**
     * 上传图像失败
     */
    UPLOAD_ICON_FAIL(75017, "UPLOAD_ICON_FAIL", "上传图像失败"),

    /**
     * 头像文件不存在
     */
    ICON_NOT_EXISTS(75018, "ICON_NOT_EXISTS", "头像文件不存在"),

    /**
     * 头像文件已经存在
     */
    ICON_ALREADY_EXIST(75019, "ICON_ALREADY_EXIST", "头像文件已经存在"),

    /**
     * 初始化基础应用异常
     */
    BASE_APP_INIT_ERROR(75020, "BASE_APP_INIT_ERROR", "初始化基础应用异常"),

    /**
     * 不可以删除他人的应用
     */
    CAN_NOT_DELETE_APP_IF_NOT_YOURS(75021, "CAN_NOT_DELETE_APP_IF_NOT_YOURS", "不可以删除他人应用"),

    /**
     * 该应用对您不可见（灰度发布）
     */
    APP_NOT_VISIBLE_FOR_YOU(75022, "APP_NOT_VISIBLE_FOR_YOU", "该应用对您不可见"),

    /**
     * 应用没有配置（灰度发布）
     */
    APP_NOT_CONFIG(75023, "APP_NOT_VISIBLE_FOR_YOU", "应用没有配置"),
    /**
     * 开发者不存在
     */
    DEV_NOT_EXIST(75024, "DEV_NOT_EXIST", "开发者不存在"),

    /**
     * 只有自定义应用可以启用
     */
    ONLY_CUSTOM_APP_CAN_ON(75025, "ONLY_CUSTOM_APP_CAN_ON", "只有自定义应用可以启用"),

    /**
     * 只有自定义应用可以停用
     */
    ONLY_CUSTOM_APP_CAN_OFF(75026, "ONLY_CUSTOM_APP_CAN_OFF", "只有自定义应用可以停用"),

    /**
     * 创建组件失败
     */
    CREATE_COMPONENT_ERROR(75027, "CREATE_COMPONENT_ERROR", "创建组件失败"),

    /**
     * 检验是否是管理员失败
     */
    CHECK_IS_ADMIN_ERROR(75028, "CHECK_IS_ADMIN_ERROR", "检验是否是管理员失败"),

    /**
     * 组件不存在
     */
    COMPONENT_NOT_EXIST(75029, "COMPONENT_NOT_EXIST", "组件不存在"),

    /**
     * 获取用户的付费类型失败
     */
    GER_USER_PAY_TYPE_FAILED(75030, "COMPONENT_NOT_EXIST", "获取用户的付费类型失败"),

    /**
     * 只有停用的应用才可以删除
     */
    ONLY_STOP_APP_CAN_DELETE(75031, "ONLY_STOP_APP_CAN_DELETE", "只有停用的应用才可以删除"),

    /**
     * 第三方接受永久授权码失败
     */
    DEV_APP_ACCEPT_CODE_ERROR(75032, "DEV_APP_ACCEPT_CODE_ERROR", "第三方接受永久授权码失败"),

    /**
     * 应用已授权
     */
    APP_AUTH_ALREADY_EXIST(75033, "APP_AUTH_ALREADY_EXIST", "应用已授权"),

    /**
     * 初始化基础应用异常
     */
    BASE_SERVICE_INIT_ERROR(75034, "BASE_SERVICE_INIT_ERROR", "初始化基础服务号异常"),
    /**
     * 该应用对您不可见（灰度发布）
     */
    SERVICE_NOT_VISIBLE_FOR_YOU(75035, "SERVICE_NOT_VISIBLE_FOR_YOU", "该服务号对您不可见"),
    /**
     * 不可以添加一个无效的服务号
     */
    CAN_NOT_ADD_INVALID_SERVICE(75037, "CAN_NOT_ADD_INVALID_SERVICE", "不可以添加一个无效的应用"),
    /**
     * 第三方接受永久授权码失败
     */
    DEV_SERVICE_ACCEPT_CODE_ERROR(75038, "DEV_SERVICE_ACCEPT_CODE_ERROR", "第三方接受永久授权码失败"),

    /**
     * 配额不足.
     */
    QUOTA_INSUFFICIENT(75050, "QUOTA_INSUFFICIENT", "配额不足"),

    TRIAL_NOT_ALLOWED(75051, "TRIAL_NOT_ALLOWED", "不支持试用"),
    TRIAL_CHANCE_OVER(75052, "TRIAL_CHANCE_OVER", "试用机会已用完"),
    ON_TRIAL(75053, "ON_TRIAL", "试用中"),
    TRIAL_DENIED_BY_ENTERPRISE_EXPIRED(75054, "TRIAL_DENIED_BY_ENTERPRISE_EXPIRED", "您所在的公司试用机会已用完"),
    UPDATE_ADMIN_FOR_NEVER_USED_APP(75055, "UPDATE_ADMIN_FOR_NEVER_USED_APP", "企业从未使用过该应用，不能更新应用管理员"),

    NO_FS_ADMIN(75060, "NO_FS_ADMIN", "没有企业管理员"),

    /**
     *示例应用未创建.
     */
    DEMO_APP_NOT_EXISTS(75061,"DEMO_APP_NOT_EXISTS" , "示例应用未创建."),

    /**
     * 只有服务号可以启用
     */
    ONLY_SERVICE_CAN_ON(75062, "ONLY_SERVICE_CAN_ON", "只有服务号可以启用"),

    /**
     * 只有服务号可以停用
     */
    ONLY_SERVICE_CAN_OFF(75063, "ONLY_SERVICE_CAN_OFF", "只有服务号可以停用"),

    /**
     * 删除永久授权码失败
     */
    DELETE_AUTHORIZATION_CODE(75064, "DELETE_AUTHORIZATION_CODE", "删除永久授权码失败 "),

    /**
     * 删除底层应用失败
     */
    DELETE_OAUTH_APP(75065, "DELETE_OAUTH_APP", "删除oauth应用失败"),

    /**
     * 服务号审批不存在
     */
    SERVICE_APPROVAL_NOT_EXITS(75066, "SERVICE_APPROVAL_NOT_EXITS", "服务号审批不存在"),
    /**
     * 服务号审批已处理
     */
    SERVICE_APPROVAL_HANDLED(75067, "SERVICE_APPROVAL_HANDLED", "服务号申请已被其他管理员审批"),

    /**
     * 服务号申请
     */
    SERVICE_APPROVAL_CANNOT_HANDLE(75068, "SERVICE_APPROVAL_HANDLED", "服务号申请不能自己审批"),

    /**
     * 删除底层应用失败
     */
    DELETE_APP_SERVICE_REF(75069, "DELETE_APP_SERVICE_REF", "删除应用关联服务号的信息失败"),

    /**
     * 微信服务号和外联服务号：没有有效的关联关系
     */
    NO_VALID_OUTER_SERVICE_LINK_WE_CHAT_SERVICE(75070, "NO_VALID_OUTER_SERVICE_LINK_WE_CHAT_SERVICE", "微信服务号和外联服务号：没有有效的关联关系"),

    /**
     * 微信服务号和外联服务号关联的企业，与用户企业不同
     */
    OUTER_SERVICE_LINK_WE_CHAT_SERVICE_FSEA_NOT_EQUAL_WITH_USER_ENTERPRISE_ACOUNT(75071, "OUTER_SERVICE_LINK_WE_CHAT_SERVICE_FSEA_NOT_EQUAL_WITH_USER_ENTERPRISE_ACOUNT", "微信服务号和外联服务号关联的企业，与用户企业不同"),

    /**
     * 客服人员不存在
     */
    NO_VALID_OPEN_CUSTOMER_EXIST(75072, "NO_VALID_OPEN_CUSTOMER_EXIST", "客服人员不存在"),

    /**
     * 删除外联服务号和微信公众号绑定关系失败
     */
    DELETE_OUTER_SERVICE_WECHAT_SERVICE(75073, "DELETE_OUTER_SERVICE_WECHAT_SERVICE", "删除外联服务号和微信公众号绑定关系失败"),

    /**
     * 发送消息失败
     */
    SEND_MSG_EXCEPTION(75074, "SEND_MSG_EXCEPTION", "发送消息失败"),

    /**
     * 微信服务号和外联服务号：没有关联关系
     */
    NO_OUTER_SERVICE_LINK_WE_CHAT_SERVICE(75075, "NO_OUTER_SERVICE_LINK_WE_CHAT_SERVICE", "微信服务号和外联服务号：没有关联关系"),

    /**
     * 无效的URL
     */
    INVALID_URL(75076, "INVALID_URL", "非法地址"),

    INVALID_ENTERPRISE(75077, "INVALID_ENTERPRISE", "企业状态错误");

    private final int errCode;

    private final String errMsg;

    private final String description;

    /**
     * 构造方法
     *
     * @param errCode     返回code
     * @param description 返回消息
     */
    private AppCenterCodeEnum(int errCode, String errMsg, String description) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.description = description;
    }


    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMessage() {
        return errMsg;
    }

    @Override
    public String getErrDescription() {
        return description;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code
     * @return
     */
    public static AppCenterCodeEnum getByCode(int code) {
        for (AppCenterCodeEnum status : values()) {
            if (status.errCode == code) {
                return status;
            }
        }
        return null;
    }

    public String asString() {
        return this.errCode + " : " + this.errMsg + " : " + this.description;
    }
}
