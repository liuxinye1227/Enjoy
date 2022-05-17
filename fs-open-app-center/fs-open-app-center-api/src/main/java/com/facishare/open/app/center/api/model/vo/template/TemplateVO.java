package com.facishare.open.app.center.api.model.vo.template;

import java.io.Serializable;
import java.util.List;

/**
 * 模板
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateVO implements Serializable {

    private static final long serialVersionUID = 2826028096528644967L;

    /**
     * 模板标题
     */
    private String title;

    /**
     * 模板内容
     */
    private String firstContent;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 关键词列表
     */
    private List<CellVO> cellsList;

    /**
     * 详情值
     */
    private String remarkValue;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CellVO> getCellsList() {
        return cellsList;
    }

    public void setCellsList(List<CellVO> cellsList) {
        this.cellsList = cellsList;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRemarkValue() {
        return remarkValue;
    }

    public void setRemarkValue(String remarkValue) {
        this.remarkValue = remarkValue;
    }

    public String getFirstContent() {
        return firstContent;
    }

    public void setFirstContent(String firstContent) {
        this.firstContent = firstContent;
    }
}
