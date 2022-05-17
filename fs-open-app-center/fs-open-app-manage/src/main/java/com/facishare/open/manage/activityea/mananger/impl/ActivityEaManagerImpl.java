package com.facishare.open.manage.activityea.mananger.impl;

import com.facishare.open.addressbook.api.EnterpriseService;
import com.facishare.open.addressbook.model.EnterpriseSimpleInfo;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.app.center.api.model.MonthActivityEaDO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.MonthActivityEaService;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.activityea.dao.ActivityEaDAO;
import com.facishare.open.manage.activityea.mananger.ActivityEaManager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.google.common.collect.Maps;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @describe: 月活企业数据
 * @author: xiaoweiwei
 * @date: 2016/6/27 14:11
 */
@Service
public class ActivityEaManagerImpl implements ActivityEaManager {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MonthActivityEaService monthActivityEaService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private ActivityEaDAO activityEaDAO;
    //活跃天数
    private final Integer activityDays = 30;
    //分批写入
    private final Integer batchSize = 1000;

    @Override
    public Long fetchEa() {
        //先TRUNCATE月活表（临时表）
        monthActivityEaService.removeAll();
        List<Integer> activityEnterpriseIdList = activityEaDAO.selectEnterpriseIdInDays(activityDays);
        int fromIndex = 0;
        int toIndex = batchSize >= activityEnterpriseIdList.size() ? activityEnterpriseIdList.size() : batchSize;
        List<MonthActivityEaDO> monthActivityEaDOList = new ArrayList<>();
        while ((fromIndex <= activityEnterpriseIdList.size() - 1) && (toIndex <= activityEnterpriseIdList.size())) {
            logger.info("listSize[{}], subList start. fromIndex[{}], toIndex[{}]",activityEnterpriseIdList.size(), fromIndex,
                    toIndex);
            List<Integer> subList = activityEnterpriseIdList.subList(fromIndex, toIndex);
            logger.info("subListSize[{}], subList end.",subList.size());
            ListResult<EnterpriseSimpleInfo> enterpriseSimpleInfoListResult = enterpriseService.getEnterpriseSimpleList(subList);
            Map<Integer, String> map = Maps.newHashMap();
            if (enterpriseSimpleInfoListResult.isSuccess()){
                for (EnterpriseSimpleInfo  enterpriseSimpleInfo : enterpriseSimpleInfoListResult.getResult()) {
                    map.put(enterpriseSimpleInfo.getEntperiseId(), enterpriseSimpleInfo.getEnterpriseAccount());
                }
            }
            for (Integer enterpriseId : subList) {
                MonthActivityEaDO mothActivityEaDO = new MonthActivityEaDO();
                mothActivityEaDO.setEnterpriseId(enterpriseId);
                mothActivityEaDO.setEa(map.get(enterpriseId));
                monthActivityEaDOList.add(mothActivityEaDO);

            }
            BaseResult<Void> saveBatchResult = monthActivityEaService.saveBatch(monthActivityEaDOList);
            if (!saveBatchResult.isSuccess()){
                logger.error("monthActivityEaService.saveBatch failed, monthActivityEaDOList[{}]", monthActivityEaDOList);
                break;
            }
            monthActivityEaDOList.clear();
            //准备下一次sub
            fromIndex = fromIndex + batchSize;
            toIndex = toIndex + batchSize;
            if (toIndex >= activityEnterpriseIdList.size()) {
                toIndex = activityEnterpriseIdList.size();
            }
        }
        BaseResult<Long> countResult = monthActivityEaService.countAll();
        if (countResult.isSuccess()){
            return countResult.getResult();
        }
        return new Long(0);
    }

    @Override
    public Long countEa() {
        return activityEaDAO.countEnterpriseIdInDays(activityDays);
    }

    @Override
    public Long readAndSaveTaskFile(MultipartFile taskFile) {
        List<String> fsEas = new ArrayList<>();
        HashSet<String> filter = new HashSet<>();
        //1.读取CSV文件
        try {
            //使用第三方开源类库OpenCSV来读取CSV文件
            CSVReader reader = new CSVReader(new InputStreamReader(
                    taskFile.getInputStream()));
            String[] nextLine;

            // 去除第一行header信息
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length > 0 && !StringUtils.isEmpty(nextLine[0].trim())) {
                    if (filter.add(nextLine[0].trim())){
                        fsEas.add(nextLine[0].trim());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("read cvsFile Failed.", e);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "读取CVS文件失败");
        }
        if (CollectionUtils.isEmpty(fsEas)) {
            logger.warn("fsEas is null. fsEas[{}]", fsEas);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "企业账号列表为空");
        }

        //2.保存CSV文件到临时表
        //2.1清空临时表
        monthActivityEaService.removeAll();

        List<MonthActivityEaDO> fsEaList = new ArrayList<>();
        fsEas.forEach(fsEa -> {
            MonthActivityEaDO monthActivityEaDO = new MonthActivityEaDO();
            monthActivityEaDO.setEa(fsEa);
            fsEaList.add(monthActivityEaDO);
        });
        int fsEaListSize = fsEaList.size();
        int fromIndex = 0;
        int toIndex = batchSize > fsEaListSize ? fsEaListSize : batchSize;
        while (toIndex > fromIndex) {
            List<MonthActivityEaDO> subList = fsEaList.subList(fromIndex, toIndex);
            com.facishare.open.app.center.api.result.BaseResult<Void> saveBatchResult =
                    monthActivityEaService.saveBatch(subList);
            if (!saveBatchResult.isSuccess()) {
                logger.error("saveBatch failed, fsEaList[{}]", fsEaList);
                throw new BizException(AjaxCode.BIZ_EXCEPTION, saveBatchResult, "保存CSV文件失败");
            }
            fromIndex = toIndex;
            toIndex = fromIndex + batchSize;
            toIndex = toIndex > fsEaListSize ? fsEaListSize : toIndex;
        }
        com.facishare.open.app.center.api.result.BaseResult<Long> countResult = monthActivityEaService.countAll();
        if (!countResult.isSuccess()){
            logger.warn("countAll failed, result[{}]", countResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, countResult, "查询企业失败.");
        }
        return countResult.getResult();
    }
}
