package com.facishare.open.app.center.api.service;

import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.Date;

/**
 * 该服务是提供给账号中心做兼容使用, 其他服务不允许调用该服务.
 * Created by xialf on 3/10/16.
 *
 * @author xialf
 */
public interface UserCenterTryService {
    /**
     * 判断用户是否拥有试用机会.
     *
     * @param user 纷享用户
     * @return 可否试用
     */
    BaseResult<Boolean> hasTrialChance(final FsUserVO user);

    /**
     * 申请试用.
     *
     * @param user 纷享用户
     * @return 试用的结束时间
     */
    BaseResult<Date> applyTrial(final FsUserVO user);
}