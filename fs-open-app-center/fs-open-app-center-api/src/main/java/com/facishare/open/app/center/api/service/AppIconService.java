package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.enums.IconType;
import com.facishare.open.app.center.api.model.vo.IconVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

import java.awt.*;
import java.util.List;

/**
 * Author: Ansel Qiao
 * Create Time: 15/8/21
 */
public interface AppIconService {


    /**
     * 上传应用头像文件
     */
    BaseResult<Void> upload(String appId, List<IconType> types, byte[] data, String extName, Rectangle rectangle);

    /**
     * 下载头像文件
     *
     * @param appId  应用id
     * @param type   头像类型图像类型:ios|android|web|service
     * @param width  头像宽度
     * @param height 头像高度
     * @return 下载文件内容
     */
    BaseResult<IconVO> download(String appId, IconType type, int width, int height);

    /**
     * 上传应用头像文件
     *
     * @return
     */
    BaseResult<Void> update(String appId, List<IconType> types, byte[] data, String extName, Rectangle rectangle);

    /**
     * 上传头像 create by lambo@20160323.
     * @param appId 应用id.
     * @param types 类型.
     * @param masterId 图片id.
     * @return
     */
    BaseResult<Void> upload(String appId, List<IconType> types, String masterId);

    /**
     * 上传头像 create by lambo@20160323.
     * @param appId 应用id.
     * @param types 类型.
     * @param masterId 图片id.
     * @return
     */
    BaseResult<Void> update(String appId, List<IconType> types, String masterId);

    /**
     * 获取应用Logo地址
     * @param appIdOrComponentId 应用ID or 组件ID
     * @param deviceType 类型 1.ios ; 2.android；3，web，4，service
     * @return appIconUrl
     */
    BaseResult<String> queryAppIconUrl(String appIdOrComponentId, IconType deviceType);

    BaseResult<String> queryAppIconUrlByUser(FsUserVO fsUserVO, String appIdOrComponentId, IconType deviceType);

    /**
     * 仅用于数据迁移回滚.
     * @param appIdOrComponentId 应用ID or 组件ID.
     * @return
     */
    @Deprecated
    BaseResult<Void> deleteAppIcon(String appIdOrComponentId);
}
