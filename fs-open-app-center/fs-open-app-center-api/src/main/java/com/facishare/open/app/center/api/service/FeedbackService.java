package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.vo.FeedbackVO;
import com.facishare.open.app.center.api.result.BaseResult;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.storage.mysql.dao.Pager;

import java.util.List;

/**
 * Created by xialf on 1/20/16.
 *
 * @author xialf
 */
public interface FeedbackService {
    /**
     * 创建反馈.
     *
     * @param opUser  用户
     * @param content 反馈内容(长度大于0, 小于400)
     * @param contact 联系方式(长度小于32)
     */
    BaseResult<Void> createFeedback(final FsUserVO opUser, final String content, final String contact);

    /**
     * 获取反馈详情.
     *
     * @param id 反馈id
     * @return 反馈内容
     */
    BaseResult<FeedbackVO> getFeedback(final int id);

    /**
     * 获取用户的所有反馈内容(按时间倒序).
     *
     * @param opUser 用户
     * @return 反馈列表
     */
    BaseResult<List<FeedbackVO>> queryFeedback(final FsUserVO opUser);

    /**
     * 获取用户反馈列表(分页).
     *
     * @param pager 分页参数
     * @return 反馈列表
     */
    BaseResult<Pager<FeedbackVO>> queryFeedbackPage(final Pager<FeedbackVO> pager);

    /**
     * 删除用户反馈.
     *
     * @param id 反馈id
     * @return 无
     */
    BaseResult<Void> deleteFeedback(final int id);
}
