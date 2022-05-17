package com.facishare.open.manage.activityea.controller;

import com.facishare.open.manage.activityea.mananger.ActivityEaManager;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @describe: 月活企业
 * @author: xiaoweiwei
 * @date: 2016/6/27 9:55
 */
@Controller
@RequestMapping("/open/manage/activityea/")
public class ActivityEaController extends BaseController {


    @Resource
    private ActivityEaManager activityEaManager;

    /**
     * 抓取月活企业
     * @return
     */
    @RequestMapping("/fetchEa")
    @ResponseBody
    public AjaxResult fetchEa() {
        Long count = activityEaManager.fetchEa();
        return new AjaxResult(count);
    }

    /**
     *
     * @return
     */
    @RequestMapping("/countEa")
    @ResponseBody
    public AjaxResult countEa() {
        Long count = activityEaManager.countEa();
        return new AjaxResult(count);
    }

    /**
     * 上传任务数据文件
     * @param taskFile
     * @return 企业数
     */
    @RequestMapping("/uploadTaskFile")
    @ResponseBody
    public AjaxResult uploadTaskFile(@RequestParam(value = "taskFile")MultipartFile taskFile) {
        checkParam(taskFile, "请上传任务文件");
        Long count  = activityEaManager.readAndSaveTaskFile(taskFile);
        return new AjaxResult(count);
    }
}
