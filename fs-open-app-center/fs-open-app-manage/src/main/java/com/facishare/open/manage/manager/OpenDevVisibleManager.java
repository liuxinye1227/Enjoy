package com.facishare.open.manage.manager;

import com.facishare.open.app.center.api.model.OpenDevDO;
import com.facishare.open.app.center.api.model.vo.OpenDevVO;
import com.facishare.open.common.storage.mysql.dao.Pager;

/**
 * Created by wangwz on 2015/9/9.
 */
public interface OpenDevVisibleManager {

    /**
     * 分页查询
     *
     * @param pager
     * @return
     */
    Pager<OpenDevVO> listAllOpenDevVisible(Pager<OpenDevVO> pager);

    /**
     * 删除开发商信息
     *
     * @param id
     * @return
     */
    void deleteByOpenDevId(String id);

    /**
     * 查询开发商信息
     *
     * @param devId
     * @return
     */
    OpenDevDO loadOpenDevByDevId(String devId);
}
