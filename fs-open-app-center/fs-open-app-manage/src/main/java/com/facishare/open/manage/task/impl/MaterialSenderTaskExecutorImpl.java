package com.facishare.open.manage.task.impl;

import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;
import com.facishare.open.app.center.api.result.AppResult;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.task.TaskExecutor;
import com.facishare.open.manage.task.model.MaterialSendTaskProperties;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.material.api.enums.CreatorTypeEnum;
import com.facishare.open.material.api.enums.MaterialStatusEnum;
import com.facishare.open.material.api.enums.MaterialTypeEnum;
import com.facishare.open.material.api.enums.MessageSendTypeEnum;
import com.facishare.open.material.api.model.vo.AccepterVO;
import com.facishare.open.material.api.model.vo.ImageTextVO;
import com.facishare.open.material.api.model.vo.MaterialViewMessageVO;
import com.facishare.open.material.api.service.MaterialService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zenglb on 2016/6/15.
 */
@Service
public class MaterialSenderTaskExecutorImpl implements TaskExecutor {
    private static Logger logger = LoggerFactory.getLogger("TASK_LOG");

    @Resource
    private OpenAppService openAppService;
    @Resource
    private MaterialService materialService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private OpenAppAdminService openAppAdminService;


    @Override
    public void checkOpenTask(OpenTaskDO openTask) {
        MaterialSendTaskProperties properties = JsonKit.json2Object(openTask.getProperties(), MaterialSendTaskProperties.class);
        Preconditions.checkNotNull(properties.getMaterialAppId(), "素材所属应用Id不能为空");
        Preconditions.checkNotNull(properties.getMaterialId(), "素材id不能为空");
        Preconditions.checkNotNull(properties.getMaterialFsEa(), "素材所属企业账号不能为空");
        Preconditions.checkNotNull(properties.getSendAppId(), "发送的应用id不能为空");

        AppResult appResult = openAppService.loadOpenAppFast(properties.getSendAppId());
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "应用不存在.");
        }
        if (!StringUtils.isEmpty(properties.getSendAppName())) {
            // 添加应用id.用于显示.
            properties.setSendAppName(appResult.getResult().getAppName());
        }
        // 使用任一用户获取素材.
        com.facishare.open.common.result.BaseResult<ImageTextVO> imageTextByIdResult = materialService.findImageTextById(properties.getMaterialId());
        if (!imageTextByIdResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, imageTextByIdResult, "查询素材失败，素材不存在.");
        }

        if (!MaterialStatusEnum.NORMAL.getCode().equals(imageTextByIdResult.getResult().getStatus())) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, imageTextByIdResult, "素材的状态不对.");
        }


        if (!CollectionUtils.isEmpty(properties.getFsEas())) {
            openTask.setRecordSize(properties.getFsEas().size());
        } else {
            com.facishare.open.common.result.BaseResult<Long> recordSizeResult = openAppAdminService.queryCountAllFsEas();
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

        List<String> fsEas = properties.getFsEas();
        if (CollectionUtils.isEmpty(fsEas)) {
            BaseResult<List<String>> listBaseResult = openAppAdminService.queryPagerAllFsEas(currentPage, pageSize);
            if (!listBaseResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, listBaseResult, "查询企业列表失败.");
            }
            fsEas = listBaseResult.getResult();
        }
        logger.info("executors fsEas,taskId[{}], fsEas[{}]", taskId, fsEas);
        if (CollectionUtils.isEmpty(fsEas)) {// 没有企业需要发送.则返回任务结束.
            logger.info("task completed, taskId[{}], fsEas[{}]", taskId, fsEas);
            return TaskStatusEnum.COMPLETED;
        }

        sendMaterial(fsEas, taskId, properties);

        if (!CollectionUtils.isEmpty(properties.getFsEas())) {// 如果ea在任务properties.
            logger.info("task completed, taskId[{}], fsEas[{}]", taskId, fsEas);
            return TaskStatusEnum.COMPLETED;
        }
        return TaskStatusEnum.RUNNING;
    }

    protected List<Integer> getFsAdmins(String fsEa) {
        ListResult<Integer> adminIdsResult = employeeService.getAdminIds(fsEa);
        if (!adminIdsResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, adminIdsResult, "查询企业的企业管理员失败.");
        }
        return adminIdsResult.getResult();
    }

    protected void sendMaterial(List<String> fsEas, String taskId, MaterialSendTaskProperties properties){
        Map<String, Set<Integer>> appAdminsMap = new HashMap<>();
        // null 表示应用管理员或服务号管理员.
        BaseResult<List<FsUserVO>> appAdminsResult = openAppAdminService.queryAppAdminByFsEas(fsEas, null, properties.getAdminAppIds());
        if (!appAdminsResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appAdminsResult, "批量查询应用管理员失败.");
        }

        for (FsUserVO fsUserVO : appAdminsResult.getResult()) {
            Set<Integer> userIds = appAdminsMap.get(fsUserVO.getEnterpriseAccount());
            if (null == userIds) {
                userIds = new HashSet<>();
            }
            userIds.add(fsUserVO.getUserId());
            appAdminsMap.put(fsUserVO.getEnterpriseAccount(), userIds);
        }
        fsEas.forEach(fsEa -> {
            logger.info("execute to ea, taskId[{}], fsEa[{}]", taskId, fsEa);
            try {
                Set<Integer> sendUserIds = new HashSet<>();
                List<Integer> fsAdmins = getFsAdmins(fsEa);
                FsUserVO sender = new FsUserVO(fsEa, true);
                if (!CollectionUtils.isEmpty(fsAdmins)) {
                    sendUserIds.addAll(fsAdmins);
                }
                Set<Integer> appAdmins = appAdminsMap.get(fsEa);
                if (!CollectionUtils.isEmpty(appAdmins)) {
                    sendUserIds.addAll(appAdmins);
                }
                if (!CollectionUtils.isEmpty(sendUserIds)) {
                    MaterialViewMessageVO message = new MaterialViewMessageVO();
                    message.setMaterialType(MaterialTypeEnum.IMAGE_TEXT);
                    message.setMaterialId(properties.getMaterialId());
                    message.setMaterialAppId(properties.getMaterialAppId());
                    message.setMaterialFsEa(properties.getMaterialFsEa());
                    message.setAppId(properties.getSendAppId());
                    AccepterVO accepterVo = new AccepterVO();
                    accepterVo.setDepartments(new ArrayList<>());
                    accepterVo.setEa(fsEa);
                    accepterVo.setIsAllEmployees(false);
                    accepterVo.setEmployees(Lists.newArrayList(sendUserIds));
                    message.setAccepterVO(accepterVo);
                    message.setCreatorTypeEnum(CreatorTypeEnum.APP_ADMIN);
                    message.setSender(sender);

                    // 发送消息执行间隔.
                    try {
                        Thread.sleep(ConfigCenter.getMessageSendPeriod());
                    } catch (InterruptedException e) {
                        logger.error("send sleep failed, taskId[{}], period[{}]", taskId, ConfigCenter.getMessageSendPeriod());
                    }

                    if (ConfigCenter.getTaskSendFlag()){
                        logger.info("materialService.sendMaterial, message[{}].", JsonKit.object2json(message));
                        com.facishare.open.common.result.BaseResult<String> sendMessageResult = materialService.sendMaterial(message, MessageSendTypeEnum.SYSTEM_MSG);
                        if (!sendMessageResult.isSuccess()) {
                            throw new BizException(AjaxCode.BIZ_EXCEPTION, sendMessageResult, "群发图文消息失败.");
                        }
                    } else {
                        logger.info("don't excute materialService.sendMaterial, message[{}].", JsonKit.object2json(message));
                    }
                }
                logger.info("execute success, taskId[{}], fsEa[{}]", taskId, fsEa);
            } catch (Exception e) {
                logger.error("send material failed, taskId[{}], properties[{}], fsEa[{}]", taskId, properties, fsEa, e);
            }
        });
    }
}
