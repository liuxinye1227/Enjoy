package com.facishare.open.manage.activityea.dao;

import java.util.List;

/**
 * @describe: 每日活跃企业记录DAO
 * @author: xiaoweiwei
 * @date: 2016/6/27 14:13
 */
public interface ActivityEaDAO {

    /**
     *
     * @param days
     * @return
     */
    Long countEnterpriseIdInDays(Integer days);

    /**
     * 按照limit m , n来查询活跃企业
     * @param days
     * @return
     */
    List<Integer> selectEnterpriseIdInDays(Integer days);

    /**
     * 按照limit m , n来查询活跃企业
     * @param days
     * @param offset
     * @param rows
     * @return
     */
    List<Integer> selectEnterpriseIdInDaysByLimit(Integer days, Integer offset, Integer rows);

}
