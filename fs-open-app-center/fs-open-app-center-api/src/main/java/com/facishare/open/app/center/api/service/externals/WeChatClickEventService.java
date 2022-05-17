package com.facishare.open.app.center.api.service.externals;

import com.facishare.open.app.center.api.model.vo.WeChatClickEventVO;
import com.facishare.open.common.result.BaseResult;

/**
 * Description: 微信自定义菜单点击发送文本回调服务
 * User: huyue
 * Date: 2016/11/22
 */
public interface WeChatClickEventService {

    /**
     * 保存点击事件服务
     *
     * @param weChatClickEventVO 点击事件对象
     * @return
     */
    BaseResult<Void> saveWeChatClickEvent(WeChatClickEventVO weChatClickEventVO);

    /**
     * 更新点击事件服务
     *
     * @param weChatClickEventVO 点击事件对象
     * @return
     */
    BaseResult<Void> updateWeChatClickEvent(WeChatClickEventVO weChatClickEventVO);
}
