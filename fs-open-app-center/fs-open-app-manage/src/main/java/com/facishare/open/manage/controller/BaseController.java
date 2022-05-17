package com.facishare.open.manage.controller;

import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.cons.CenterConstants;
import com.facishare.open.manage.manager.WebAuthManager;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.portal.model.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    public WebAuthManager webAuthManager;

    @Value("${fs.open.app.center.manager.login.url}")
    public String loginUrl;

    protected static final AjaxResult SUCCESS = new AjaxResult(null);

    //不需要拦截的请求集合
    private static List<String> noInterceptList = new ArrayList<>();

    static {
        noInterceptList.add("/open/manage/rest/openApp/queryUserPermission");
    }

    protected void checkParam(Object obj, String message) {
        if (null == obj) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }

        if (obj instanceof String && StringUtils.isBlank((String) obj)) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }
    }

    protected void checkParam(String obj, String regex, String message) {
        checkParam(obj, message);
        if (null != regex && !obj.matches(regex)) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }
    }

    protected void checkAuth(UserDO userDO, String uri){
        if (uri != null && !noInterceptList.contains(uri) && !uri.startsWith("/open/manage/test/")) {
            boolean isUserPermission = webAuthManager.verifyUserPermission(userDO.getId()+"", CenterConstants.SYSTEM_ID, uri);
            if (!isUserPermission) {
                throw new BizException(AjaxCode.NO_AUTHORITY, "NO_AUTHORITY", "权限不足");
            }
        }
    }

    /**
     * 异常通用处理
     *
     * @param request
     * @param e
     * @throws Exception
     */
    @ExceptionHandler
    @ResponseBody
    public AjaxResult exception(HttpServletRequest request, Exception e) throws Exception {
        if (e instanceof HttpMessageNotReadableException) {
            logger.warn("req [{}] failed", request.getRequestURI(), e);
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "系统异常:" + e.getLocalizedMessage());
        }
        if (e instanceof BizException) {
            logger.warn("req [{}] failed", request.getRequestURI(), e);
            BizException biz = (BizException) e;
            if (AjaxCode.USER_NOT_LOGIN == biz.getErrCode()){
                return new AjaxResult(biz.getErrCode(), biz.getErrDescription(), loginUrl);
            }
            return new AjaxResult(biz.getErrCode(), biz.getErrDescription());
        }
        if (e instanceof BindException) {
            logger.warn("req [{}] failed", request.getRequestURI(), e);
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "参数格式异常");
        }
        logger.error("req [{}] failed", request.getRequestURI(), e);
        return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "系统异常");
    }

    /**
     * 获取用户信息
     *
     * @param portalUserId cookie.
     * @param portalToken  cookie.
     * @return 用户.
     */
//    @ModelAttribute
    public UserDO processUserDOFormCookie(@CookieValue(value = "portalUserId", required = false) String portalUserId,
                                          @CookieValue(value = "portalToken", required = false) String portalToken,
                                          HttpServletRequest request) {
        if (!ConfigCenter.needVerifyUserPermission()){
            return new UserDO();
        }


        if (StringUtils.isBlank(portalUserId) || StringUtils.isBlank(portalToken)) {
            throw new BizException(AjaxCode.USER_NOT_LOGIN, "USER_NOT_LOGIN", "用户未登录");
        }
        // TODO: 2016/5/5 add by zhouq 先注释上线，完善url再做url判断 
        String url = request.getRequestURI();
       /* if (!noInterceptList.contains(url)) {
            boolean isUserPermission = webAuthManager.verifyUserPermission(portalUserId, CenterConstants.SYSTEM_ID, url);
            if (!isUserPermission) {
                throw new BizException(AjaxCode.NO_AUTHORITY, "NO_AUTHORITY", "权限不足");
            }
        }*/
        UserDO userDO = webAuthManager.loadWebFsUser(portalUserId, portalToken);
        logger.info("user[{},{},{}], access url[{}], param[]", userDO.getEmployeeId() , userDO.getEmployeeName(), userDO.getPhone(), url, request.getParameterMap());
        if (ConfigCenter.needVerifyUserPermission()){
            checkAuth(userDO,url);
        }
        return userDO;
    }
}
