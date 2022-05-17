package com.facishare.open.manage.task.impl;

import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.MonthActivityEaService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.task.model.MaterialSendTaskProperties;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.material.api.enums.CreatorTypeEnum;
import com.facishare.open.material.api.enums.MaterialStatusEnum;
import com.facishare.open.material.api.model.vo.ImageTextVO;
import com.facishare.open.material.api.service.MaterialService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @describe: 给月活企业发送运营素材
 * @author: xiaoweiwei
 * @date: 2016/6/23 17:10
 */
@Service
public class MonthActivityTaskExecutorImpl extends MaterialSenderTaskExecutorImpl {
    private static Logger logger = LoggerFactory.getLogger("TASK_LOG");

    @Resource
    private OpenAppService openAppService;
    @Resource
    private MaterialService materialService;
    @Resource
    private MonthActivityEaService monthActivityEaService;


    @Override
    public void checkOpenTask(OpenTaskDO openTask) {
        MaterialSendTaskProperties properties = JsonKit.json2Object(openTask.getProperties(), MaterialSendTaskProperties.class);
        Preconditions.checkNotNull(properties.getSendAppId(), "发送的应用id不能为空");
        boolean isSendMaterial = true;
        if (!StringUtils.isBlank(properties.getTextMsg())){
            isSendMaterial = false;
        }else {
            Preconditions.checkNotNull(properties.getMaterialAppId(), "素材所属应用Id不能为空");
            Preconditions.checkNotNull(properties.getMaterialId(), "素材id不能为空");
            Preconditions.checkNotNull(properties.getMaterialFsEa(), "素材所属企业账号不能为空");
        }

        AppResult appResult = openAppService.loadOpenAppFast(properties.getSendAppId());
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用不存在.");
        }
        if (!StringUtils.isEmpty(properties.getSendAppName())) {
            // 添加应用id.用于显示.
            properties.setSendAppName(appResult.getResult().getAppName());
        }
        if (isSendMaterial){
            // 使用任一用户获取素材.
            com.facishare.open.common.result.BaseResult<ImageTextVO> imageTextByIdResult = materialService.findImageTextById(properties.getMaterialId());
            if (!imageTextByIdResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, imageTextByIdResult, "查询素材失败，素材不存在.");
            }

            if (!MaterialStatusEnum.NORMAL.getCode().equals(imageTextByIdResult.getResult().getStatus())) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, imageTextByIdResult, "素材的状态不对.");
            }
        }

        if (!CollectionUtils.isEmpty(properties.getFsEas())) {
            openTask.setRecordSize(properties.getFsEas().size());
        } else {
            BaseResult<Long> recordSizeResult = monthActivityEaService.countAll();
            if (!recordSizeResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "计算应用数据失败.");
            }
            openTask.setRecordSize(recordSizeResult.getResult().intValue());
        }
        if (null == openTask.getRecordSize() || 0 >= openTask.getRecordSize()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "没有需要处理的任务.");
        }

    }

    @Override
    public TaskStatusEnum doExecute(String taskId, String propertiesJson, Integer currentPage, Integer pageSize) {
        MaterialSendTaskProperties properties = JsonKit.json2Object(propertiesJson, MaterialSendTaskProperties.class);
        //如果超过发送量，则挂起。
        if (properties.getSendCapacity() != null && properties.getSendCapacity() < currentPage * pageSize){
            return TaskStatusEnum.SUSPEND;
        }
        BaseResult<List<String>> eaListResult = monthActivityEaService.queryPagerAllEaList(currentPage, pageSize);
        if (!eaListResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, eaListResult, "查询企业账号列表失败.");
        }
        List<String> fsEas = eaListResult.getResult();

        logger.info("executors fsEas,taskId[{}], fsEas[{}]", taskId, fsEas);
        if (CollectionUtils.isEmpty(fsEas)) {// 没有企业需要发送.则返回任务结束.
            logger.info("task completed, taskId[{}], fsEas[{}]", taskId, fsEas);
            return TaskStatusEnum.COMPLETED;
        }
        super.sendMaterial(fsEas, taskId, properties);
        return TaskStatusEnum.RUNNING;
    }

}
