package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.enums.VisibleEnum;
import com.facishare.open.app.center.api.model.vo.AppEaVisibleVO;
import com.facishare.open.app.center.api.model.vo.FsEaVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.result.StatusResult;
import com.facishare.open.common.model.FsUserVO;

import java.util.List;

/**
 * 企业灰度发布，应用对于企业的可见判断。
 *
 * @author zenglb
 * @since 2015年8月31日
 */
public interface AppEaVisibleService {

    /**
     * 保存应用对企业的关系.
     */
    StatusResult saveVisible(FsUserVO user, String appId, VisibleEnum visibleEnum, List<FsEaVO> fsEas);

    /**
     * 查询单个应用的配置详情.
     *
     * @param appId 应用id
     */
    BaseResult<AppEaVisibleVO> queryEaVisibleDOByAppId(String appId);

    /**
     * 批量查询app的灰度情况返回有灰度的appid.
     *
     * @param appIds 应用列表.
     * @return 有灰度的appid .
     */
    BaseResult<List<String>> queryAppIdVisibleConfig(List<String> appIds);

    /**
     * 增加应用对企业的可见(应用广场).
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     */
    BaseResult<Void> addVisible(final String fsEa, final String appId);

    /**
     * 删除应用对企业的可见(应用广场).
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     */
    BaseResult<Void> removeVisible(final String fsEa, final String appId);

    /**
     * 企业是否可见应用(应用广场).
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     */
    BaseResult<Boolean> isVisible(final String fsEa, final String appId);
}
