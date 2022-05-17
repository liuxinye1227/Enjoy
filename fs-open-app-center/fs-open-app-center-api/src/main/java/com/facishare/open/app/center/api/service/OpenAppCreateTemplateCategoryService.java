package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppCreateTemplateCategoryDO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * Created by liqiulin on 2016/7/14.
 */
public interface OpenAppCreateTemplateCategoryService {
    BaseResult<List<OpenAppCreateTemplateCategoryDO>> queryCreateTemplateCategoryList(int templateType);
}
