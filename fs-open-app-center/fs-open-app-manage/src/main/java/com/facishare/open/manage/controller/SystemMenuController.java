package com.facishare.open.manage.controller;

import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.cons.CenterConstants;
import com.facishare.portal.model.UserDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统菜单
 * Created by zhouq
 * on 2016/4/20.
 */
@Controller
@RequestMapping("/open/manage/rest/openApp")
public class SystemMenuController extends BaseController {

    /**
     * 获取用户信息
     * @param portalUserId cookie.
     * @param portalToken cookie.
     * @return 用户.
     */
    @ModelAttribute
    public UserDO processUserDOFormCookie(@CookieValue(value = "portalUserId", required = false) String portalUserId,
                                          @CookieValue(value = "portalToken", required = false) String portalToken ) {
        if (StringUtils.isBlank(portalUserId) || StringUtils.isBlank(portalToken)) {
            throw new BizException(AjaxCode.USER_NOT_LOGIN, "USER_NOT_LOGIN", "用户未登录");
        }
        return webAuthManager.loadWebFsUser(portalUserId, portalToken);
    }

    @RequestMapping("/queryUserPermission")
    @ResponseBody
    public AjaxResult queryUserPermission(@ModelAttribute UserDO user) {
        UserDO systemDO = webAuthManager.queryUserPermission(user, CenterConstants.SYSTEM_ID);
        return new AjaxResult(systemDO);
    }

}
