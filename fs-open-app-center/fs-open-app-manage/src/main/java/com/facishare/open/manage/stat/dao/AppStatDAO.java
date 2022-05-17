package com.facishare.open.manage.stat.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 统计信息
 *
 * @author zenglb
 * @date 2015年9月28日
 */
public interface AppStatDAO {

    /**
     * 查询分页数据
     *
     * @param parameters
     * @return
     */
    Long queryAllAppStat4SearchCount(Map<String, Object> parameters);

    /**
     * 查询分页的明细
     *
     * @param parameters
     * @return
     */
    List<Map<String, Object>> queryAllAppStat4SearchList(Map<String, Object> parameters);

    /**
     * 查询应用的时间区间内的明细数据
     *
     * @return
     */
    List<Map<String, Object>> queryDtlByAppIdAndStatDate(@Param("appId") String appId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    /**
     * 查询应用的时间区间内的明细数据
     *
     * @param componentIds
     * @param startDate
     * @param endDate
     * @return
     */
    List<Map<String, Object>> queryDtlByComponentIdsAndStatDate(@Param("componentIds") List<String> componentIds, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
