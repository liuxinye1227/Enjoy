package com.facishare.open.manage.controller;

import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.support.base.Pager;
import com.facishare.open.support.model.ArticleVO;
import com.facishare.open.support.result.ResultCode;
import com.facishare.open.support.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xialf on 12/9/15.
 *
 * @author xialf
 */

@RestController
@RequestMapping("/open/manage/support/articles")
public class SupportArticleController extends BaseController {
    @Resource
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult createArticle(@RequestBody ArticleVO articleVO) {
        checkParam(articleVO.getTitle(), "文章标题不能为空");
        checkParam(articleVO.getContent(), "文章内容不能为空");
        checkParam(articleVO.getAuthor(), "请填写文章作者");
        checkParam(articleVO.getCategoryId(), "请选择文章的分类");
        BaseResult<Integer> createResult = articleService.createArticle(articleVO);
        if (!createResult.isSuccess()) {
            logger.warn("fail to create article, articleVO={}, result=[}",
                    articleVO, createResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION, createResult.getErrMessage(), "创建文章失败");
        }
        return new AjaxResult(createResult.getResult());
    }

    @RequestMapping(value = "{articleId}", method = RequestMethod.DELETE)
    public AjaxResult deleteArticle(@PathVariable Integer articleId) {
        checkParam(articleId, "请填写文章id");
        BaseResult<Void> deleteResult = articleService.deleteArticle(articleId);
        if (!deleteResult.isSuccess()) {
            logger.warn("fail to delete article, articleId={}, result={}",
                    articleId, deleteResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    deleteResult.getErrMessage(), "删除文章失败");
        } else {
            return SUCCESS;
        }
    }

    @RequestMapping(value = "{articleId}", method = RequestMethod.PUT)
    public AjaxResult updateArticle(@PathVariable Integer articleId, @RequestBody ArticleVO articleVO) {
        checkParamForUpdate(articleVO.getTitle(), "文章标题不能为空");
        checkParamForUpdate(articleVO.getContent(), "文章内容不能为空");
        checkParamForUpdate(articleVO.getAuthor(), "请填写文章作者");
        checkParamForUpdate(articleVO.getCategoryId(), "请选择文章的分类");
        if (!articleId.equals(articleVO.getArticleId())) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "url中的articleId与JSON数据中的articleId不一致");
        }
        BaseResult<Void> updateResult = articleService.updateArticle(articleVO);
        if (!updateResult.isSuccess()) {
            logger.warn("fail to update article, articleVO={}, result={}",
                    articleVO, updateResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    updateResult.getErrMessage(), "更新文章失败");
        }
        return SUCCESS;
    }

    private void checkParamForUpdate(final Object obj, final String message) {
        if (obj != null && obj instanceof String && StringUtils.isBlank((String) obj)) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }
    }

    @RequestMapping(value = "{articleId}", method = RequestMethod.GET)
    public AjaxResult getArticle(@PathVariable Integer articleId) {
        checkParam(articleId, "请填写文章id");
        BaseResult<ArticleVO> articleResult = articleService.getArticle(articleId);
        if (!articleResult.isSuccess()) {
            logger.warn("fail to get article, articleId={}, result={}",
                    articleId, articleResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    articleResult.getErrMessage(), "获取文章详情失败");
        }
        return new AjaxResult(articleResult.getResult());
    }

    @RequestMapping(value = "page", method = RequestMethod.POST)
    public AjaxResult getArticles(@RequestBody Pager<ArticleVO> articleVOPager) {
        BaseResult<Pager<ArticleVO>> articleVOsResult = articleService.getArticles(articleVOPager);
        if (!articleVOsResult.isSuccess()) {
            logger.warn("fail to get articles in page format, pager={}, result={}",
                    articleVOPager, articleVOsResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    articleVOsResult.getErrMessage(), "获取文章列表失败");
        }
        return new AjaxResult(articleVOsResult.getResult());
    }

    @RequestMapping(value = "{articleId}/publish", method = RequestMethod.PUT)
    public AjaxResult publicArticle(@PathVariable Integer articleId) {
        checkParam(articleId, "请填写文章id");
        BaseResult<Void> publishResult = articleService.publishArticle(articleId);
        if (!publishResult.isSuccess()) {
            logger.warn("fail to publish article, articleId={}, result={}",
                    articleId, publishResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    publishResult.getErrMessage(), "发布文章失败");
        }
        return SUCCESS;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public AjaxResult updateArticles(@RequestBody List<ArticleVO> articleVOs) {
        if (articleVOs == null) {
            return new AjaxResult(ResultCode.ILLEGAL_PARAM, "文章列表不能为空");
        }
        final boolean allHasId = articleVOs.stream().allMatch(article -> article.getArticleId() != null);
        if (!allHasId) {
            return new AjaxResult(ResultCode.ILLEGAL_PARAM, "所有文章都必须有Id");
        }

        final BaseResult<Void> updateResult = articleService.updateArticles(articleVOs);
        if (!updateResult.isSuccess()) {
            logger.warn("fail to articleService.updateArticles, articleVOs[{}], result[{}]",
                    articleVOs, updateResult);
            return new AjaxResult(ResultCode.BIZ_ERROR, "保存文章列表失败");
        }
        return new AjaxResult(null);
    }
}
