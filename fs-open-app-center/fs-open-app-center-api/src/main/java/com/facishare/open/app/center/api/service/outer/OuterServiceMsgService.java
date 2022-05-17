package com.facishare.open.app.center.api.service.outer;

import com.facishare.open.app.center.api.model.vo.OuterServiceWechatVO;
import com.facishare.open.common.result.BaseResult;

/**
 * Description: 外联服务号
 * User: zhouq
 * Date: 2016/11/1
 */
public interface OuterServiceMsgService {

    /**
     * 新增外联服务号和微信公众号
     * @param outerServiceWechatVO entity
     * @return
     */
    BaseResult<Boolean> addOpenServiceExternalMsg(OuterServiceWechatVO outerServiceWechatVO);

    /**
     * 更新外联服务号和微信公众号
     * @param outerServiceWechatVO entity
     * @return
     */
    BaseResult<Boolean> updateOpenServiceExternalMsg(OuterServiceWechatVO outerServiceWechatVO);

    /**
     * 查询外联服务号和微信公众号
     * @param outerServiceWechatVO entity
     * @return
     */
    BaseResult<OuterServiceWechatVO> queryOpenServiceExternalMsg(OuterServiceWechatVO outerServiceWechatVO);
}
