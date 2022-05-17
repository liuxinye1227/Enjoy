package com.facishare.open.manage.manager.impl;

import com.facishare.appserver.comment.api.CommentConstants;
import com.facishare.appserver.comment.api.CommentService;
import com.facishare.appserver.comment.api.pb.GetBatchStatisticsArgs;
import com.facishare.appserver.comment.api.pb.GetBatchStatisticsResult;
import com.facishare.appserver.comment.api.vo.StatisticsVO;
import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.*;
import com.facishare.open.app.center.api.result.*;
import com.facishare.open.app.center.api.service.*;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.mongo.MessageDAO;
import com.facishare.open.manage.mongo.MessageImageTextParamDAO;
import com.facishare.open.manage.dao.OpenAppDAO;
import com.facishare.open.manage.kits.JsonKit;
import com.facishare.open.manage.manager.AppVisibleManager;
import com.facishare.open.manage.manager.ServiceManager;
import com.facishare.open.manage.model.OpenDevAppVO;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.material.api.enums.MaterialTypeEnum;
import com.facishare.open.msg.constant.MessageSendTypeEnum;
import com.facishare.open.msg.result.MessageExhibitionResult;
import com.facishare.open.msg.service.MessageExhibitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chenzs on 2016/10/12.
 */
@Service
public class ServiceManagerImpl implements ServiceManager {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OpenAppService openAppService;

    @Autowired
    private AppIconService appIconService;

    @Autowired
    private OpenDictService openDictService;

    @Autowired
    private AppVisibleManager appVisibleManager;

    @Autowired
    private OpenAppDAO openAppDAO;

    @Autowired
    private MessageExhibitionService messageExhibitionService;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private MessageImageTextParamDAO messageImageTextParamDAO;

    @Resource
    private CommentService commentService;

    @Override
    public Pager<OpenDevAppVO> queryServiceByDev(Pager<OpenDevAppVO> pager) {
        Pager<OpenAppDO> param = new Pager<OpenAppDO>();
        param.setParams(pager.getParams());
        param.setCurrentPage(pager.getCurrentPage());
        param.setPageSize(pager.getPageSize());
        List<Integer> statusList = new ArrayList<Integer>();
        for (AppStatus status : AppStatus.values()) {
            if (AppStatus.DELETED != status) {
                statusList.add(status.status());
            }
        }
        param.addParam("statusList", statusList);
        AppPagerResult appPagerResult = openAppService.queryServiceByDev(param);
        if (!appPagerResult.isSuccess()) {
            logger.warn(" queryServiceByDev error, {}", JsonKit.object2json(param));
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appPagerResult, "查询服务号列表异常");
        }
        Pager<OpenAppDO> pagerDevApps = appPagerResult.getResult();
        pager.setRecordSize(pagerDevApps.getRecordSize());

        if (null != pagerDevApps.getData()) {
            OpenDevAppVO appVO = null;
            List<String> appIds = pagerDevApps.getData().stream().map(OpenAppDO::getAppId).collect(Collectors.toList());
            List<String> visibleAppIds = appVisibleManager.queryAppIdVisibleConfig(appIds); // visibleAppIds ：appIds当中在app_visible表的

            // OpenDevAppVO => OpenAppDO
            for (OpenAppDO appDO : pagerDevApps.getData()) {
                appVO = new OpenDevAppVO();
                appVO.setAppId(appDO.getAppId());
                appVO.setAppName(appDO.getAppName());
                appVO.setAppCreater(appDO.getAppCreater());
                if (null != appDO.getOpenDevDO()) {
                    appVO.setDevName(appDO.getOpenDevDO().getDevName());
                }
                appVO.setStatus(appDO.getStatus());
                DictResult dictResult = openDictService.loadOpenDict(CommonConstant.DICT_TYPE_APP_STATUS, "" + appDO.getStatus());
                appVO.setStatusName(dictResult.isSuccess() ? dictResult.getResult().getDictValue() : "");
                appVO.setAppType(appDO.getAppType());
                if (AppCenterEnum.AppType.BASE_SERVICE_APP.value() == appDO.getAppType()) {
                    appVO.setAppTypeName("基础服务号");
                } else if (AppCenterEnum.AppType.EXT_SERVICE_APP.value() == appDO.getAppType()) {
                    appVO.setAppTypeName("扩展服务号");
                }
                appVO.setOnLineDate(appDO.getGmtCreate());
                appVO.setAppLogoUrl(queryAppIconUrl(appDO.getAppId(), IconType.WEB));

                //添加灰度
                appVO.setVisibleStatus(VisibleEnum.ALL.getCode());
                appVO.setVisibleStatusName(VisibleEnum.ALL.getName());
                if (AppStatus.ON_LINE.getStatus() != appDO.getStatus()) {
                    appVO.setVisibleStatus(VisibleEnum.NONE.getCode());
                    appVO.setVisibleStatusName(VisibleEnum.NONE.getName());
                }
                if (visibleAppIds.contains(appDO.getAppId())) {
                    appVO.setVisibleStatus(VisibleEnum.PART.getCode());
                    appVO.setVisibleStatusName(VisibleEnum.PART.getName());
                }
                pager.getData().add(appVO);
            }
        }
        return pager;
    }

    /**
     * 查询iconUrl
     *
     * @param appIdOrComponentId 应用或者组件ID
     * @param type               图片类型
     * @return iconUrl
     */
    private String queryAppIconUrl(String appIdOrComponentId, IconType type) {
        BaseResult<String> iconUrlResult = appIconService.queryAppIconUrl(appIdOrComponentId, type);
        if (!iconUrlResult.isSuccess()) {
            logger.warn("queryAppIconUrl failed, appIdOrComponentId[{}], type[{}], result[{}] : " + appIdOrComponentId, type, iconUrlResult);
        }
        return iconUrlResult.getResult();
    }

    @Override
    public List<OpenAppDO> queryServicesByAppTypes(List<AppCenterEnum.AppType> appTypeList) {
        List<AppStatus> statusList = new ArrayList<AppStatus>();
        for (AppStatus status : AppStatus.values()) {
            if (AppStatus.ON_LINE == status) {
                statusList.add(status);
            }
        }

        AppListResult appListResult = openAppService.queryAppListByStatusAndAppTypesOrderByAppName(statusList, appTypeList);
        if (!appListResult.isSuccess()) {
            logger.warn(" queryAppListByStatusAndAppTypesOrderByAppName error, statusList[{}], appTypeList[{}]", statusList, appTypeList);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, appListResult, "查询服务号列表异常");
        }

        return appListResult.getResult();
    }

    @Override
    public long getGroupSendMsgCount(long startTime, long endTime) {
        List<Integer> sendTypes = new ArrayList<>();
        sendTypes.add(com.facishare.open.material.api.enums.MessageSendTypeEnum.GROUP_SEND_MSG.getCode());
        return messageDAO.countMessage(null, sendTypes, startTime, endTime);
    }

    @Override
    public long getGroupSendMsgServiceCount(long startTime, long endTime) {
        List<Integer> sendTypes = new ArrayList<>();
        sendTypes.add(com.facishare.open.material.api.enums.MessageSendTypeEnum.GROUP_SEND_MSG.getCode());
        return messageDAO.countMessageApp(null, sendTypes, startTime, endTime);
    }

    @Override
    public long getGroupSendImageTextMsgCount(long startTime, long endTime) {
        return getGroupSendImageTextParamIds(startTime, endTime).size();
    }

    private List<String> getGroupSendImageTextParamIds(long startTime, long endTime) {
        List<Integer> sendTypes = new ArrayList<>();
        sendTypes.add(com.facishare.open.material.api.enums.MessageSendTypeEnum.GROUP_SEND_MSG.getCode());

        List<Integer> types = new ArrayList<>();
        types.add(MaterialTypeEnum.IMAGE_TEXT.getCode());
        List<String> messageIds = messageDAO.queryMessage(types, sendTypes, startTime, endTime);
        if (CollectionUtils.isEmpty(messageIds)) {
            return new ArrayList<>(0);
        }
        return messageImageTextParamDAO.queryImageTextParamIds(messageIds, startTime, endTime);
    }

    @Override
    public long getGroupSendImageTextMsgReadCount(long startTime, long endTime) {
        List<String> msgImageTextParamIds = getGroupSendImageTextParamIds(startTime, endTime);
        int imageTextParamSize = msgImageTextParamIds.size();
        logger.info("imageTextParamSize[{}]", imageTextParamSize);
        int maxCountSizePerTime = ConfigCenter.MAX_SIZE_FOR_COUNT_IMAGE_TEXT_READ_COUNT;
        logger.info("maxSizePerTime[{}]", maxCountSizePerTime);
        int pageCount = imageTextParamSize / maxCountSizePerTime;
        if (imageTextParamSize % maxCountSizePerTime > 0) {
            pageCount += 1;
        }
        long count = 0;
        for (int i=0; i<pageCount; i++) {
            int fromIndex = maxCountSizePerTime * i;
            int toIndex = 0;
            if (i == pageCount -1) {
                toIndex = msgImageTextParamIds.size();
            } else {
                toIndex += (fromIndex + maxCountSizePerTime);
            }
            long tempCount = countReadCount(msgImageTextParamIds.subList(fromIndex, toIndex));
            logger.info("第[{}]次  count[{}]", i, tempCount);
            count += tempCount;
        }
        return count;
    }

    private long countReadCount(List<String> msgImageTextParamIds) {
        GetBatchStatisticsArgs args = new GetBatchStatisticsArgs();
        args.setType(CommentConstants.ACTIVITY_SOURCE_TOPIC);
        args.setRefIdList(msgImageTextParamIds);
        GetBatchStatisticsResult getBatchStatisticsResult = commentService.getBatchStatistics(args);
        if (getBatchStatisticsResult.getCode()!=1) {
            logger.error("call commentService.getBatchStatistics error. args[{}], getBatchStatisticsResult[{}]",
                    args, getBatchStatisticsResult);
            throw new BizException(getBatchStatisticsResult.getCode(), getBatchStatisticsResult.getMessage());
        }
        long readCount = 0;
        List<StatisticsVO> statisticsVOs = getBatchStatisticsResult.getStatisticsVOList();
        if (!CollectionUtils.isEmpty(statisticsVOs)) {
            for (StatisticsVO vo : statisticsVOs) {
                readCount += vo.getReadNum();
            }
        }
        return readCount;
    }

    @Override
    public long getNewServiceCount(long startTime, long endTime) {
        return openAppDAO.countService(new Date(startTime), new Date(endTime));
    }

    @Override
    public long getServiceCount(long endTime) {
        return openAppDAO.countService(null, new Date(endTime));
    }

    @Override
    public long getServiceEaCount(long endTime) {
        return openAppDAO.countServiceEa(null, new Date(endTime));
    }

    @Override
    public long countReplyMsgNum(List<MessageSendTypeEnum> sendTypeEnumList, long startTime, long endTime) {
        MessageExhibitionResult<Long> countReplyMsgNumResult =
                messageExhibitionService.countReplyMsgNum(startTime, endTime, sendTypeEnumList);
        if (!countReplyMsgNumResult.isSuccess()) {
            logger.error("messageExhibitionService.countReplyMsgNumResult error. startTime[{}], endTime[{}], sendTypeEnumList[{}], countReplyMsgNumResult[{}]",
                    startTime, endTime, sendTypeEnumList, countReplyMsgNumResult);
            throw new BizException(countReplyMsgNumResult);
        }
        return countReplyMsgNumResult.getData();
    }

    @Override
    public long countReplyMsgService(List<MessageSendTypeEnum> sendTypeEnumList,long startTime, long endTime) {
        MessageExhibitionResult<List<String>> getAppIdListResult =
                messageExhibitionService.getAppIdList(startTime, endTime, sendTypeEnumList);
        if (!getAppIdListResult.isSuccess()) {
            logger.error("messageExhibitionService.getAppIdList error. startTime[{}], endTime[{}], sendTypeEnumList[{}], getAppIdListResult[{}]",
                    startTime, endTime, sendTypeEnumList, getAppIdListResult);
            throw new BizException(getAppIdListResult);
        }

        if (CollectionUtils.isEmpty(getAppIdListResult.getData())) {
            return 0L;
        } else {
            return getAppIdListResult.getData().size();
        }
    }
}
