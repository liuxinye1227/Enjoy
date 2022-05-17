package com.facishare.open.app.center.api.service.externals;

import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * 用于北京或其它非应用中心的调用修改可见范围服务.
 * Created by zenglb on 2016/8/23.
 */
public interface AppViewBizService {


    /**
     * 添加 员工级可见范围.可重复调用.
     *
     * @param componentId 组件id.
     * @param fsEa        企业账号.
     * @param userIdList  用户id列表.
     * @return 影响个数.
     */
    BaseResult<Integer> addEmployeeView(String componentId, String fsEa, List<Integer> userIdList);

    /**
     * 移除可见范围的人员.可重复调用.
     *
     * @param componentId 组件id.
     * @param fsEa        企业账号.
     * @param userIdList  用户id列表.
     * @return 影响个数.
     */
    BaseResult<Integer> removeEmployeeView(String componentId, String fsEa, List<Integer> userIdList);
}
