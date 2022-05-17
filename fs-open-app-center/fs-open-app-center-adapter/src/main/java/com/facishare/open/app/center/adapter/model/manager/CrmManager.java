package com.facishare.open.app.center.adapter.model.manager;

import com.facishare.open.app.center.adapter.model.proto.QueryCrmEmployeeTrialInfos;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmQuotaRecords;

import java.util.Collection;
import java.util.List;

/**
 * Created by xialf on 7/7/16.
 *
 * @author xialf
 */
public interface CrmManager {
    /**
     * 保存用户到CRM可见范围.
     *
     * @param fsEa    企业账号
     * @param userIds 用户id列表
     */
    void saveUsers(final String fsEa, final Collection<Integer> userIds);

    /**
     * 把用户从CRM可见范围中删除.
     *
     * @param fsEa    企业账号
     * @param userIds 用户id列表
     */
    void removeUsers(final String fsEa, final Collection<Integer> userIds);

    /**
     * 获取CRM的可用用户(可见范围).
     *
     * @param fsEa 企业员工
     * @return 用户列表
     */
    List<Integer> queryUsers(final String fsEa);

    /**
     * 获取CRM的当前有效配额.
     *
     * @param fsEa 企业账号
     * @return 有效配额
     */
    int queryQuota(final String fsEa);

    /**
     * 获取CRM的购买状态.
     *
     * @param fsEa 企业账号
     * @return 状态
     */
    int queryStatus(final String fsEa);

    /**
     * 获取CRM的配额记录.
     *
     * @param fsEa 企业账号
     */
    List<QueryCrmQuotaRecords.QuotaRecordItem> queryCrmQuotaRecords(final String fsEa);

    /**
     * 获取CRM的个人试用记录.
     *
     * @param fsEa 企业账号
     */
    List<QueryCrmEmployeeTrialInfos.CrmEmployeeTrialItem> queryCrmEmployeeTrialInfos(final String fsEa);
}
