package com.facishare.open.manage.stat.manager;

import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 应用统计
 *
 * @author zenglb
 * @date 2015年9月28日
 */
public interface AppStatManager {

    /**
     * 分页查询
     *
     * @param pager
     * @return
     */
    Pager<Map<String, Object>> queryAllAppStat4Search(Pager<Map<String, Object>> pager);

    /**
     * 明细数据
     *
     * @param appId
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String, Object>> queryDtlByAppId(String appId, Date startDate, Date endDate);
}
