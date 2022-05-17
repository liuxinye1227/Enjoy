package com.facishare.open.app.center.api.service;

import com.facishare.open.common.result.BaseResult;
import javafx.util.Pair;

import java.util.List;

/**
 * CRM可见性服务.
 * Created by xialf on 2017/02/10.
 *
 * @author xialf
 * @since 2017/02/10 5:15 PM
 */
public interface CrmViewService {
    /**
     * 查询CRM可用用户.
     *
     * @param fsEa 企业账号
     * @return 按照添加时间倒序排列用户
     */
    BaseResult<List<Pair<Integer, Long>>> queryUsers(final String fsEa);

    /**
     * 增加CRM用户.
     * <p>
     * 1. 会受到配额的限制
     * 2. 会在CRM应用中设置"角色"
     *
     * @param fsEa    企业账号
     * @param userIds 用户id
     * @return {@code BaseResult.getErrCode} {@link com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum#QUOTA_INSUFFICIENT} {@code if residual quota is < userIds.size()}
     */
    BaseResult<Void> addUsers(final String fsEa, List<Integer> userIds);

    /**
     * 删除CRM用户.
     * <p>
     * 因为CRM的用户删除操作是通过MQ通知CRM应用来执行的，所以此接口调用成功，也存在极小的概率用户没有删除成功
     *
     * @param fsEa    企业账号
     * @param userIds 用户列表
     */
    BaseResult<Void> removeUsers(final String fsEa, List<Integer> userIds);

    /**
     * 用户是否可以访问CRM.
     *
     * @param fsEa   企业账号
     * @param userId 用户id
     * @return 是否可以访问CRM
     */
    BaseResult<Boolean> canAccess(final String fsEa, int userId);
}
