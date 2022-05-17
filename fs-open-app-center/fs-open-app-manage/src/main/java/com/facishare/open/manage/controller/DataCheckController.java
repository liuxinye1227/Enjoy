package com.facishare.open.manage.controller;

import com.facishare.open.data.check.model.EnterpriseCheckResult;
import com.facishare.open.data.check.service.EnterpriseCheckService;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.data.check.service.EnterpriseRepairService;
import com.facishare.open.manage.ajax.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xialf on 2017/03/24.
 *
 * @author xialf
 * @since 2017/03/24 4:30 PM
 */
@Slf4j
@RestController
@RequestMapping("/open/manage/datacheck/")
public class DataCheckController extends BaseController {
    @Resource
    private EnterpriseCheckService enterpriseCheckService;

    @Resource
    private EnterpriseRepairService enterpriseRepairService;

    @RequestMapping("checkEnterprise/{fsEa:\\w+}")
    AjaxResult checkEnterprise(@PathVariable String fsEa) {
        final BaseResult<EnterpriseCheckResult> result = enterpriseCheckService.checkEnterprise(fsEa);
        if (!result.isSuccess()) {
            log.warn("fail to enterpriseCheckService.checkEnterprise: fsEa[{}], result[{}]",
                    fsEa, result);
            throw new BizException(result);
        }
        return new AjaxResult(result.getResult());
    }

    @RequestMapping("repair/crmView/{fsEa:\\w+}")
    public AjaxResult repair(@PathVariable String fsEa) {
        final BaseResult<Void> repairResult = enterpriseRepairService.repairCrmView(fsEa);
        if (!repairResult.isSuccess()) {
            log.warn("fail to enterpriseRepairService.repairCrmView: fsEa[{}], result[{}]",
                    fsEa, repairResult);
            throw new BizException(repairResult);
        }
        return new AjaxResult(repairResult.getResult());
    }

    @RequestMapping("checkHealth/crmView/{fsEa:\\w+}")
    public AjaxResult checkHealth(@PathVariable String fsEa) {
        final BaseResult<String> healthResult = enterpriseRepairService.checkCrmView(fsEa);
        if (!healthResult.isSuccess()) {
            log.warn("fail to enterpriseRepairService.checkCrmView: fsEa[{}], result[{}]", fsEa, healthResult);
            throw new BizException(healthResult);
        }

        return new AjaxResult(healthResult.getResult());
    }
}
