package com.facishare.open.app.center.api.result;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.common.storage.mysql.dao.Pager;

/**
 * 分页返回
 *
 * @author zenglb
 * @date 2015年8月24日
 */
public class AppPagerResult extends BaseResult<Pager<OpenAppDO>> {

    private static final long serialVersionUID = 4842335370562030748L;

    public AppPagerResult() {
    }

    public AppPagerResult(Pager<OpenAppDO> result) {
        super(result);
    }

    public AppPagerResult(AppCenterCodeEnum codeEnum) {
        super(codeEnum);
    }

    public AppPagerResult(BizException e) {
        super(e);
    }
}
