package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenDemoAppDO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;

/**
 * 示例应用.
 * Created by zenglb on 2016/3/30.
 */
public interface OpenDemoAppService {

    BaseResult<OpenDemoAppDO> loadDemoAppByFsEa(FsUserVO fsUserVO,String fsEa);

    BaseResult<String> saveDemoApp(FsUserVO fsUserVO,String fsEa);

    BaseResult<Void> updateDemoAppId(FsUserVO fsUserVO,String id,String appId);
}
