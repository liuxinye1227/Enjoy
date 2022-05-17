package com.facishare.open.manage.controller;

import com.facishare.open.addressbook.api.EnterpriseService;
import com.facishare.open.addressbook.model.MetaParam;
import com.facishare.open.addressbook.model.ShortEnterprisesResult;
import com.facishare.open.addressbook.result.BeanResult;
import com.facishare.open.app.center.api.model.vo.FsEaVO;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/open/manage/rest/fsEa")
public class FsEaController extends BaseController {
    @Resource
    private EnterpriseService enterpriseService;

    private FsUserVO searchUser;

    @Value("${fs.open.app.center.FsEaController.searchUser}")
    public void setSearchUser(String fsUserVOString) {
        searchUser = FsUserVO.toFsUserVO(fsUserVOString);
    }

    @RequestMapping("/searchFsEaByText")
    @ResponseBody
    public AjaxResult searchFsEaByText(@RequestParam(value = "text", required = false) String text,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        checkParam(text,"搜索内容不能为空");
        text = text.trim();
        checkParam(text,"[\\s\\S]{2,}","搜索内容至少两个字符");

        MetaParam metaParam = new MetaParam(searchUser.getEnterpriseAccount(),searchUser.getUserId());
        BeanResult<ShortEnterprisesResult> shortEnterprisesResult = enterpriseService.getShortEnterprises(metaParam, text, pageSize, 1);
        if(!shortEnterprisesResult.isSuccess()){
           throw  new BizException(AjaxCode.BIZ_EXCEPTION,shortEnterprisesResult,"查询公司失败");
        }

        List<FsEaVO> result = new ArrayList<>();
        if(!CollectionUtils.isEmpty(shortEnterprisesResult.getResult().getEnterprises())){
            shortEnterprisesResult.getResult().getEnterprises().forEach(info -> {
                result.add(new FsEaVO(info.getEnterpriseAccount(),info.getEnterpriseName()));
            });
        }
        return new AjaxResult(result);
    }
}
