package com.facishare.open.manage.manager;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.VisibleEnum;
import com.facishare.open.app.center.api.model.vo.AppEaVisibleVO;
import com.facishare.open.app.center.api.model.vo.FsEaVO;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.List;

/**
 * 设置应用对于企业的可见.
 *
 * @author zenglb
 * @date 2015年9月1日
 */
public interface AppVisibleManager {

    /**
     * 保存企业可见.
     *
     * @param appId
     * @param visibleEnum 灰度状态.
     * @param fsEaVOs
     * @return
     */
    void save(String appId, VisibleEnum visibleEnum, List<FsEaVO> fsEaVOs);

    /**
     * 查询单个应用的灰度企业.
     *
     * @param appId
     * @return
     */
    AppEaVisibleVO queryEaVisibleDOByAppId(String appId);

    /**
     * 批量查询应用是否灰度.
     * @param appIds
     * @return
     */
    List<String> queryAppIdVisibleConfig(List<String> appIds);
}
