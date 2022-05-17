package com.facishare.open.app.center.api.model.enums;

/**
 * 任务状态的枚举.
 */
public enum TaskStatusEnum {

    /**
     * 初始化
     */
    INIT(1, "初始化"),

    /**
     * 运行中.
     */
    RUNNING(2, "运行中"),

    /**
     * 暂停
     */
    SUSPEND(3, "暂停"),

    /**
     * 任务完成.
     */
    COMPLETED(4, "任务完成"),

    /**
     * 取消任务.
     */
    CANCEL(5, "取消任务"),

    /**
     * 删除任务.
     */
    DELETED(6, "删除任务"),

    /**
     * 任务异常.
     */
    EXCEPTION(7, "任务异常"),;

    private final int code;
    private final String desc;

    TaskStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TaskStatusEnum getByCode(int code) {
        for (TaskStatusEnum accessTypeEnum : values()) {
            if (accessTypeEnum.code == code) {
                return accessTypeEnum;
            }
        }
        throw new EnumConstantNotPresentException(TaskStatusEnum.class, Integer.toString(code));
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
