package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.storage.mysql.dao.Pager;

/**
 * 任务调度查询.
 * Created by zenglb on 2016/6/14.
 */
public interface TaskService {
    /**
     * 查询所有任务.
     *
     * @param pager
     * @return
     */
    BaseResult<Pager<OpenTaskDO>> queryPageAllTask(Pager<OpenTaskDO> pager);

    /**
     * 添加任务.
     *
     * @param task
     * @return
     */
    BaseResult<String> addTask(OpenTaskDO task);

    /**
     * 修改任务状态.
     *
     * @param taskId 任务id.
     * @return
     */
    BaseResult<OpenTaskDO> queryOpenTaskByTaskId(String taskId);

    /**
     * 更新任务.
     *
     * @param task
     * @return
     */
    BaseResult<Void> modifyTask(OpenTaskDO task);
}
