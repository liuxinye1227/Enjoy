package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.vo.ServiceApprovalVO;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * @describe: 服务号审批service
 * @author: xiaoweiwei
 * @date: 2016/10/18 11:43
 */
public interface ServiceApprovalService {
    /**
     *
     * @param preApprovalId
     * @return
     */
    BaseResult<Boolean> queryByPreApprovalId(String preApprovalId);

    /**
     * 是否存在名称
     * @param fsEa
     * @param serviceName
     * @return
     */
    BaseResult<Boolean> existsName(String fsEa, String serviceName);

    /**
     *
     * @param fsUserVO
     * @return
     */
    BaseResult<Long> countByCreatorUser(FsUserVO fsUserVO);

    /**
     * 查询
     * @param approvalId
     * @return
     */
    BaseResult<ServiceApprovalVO> queryApproval(String approvalId);

    /**
     * 创建
     * @param serviceApprovalVO
     * @return
     */
    BaseResult<String> create(ServiceApprovalVO serviceApprovalVO);

    /**
     * 处理审批
     */
    BaseResult<ServiceApprovalVO> handleApproval(FsUserVO fsUserVO, String approvalId, Integer status, String approvalMsg);

}