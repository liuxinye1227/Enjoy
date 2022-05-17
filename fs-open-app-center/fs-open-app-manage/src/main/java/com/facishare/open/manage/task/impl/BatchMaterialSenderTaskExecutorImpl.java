package com.facishare.open.manage.task.impl;

import com.facishare.open.app.ad.api.utils.IdGenerateUtils;
import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.MonthActivityEaService;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.task.model.MaterialSendTaskProperties;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.material.api.enums.CreatorTypeEnum;
import com.facishare.open.material.api.enums.MaterialTypeEnum;
import com.facishare.open.material.api.model.vo.AccepterVO;
import com.facishare.open.material.api.model.vo.MaterialViewMessageVO;
import com.facishare.open.material.api.service.MaterialService;
import com.facishare.open.msg.constant.MessageTypeEnum;
import com.facishare.open.msg.model.SendTextMessageVO;
import com.facishare.open.msg.result.MessageResult;
import com.facishare.open.msg.service.SendMessageService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 群发素材
 * Created by linchf on 2016/8/31.
 */
@Service
public class BatchMaterialSenderTaskExecutorImpl extends MonthActivityTaskExecutorImpl {
    private static Logger logger = LoggerFactory.getLogger("TASK_LOG");

    @Resource
    private MonthActivityEaService monthActivityEaService;
    @Resource
    private OpenAppAdminService openAppAdminService;
    @Resource
    private MaterialService materialService;
    @Resource
    private SendMessageService sendMessageService;

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
        if (StringUtils.isNoneBlank(properties.getTextMsg())){
            sendTextMessage(fsEas, taskId, properties);
        }else {
            sendMaterial(fsEas, taskId, properties);
        }
        return TaskStatusEnum.RUNNING;
    }

    private void sendTextMessage(List<String> fsEas, String taskId, MaterialSendTaskProperties properties) {
        Map<String, Set<Integer>> appAdminsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(properties.getAppAdminTypes()) || !CollectionUtils.isEmpty(properties.getAdminAppIds())) {
            BaseResult<List<FsUserVO>> appAdminsResult = openAppAdminService.queryAppAdminByFsEas(fsEas, properties.getAppAdminTypes(), properties.getAdminAppIds());
            if (!appAdminsResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appAdminsResult, "批量应用管理员失败.");
            }

            for (FsUserVO fsUserVO : appAdminsResult.getResult()) {
                Set<Integer> userIds = appAdminsMap.get(fsUserVO.getEnterpriseAccount());
                if (null == userIds) {
                    userIds = new HashSet<>();
                }
                userIds.add(fsUserVO.getUserId());
                appAdminsMap.put(fsUserVO.getEnterpriseAccount(), userIds);
            }
        }

        fsEas.forEach(fsEa -> {
            logger.info("execute to ea, taskId[{}], fsEa[{}]", taskId, fsEa);
            try {
                Set<Integer> sendUserIds = new HashSet<>();
                FsUserVO sender = new FsUserVO(fsEa, true);
                if (properties.getToSysAdmin() != null && properties.getToSysAdmin() == CommonConstant.YES) {
                    List<Integer> fsAdmins = getFsAdmins(fsEa);
                    if (!CollectionUtils.isEmpty(fsAdmins)) {
                        sendUserIds.addAll(fsAdmins);
                    }
                }

                Set<Integer> appAdmins = appAdminsMap.get(fsEa);
                if (!CollectionUtils.isEmpty(appAdmins)) {
                    sendUserIds.addAll(appAdmins);
                }
                if (!CollectionUtils.isEmpty(sendUserIds)) {
                    //发送消息
                    SendTextMessageVO textMessageVO = new SendTextMessageVO();
                    textMessageVO.setEnterpriseAccount(fsEa);
                    textMessageVO.setType(MessageTypeEnum.TEXT);
                    textMessageVO.setPostId(IdGenerateUtils.generateUUID());
                    textMessageVO.setContent(properties.getTextMsg());
                    textMessageVO.setAppId(properties.getSendAppId());
                    textMessageVO.setToUserList(Lists.newArrayList(sendUserIds));
                    textMessageVO.setAdminUserId(sender.asStringUser());

                    // 发送消息执行间隔.
                    try {
                        Thread.sleep(ConfigCenter.getMessageSendPeriod());
                    } catch (InterruptedException e) {
                        logger.error("send sleep failed, taskId[{}], period[{}]", taskId, ConfigCenter.getMessageSendPeriod());
                    }

                    if (ConfigCenter.getTaskSendFlag()){
                        logger.info("sendMessageService.sendTextMessage, message[{}].", JsonKit.object2json(textMessageVO));
                        MessageResult sendMessageResult = sendMessageService.sendTextMessage(textMessageVO, com.facishare.open.msg.constant.MessageSendTypeEnum.SYSTEM_PROMPT);
                        if (!sendMessageResult.isSuccess()) {
                            logger.error("failed to send text message: messageVO[{}], textMsg[{}], result[{}]",
                                    textMessageVO, textMessageVO, sendMessageResult);
                            throw new BizException(AjaxCode.BIZ_EXCEPTION, sendMessageResult, "群发图文消息失败.");
                        }
                    } else {
                        logger.info("don't execute sendMessageService.sendTextMessage, message[{}].", JsonKit.object2json(textMessageVO));
                    }
                }
                logger.info("execute success, taskId[{}], fsEa[{}]", taskId, fsEa);
            } catch (Exception e) {
                logger.error("send material failed, taskId[{}], properties[{}], fsEa[{}]", taskId, properties, fsEa, e);
            }
        });
    }

    @Override
    protected void sendMaterial(List<String> fsEas, String taskId, MaterialSendTaskProperties properties) {
        List<String> appAdminType = properties.getAppAdminTypes();

        Map<String, Set<Integer>> appAdminsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(appAdminType)) {
            BaseResult<List<FsUserVO>> appAdminsResult = openAppAdminService.queryAppAdminByFsEas(fsEas, appAdminType, properties.getAdminAppIds());
            if (!appAdminsResult.isSuccess()) {
                throw new BizException(AjaxCode.BIZ_EXCEPTION, appAdminsResult, "批量应用管理员失败.");
            }

            for (FsUserVO fsUserVO : appAdminsResult.getResult()) {
                Set<Integer> userIds = appAdminsMap.get(fsUserVO.getEnterpriseAccount());
                if (null == userIds) {
                    userIds = new HashSet<>();
                }
                userIds.add(fsUserVO.getUserId());
                appAdminsMap.put(fsUserVO.getEnterpriseAccount(), userIds);
            }
        }
        fsEas.forEach(fsEa -> {
            logger.info("execute to ea, taskId[{}], fsEa[{}]", taskId, fsEa);
            try {
                Set<Integer> sendUserIds = new HashSet<>();
                FsUserVO sender = new FsUserVO(fsEa, true);
                if (properties.getToSysAdmin() != null && properties.getToSysAdmin() == CommonConstant.YES) {
                    List<Integer> fsAdmins = getFsAdmins(fsEa);
                    if (!CollectionUtils.isEmpty(fsAdmins)) {
                        sendUserIds.addAll(fsAdmins);
                    }
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
                        com.facishare.open.common.result.BaseResult<String> sendMessageResult = materialService.sendMaterial(message,
                                com.facishare.open.material.api.enums.MessageSendTypeEnum.SYSTEM_MSG);
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


