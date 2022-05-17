package com.facishare.open.manage.controller;

import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.kits.DateKit;
import com.facishare.open.manage.stat.manager.AppStatManager;
import com.facishare.open.manage.stat.model.enums.StatTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 应用数据统计
 *
 * @author zenglb
 * @date 2015年9月30日
 */
@Controller
@RequestMapping("/open/manage/rest/appStat")
public class AppStatController extends BaseController {
    @Autowired
    private AppStatManager appStatManager;

    /**
     * 查询应用数据可支持appId查询
     *
     * @param pager 分页对象。支持参数 currentPage，pageSize
     * @param appId 应用ID
     * @return
     */
    @RequestMapping("/queryPager")
    @ResponseBody
    public AjaxResult queryPager(Pager<Map<String, Object>> pager,
                                 @RequestParam(value = "appId", required = false) String appId) {
        if (StringUtils.isNotBlank(appId)) {
            pager.addParam("appId", appId);
        }
        //默认为20条记录一页
        pager.setPageSize(20);
        pager.addParam("statType", StatTypeEnum.EA_OPEN.getType());
        pager =  appStatManager.queryAllAppStat4Search(pager);
        return new AjaxResult(pager);
    }

    /**
     * 加载单个应用最近七天数据
     *
     * @param appId appId
     * @return
     */
    @RequestMapping("/loadDtlList")
    @ResponseBody
    public AjaxResult loadDtlList(@RequestParam(value = "appId", required = false) String appId) {
        Date date = DateKit.now();
        List<Map<String, Object>> lst = appStatManager.queryDtlByAppId(appId, DateKit.addDay(date, -7), date);
        return new AjaxResult(lst);
    }
}
