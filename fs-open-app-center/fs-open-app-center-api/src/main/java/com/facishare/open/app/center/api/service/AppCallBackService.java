package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.CallBackBindDO;
import com.facishare.open.app.center.api.result.BaseResult;

/**
 * 回调服务   提供业务接口  
 * @author: wangtao
 * @date: 2015年7月29日 下午2:04:18
 */
public interface AppCallBackService {

    /**
     * 获取企业应用或应用组件的跳转 url, 用于免登录
     * @param callBackBindDO      企业应用Id, 纷享用户账号Id
     * @return         返回企业应用的URL, Code
     */
    BaseResult getAppCallBackBindParam(CallBackBindDO callBackBindDO);

}
