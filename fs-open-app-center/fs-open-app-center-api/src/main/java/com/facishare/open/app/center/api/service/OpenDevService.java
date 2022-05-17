package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenDevDO;
import com.facishare.open.app.center.api.model.vo.OpenDevVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.List;

/**
 * Created by wangwz on 2015/9/6.
 */
public interface OpenDevService {

    /**
     * 查询开发商列表
     *
     * @return
     */
    BaseResult<Pager<OpenDevVO>> queryOpenDevVisibleByPage(Pager<OpenDevVO> pager);

    /**
     * 创建开发商信息
     *
     * @param openDevVO
     * @return
     */
    BaseResult<OpenDevDO> createOpenDev(OpenDevDO openDevDO);

    /**
     * 修改一个开发商信息
     *
     * @param openDevDO
     * @return
     */
    BaseResult<OpenDevDO> updateOpenDev(OpenDevDO openDevDO);

    /**
     * 删除开发商信息
     *
     * @param id
     * @return
     */
    StatusResult deleteByOpenDevId(String id);

    /**
     * 查询开发商信息
     *
     * @param devId
     */
    BaseResult<OpenDevDO> loadOpenDevByDevId(String devId);

    /**
     * 批量查询开发者信息
     *
     * @param devIds
     * @return
     */
    BaseResult<List<OpenDevDO>> loadOpenDevByDevIds(List<String> devIds);
}
