package com.facishare.open.manage.task;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.manager.TaskManager;
import com.facishare.open.manage.task.enums.TaskExecuteTypeEnum;
import com.facishare.open.manage.task.impl.*;
import com.facishare.open.manage.utils.ConfigCenter;
import io.netty.util.internal.ConcurrentSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度器.
 * Created by zenglb on 2016/6/29.
 */
@Service
public class TaskScheduler {

    private static final ConcurrentSet<String> taskIdSet = new ConcurrentSet<>();
    private static Logger taskLog = LoggerFactory.getLogger("TASK_LOG");
    private final Map<String, TaskExecutor> taskExecutorMap = new HashMap<>();

    @Resource
    private TaskManager taskManager;

    @Resource
    public void setMaterialSenderTaskExecutorImpl(MaterialSenderTaskExecutorImpl taskExecutor) {
        taskExecutorMap.put(TaskExecuteTypeEnum.MATERIAL_SENDER.getCode(), taskExecutor);
    }
    @Resource
    public void setMonthActivityTaskExecutorImpl(MonthActivityTaskExecutorImpl taskExecutor) {
        taskExecutorMap.put(TaskExecuteTypeEnum.MONTH_ACTIVITY_SENDER.getCode(), taskExecutor);
    }
    @Resource
    public void setBatchMaterialSenderTaskExecutorImpl(BatchMaterialSenderTaskExecutorImpl taskExecutor) {
        taskExecutorMap.put(TaskExecuteTypeEnum.BATCH_MATERIAL_SENDER.getCode(), taskExecutor);
    }

    @Resource
    public void setCollectAppInfoTaskExecutorImpl(CollectAppInfoTaskExecutorImpl taskExecutor) {
        taskExecutorMap.put(TaskExecuteTypeEnum.COLLECT_APP_INFO.getCode(), taskExecutor);
    }

    /**
     * 查询应用的执行状态。
     *
     * @param taskId
     * @return
     */
    public Integer getTaskExecutionStatus(String taskId) {
        return taskIdSet.contains(taskId) ? CommonConstant.YES : CommonConstant.NO;
    }

    public void checkOpenTask(OpenTaskDO openTask) {
        TaskExecuteTypeEnum taskExecuteType = TaskExecuteTypeEnum.getByCode(openTask.getTaskExecuteType());
        if (null == taskExecuteType) {
            throw new BizException(AjaxCode.PARAM_ERROR, "不支持的任务执行类型.");
        }
        TaskExecutor taskExecutor = taskExecutorMap.get(openTask.getTaskExecuteType());
        if (null == taskExecutor) {
            throw new BizException(AjaxCode.PARAM_ERROR, "没有找到执行类型.");
        }
        taskExecutor.checkOpenTask(openTask);
    }

    public void runTask(OpenTaskDO task) {
        String taskId = task.getTaskId();
        final TaskExecuteTypeEnum taskExecuteType = TaskExecuteTypeEnum.getByCode(task.getTaskExecuteType());
        if (null == taskExecuteType) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "任务类型不支持");
        }
        final TaskExecutor taskExecutor = taskExecutorMap.get(task.getTaskExecuteType());
        if (null == taskExecutor) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "任务提交失败.");
        }
        if (!taskIdSet.add(taskId)) { //任务不可以重复执行.
            return;
        }
        // 任务调度周期最少1秒.
        Integer taskPeriod = task.getTaskPeriod();
        if (null == taskPeriod || taskPeriod < 1000) {
            taskPeriod = 1000;
        }

        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!ConfigCenter.isCanRunningTask()) {
                    taskLog.debug("taskId[{}] , not in task running interval", taskId);
                    return;
                }
                TaskStatusEnum status = null;
                OpenTaskDO newTask = new OpenTaskDO();
                newTask.setTaskId(taskId);
                try {
                    OpenTaskDO task = taskManager.queryOpenTaskByTaskId(taskId);
                    if (null == task) {
                        throw new BizException(AjaxCode.BIZ_EXCEPTION, "任务不存在..");
                    }

                    if (task.getStatus() != TaskStatusEnum.RUNNING.getCode()) {
                        taskIdSet.remove(taskId);
                        scheduledExecutorService.shutdown();
                        return;
                    }

                    Integer currentPage = task.getCurrentPage();
                    if (null == currentPage || currentPage < 1) {
                        currentPage = 1;
                    }
                    Integer pageSize = task.getPageSize();
                    if (null == pageSize || pageSize < 1) {
                        pageSize = 10;
                    }
                    long start = System.currentTimeMillis();
                    taskLog.info("======== doExecute start,taskId[{}], properties[{}], currentPage[{}], pageSize[{}], taskExecuteType[{}]", taskId,
                            task.getProperties(), currentPage, pageSize, taskExecuteType);
                    status = taskExecutor.doExecute(taskId, task.getProperties(), currentPage, pageSize);
                    taskLog.info("======== doExecute end,taskId[{}], properties[{}], currentPage[{}], pageSize[{}], taskExecuteType[{}], status[{}], timeCost[{}]", taskId,
                            task.getProperties(), currentPage, pageSize, taskExecuteType, status, (System.currentTimeMillis() - start));
                    if (null == status || status == TaskStatusEnum.RUNNING) {
                        newTask.setCurrentPage(currentPage + 1);
                        status = null;
                    }
                } catch (Exception | Error e) {
                    taskLog.error("run task failed. taskId[{}]", taskId, e);
                    status = TaskStatusEnum.EXCEPTION;
                }
                if (null != status) {
                    newTask.setStatus(status.getCode());
                    if (status == TaskStatusEnum.COMPLETED) {
                        newTask.setGmtEnd(new Date());
                    }
                }

                try {
                    taskManager.modifyTask(newTask);
                } catch (Exception e) {
                    taskLog.error("updateTask failed. taskId[{}]", taskId, e);
                    taskIdSet.remove(taskId);
                    scheduledExecutorService.shutdown();
                    return;
                }

            }
        }, 1000, taskPeriod, TimeUnit.MILLISECONDS); // 延迟1s调度.
    }
}
