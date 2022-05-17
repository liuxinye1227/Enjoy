package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.EmployeeRange;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.Collection;
import java.util.Map;

/**
 * Created by xialf on 3/10/16.
 *
 * @author xialf
 */
public interface TrialService {
    BaseResult<Void> applyEmployeeTrial(final FsUserVO user, final String appId);

    BaseResult<Void> applyEnterpriseTrial(final FsUserVO admin, final String appId, Collection<Integer> appAdminIds,
                                          Map<String, EmployeeRange> componentViews);
}
