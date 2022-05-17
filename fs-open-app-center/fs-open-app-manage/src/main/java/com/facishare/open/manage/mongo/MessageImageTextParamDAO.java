package com.facishare.open.manage.mongo;

import java.util.List;

/**
 * Created by liqiulin on 2017/2/6.
 */
public interface MessageImageTextParamDAO {

    /**
     * 查询图文ID列表
     * @param messageIds
     * @param startTime
     * @param endTime
     * @return
     */
    List<String> queryImageTextParamIds(List<String> messageIds, long startTime, long endTime);

}
