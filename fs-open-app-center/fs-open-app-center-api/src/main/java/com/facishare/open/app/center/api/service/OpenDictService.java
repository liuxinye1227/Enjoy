package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.result.DictListResult;
import com.facishare.open.app.center.api.result.DictResult;

/**
 * 字典数据的拉取
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public interface OpenDictService {
    /**
     * 加载单个字典数据 类型+key
     *
     * @param dictType 字典类型
     * @param dictKey  key值
     * @return
     */
    DictResult loadOpenDict(String dictType, String dictKey);

    /**
     * 查询单个字典类型的列表数据 类型
     *
     * @param dictType 字典类型
     * @return
     */
    DictListResult queryList(String dictType);
}
