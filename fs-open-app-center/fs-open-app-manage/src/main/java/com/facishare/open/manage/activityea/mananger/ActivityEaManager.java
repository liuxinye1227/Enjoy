package com.facishare.open.manage.activityea.mananger;

import org.springframework.web.multipart.MultipartFile;

/**
 * @describe:
 * @author: xiaoweiwei
 * @date: 2016/6/24 14:21
 */
public interface ActivityEaManager {
    /**
     * 抓取月活企业
     */
    Long fetchEa();

    /**
     * 统计企业个数
     * @return
     */
    Long countEa();

    /**
     * 解析并保存任务数据文件到临时表.
     * @param taskFile 数据文件
     * @return count 企业数
     */
    Long readAndSaveTaskFile(MultipartFile taskFile);
}
