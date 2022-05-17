package com.facishare.open.app.center.api.service;

import com.facishare.open.app.center.api.model.OpenAppCreateTemplateDO;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;

import java.util.List;

/**
 * impl.
 * Created by zenglb on 2016/3/21.
 */
public interface OpenAppCreateTemplateService {
    BaseResult<OpenAppCreateTemplateDO> loadById(String templateId);

    BaseResult<List<OpenAppCreateTemplateDO>> queryCreateTemplateList(FsUserVO user, int templateType);

    BaseResult<Integer> incrementServiceNum(String templateId);
}
