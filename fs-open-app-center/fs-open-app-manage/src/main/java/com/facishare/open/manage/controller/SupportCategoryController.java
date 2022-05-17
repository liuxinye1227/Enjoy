package com.facishare.open.manage.controller;

import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.support.base.Pager;
import com.facishare.open.support.model.CategoryVO;
import com.facishare.open.support.result.ResultCode;
import com.facishare.open.support.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 文章分类
 */
@RestController
@RequestMapping("/open/manage/support/categories")
public class SupportCategoryController extends BaseController {
    private static final String CHANNEL_KEY = "channel";
    private static final String SUPPORT_CHANNEL = "SUPPORT"; //帮助中心频道名称(协议)

    @Resource
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST)
    public AjaxResult createCategory(@RequestBody CategoryVO categoryVO) {
        checkParam(categoryVO.getCategoryName(), "请填写文章分类名称");
        checkParam(categoryVO.getChannel(), "请填写频道名称");
        if (!Character.isLetter(categoryVO.getChannel().charAt(0))
                || categoryVO.getChannel().length() > 8) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "频道名称必须以字母开头且长度不大于8");
        }
        checkParam(categoryVO.getSeqNum(), "请填写分类序号");

        BaseResult<Integer> createResult = categoryService.createCategory(categoryVO);
        if (!createResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, createResult.getErrMessage(), "创建文章分类失败");
        }
        return new AjaxResult(createResult.getResult());
    }

    @RequestMapping(value = "{categoryId:[\\d]+}", method = RequestMethod.DELETE)
    public AjaxResult deleteCategory(@PathVariable Integer categoryId) {
        checkParam(categoryId, "请填写文章分类id");
        BaseResult<Void> deleteResult = categoryService.deleteCategory(categoryId);
        if (!deleteResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    deleteResult.getErrMessage(), "删除文章分类失败");
        } else {
            return SUCCESS;
        }
    }

    @RequestMapping(value = "{categoryId:[\\d]+}", method = RequestMethod.PUT)
    public AjaxResult updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryVO categoryVO) {
        checkParam(categoryVO.getCategoryId(), "请填写文章分类id");
        checkParamForUpdate(categoryVO.getCategoryName(), "请填写文章分类名称");
        if (!categoryId.equals(categoryVO.getCategoryId())) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "url中的categoryId与JSON数据中的categoryId不一致");
        }
        BaseResult<Void> updateResult = categoryService.updateCategory(categoryVO);
        if (!updateResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    updateResult.getErrMessage(), "更新文章分类失败");
        }
        return SUCCESS;
    }

    private void checkParamForUpdate(final Object obj, final String message) {
        if (obj != null && obj instanceof String && StringUtils.isBlank((String) obj)) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public AjaxResult getCategories() {
        BaseResult<List<CategoryVO>> categoriesResult = categoryService.queryCategoriesTree(SUPPORT_CHANNEL);
        if (!categoriesResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    categoriesResult.getErrMessage(), "获取所有文章分类失败");
        }
        return new AjaxResult(categoriesResult.getResult());
    }

    @RequestMapping(value = "{categoryId:[\\d]+}", method = RequestMethod.GET)
    public AjaxResult getCategory(@PathVariable Integer categoryId) {
        BaseResult<CategoryVO> categoryResult = categoryService.getCategory(categoryId);
        if (!categoryResult.isSuccess()) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    categoryResult.getErrMessage(), "获取文章分类失败");
        }
        return new AjaxResult(categoryResult.getResult());
    }

    @RequestMapping(value = "{channel:^[a-zA-Z]+[\\w]+}", method = RequestMethod.GET)
    public AjaxResult getCategoriesByChannel(@PathVariable String channel) {
        BaseResult<List<CategoryVO>> categoriesResult = categoryService.queryCategoriesTree(channel);
        if (!categoriesResult.isSuccess()) {
            logger.warn("fail to getCategories by channel, channel={}, result={}",
                    channel, categoriesResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    categoriesResult.getErrMessage(), "获取频道文章分类失败" + channel);
        }
        return new AjaxResult(categoriesResult.getResult());
    }

    @RequestMapping(value = "statistics/page", method = RequestMethod.POST)
    public AjaxResult getCategoriesByChannelPage(@RequestBody Pager<CategoryVO> pager) {
        if (!pager.getParams().containsKey(CHANNEL_KEY)
                || StringUtils.isBlank(pager.getParams().get(CHANNEL_KEY).toString())) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "频道不能为空");
        }
        BaseResult<Pager<CategoryVO>> statisticsResult =
                categoryService.queryCategoriesByChannelPage(pager, pager.getParams().get(CHANNEL_KEY).toString());
        if (!statisticsResult.isSuccess()) {
            logger.warn("fail to query statistics, pager[{}], result[{}]",
                    pager, statisticsResult);
            throw new BizException(AjaxCode.BIZ_EXCEPTION,
                    statisticsResult.getErrMessage(), "获取分类统计信息失败");
        }
        return new AjaxResult(statisticsResult.getResult());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public AjaxResult updateCategories(@RequestBody List<CategoryVO> categoryVOs) {
        if (categoryVOs == null) {
            return new AjaxResult(ResultCode.ILLEGAL_PARAM, "分类列表不能为空");
        }
        final boolean allHasId = categoryVOs.stream().allMatch(category -> category.getCategoryId() != null);
        if (!allHasId) {
            return new AjaxResult(ResultCode.ILLEGAL_PARAM, "所有分类都必须有categoryId");
        }

        final BaseResult<Void> updateResult = categoryService.updateCategories(categoryVOs);
        if (!updateResult.isSuccess()) {
            logger.warn("fail to categoryService.updateCategories, categoryVOs[{}], result[{}]",
                    categoryVOs, updateResult);
            return new AjaxResult(ResultCode.BIZ_ERROR, "保存分类失败");
        }
        return new AjaxResult(null);
    }

    @RequestMapping(value = "detail/{categoryId:[\\d]+}", method = RequestMethod.GET)
    public AjaxResult getCategoryWithArticleSummaries(@PathVariable final Integer categoryId) {
        if (categoryId == null) {
            return new AjaxResult(ResultCode.ILLEGAL_PARAM, "categoryId不能为空");
        }

        //是否为一级分类
        final BaseResult<Boolean> isTopCategoryResult = categoryService.isTopCategory(categoryId);
        if (!isTopCategoryResult.isSuccess()) {
            logger.warn("fail to call categoryService.isTopCategory, categoryId[{}], result[{}]",
                    categoryId, isTopCategoryResult);
            throw new BizException(isTopCategoryResult);
        }
        if (!isTopCategoryResult.getResult()) {
            return new AjaxResult(ResultCode.ILLEGAL_PARAM, String.format("categoryId:%d不是一级分类", categoryId));
        }

        //获取详细信息
        final BaseResult<CategoryVO> categoryWithArticleSummaries = categoryService.queryCategoryWithArticleSummaries(categoryId);
        if (!categoryWithArticleSummaries.isSuccess()) {
            logger.warn("fail to call categoryService.getCategoryWithArticleSummaries, categoryId[{}], result[{}]",
                    categoryId, categoryWithArticleSummaries);
            throw new BizException(categoryWithArticleSummaries);
        }
        return new AjaxResult(categoryWithArticleSummaries.getResult());
    }
}
