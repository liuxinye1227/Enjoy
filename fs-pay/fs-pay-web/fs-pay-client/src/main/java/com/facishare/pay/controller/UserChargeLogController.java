package com.facishare.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.pay.business.model.UserChargeLogDo;
import com.facishare.pay.business.service.UserChargeLogService;
import com.facishare.pay.common.result.ModelResult;
/**
 * 用户充值流水
 * @author guom
 * */
@Controller
@RequestMapping("userChargeLog")
public class UserChargeLogController {
    
    @Autowired
    private UserChargeLogService userChargeLogService;
    
    @RequestMapping("/queryUserChargeLog")
    @ResponseBody
    public ModelResult<Pager<UserChargeLogDo>> queryUserChargeLog(Pager<UserChargeLogDo> page) {
        return userChargeLogService.queryUserChargeLog(page, null);
    }
    
    
}
