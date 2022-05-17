package com.facishare.open.manage.manager.impl;

import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;
import com.facishare.open.app.center.api.service.TaskService;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.manager.TaskManager;
import com.facishare.open.manage.task.TaskScheduler;
import com.facishare.open.manage.task.enums.TaskExecuteTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * impl.
 * Created by zenglb on 2016/6/14.
 */
@Service
public class TaskManagerImpl implements TaskManager {

    @Resource
    private TaskService taskService;

    @Resource
    private TaskScheduler taskScheduler;

    @Override
    public Pager<Map<String, Object>> queryPageAllTask(Pager<OpenTaskDO> pager) {
        BaseResult<Pager<OpenTaskDO>> pagerBaseResult = taskService.queryPageAllTask(pager);
        if (!pagerBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, pagerBaseResult, "查询任务列表失败.");
        }
        Pager<OpenTaskDO> taskPager = pagerBaseResult.getResult();

        Pager<Map<String, Object>> result = new Pager<>();
        result.setRecordSize(taskPager.getRecordSize());
        result.setData(taskPager.getData().stream().map(this::toMap).collect(Collectors.toList()));
        return result;
    }

    private final Map<String, Object> toMap(OpenTaskDO openTaskDO) {
        Map<String, Object> result = new HashMap<>();
        result.putAll(JsonKit.json2Object(JsonKit.object2json(openTaskDO), result.getClass()));
        result.put("executionStatus", taskScheduler.getTaskExecutionStatus(openTaskDO.getTaskId()));
        result.put("schedulePercentage", openTaskDO.getCurrentPage() / (openTaskDO.getRecordSize() / openTaskDO.getPageSize() + 1) * 100);
        return result;
    }

    @Override
    public String addTask(OpenTaskDO openTask) {
        TaskExecuteTypeEnum taskExecuteType = TaskExecuteTypeEnum.getByCode(openTask.getTaskExecuteType());
        if (null == taskExecuteType) {
            throw new BizException(AjaxCode.PARAM_ERROR, "不支持的任务执行类型.");
        }

        openTask.setCurrentPage(1);
        if (null == openTask.getPageSize()) {
            openTask.setPageSize(10);
        }
        if (StringUtils.isEmpty(openTask.getTaskName())) {
            openTask.setTaskName(taskExecuteType.getDesc() + "-" + openTask.getTaskId());
        }
        openTask.setStatus(TaskStatusEnum.INIT.getCode());
        if (null == openTask.getTaskType()) {
            openTask.setTaskType(1);
        }

        if (openTask.getTaskPeriod() == null) {
            openTask.setTaskPeriod(1000);
        }

        taskScheduler.checkOpenTask(openTask);

        BaseResult<String> taskIdResult = taskService.addTask(openTask);
        if (!taskIdResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, taskIdResult, "添加任务失败.");
        }
        return taskIdResult.getResult();
    }

    @Override
    public void modifyTaskStatus(String taskId, Integer status) {
        BaseResult<OpenTaskDO> openTaskDOBaseResult = taskService.queryOpenTaskByTaskId(taskId);
        if (!openTaskDOBaseResult.isSuccess() || openTaskDOBaseResult.getResult().getStatus() == TaskStatusEnum.DELETED.getCode()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, openTaskDOBaseResult, "任务不存在");
        }
        OpenTaskDO task = new OpenTaskDO();
        task.setTaskId(taskId);
        task.setStatus(status);
        if (TaskStatusEnum.RUNNING.getCode() == status) {
            taskScheduler.checkOpenTask(openTaskDOBaseResult.getResult());
            // 设置任务开始时间.
            task.setGmtStart(new Date());
        }
        //更新任务.
        modifyTask(task);

        if (TaskStatusEnum.RUNNING.getCode() == status) {
            taskScheduler.runTask(openTaskDOBaseResult.getResult());
        }
    }

    @Override
    public void modifyTask(OpenTaskDO openTask) {
        BaseResult<Void> openTaskDOBaseResult = taskService.modifyTask(openTask);
        if (!openTaskDOBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, openTaskDOBaseResult, "更新任务失败");
        }
    }

    @Override
    public OpenTaskDO queryOpenTaskByTaskId(String taskId) {
        BaseResult<OpenTaskDO> openTaskDOBaseResult = taskService.queryOpenTaskByTaskId(taskId);
        if (!openTaskDOBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, openTaskDOBaseResult, "查询单个任务失败");
        }
        return openTaskDOBaseResult.getResult();
    }
}
