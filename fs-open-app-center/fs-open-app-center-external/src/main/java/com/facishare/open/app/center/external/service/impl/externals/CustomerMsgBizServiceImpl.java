package com.facishare.open.app.center.external.service.impl.externals;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.enums.OpenCustomerRoleEnum;
import com.facishare.open.app.center.api.model.vo.CheckAndGetCustomerIdsVO;
import com.facishare.open.app.center.api.model.vo.InvalidCustomerMessageVO;
import com.facishare.open.app.center.api.model.vo.OpenCustomerVO;
import com.facishare.open.app.center.api.model.vo.OuterServiceWechatVO;
import com.facishare.open.app.center.api.service.externals.CustomerMsgBizService;
import com.facishare.open.app.center.api.service.outer.OpenCustomerService;
import com.facishare.open.app.center.api.service.outer.OuterServiceWechatService;
import com.facishare.open.app.center.external.utils.ConfigCenter;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.msg.result.MessageExhibitionResult;
import com.facishare.open.msg.result.MessageResult;
import com.facishare.open.msg.service.MessageExhibitionService;
import com.facishare.open.msg.service.SendMessageService;
import com.facishare.open.oauth.result.CommonResult;
import com.facishare.open.oauth.service.EaAuthService;
import com.facishare.wechat.proxy.common.result.ModelResult;
import com.facishare.wechat.proxy.constants.BusinessType;
import com.facishare.wechat.proxy.model.auth.BindInfo;
import com.facishare.wechat.proxy.model.vo.FSUserVO;
import com.facishare.wechat.proxy.model.vo.QueryBindInfoVo;
import com.facishare.wechat.proxy.model.wx.WechatUserBindFsResult;
import com.facishare.wechat.proxy.service.WechatAuthService;
import com.facishare.wechat.proxy.service.WechatUserBindInfoService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * impl.
 * Created by zenglb on 2016/11/30.
 */
@Service("customerMsgBizServiceImpl")
public class CustomerMsgBizServiceImpl implements CustomerMsgBizService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private OuterServiceWechatService outerServiceWechatService;
    @Resource
    private OpenCustomerService openCustomerService;
    @Resource
    private WechatUserBindInfoService wechatUserBindInfoService;
    @Resource
    private SendMessageService sendMessageService;
    @Resource
    private MessageExhibitionService messageExhibitionService;
    @Resource
    private EaAuthService eaAuthService;
    @Resource
    private WechatAuthService wechatAuthService;

    @Override
    public BaseResult<CheckAndGetCustomerIdsVO> checkAndGetCustomerIds(String fsEa, String appId, String wxOpenId, Integer userId) {
        if (StringUtils.isBlank(fsEa) || StringUtils.isBlank(appId) || StringUtils.isBlank(wxOpenId) || null == userId || userId <= 0) {
            return new BaseResult<>(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION);
        }
        try {
            OuterServiceWechatVO outerServiceWechatArg = new OuterServiceWechatVO();
            outerServiceWechatArg.setAppId(appId);
            outerServiceWechatArg.setFsEa(fsEa);
            outerServiceWechatArg.setStatus(CommonConstant.VALID);
            BaseResult<OuterServiceWechatVO> outerServiceWechatResult = outerServiceWechatService.queryOuterServiceWechat(outerServiceWechatArg);
            if (!outerServiceWechatResult.isSuccess()) {
                logger.warn("queryOuterServiceWechat failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}], args[{}], result[{}]", fsEa, appId, wxOpenId, userId, outerServiceWechatArg, outerServiceWechatResult);
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，服务号已经取消授权");
                return new BaseResult<>(new BizException(outerServiceWechatResult));
            }
            // 绑定状态为空时表示初始状态.
            if (null == outerServiceWechatResult.getResult() || null != outerServiceWechatResult.getResult().getUnbindSource()) {
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，服务号已经取消授权");
                return new BaseResult<>(AppCenterCodeEnum.NO_VALID_OUTER_SERVICE_LINK_WE_CHAT_SERVICE);
            }
            CommonResult commonResult = eaAuthService.isEaAuthStatusNormal(null, null,
                    fsEa, appId);
            if (!commonResult.isSuccess()) {
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，服务号已停用");
                return new BaseResult<>(AppCenterCodeEnum.APP_IS_STOP);
            }

            ModelResult<BindInfo> modelResult = wechatAuthService.getBindInfoByAppId(outerServiceWechatResult.getResult().getWxAppId(), BusinessType.BUSI_TYPE_APP_CENTER);
            if (!modelResult.isSuccess() || null == modelResult.getResult()) {
                logger.warn("failed to call getBindInfoByAppId, BindInfo[{}], BusinessType[{}], modelResult[{}]", outerServiceWechatResult.getResult(), BusinessType.BUSI_TYPE_APP_CENTER, modelResult);
                return new BaseResult<>(new BizException(modelResult.getErrorCode(), modelResult.getErrorMessage()));
            }
            BindInfo bindInfo = modelResult.getResult();
            if (CommonConstant.WECHAT_SERVICE == bindInfo.getType()) {
                if (CommonConstant.WECHAT_IS_UNAUTHERIZED == bindInfo.getVerifyType()) { //未认证的服务号
                    sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，未认证的服务号暂不支持回复消息");
                    return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.NO));
                }
            } else {//订阅号
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败, 订阅号暂不支持回复消息");
                return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.NO));
            }

            BaseResult<OpenCustomerVO> openCustomerVOBaseResult = openCustomerService.queryOpenCustomer(fsEa, appId, userId);
            if (!openCustomerVOBaseResult.isSuccess()) {
                logger.warn("queryOuterServiceWechat failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}], args[{}], result[{}]", fsEa, appId, wxOpenId, userId, outerServiceWechatArg, outerServiceWechatResult);
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，您已不是微信客服");
                return new BaseResult<>(new BizException(outerServiceWechatResult));
            }
            OpenCustomerVO openCustomerVO = openCustomerVOBaseResult.getResult();
            if (null == openCustomerVOBaseResult.getResult() || CommonConstant.VALID != openCustomerVO.getStatus()) {
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，您已不是微信客服");
                return new BaseResult<>(AppCenterCodeEnum.NO_VALID_OPEN_CUSTOMER_EXIST);
            }

            //加载原先设置的服务专员.
            QueryBindInfoVo queryBindInfoVo = new QueryBindInfoVo();
            queryBindInfoVo.setEnterpriseAccount(fsEa);
            queryBindInfoVo.setWxAppId(outerServiceWechatResult.getResult().getWxAppId());
            queryBindInfoVo.setWxOpenIdList(Lists.newArrayList(wxOpenId));

            ModelResult<List<WechatUserBindFsResult>> listModelResult = wechatUserBindInfoService.queryBindFsUser(queryBindInfoVo);
            if (!listModelResult.isSuccess() || CollectionUtils.isEmpty(listModelResult.getResult())) {
                logger.warn("queryBindFsUser failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}], args[{}], result[{}]", fsEa, appId, wxOpenId, userId, queryBindInfoVo, listModelResult);
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，对方已取消关注公众号");
                return new BaseResult<>(AppCenterCodeEnum.NO_VALID_OPEN_CUSTOMER_EXIST);
            }
            List<Integer> serviceCustomerUserIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(listModelResult.getResult().get(0).getFsUserList())) {
                serviceCustomerUserIds.addAll(listModelResult.getResult().get(0).getFsUserList().stream().map(FSUserVO::getFsUserId).map(Long::intValue).collect(Collectors.toList()));
            }

            List<Integer> roleList = Lists.newArrayList(OpenCustomerRoleEnum.MANAGER.getCode());
            // 如果没有客服，则查询普通客服 + 客服主管.
            if (CollectionUtils.isEmpty(serviceCustomerUserIds)){
                roleList.add(OpenCustomerRoleEnum.COMMON.getCode());
            }
            BaseResult<List<OpenCustomerVO>> queryResult = openCustomerService.query(new FsUserVO(fsEa, userId), fsEa, appId, roleList, CommonConstant.VALID);
            if (!queryResult.isSuccess()){
                logger.warn("queryBindFsUser failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}], roleList[{}], result[{}]", fsEa, appId, wxOpenId, userId, roleList, queryResult);
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，您已不是对方的服务专员");
                return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.NO));
            }
            if (!CollectionUtils.isEmpty(queryResult.getResult())){
                serviceCustomerUserIds.addAll(queryResult.getResult().stream().map(OpenCustomerVO::getUserId).collect(Collectors.toList()));
            }
            if (!serviceCustomerUserIds.contains(userId)){
                return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.NO, serviceCustomerUserIds));
            }

            MessageExhibitionResult<Long> lastUpMsgTimeResult = messageExhibitionService.getLastUpMsgTime(appId, fsEa, wxOpenId);
            if (!lastUpMsgTimeResult.isSuccess()) {
                logger.warn("getLastUpMsgTime failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}], lastUpMsgTimeResult[{}]", fsEa, appId, wxOpenId, userId, lastUpMsgTimeResult);
                return new BaseResult<>(new BizException(lastUpMsgTimeResult));
            }
            Long data = lastUpMsgTimeResult.getData();
            if (null == data || data <= 0) {
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, "发送失败，对方未发过消息，您不能回复");
                return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.NO));
            }

            if ((data + ConfigCenter.WE_CHAT_MAX_REPLY_TIMEOUT) < (System.currentTimeMillis())) {
                sendPromptTextMessage(fsEa, appId, wxOpenId, userId, ConfigCenter.HINT_MSG_WE_CHAT_MAX_REPLY_TIMEOUT);
                return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.NO));
            }

            return new BaseResult<>(new CheckAndGetCustomerIdsVO(CommonConstant.YES, serviceCustomerUserIds));
        } catch (Exception e) {
            logger.warn("checkCustomerStatus failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}]", fsEa, appId, wxOpenId, userId, e);
            return new BaseResult<>(AppCenterCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    private void sendPromptTextMessage(String fsEa, String appId, String wxOpenId, Integer userId, String message) {
        MessageResult messageResult = sendMessageService.sendWechatPromptTextMessage(appId, fsEa, wxOpenId, message, Lists.newArrayList(userId));
        if (!messageResult.isSuccess()) {
            logger.error("sendWechatPromptTextMessage failed, fsEa[{}], appId[{}], wxOpenId[{}], userId[{}], message[{}], result[{}]", fsEa, appId, wxOpenId, userId, message, messageResult);
        }
    }

    @Override
    public BaseResult<List<InvalidCustomerMessageVO>> queryInvalidCustomerMessage(String fsEa, String appId, String wxOpenId, List<Integer> delCustomerIds) {
        return new BaseResult<>(delCustomerIds.stream().map(id -> new InvalidCustomerMessageVO(id, "您已不是对方的服务专员")).collect(Collectors.toList())) ;
    }
}
