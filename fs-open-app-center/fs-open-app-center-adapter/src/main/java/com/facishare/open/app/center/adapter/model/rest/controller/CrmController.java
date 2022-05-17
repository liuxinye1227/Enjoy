package com.facishare.open.app.center.adapter.model.rest.controller;

import com.facishare.open.app.center.adapter.model.manager.AppManager;
import com.facishare.open.app.center.adapter.model.manager.CrmManager;
import com.facishare.open.app.center.adapter.model.proto.AddCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.AjaxResult;
import com.facishare.open.app.center.adapter.model.proto.ProtobufResult;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmAvail;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmEmployeeTrialInfos;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmQuotaRecords;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmStatus;
import com.facishare.open.app.center.adapter.model.proto.QueryQuota;
import com.facishare.open.app.center.adapter.model.proto.QueryUsers;
import com.facishare.open.app.center.adapter.model.proto.RemoveCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.RemoveUsers;
import com.facishare.open.app.center.adapter.model.proto.SaveUsers;
import com.facishare.open.app.center.adapter.model.util.ConfigCenter;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.service.OpenFsUserAppViewService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by xialf on 5/30/16.
 *
 * @author xialf
 */
@Slf4j
@Path("/CRM")
public class CrmController {
    @Resource
    private CrmManager crmManager;

    @Resource
    private AppManager appManager;

    @Resource
    private OpenFsUserAppViewService openFsUserAppViewService;

    @POST
    @Path("/queryCrmAvailability")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryCrmAvail.Result queryCrmAvailability(QueryCrmAvail.Arg arg) {
        QueryCrmAvail.Result result = new QueryCrmAvail.Result();
        if (null == arg || null == arg.getFsEa() || null == arg.getUserId()) {
            result.setCode(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode());
            return result;
        }
        try {
            FsUserVO fsUser = new FsUserVO(arg.getFsEa(), arg.getUserId());

            final com.facishare.open.app.center.api.result.BaseResult<Boolean> accessResult =
                    openFsUserAppViewService.canAccessComponent(fsUser, ConfigCenter.getCrmComponentId());
            if (!accessResult.isSuccess()) {
                log.warn("fail to call openFsUserAppViewService.canAccessComponent, user={}, componentId={}",
                        fsUser, ConfigCenter.getCrmComponentId());
                result.setCode(accessResult.getErrCode());
                return result;
            }
            result.setCode(AppCenterCodeEnum.SUCCESS.getErrCode());
            result.setAvailability(accessResult.getResult() ? 1 : 2); //1可见，2不可见
            return result;
        } catch (BizException e) {
            log.warn("fail to queryCrmAvailability, arg={}", arg, e);
            return new QueryCrmAvail.Result(e.getErrCode());
        } catch (Exception e) {
            log.error("fail to queryCrmAvailability ,arg={}", arg, e);
            return new QueryCrmAvail.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode());
        }
    }

    @GET
    @Path("/queryCrmAvail")
    @Produces(MediaType.APPLICATION_JSON)
    public AjaxResult queryCrmAvail(@QueryParam(value = "fsEa") String fsEa,
                                    @QueryParam(value = "userId") String userId) {
        try {
            Preconditions.checkArgument(fsEa != null, "fsEa cannot be null");
            Preconditions.checkArgument(userId != null, "userId cannot be null");

            FsUserVO fsUser;
            try {
                fsUser = new FsUserVO(fsEa, Integer.parseInt(userId));
            } catch (Exception e) {
                throw new IllegalArgumentException("userId should be integer: " + userId);
            }

            final com.facishare.open.app.center.api.result.BaseResult<Boolean> accessResult =
                    openFsUserAppViewService.canAccessComponent(fsUser, ConfigCenter.getCrmComponentId());
            if (!accessResult.isSuccess()) {
                log.warn("fail to call openFsUserAppViewService.canAccessComponent, user={}, componentId={}",
                        fsUser, ConfigCenter.getCrmComponentId());
                return new AjaxResult(accessResult);
            }
            return new AjaxResult(accessResult.getResult() ? 1 : 2); //1可见，2不可见
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryCrmAvail, fsEa[{}], userId[{}]", fsEa, userId, e);
            return new AjaxResult(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            if (e.getErrCode() == AppCenterCodeEnum.QUOTA_INSUFFICIENT.getErrCode()) {
                log.warn("fail to queryCrmAvail because quota insufficient, fsEa[{}], userId[{}]",  fsEa, userId);
            } else {
                log.warn("fail to queryCrmAvail, fsEa[{}], userId[{}]",  fsEa, userId, e);
            }
            return new AjaxResult(e);
        } catch (Exception e) {
            log.error("fail to queryCrmAvail, fsEa[{}], userId[{}]",  fsEa, userId, e);
            return new AjaxResult(AppCenterCodeEnum.SYSTEM_EXCEPTION);
        }
    }

    @POST
    @Path("/view/saveUsers")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public SaveUsers.Result saveUsers(SaveUsers.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "SaveUsers.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");
            Preconditions.checkArgument(arg.getUserIds() != null, "userIds cannot be null");

            crmManager.saveUsers(arg.getFsEa(), arg.getUserIds());
            return new SaveUsers.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
        } catch (IllegalArgumentException e) {
            log.warn("fail to saveUsers, arg[{}]", arg, e);
            return new SaveUsers.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            if (e.getErrCode() == AppCenterCodeEnum.QUOTA_INSUFFICIENT.getErrCode()) {
                log.warn("fail to saveUsers because quota insufficient, arg[{}]", arg);
            } else {
                log.warn("fail to saveUsers, arg[{}]", arg, e);
            }
            return new SaveUsers.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to saveUsers, arg[{}]", arg, e);
            return new SaveUsers.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/view/removeUsers")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public RemoveUsers.Result removeUsers(RemoveUsers.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "RemoveUsers.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");
            Preconditions.checkArgument(arg.getUserIds() != null, "userIds cannot be null");

            crmManager.removeUsers(arg.getFsEa(), arg.getUserIds());
            return new RemoveUsers.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
        } catch (IllegalArgumentException e) {
            log.warn("fail to removeUsers, arg[{}]", arg, e);
            return new RemoveUsers.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to removeUsers, arg[{}]", arg, e);
            return new RemoveUsers.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to removeUsers, arg[{}]", arg, e);
            return new RemoveUsers.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/view/queryUsers")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryUsers.Result queryUsers(QueryUsers.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "QueryUsers.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");

            final List<Integer> users = crmManager.queryUsers(arg.getFsEa());
            final QueryUsers.Result result = new QueryUsers.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
            result.setFsEa(arg.getFsEa());
            result.setUserIds(users);

            return result;
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryUsers, arg[{}]", arg, e);
            return new QueryUsers.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to queryUsers, arg[{}]", arg, e);
            return new QueryUsers.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryUsers, arg[{}]", arg, e);
            return new QueryUsers.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/admin/query")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryCrmAppAdmins.Result queryAppAdmins(QueryCrmAppAdmins.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "QueryCrmAppAdmins.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");
            Preconditions.checkArgument(arg.getOperatorId() != null, "operatorId cannot be null");

            final List<Integer> admins = appManager.queryAppAdmins(arg.getFsEa(), ConfigCenter.getCrmAppId());
            return new QueryCrmAppAdmins.Result(admins);
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryAppAdmins, arg[{}]", arg, e);
            return new QueryCrmAppAdmins.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to queryAppAdmins, arg[{}]", arg, e);
            return new QueryCrmAppAdmins.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryAppAdmins, arg[{}]", arg, e);
            return new QueryCrmAppAdmins.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/admin/add")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public AddCrmAppAdmins.Result addAppAdmins(AddCrmAppAdmins.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "AddCrmAppAdmins.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");
            Preconditions.checkArgument(arg.getOperatorId() != null, "operatorId cannot be null");
            Preconditions.checkArgument(arg.getAppAdmins() != null, "appAdmins cannot be null");

            if (!arg.getAppAdmins().isEmpty()) {
                appManager.addAppAdmins(new FsUserVO(arg.getFsEa(), arg.getOperatorId()), ConfigCenter.getCrmAppId(), arg.getAppAdmins());
            }
            return new AddCrmAppAdmins.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
        } catch (IllegalArgumentException e) {
            log.warn("fail to addAppAdmins, arg[{}]", arg, e);
            return new AddCrmAppAdmins.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            if (e.getErrCode() == AppCenterCodeEnum.QUOTA_INSUFFICIENT.getErrCode()) {
                log.warn("fail to addAppAdmins because quota insufficient, arg[{}]", arg);
            } else {
                log.warn("fail to addAppAdmins, arg[{}]", arg, e);
            }
            return new AddCrmAppAdmins.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to addAppAdmins, arg[{}]", arg, e);
            return new AddCrmAppAdmins.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/admin/remove")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public RemoveCrmAppAdmins.Result removeAppAdmins(RemoveCrmAppAdmins.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "RemoveCrmAppAdmins.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");
            Preconditions.checkArgument(arg.getOperatorId() != null, "operatorId cannot be null");
            Preconditions.checkArgument(arg.getAppAdmins() != null, "appAdmins cannot be null");

            if (!arg.getAppAdmins().isEmpty()) {
                appManager.removeAppAdmins(new FsUserVO(arg.getFsEa(), arg.getOperatorId()), ConfigCenter.getCrmAppId(), arg.getAppAdmins());
            }
            return new RemoveCrmAppAdmins.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
        } catch (IllegalArgumentException e) {
            log.warn("fail to removeAppAdmins, arg[{}]", arg, e);
            return new RemoveCrmAppAdmins.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to removeAppAdmins, arg[{}]", arg, e);
            return new RemoveCrmAppAdmins.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to removeAppAdmins, arg[{}]", arg, e);
            return new RemoveCrmAppAdmins.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/queryQuota")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryQuota.Result queryQuota(QueryQuota.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "QueryQuota.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");

            final int quota = crmManager.queryQuota(arg.getFsEa());
            final QueryQuota.Result result = new QueryQuota.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
            result.setQuota(quota);
            return result;
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryQuota, arg[{}]", arg, e);
            return new QueryQuota.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to queryQuota, arg[{}]", arg, e);
            return new QueryQuota.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryQuota, arg[{}]", arg, e);
            return new QueryQuota.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/queryStatus")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryCrmStatus.Result queryStatus(QueryCrmStatus.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "QueryCrmStatus.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");

            final int status = crmManager.queryStatus(arg.getFsEa());
            return new QueryCrmStatus.Result(status);
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryStatus, arg[{}]", arg, e);
            return new QueryCrmStatus.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to queryStatus, arg[{}]", arg, e);
            return new QueryCrmStatus.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryStatus, arg[{}]", arg, e);
            return new QueryCrmStatus.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/queryQuotaRecords")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryCrmQuotaRecords.Result queryQuotaRecord(QueryCrmQuotaRecords.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "QueryCrmQuotaRecords.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");

            return new QueryCrmQuotaRecords.Result(crmManager.queryCrmQuotaRecords(arg.getFsEa()));
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryQuotaRecord, arg[{}]", arg, e);
            return new QueryCrmQuotaRecords.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to queryQuotaRecord, arg[{}]", arg, e);
            return new QueryCrmQuotaRecords.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryQuotaRecord, arg[{}]", arg, e);
            return new QueryCrmQuotaRecords.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/queryEmployeeTrialInfos")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public QueryCrmEmployeeTrialInfos.Result queryEmployeeTrialInfos(QueryCrmEmployeeTrialInfos.Arg arg) {
        try {
            Preconditions.checkArgument(arg != null, "QueryCrmEmployeeTrialInfos.Arg cannot be null");
            Preconditions.checkArgument(arg.getFsEa() != null, "fsEa cannot be null");

            return new QueryCrmEmployeeTrialInfos.Result(crmManager.queryCrmEmployeeTrialInfos(arg.getFsEa()));
        } catch (IllegalArgumentException e) {
            log.warn("fail to queryEmployeeTrialInfos, arg[{}]", arg, e);
            return new QueryCrmEmployeeTrialInfos.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            log.warn("fail to queryEmployeeTrialInfos, arg[{}]", arg, e);
            return new QueryCrmEmployeeTrialInfos.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            log.error("fail to queryEmployeeTrialInfos, arg[{}]", arg, e);
            return new QueryCrmEmployeeTrialInfos.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @GET
    @Path("ping")
    public String ping() {
        return "success";
    }

}
