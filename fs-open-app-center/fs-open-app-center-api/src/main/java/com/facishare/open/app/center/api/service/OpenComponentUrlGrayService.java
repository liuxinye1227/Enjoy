package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.vo.ComponentLoginUrlVO;
import com.facishare.open.app.center.api.model.vo.OpenComponentUrlGrayVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.List;

/**
 * 应用组件地址灰度.
 * Created by zenglb on 2016/4/13.
 */
public interface OpenComponentUrlGrayService {

    /**
     * 根据组件id加载组件地址的灰度配置信息.
     *
     * @param fsUserVO    操作人.
     * @param componentId 组件id.
     * @return 返回跳转地址.
     */
    BaseResult<OpenComponentUrlGrayVO> queryByComponentId(FsUserVO fsUserVO, String componentId);

    /**
     * 批量根据组件id加载组件地址的灰度配置信息.
     *
     * @param fsUserVO     操作人.
     * @param componentIds 组件id.
     * @return 返回跳转地址.
     */
    BaseResult<List<OpenComponentUrlGrayVO>> queryByComponentIdsBatch(FsUserVO fsUserVO, List<String> componentIds);

    /**
     * 保存组件地址的灰度配置信息.
     *
     * @param fsUserVO               操作人.
     * @param openComponentUrlGrayVO 配置信息.
     * @return 返回跳转地址.
     */
    BaseResult<String> saveOpenComponentUrlGray(FsUserVO fsUserVO, OpenComponentUrlGrayVO openComponentUrlGrayVO);
}
