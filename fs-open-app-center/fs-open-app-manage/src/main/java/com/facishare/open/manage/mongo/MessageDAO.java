package com.facishare.open.manage.mongo;

import com.facishare.open.manage.model.MessageDO;

import java.util.List;

/**
 * Created by liqiulin on 2017/2/6.
 */
public interface MessageDAO {
    /**
     * 统计消息数
     * @param types
     * @param sendTypes
     * @param startTime
     * @param endTime
     * @return
     */
    long countMessage(List<Integer> types, List<Integer> sendTypes, long startTime, long endTime);

    /**
     * 查询消息ID列表
     * @param types
     * @param sendTypes
     * @param startTime
     * @param endTime
     * @return
     */
    List<String> queryMessage(List<Integer> types, List<Integer> sendTypes, long startTime, long endTime);

    /**
     * 统计消息的应用数
     * @param types
     * @param sendTypes
     * @param startTime
     * @param endTime
     * @return
     */
    long countMessageApp(List<Integer> types, List<Integer> sendTypes, long startTime, long endTime);
}
