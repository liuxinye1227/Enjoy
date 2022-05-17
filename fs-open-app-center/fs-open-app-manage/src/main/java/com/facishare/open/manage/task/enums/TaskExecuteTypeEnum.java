package com.facishare.open.manage.task.enums;

/**
 * 任务处理类型.
 * Created by zenglb on 2016/6/28.
 */
public enum TaskExecuteTypeEnum {
    MATERIAL_SENDER("MATERIAL_SENDER", "运营发送素材消息."),
    MONTH_ACTIVITY_SENDER("MONTH_ACTIVITY_SENDER", "月活企业发送素材消息."),
    COLLECT_APP_INFO("COLLECT_APP_INFO", "应用收集应用消息."),
    APP_DATA_MIGRATION("APP_DATA_MIGRATION", "应用数据迁移."),
    APP_DATA_MIGRATION_REVERT("APP_DATA_MIGRATION_REVERT", "应用数据迁移还原."),
    BATCH_MATERIAL_SENDER("BATCH_MATERIAL_SENDER", "批量发送素材消息")
    ,;

    private final String code;
    private final String desc;

    TaskExecuteTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    /**
     * 根据code获取任务处理类型.
     *
     * @param code 模式code
     * @return 应用模式
     */
    public static TaskExecuteTypeEnum getByCode(String code) {
        for (TaskExecuteTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
