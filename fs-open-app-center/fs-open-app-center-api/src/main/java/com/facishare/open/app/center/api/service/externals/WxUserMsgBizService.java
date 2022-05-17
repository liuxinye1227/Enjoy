package com.facishare.open.app.center.api.service.externals;

import com.facishare.open.app.center.api.model.vo.WeChatMessageVO;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * 用于微信代理的上行消息查询相关发送人,与关注信息..
 * Created by zenglb on 2016/8/23.
 */
public interface WxUserMsgBizService {

    /**
     * 微信用户关注时查询一条关注消息.
     *
     * @param wxAppId  [必须]微信服务号id.
     * @param wxOpenId [必须]微信用户id.
     * @param fsEa     [必须]纷享企业.
     * @param userId   [可选]客服人员的userId。如果扫客服人员名片关注，则返回扫客服人员的名片.
     * @return 返回微信响应的消息.
     */
    BaseResult<WeChatMessageVO> queryFollowMessage(String wxAppId, String wxOpenId, String fsEa, Integer userId);

    /**
     * 微信用户上行消息时查询需要上行到哪些人.
     *
     * @param wxAppId         微信服务号id.
     * @param wxOpenId        微信用户id.
     * @param fsEa            纷享企业.
     * @param followerUserIds 跟进人(服务专员),可选.如果已经有对应的专员去跟进也返回.没有专员返回null.或空list.
     * @return 返回微信响应的消息.
     */
    BaseResult<List<FsUserVO>> querySendRange(String wxAppId, String wxOpenId, String fsEa, List<Integer> followerUserIds);
}
