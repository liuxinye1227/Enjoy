package com.facishare.open.manage.task.impl;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.AppDataMigrationConfigDO;
import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.OpenTaskDO;
import com.facishare.open.app.center.api.model.enums.AppCenterEnum;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.model.enums.AppStatus;
import com.facishare.open.app.center.api.model.enums.TaskStatusEnum;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenAppComponentService;
import com.facishare.open.app.center.api.service.OpenAppService;
import com.facishare.open.app.center.api.service.migration.AppDataMigrationConfigService;
import com.facishare.open.autoreplymsg.result.GetCustomServiceSwitchResult;
import com.facishare.open.autoreplymsg.service.MsgAutoReplyService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.custom.menu.api.enums.CustomMenuStatusEnum;
import com.facishare.open.custom.menu.api.model.vo.OpenMenuVO;
import com.facishare.open.custom.menu.api.service.CustomMenuService;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.stat.dao.OpenApiCallNumDataDAO;
import com.facishare.open.manage.task.TaskExecutor;
import com.facishare.open.manage.task.model.AppDataMigrationTaskProperties;
import com.facishare.open.manage.utils.BizCommonUtils;
import com.facishare.open.msg.constant.MessageSendTypeEnum;
import com.facishare.open.msg.result.MessageExhibitionResult;
import com.facishare.open.msg.service.MessageExhibitionService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * impl.
 * Created by zenglb on 2016/6/23.
 */
@Service
public class CollectAppInfoTaskExecutorImpl implements TaskExecutor {
    private static Logger taskLog = LoggerFactory.getLogger("TASK_LOG");

    @Resource
    private OpenAppService openAppService;
    @Resource
    private OpenAppComponentService openAppComponentService;
    @Resource
    private CustomMenuService customMenuService;
    @Resource
    private AppDataMigrationConfigService appDataMigrationConfigService;
    @Resource
    private MessageExhibitionService messageExhibitionService;
    @Resource
    private OpenApiCallNumDataDAO openApiCallNumDataDAO;
    @Resource
    private MsgAutoReplyService msgAutoReplyService;

    @Override
    public void checkOpenTask(OpenTaskDO openTask) {
        AppDataMigrationTaskProperties properties = JsonKit.json2Object(openTask.getProperties(), AppDataMigrationTaskProperties.class);
        Pager<OpenAppDO> pager = new Pager<>();
        pager.setCurrentPage(1);
        pager.setPageSize(1);
        pager.addParam("statusList", Lists.newArrayList(AppStatus.ON_LINE.getStatus()));
        pager.addParam("appTypes", Lists.newArrayList(AppCenterEnum.AppType.CUSTOM_APP.value()));
        if (!CollectionUtils.isEmpty(properties.getAppIds())) {
            pager.addParam("appIds", properties.getAppIds());
        }
        if (!CollectionUtils.isEmpty(properties.getFsEas())) {
            pager.addParam("fsEas", properties.getFsEas());
        }

        BaseResult<Pager<OpenAppDO>> pagerBaseResult = openAppService.queryAppByStatusAndTypesByPager(pager);
        if (!pagerBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, pagerBaseResult, "加载需要处理的应用列表失败.");
        }
        openTask.setRecordSize(pagerBaseResult.getResult().getRecordSize());
        if (null == openTask.getRecordSize() || 0 >= openTask.getRecordSize()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "没有需要处理的任务.");
        }
    }

    @Override
    public TaskStatusEnum doExecute(String taskId, String propertiesJson, Integer currentPage, Integer pageSize) {
        AppDataMigrationTaskProperties properties = JsonKit.json2Object(propertiesJson, AppDataMigrationTaskProperties.class);

        Pager<OpenAppDO> pager = new Pager<>();
        pager.setCurrentPage(currentPage);
        pager.setPageSize(pageSize);
        pager.addParam("statusList", Lists.newArrayList(AppStatus.ON_LINE.getStatus()));
        pager.addParam("appTypes", Lists.newArrayList(AppCenterEnum.AppType.CUSTOM_APP.value()));
        if (!CollectionUtils.isEmpty(properties.getAppIds())) {
            pager.addParam("appIds", properties.getAppIds());
        }
        if (!CollectionUtils.isEmpty(properties.getFsEas())) {
            pager.addParam("fsEas", properties.getFsEas());
        }

        BaseResult<Pager<OpenAppDO>> pagerBaseResult = openAppService.queryAppByStatusAndTypesByPager(pager);
        if (!pagerBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, pagerBaseResult, "加载需要处理的应用列表失败.");
        }
        List<AppDataMigrationConfigDO> configList = new ArrayList<>();
        pager = pagerBaseResult.getResult();
        if (!CollectionUtils.isEmpty(pager.getData())) {
            taskLog.info("record size [{}]", pager.getData().size());
            pager.getData().forEach(openAppDO -> {
                String appId = openAppDO.getAppId();
                // 是否灰度企业.只收集灰度企业的应用数据.
                if (!BizCommonUtils.isAllowAppDateMigration(openAppDO.getAppCreater())){
                    taskLog.info("collect app info not allow. appId[{}], fsEa[{}] appName[{}]", appId, openAppDO.getAppCreater(), openAppDO.getAppName());
                    return;
                }
                taskLog.info("execute appId [{}] app[{}]", appId, openAppDO);
                AppDataMigrationConfigDO config = new AppDataMigrationConfigDO();
                // 设置应用基本信息.
                config.setAppId(appId);
                config.setStatus(CommonConstant.VALID);
                config.setAppCreator(openAppDO.getAppCreater());
                config.setAppGmtCreate(openAppDO.getGmtCreate());
                // 查询开发者模式.
                config.setAppMode(openAppDO.getAppMode());
                try {
                    // 设置调用api次数.
                    Integer openApiNum = openApiCallNumDataDAO.queryByAppId(appId);
                    if (null == openApiNum || openApiNum < 0) {
                        openApiNum = 0;
                    }
                    config.setOpenApiNum(openApiNum);
                    taskLog.info("queryByAppId appId [{}] set openApiNum [{}]", appId, openApiNum);
                    // 加载组件.设置好组件的相关信息.
                    BaseResult<List<OpenAppComponentDO>> components = openAppComponentService.queryAppComponentListByAppId(new FsUserVO("dataMigration", true), appId);
                    if (components.isSuccess()) {
                        for (OpenAppComponentDO openAppComponentDO : components.getResult()) {
                            taskLog.info("load component appId [{}] component[{}]", appId, openAppComponentDO);
                            if (AppComponentTypeEnum.WEB.getType() == openAppComponentDO.getComponentType()) {
                                config.setWebComponentId(openAppComponentDO.getComponentId());
                            } else if (AppComponentTypeEnum.APP.getType() == openAppComponentDO.getComponentType()) {
                                config.setAppComponentId(openAppComponentDO.getComponentId());
                            }
                        }
                    }
                    // 加载自定义菜单信息.
                    Integer customMenuStatus = 3; // 1.仅开启.2.开启有内容. 3.未开启
                    com.facishare.open.custom.menu.api.result.BaseResult<CustomMenuStatusEnum> customMenuStatusResult = customMenuService.findCustomMenuStatus(appId);
                    if (customMenuStatusResult.isSuccess()) {
                        taskLog.info("load custom menu status appId [{}] customMenuStatusResult[{}]", appId, customMenuStatusResult);
                        if (CustomMenuStatusEnum.NORMAL == customMenuStatusResult.getResult()) {
                            customMenuStatus = 1;
                            com.facishare.open.custom.menu.api.result.BaseResult<OpenMenuVO> customMenuByAppId = customMenuService.findCustomMenuByAppId(appId);
                            if (customMenuByAppId.isSuccess() && null != customMenuByAppId.getResult()) {
                                customMenuStatus = 2;
                            }
                        }
                    }
                    config.setCustomMenuStatus(customMenuStatus);
                    taskLog.info("set custom menu status appId [{}] set customMenuStatus [{}]", appId, customMenuStatus);
                    String fsEa = openAppDO.getAppCreater();
                    // 添加应用群发消息数据.
                    MessageExhibitionResult<Long> countSendNumByAppIdResult = messageExhibitionService.countSendNumByAppId(fsEa, appId, MessageSendTypeEnum.ADMINISTRATOR_PUSH);
                    if (!countSendNumByAppIdResult.isSuccess()) {
                        throw new BizException(AjaxCode.BIZ_EXCEPTION, countSendNumByAppIdResult, "查询应用的群发消息数据.");
                    }
                    config.setGroupMsgNum(countSendNumByAppIdResult.getData().intValue());
                    taskLog.info("countSendNumByAppId success appId [{}] set groupMsgNum [{}]", appId, config.getGroupMsgNum());
                    //添加应用用户上行消息数据.
                    MessageExhibitionResult<Long> countReceiveNumByAppIdResult = messageExhibitionService.countReceiveNumByAppId(fsEa, appId);
                    if (!countReceiveNumByAppIdResult.isSuccess()) {
                        throw new BizException(AjaxCode.BIZ_EXCEPTION, countReceiveNumByAppIdResult, "查询应用的用户上行消息数据.");
                    }
                    config.setUserMsgNum(countReceiveNumByAppIdResult.getData().intValue());
                    taskLog.info("countReceiveNumByAppId success appId [{}] set userMsgNum [{}]", appId, config.getUserMsgNum());
                    // 收集自动回复的开关设置 1.开启 2.关闭
                    config.setAutoReplyStatus(CommonConstant.INVALID);
                    int queryAutoReplySwitch = msgAutoReplyService.queryAutoReplySwitch(openAppDO.getAppCreater(), appId);
                    if (1 == queryAutoReplySwitch){
                        config.setAutoReplyStatus(CommonConstant.VALID);
                    }
                    taskLog.info("queryAutoReplySwitch success appId [{}] set autoReplyStatus [{}]", appId, config.getAutoReplyStatus());
                    // 收集移动客服的开关设置 1.开启 2.关闭
                    config.setCustomServiceStatus(CommonConstant.INVALID);
                    GetCustomServiceSwitchResult queryCustomServiceReplySwitchResult = msgAutoReplyService.queryCustomServiceReplySwitch(openAppDO.getAppCreater(), appId);
                    if (queryCustomServiceReplySwitchResult.isSuccess() && 1 == queryCustomServiceReplySwitchResult.getReplySwitch()) {
                        config.setCustomServiceStatus(CommonConstant.VALID);
                    }
                    taskLog.info("queryCustomServiceReplySwitch success appId [{}] set customServiceStatus [{}]", appId, config.getCustomServiceStatus());
                    taskLog.info("collect app info success.appId [{}] config[{}]", appId, config);
                } catch (Exception e) {
                    config.setStatus(CommonConstant.INVALID);
                    taskLog.error("collect app info failed,appId [{}] config[{}]", appId, config, e);
                }
                configList.add(config);
            });
            if (!CollectionUtils.isEmpty(configList)){
                BaseResult<Void> saveResult = appDataMigrationConfigService.saveBatch(configList);
                if (!saveResult.isSuccess()) {
                    throw new BizException(AjaxCode.BIZ_EXCEPTION, saveResult, "批量保存迁移数据的配置信息失败.");
                }
            }
        }
        if (pager.getPageTotal() <= currentPage) { // 任务执行完成.
            return TaskStatusEnum.COMPLETED;
        }
        return TaskStatusEnum.RUNNING;
    }
}
