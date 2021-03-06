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
        Preconditions.checkNotNull(properties.getMaterialAppId(), "??????????????????Id????????????");
        Preconditions.checkNotNull(properties.getMaterialId(), "??????id????????????");
        Preconditions.checkNotNull(properties.getMaterialFsEa(), "????????????????????????????????????");
        Preconditions.checkNotNull(properties.getSendAppId(), "???????????????id????????????");

        AppResult appResult = openAppService.loadOpenAppFast(properties.getSendAppId());
        if (!appResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "???????????????.");
        }
        if (!StringUtils.isEmpty(properties.getSendAppName())) {
            // ????????????id.????????????.
            properties.setSendAppName(appResult.getResult().getAppName());
        }
        // ??????????????????????????????.
        com.facishare.open.common.result.BaseResult<ImageTextVO> imageTextByIdResult = materialService.findImageTextById(properties.getMaterialId());
        if (!imageTextByIdResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, imageTextByIdResult, "????????????????????????????????????.");
        }

        if (!MaterialStatusEnum.NORMAL.getCode().equals(imageTextByIdResult.getResult().getStatus())) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, imageTextByIdResult, "?????????????????????.");
        }


        if (!CollectionUtils.isEmpty(properties.getFsEas())) {
            openTask.setRecordSize(properties.getFsEas().size());
        } else {
            com.facishare.open.common.result.BaseResult<Long> recordSizeResult = openAppAdminService.queryCountAllFsEas();
            if (!recordSizeResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appResult, "????????????????????????.");
            }
            openTask.setRecordSize(recordSizeResult.getResult().intValue());
        }
        if (null == openTask.getRecordSize() || 0 >= openTask.getRecordSize()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "???????????????????????????.");
        }
    }

    @Override
    public TaskStatusEnum doExecute(String taskId, String propertiesJson, Integer currentPage, Integer pageSize) {
        MaterialSendTaskProperties properties = JsonKit.json2Object(propertiesJson, MaterialSendTaskProperties.class);

        List<String> fsEas = properties.getFsEas();
        if (CollectionUtils.isEmpty(fsEas)) {
            BaseResult<List<String>> listBaseResult = openAppAdminService.queryPagerAllFsEas(currentPage, pageSize);
            if (!listBaseResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, listBaseResult, "????????????????????????.");
            }
            fsEas = listBaseResult.getResult();
        }
        logger.info("executors fsEas,taskId[{}], fsEas[{}]", taskId, fsEas);
        if (CollectionUtils.isEmpty(fsEas)) {// ????????????????????????.?????????????????????.
            logger.info("task completed, taskId[{}], fsEas[{}]", taskId, fsEas);
            return TaskStatusEnum.COMPLETED;
        }

        sendMaterial(fsEas, taskId, properties);

        if (!CollectionUtils.isEmpty(properties.getFsEas())) {// ??????ea?????????properties.
            logger.info("task completed, taskId[{}], fsEas[{}]", taskId, fsEas);
            return TaskStatusEnum.COMPLETED;
        }
        return TaskStatusEnum.RUNNING;
    }

    protected List<Integer> getFsAdmins(String fsEa) {
        ListResult<Integer> adminIdsResult = employeeService.getAdminIds(fsEa);
        if (!adminIdsResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, adminIdsResult, "????????????????????????????????????.");
        }
        return adminIdsResult.getResult();
    }

    protected void sendMaterial(List<String> fsEas, String taskId, MaterialSendTaskProperties properties){
        Map<String, Set<Integer>> appAdminsMap = new HashMap<>();
        // null ??????????????????????????????????????????.
        BaseResult<List<FsUserVO>> appAdminsResult = openAppAdminService.queryAppAdminByFsEas(fsEas, null, properties.getAdminAppIds());
        if (!appAdminsResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appAdminsResult, "?????????????????????????????????.");
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

                    // ????????????????????????.
                    try {
                        Thread.sleep(ConfigCenter.getMessageSendPeriod());
                    } catch (InterruptedException e) {
                        logger.error("send sleep failed, taskId[{}], period[{}]", taskId, ConfigCenter.getMessageSendPeriod());
                    }

                    if (ConfigCenter.getTaskSendFlag()){
                        logger.info("materialService.sendMaterial, message[{}].", JsonKit.object2json(message));
                        com.facishare.open.common.result.BaseResult<String> sendMessageResult = materialService.sendMaterial(message, MessageSendTypeEnum.SYSTEM_MSG);
                        if (!sendMessageResult.isSuccess()) {
                            throw new BizException(AjaxCode.BIZ_EXCEPTION, sendMessageResult, "????????????????????????.");
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
