package com.facishare.open.manage.manager;

import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.Map;

/**
 * 任务.
 * Created by zenglb on 2016/6/14.
 */
public interface TaskManager {

    /**
     * 分页查询.
     *
     * @param pager
     * @return
     */
    Pager<Map<String, Object>> queryPageAllTask(Pager<OpenTaskDO> pager);

    /**
     * 修改状态.
     *
     * @param taskId
     * @param status
     */
    void modifyTaskStatus(String taskId, Integer status);

    /**
     * 添加任务.
     *
     * @param openTask
     * @return
     */
    String addTask(OpenTaskDO openTask);

    /**
     * 更新任务.
     *
     * @param openTask
     */
    void modifyTask(OpenTaskDO openTask);

    /**
     * 加载单个任务.
     * @param taskId
     * @return
     */
    OpenTaskDO queryOpenTaskByTaskId(String taskId);
}
