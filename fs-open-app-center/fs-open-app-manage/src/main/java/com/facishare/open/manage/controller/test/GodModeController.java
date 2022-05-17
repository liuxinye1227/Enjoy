package com.facishare.open.manage.controller.test;

import com.facishare.open.addressbook.api.CircleService;
import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.addressbook.model.Circle;
import com.facishare.open.addressbook.model.Employee;
import com.facishare.open.addressbook.model.MetaParam;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.app.ad.api.enums.ModuleKeyEnum;
import com.facishare.open.app.ad.api.service.CheckAppUpdatedService;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.controller.BaseController;
import com.facishare.portal.model.UserDO;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 上帝模式的专用跳转地址.
 * Created by zenglb on 2016/11/15.
 */
@RestController
@RequestMapping("/open/manage/god/mode")
public class GodModeController extends BaseController {

    @Resource
    private OpenAppAdminService openAppAdminService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private CircleService circleService;
    @Resource
    private CheckAppUpdatedService checkAppUpdatedService;

    /**
     * 查询应用的管理员.
     *
     * @param fsEa  企业账号
     * @param appId 应用id
     */
    @RequestMapping(value = "/queryAppAdmin")
    public AjaxResult queryAppAdmin(HttpServletRequest request,
                                    @ModelAttribute UserDO userDO,
                                    @RequestParam(value = "appId", required = false) String appId,
                                    @RequestParam(value = "fsEa", required = false) String fsEa) {
        checkParam(appId, "appId required ");
        checkParam(fsEa, "fsEa required");
        checkAuth(userDO, request.getRequestURI());
        BaseResult<List<String>> listBaseResult = openAppAdminService.queryAppAdminIdList(fsEa, appId);
        if (!listBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, listBaseResult, "业务异常: " + listBaseResult);
        }
        if (!CollectionUtils.isEmpty(listBaseResult.getResult())) {
            List<Integer> userIds = listBaseResult.getResult().stream().map(FsUserVO::new).map(FsUserVO::getUserId).collect(Collectors.toList());
            return new AjaxResult(getEmployeesNoAdminId(fsEa, userIds));
        }
        return new AjaxResult(Lists.newArrayList());
    }

    private List<Map<String,Object>> getEmployeesNoAdminId(String fsEa, List<Integer> userIds) {
        ListResult<Employee> employeesNoAdminId = employeeService.getEmployeesNoAdminId(fsEa, userIds);
        if (!employeesNoAdminId.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, employeesNoAdminId, "查询员工信息失败");
        }
        List<Map<String,Object>> result = new ArrayList<>();
        List<Employee> employeeList = employeesNoAdminId.getResult();
        HashMap<Integer, Object> circleMap= new HashMap<>();
        if (!CollectionUtils.isEmpty(employeeList)) {
            List<Integer> circleIds = employeeList.stream().map(Employee::getFlatCirlceIds).flatMap(List::stream).distinct().collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(circleIds)) {
                ListResult<Circle> creatorUserCircleListResult = circleService.getCircles(new MetaParam(fsEa,
                        employeeService.getAdminIds(fsEa).getResult().get(0)), circleIds);
                if (!creatorUserCircleListResult.isSuccess()) {
                    throw new BizException(AjaxCode.BIZ_EXCEPTION, "业务异常");
                }

                circleMap.putAll(creatorUserCircleListResult.getResult().stream().collect(Collectors.toMap(Circle::getCircleId, e -> e)));
            }
        }
        employeeList.forEach(employee -> {
            Map<String ,Object> line = new HashMap<>();
            line.put("user", employee);
            if(!CollectionUtils.isEmpty(employee.getFlatCirlceIds())){
                line.put("dep", employee.getFlatCirlceIds().stream().map(i -> circleMap.get(i)).collect(Collectors.toList()));
            }
            result.add(line);
        });
        return result;
    }


    /**
     * 查询用户列表。
     *
     * @param fsEa          企业账号
     * @param userIdsString 应用id
     */
    @RequestMapping(value = "/getEmployees")
    public AjaxResult getEmployees(HttpServletRequest request,
                                   @ModelAttribute UserDO userDO,
                                   @RequestParam(value = "fsEa", required = false) String fsEa,
                                   @RequestParam(value = "userIds", required = false) String userIdsString) {
        checkParam(userIdsString, "userIdsString required ");
        checkParam(fsEa, "fsEa required");
        checkAuth(userDO, request.getRequestURI());

        List<Integer> userIds = Arrays.asList(userIdsString.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        return new AjaxResult(getEmployeesNoAdminId(fsEa, userIds));
    }

    /**
     * 查询用户系统管理员列表。
     *
     * @param fsEa 企业账号
     */
    @RequestMapping(value = "/getAdminIds")
    public AjaxResult getAdminIds(HttpServletRequest request,
                                  @ModelAttribute UserDO userDO,
                                  @RequestParam(value = "fsEa", required = false) String fsEa) {
        checkParam(fsEa, "fsEa required");
        checkAuth(userDO, request.getRequestURI());
        ListResult<Integer> adminIds = employeeService.getAdminIds(fsEa);
        if (!adminIds.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, adminIds, "查询系统管理员失败");
        }
        return new AjaxResult(getEmployeesNoAdminId(fsEa, adminIds.getResult()));
    }


    @RequestMapping("/resetTagByModuleKey")
    public AjaxResult resetTagByModuleKey(HttpServletRequest request,
                                          @ModelAttribute UserDO userDO,
                                          @RequestParam(value = "moduleKey", required = false) String moduleKey) {
        checkParam(moduleKey, "moduleKey required");
        checkParam(ModuleKeyEnum.getByModuleKey(moduleKey), "ModuleKeyEnum parse failed");
        checkAuth(userDO, request.getRequestURI());

        com.facishare.open.common.result.BaseResult<Void> voidBaseResult = checkAppUpdatedService.resetTagByModuleKey(null, ModuleKeyEnum.getByModuleKey(moduleKey));
        if (!voidBaseResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, voidBaseResult, "重置失败");
        }
        return new AjaxResult(null);
    }
}
