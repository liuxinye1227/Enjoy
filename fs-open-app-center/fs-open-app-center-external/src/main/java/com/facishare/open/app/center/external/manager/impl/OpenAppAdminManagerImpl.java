package com.facishare.open.app.center.external.manager.impl;

import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.app.center.external.manager.OpenAppAdminManager;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.github.jedis.support.MergeJedisCmd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * impl.
 * Created by zenglb on 2016/10/12.
 */
@Service
public class OpenAppAdminManagerImpl implements OpenAppAdminManager {

    /**
     * 应用管理员的id列表缓存.
     */
    private final static String APP_ADMIN_USER_ID_LIST = "com.ea.app.admin.%s";//appId,

    private final  Gson gson = new Gson();

    @Resource
    private OpenAppAdminService openAppAdminService;

    @Resource
    private MergeJedisCmd jedis;

    @Override
    public boolean isAppAdmin(FsUserVO fsUserVO, String appId) {
        List<FsUserVO> appAdminListByAppId = findAppAdminListByAppId(fsUserVO.getEnterpriseAccount(), appId);
        return appAdminListByAppId.stream()
                .anyMatch(userVo -> Objects.equals(userVo.getUserId(), fsUserVO.getUserId()));
    }

    @Override
    public List<FsUserVO> findAppAdminListByAppId(String fsEa, String appId) {
        List<FsUserVO> result = null;
        String key = String.format(APP_ADMIN_USER_ID_LIST, appId);

        String value = jedis.hget(key, fsEa);
        if (null == value){
            BaseResult<List<String>> queryAppAdminIdListResult = openAppAdminService.queryAppAdminIdList(fsEa, appId);
            if (!queryAppAdminIdListResult.isSuccess()){
                throw new BizException(queryAppAdminIdListResult);
            }
            result = queryAppAdminIdListResult.getResult().stream().map(FsUserVO::new).collect(Collectors.toList());
            value = gson.toJson(result);
            jedis.hset(key, fsEa, value);//设置缓存.
        }
        if (null == result && null != value){
            result = gson.fromJson(value, new TypeToken<List<FsUserVO>>() {
            }.getType());
        }
        if (Objects.isNull(result)) {
            return Collections.emptyList();
        }
        return result;
    }
}
