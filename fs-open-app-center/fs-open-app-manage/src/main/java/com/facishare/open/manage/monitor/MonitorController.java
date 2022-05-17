package com.facishare.open.manage.monitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zenglb on 2015/11/10.
 */
@Controller
@RequestMapping("/open/manage/monitor")
public class MonitorController {

    /**
     * 用于监控服务是否正常服务
     *
     * @return
     */
    @RequestMapping("/helloIsOk")
    @ResponseBody
    public String check() {
        return "success|" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date());
    }
}
