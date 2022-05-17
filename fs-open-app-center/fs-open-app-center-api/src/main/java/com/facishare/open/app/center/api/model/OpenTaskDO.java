package com.facishare.open.app.center.api.model;

import java.util.Date;

/**
 * 任务调度表.
 */
public class OpenTaskDO extends BaseDO {

    /**
     * id
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务类型,1.单次执行任务.2.间隔任务.3.
     */
    private Integer taskType;

    /**
     * 结束时间
     */
    private Date gmtEnd;

    /**
     * 任务开始时间
     */
    private Date gmtStart;

    /**
     * 页大小.
     */
    private Integer pageSize;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 记录数.
     */
    private Integer recordSize;

    /**
     * 任务调用间隔.秒为单位
     */
    private Integer taskPeriod;

    /**
     * 任务执行者类型 任务执行者类型. send_material 发送素材.
     */
    private String taskExecuteType;

    public OpenTaskDO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTaskPeriod() {
        return taskPeriod;
    }

    public void setTaskPeriod(Integer taskPeriod) {
        this.taskPeriod = taskPeriod;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public String getTaskExecuteType() {
        return taskExecuteType;
    }

    public void setTaskExecuteType(String taskExecuteType) {
        this.taskExecuteType = taskExecuteType;
    }
}