package com.facishare.open.manage.controller;

import com.facishare.open.addressbook.api.EmployeeService;
import com.facishare.open.addressbook.model.Employee;
import com.facishare.open.addressbook.result.ListResult;
import com.facishare.open.app.center.api.model.vo.FeedbackVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.app.center.api.service.FeedbackService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.model.FeedbackForShow;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * Created by xialf on 1/22/16.
 *
 * @author xialf
 */
@Controller
@RequestMapping("/open/manage/rest/feedback")
public class FeedbackController extends BaseController {
    @Resource
    private FeedbackService feedbackService;

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "{feedbackId:[\\d]+}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult deleteFeedback(@PathVariable Integer feedbackId) {
        checkParam(feedbackId, "请填写文章id");
        BaseResult<Void> deleteResult = feedbackService.deleteFeedback(feedbackId);
        if (!deleteResult.isSuccess()) {
            logger.warn("fail to delete feedback, feedbackId={}, result={}",
                    feedbackId, deleteResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    deleteResult.getErrMessage(), "删除用户反馈失败");
        } else {
            return SUCCESS;
        }
    }

    @RequestMapping(value = "{feedbackId:[\\d]+}", method = RequestMethod.GET)
    @ResponseBody
    public AjaxResult getFeedback(@PathVariable Integer feedbackId) {
        checkParam(feedbackId, "请填写文章id");
        BaseResult<FeedbackVO> feedbackResult = feedbackService.getFeedback(feedbackId);
        if (!feedbackResult.isSuccess()) {
            logger.warn("fail to get feedback, feedbackId={}, result={}",
                    feedbackId, feedbackResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    feedbackResult.getErrMessage(), "获取用户反馈详情失败");
        } else {
            return new AjaxResult(this.convertForShow(feedbackResult.getResult()));
        }
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult queryAppBestApps(@RequestBody Pager<FeedbackVO> pager) {
        final BaseResult<Pager<FeedbackVO>> feedbackPageResult = feedbackService.queryFeedbackPage(pager);
        if (!feedbackPageResult.isSuccess()) {
            logger.warn("fail to get feedback in page format, pager={}, result={}",
                    pager, feedbackPageResult);
            return new AjaxResult(AjaxCode.BIZ_EXCEPTION, "获取反馈列表失败");
        }
        Pager<FeedbackForShow> feedbackForShowPager = new Pager<>();
        feedbackForShowPager.setCurrentPage(feedbackPageResult.getResult().getCurrentPage());
        feedbackForShowPager.setPageSize(feedbackPageResult.getResult().getPageSize());
        feedbackForShowPager.setRecordSize(feedbackPageResult.getResult().getRecordSize());
        feedbackForShowPager.setPageTotal(feedbackPageResult.getResult().getPageTotal());
        feedbackForShowPager.setParams(pager.getParams());
        feedbackForShowPager.setData(feedbackPageResult.getResult().getData().stream()
                .map(this::convertForShow)
                .collect(Collectors.toList()));
        return new AjaxResult(feedbackForShowPager);
    }

    private FeedbackForShow convertForShow(final FeedbackVO feedbackVO) {
        FeedbackForShow feedbackForShow = new FeedbackForShow();
        feedbackForShow.setId(feedbackVO.getId());
        feedbackForShow.setFsUserId(feedbackVO.getFsUserId());
        feedbackForShow.setContent(feedbackVO.getContent());
        feedbackForShow.setContact(feedbackVO.getContact());
        feedbackForShow.setGmtCreate(feedbackVO.getGmtCreate());

        //todo 填充公司名字和用户名字
        feedbackForShow.setEaName("");
        feedbackForShow.setUserName("");
        FsUserVO user = FsUserVO.toFsUserVO(feedbackVO.getFsUserId());
        String fsEa = user.getEnterpriseAccount();
        final ListResult<Employee> employeesResult =
                employeeService.getEmployeesNoAdminId(fsEa, Lists.newArrayList(user.getUserId()));
        if (!employeesResult.isSuccess()) {
            logger.warn("fail to employeeService.getEmployees, fsEa={}, employeeIds={}",
                    fsEa, user.getUserId());
        } else {
            feedbackForShow.setUserName(employeesResult.getResult().get(0).getFullName());
        }
        return feedbackForShow;
    }
}
