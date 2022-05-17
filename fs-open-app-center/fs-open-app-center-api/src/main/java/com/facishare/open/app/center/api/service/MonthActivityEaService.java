package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.MonthActivityEaDO;
import com.facishare.open.app.center.api.result.BaseResult;

import java.util.List;

/**
 * @describe:
 * @author: xiaoweiwei
 * @date: 2016/6/24 16:40
 */
public interface MonthActivityEaService {

    /**
     *
     * @param monthActivityEaDOList
     * @return
     */
    BaseResult<Void> saveBatch(List<MonthActivityEaDO> monthActivityEaDOList);

    /**
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    BaseResult<List<String>> queryPagerAllEaList(Integer currentPage, Integer pageSize);

    /**
     *  清空数据
     * @return
     */
    BaseResult<Void> removeAll();

    /**
     * 统计
     * @return
     */
    BaseResult<Long> countAll();

}
