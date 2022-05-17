package com.facishare.open.app.center.api.service.outer;

import com.facishare.open.app.center.api.model.vo.OpenAppVO;
import com.facishare.open.app.center.api.model.vo.OpenCustomerVO;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * 开平客服
 * Created by chenzs on 2016/11/2.
 */
public interface OpenCustomerService {

    /**
     * 查询开平客服
     *
     * @param fsUserVO  纷享用户
     * @param appId     外联服务号appId
     * @param status    如果是null，则查询所有状态
     * @return
     */
    BaseResult<List<OpenCustomerVO>> queryByAppId(FsUserVO fsUserVO, String appId, Integer status);

    /**
     * 查询开平客服：按修改时间倒序
     *
     * @param fsUserVO  纷享用户
     * @param fsEa
     * @param appId  外联服务号appId
     * @param roleList   如果是null，则查询所有角色
     * @param status 如果是null，则查询所有状态
     * @return
     */
    BaseResult<List<OpenCustomerVO>> query(FsUserVO fsUserVO, String fsEa, String appId, List<Integer> roleList, Integer status);

    /**
     * 查询客服身份.
     *
     * @param fsEa
     * @param appId
     * @param userId
     * @return
     */
    BaseResult<OpenCustomerVO> queryOpenCustomer(String fsEa, String appId, Integer userId);

    /**
     * 查询开平客服
     *
     * @param fsUserVO   纷享用户
     * @param customerId
     * @param status     如果是null，则查询所有状态
     * @return
     */
    BaseResult<OpenCustomerVO> queryByCustomerId(FsUserVO fsUserVO, String customerId, Integer status);

    /**
     * 创建开平客服
     *
     * @param fsUserVO       纷享用户
     * @param openCustomerVO openCustomerVO中必带的参数：fsEa、appId、wxAppId、srcType、role；    openCustomerVO里面的userId，通过userIds来传
     * @param userIds        要添加为客服的userIds
     * @return
     */
    BaseResult<Void> createOpenCustomers(FsUserVO fsUserVO, OpenCustomerVO openCustomerVO, List<Integer> userIds);

    /**
     * 更新开平客服角色
     *
     * @param fsUserVO   纷享用户
     * @param entity
     * @return
     */
    BaseResult<Void> updateOpenCustomerRole(FsUserVO fsUserVO, OpenCustomerVO entity);

    /**
     * 批量修改开平客服的修改时间
     *
     * @param fsUserVO   纷享用户
     * @param customerIds
     * @return
     */
    BaseResult<Void> updateOpenCustomersGmtModified(FsUserVO fsUserVO, List<String> customerIds);

    /**
     * 删除单个开平客服
     *
     * @param fsUserVO
     * @param customerId
     * @return
     */
    BaseResult<Void> deleteOpenCustomer(FsUserVO fsUserVO, String customerId);

    /**
     * 查询外联服务号列表：手机端使用
     *
     * @param fsUserVO 用户
     * @return 外联服务号列表
     */
    BaseResult<List<OpenAppVO>> queryOuterServices(FsUserVO fsUserVO);

    /**
     * 外联服务号修改了，通知纷享用户的终端重新拉取外联服务号列表
     *
     * @param fsUserVO 操作人
     * @param appId    外联服务号appId
     * @return
     */
    BaseResult<Void> notifyOuterServiceModified(FsUserVO fsUserVO, String appId);
}
