package com.facishare.open.app.center.api.service.externals;

import com.facishare.open.app.center.api.model.vo.CallbackMsgVO;
import com.facishare.open.app.center.api.model.vo.WeChatClickEventVO;
import com.facishare.open.common.result.BaseResult;

/**
 * Description: 微信消息外部服务
 * User: zhouq
 * Date: 2016/11/2
 */
public interface WechatMsgService {

    /**
     * 群发消息到微信回调服务
     * @param callbackMsgVO 微信消息对象
     * @return
     */
    BaseResult<Boolean> callbackOpenServiceExternalMsg(CallbackMsgVO callbackMsgVO);

    /**
     * 获取自定义菜单click事件对应的文本消息内容
     * @param weChatClickEventVO 微信自定义菜单点击事件对象
     * @return
     */
    BaseResult<String> callbackCustomerMenuClickEvent(WeChatClickEventVO weChatClickEventVO);
}
