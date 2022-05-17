package com.facishare.open.app.center.api.service.externals;


import com.facishare.open.app.center.api.model.vo.CheckAndGetCustomerIdsVO;
import com.facishare.open.app.center.api.model.vo.InvalidCustomerMessageVO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * 客服上行消息时的权限验证.
 * Created by zenglb on 2016/11/29.
 */
public interface CustomerMsgBizService {

    /**
     * 验证客服是否可以回复消息到微信.
     *
     * @param fsEa     企业账号.
     * @param appId    外联服务号开平应用id.
     * @param wxOpenId 微信用户id.
     * @param userId   当前客服id.
     * @return 是否可以回复消息. 1.是，2. 否.
     */
    BaseResult<CheckAndGetCustomerIdsVO> checkAndGetCustomerIds(String fsEa, String appId, String wxOpenId, Integer userId);

    /**
     * 查询无效客服的消息列表.
     *
     * @param fsEa     企业账号.
     * @param appId    外联服务号开平应用id.
     * @param wxOpenId 微信用户id.
     * @param delCustomerIds  删除后的客服id.
     * @return  消息列表。
     */
    BaseResult<List<InvalidCustomerMessageVO>> queryInvalidCustomerMessage(String fsEa, String appId, String wxOpenId, List<Integer> delCustomerIds);
}
