package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.manager.TaskManager;
import com.facishare.open.manage.task.enums.TaskExecuteTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 任务
 */
@Controller
@RequestMapping("/open/manage/rest/task")
public class TaskController extends BaseController {

    @Resource
    private TaskManager taskManager;

    @RequestMapping("/queryPage")
    @ResponseBody
    public AjaxResult queryPage(Pager<OpenTaskDO> pager) {
        Pager<Map<String, Object>> result = taskManager.queryPageAllTask(pager);
        return new AjaxResult(result);
    }

    @RequestMapping("/addTask")
    @ResponseBody
    public AjaxResult addTask(OpenTaskDO openTask) {
        checkParam(openTask.getTaskExecuteType(), "请选择任务类型.");
        checkParam(openTask.getProperties(), "请选择使用参数.");

        TaskExecuteTypeEnum taskExecuteType = TaskExecuteTypeEnum.getByCode(openTask.getTaskExecuteType());
        if (null == taskExecuteType) {
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "不支持的任务类型.");
        }
        if (null == openTask.getTaskType()) { // 默认为单次执行的任务.
            openTask.setTaskType(1);
        }
        String taskId = taskManager.addTask(openTask);
        return new AjaxResult(taskId);
    }

    @RequestMapping("/modifyTaskStatus")
    @ResponseBody
    public AjaxResult modifyTaskStatus(@RequestParam(value = "taskId", required = false) String taskId,
                                       @RequestParam(value = "status", required = false) Integer status) {
        checkParam(taskId, "请填写任务id");
        checkParam(status + "", "[2356]{1}", "状态参数不对.");
        taskManager.modifyTaskStatus(taskId, status);
        return SUCCESS;
    }

    @RequestMapping("/modifyTask")
    @ResponseBody
    public AjaxResult modifyTask(OpenTaskDO openTask) {
        checkParam(openTask.getTaskId(), "请填写任务id");
        // task type 不允许修改.
        openTask.setTaskExecuteType(null);
        // 任务状态不可修改.
        openTask.setStatus(null);
        taskManager.modifyTask(openTask);
        return SUCCESS;
    }
}
