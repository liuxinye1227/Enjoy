package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppComponentDO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.List;

/**
 * 组件服务,只操作应用中心数据库,不依赖其它外部服务.
 *
 * @author zenglb
 * @date 2015年10月13日
 */
public interface OpenAppComponentService {

    /**
     * 创建组件.
     *
     * @param opUser 操作人
     * @param entity 组件实体
     * @return 空
     */
    BaseResult<Void> createOpenAppComponent(FsUserVO opUser, OpenAppComponentDO entity);

    /**
     * 查询一个应用组件.
     *
     * @param opUser      操作人
     * @param componentId 组件id
     * @return 组件信息
     */
    BaseResult<OpenAppComponentDO> loadOpenAppComponentById(FsUserVO opUser, String componentId);

    /**
     * 批量创建组件.
     *
     * @param opUser   操作人
     * @param entities 组件实体列表
     * @return 空
     */
    BaseResult<Void> createOpenAppComponentBatch(FsUserVO opUser, List<OpenAppComponentDO> entities);

    /**
     * 更新组件.
     *
     * @param opUser 操作人
     * @param entity 组件实体
     * @return 空
     */
    BaseResult<Void> updateOpenAppComponent(FsUserVO opUser, OpenAppComponentDO entity);

    /**
     * 根据appId查询生效的应用组件.
     *
     * @param opUser 操作人
     * @param appId  应用id
     * @return 组件列表
     */
    BaseResult<List<OpenAppComponentDO>> queryAppComponentListByAppId(FsUserVO opUser, String appId);

    /**
     * 根据appId查询应用组件，不区分状态.
     *
     * @param opUser 操作人
     * @param appId  应用id
     * @return 组件列表
     */
    BaseResult<List<OpenAppComponentDO>> queryAllStatusComponentListByAppId(FsUserVO opUser, String appId);

    /**
     * 删除自定义应用组件.
     *
     * @param opUser      操作人
     * @param componentId 组件id
     * @return 是否成功
     */
    BaseResult<Void> deleteCustomComponent(final FsUserVO opUser, final String componentId);

    /**
     * 物理删除组件.仅用于数据迁移.
     *
     * @param opUser      操作人
     * @param componentId 组件id
     * @return 是否成功
     */
    @Deprecated
    BaseResult<Void> deleteComponentPhysical(final FsUserVO opUser, final String componentId);

    /**
     * 是否服务号可见范围成员
     * 已经废弃，请使用isComponentViewMember接口，待互联服务号发版后可删除  added by liqiulin 20170727
     * @param appId
     * @param user
     * @return
     */
    @Deprecated
    BaseResult<Boolean> isServiceViewMember(String appId, FsUserVO user);

    /**
     * 判断用户是否在应用组件的的可见范围内
     * @param appId
     * @param user
     * @param appComponentType 组件类型 @com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum
     * @return
     */
    BaseResult<Boolean> isComponentViewMember(String appId, FsUserVO user, int appComponentType);

    /**
     * 判断用户是否互联服务号下游可见范围企业对接人
     * @param fsUserVO
     * @param appId
     * @param upstreamEa
     * @return
     */
    BaseResult<Boolean> isLinkServiceDownstreamViewMember(FsUserVO fsUserVO, String appId, String upstreamEa);
}
