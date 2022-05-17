package com.facishare.open.app.center.adapter.model.rest.controller;

import com.facishare.open.app.center.adapter.model.manager.AppManager;
import com.facishare.open.app.center.adapter.model.proto.IsAppAdmin;
import com.facishare.open.app.center.adapter.model.proto.ProtobufResult;
import com.facishare.open.app.center.adapter.model.proto.QueryAppAdmins;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by xialf on 7/20/16.
 *
 * @author xialf
 */
@Path("/app")
public class AppController {
    private static Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @Resource
    private AppManager appManager;

    @POST
    @Path("/queryAppAdmins")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public  QueryAppAdmins.Result queryAppAdmins(QueryAppAdmins.Arg arg) {
        try {
             QueryAppAdmins.Result result = new  QueryAppAdmins.Result(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
            result.setAppAdmins(appManager.queryAppAdmins(arg.getFsEa(), arg.getAppId()));
            return result;
        } catch (IllegalArgumentException e) {
            LOGGER.warn("fail to queryAppAdmins, arg[{}]", arg, e);
            return new  QueryAppAdmins.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            LOGGER.warn("fail to queryAppAdmins, arg[{}]", arg, e);
            return new  QueryAppAdmins.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("fail to queryAppAdmins, arg[{}]", arg, e);
            return new  QueryAppAdmins.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }

    @POST
    @Path("/isAppAdmin")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public  IsAppAdmin.Result isAppAdmin(IsAppAdmin.Arg arg) {
        try {
            final boolean isAppAdmin = appManager.isAppAdmin(new FsUserVO(arg.getFsEa(), arg.getUserId()), arg.getAppId());
            return new IsAppAdmin.Result(isAppAdmin);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("fail to isAppAdmin, arg[{}]", arg, e);
            return new  IsAppAdmin.Result(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), e.getMessage());
        } catch (BizException e) {
            LOGGER.warn("fail to isAppAdmin, arg[{}]", arg, e);
            return new  IsAppAdmin.Result(e.getErrCode(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("fail to isAppAdmin, arg[{}]", arg, e);
            return new  IsAppAdmin.Result(AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrCode(), AppCenterCodeEnum.SYSTEM_EXCEPTION.getErrMsg());
        }
    }
}
