package com.facishare.open.app.center.api.service.outer;

import com.facishare.open.app.center.api.model.vo.OuterServiceWechatVO;
import com.facishare.open.common.result.BaseResult;

/**
 * Description: 外联服务号和微信公众号
 * User: zhouq
 * Date: 2016/11/1
 */
public interface OuterServiceWechatService {

    /**
     * 新增外联服务号和微信公众号
     * @param outerServiceWechatVO entity
     * @return
     */
    BaseResult<Boolean> addOuterServiceWechat(OuterServiceWechatVO outerServiceWechatVO);

    /**
     * 更新外联服务号和微信公众号
     * @param outerServiceWechatVO entity
     * @return
     */
    BaseResult<Boolean> updateOuterServiceWechat(OuterServiceWechatVO outerServiceWechatVO);

    /**
     * 查询外联服务号和微信公众号
     * @param outerServiceWechatVO entity
     * @return
     */
    BaseResult<OuterServiceWechatVO> queryOuterServiceWechat(OuterServiceWechatVO outerServiceWechatVO);
}
