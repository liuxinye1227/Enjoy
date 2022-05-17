package com.facishare.open.app.center.api.model.property;

import com.facishare.open.app.center.api.model.vo.custom.create.template.AppCreateTemplateDtlImgVO;

import java.io.Serializable;
import java.util.List;

/**
 * 创建模板属性.
 * Created by zenglb on 2016/3/23.
 */
public class AppCreateTemplateProperties implements Serializable {

    /**
     * 单个明细图片.
     */
    private List<AppCreateTemplateDtlImgVO> dtlImgList;
    /**
     * 结果页图片.
     */
    private List<AppCreateTemplateDtlImgVO> resultImgList;

    /**
     * 开启功能清单.
     */
    private List<String> funcList;

    public List<AppCreateTemplateDtlImgVO> getDtlImgList() {
        return dtlImgList;
    }

    public void setDtlImgList(List<AppCreateTemplateDtlImgVO> dtlImgList) {
        this.dtlImgList = dtlImgList;
    }

    public List<AppCreateTemplateDtlImgVO> getResultImgList() {
        return resultImgList;
    }

    public void setResultImgList(List<AppCreateTemplateDtlImgVO> resultImgList) {
        this.resultImgList = resultImgList;
    }

    public List<String> getFuncList() {
        return funcList;
    }

    public void setFuncList(List<String> funcList) {
        this.funcList = funcList;
    }
}
