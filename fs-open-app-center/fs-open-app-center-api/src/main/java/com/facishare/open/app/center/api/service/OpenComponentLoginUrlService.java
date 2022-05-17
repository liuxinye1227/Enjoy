package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.vo.ComponentLoginUrlVO;
import com.facishare.open.app.center.api.model.vo.OpenComponentUrlGrayVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.List;

/**
 * 应用组件地址.
 * Created by zenglb on 2016/4/13.
 */
public interface OpenComponentLoginUrlService {

    /**
     * 查询单个组件的跳转地址.
     *
     * @param fsUserVO    操作人.
     * @param componentId 组件id.
     * @param fsEa        企业id.
     * @return 返回跳转地址.
     */
    BaseResult<ComponentLoginUrlVO> queryLoginUrlByFsEaAndComponentId(FsUserVO fsUserVO, String fsEa, String componentId);

    /**
     * 批量查询单个组件的跳转地址.
     *
     * @param fsUserVO     操作人.
     * @param componentIds 组件id.
     * @param fsEa         企业id.
     * @return 返回跳转地址.
     */
    BaseResult<List<ComponentLoginUrlVO>> queryLoginUrlByFsEaAndComponentIdsBatch(FsUserVO fsUserVO, String fsEa, List<String> componentIds);

    /**
     * 批量重置组件的跳转地址的缓存.
     *
     * @param fsUserVO     操作人.
     * @param componentIds 组件id.
     * @return 返回跳转地址.
     */
    BaseResult<Void> resetComponentLoginUrlCacheBatch(FsUserVO fsUserVO, List<String> componentIds);
}
