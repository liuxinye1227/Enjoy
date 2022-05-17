package com.facishare.open.manage.task;

import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;

/**
 * 任务执行器.
 * Created by zenglb on 2016/6/15.
 */
public interface TaskExecutor {

    /**
     * 前置验证.
     * @param openTask
     * @return
     */
    void checkOpenTask(OpenTaskDO openTask);

    /**
     * 任务执行.
     * @param taskId
     * @param propertiesJson
     * @return
     */
    TaskStatusEnum doExecute(String taskId, String propertiesJson, Integer currentPage, Integer pageSize);
}
