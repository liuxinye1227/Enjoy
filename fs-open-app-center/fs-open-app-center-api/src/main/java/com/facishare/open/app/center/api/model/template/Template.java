package com.facishare.open.app.center.api.model.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板消息结构
 *
 * @author songk
 * @date 2015年8月28日
 */
public class Template implements Serializable {

    private static final long serialVersionUID = 5045181277306688693L;

    /**
     * 模板标题
     */
    private TemplateTitle title = new TemplateTitle();

    /**
     * 模板第一句话
     */
    private TemplateFirst first;

    /**
     * 模板内容
     */
    private List<TemplateInfo> infos = new ArrayList<TemplateInfo>();

    /**
     * 模板备注
     */
    private TemplateRemark remark;

    /**
     * 模板底部跳转内容
     */
    private TemplateButton button = new TemplateButton();

    /**
     * 模板媒介（图片、音频等）
     */
    private List<TemplateMedia> mediaList = new ArrayList<TemplateMedia>();

    public TemplateTitle getTitle() {
        return title;
    }

    public void setTitle(TemplateTitle title) {
        this.title = title;
    }

    public TemplateFirst getFirst() {
        return first;
    }

    public void setFirst(TemplateFirst first) {
        this.first = first;
    }

    public List<TemplateInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<TemplateInfo> infos) {
        this.infos = infos;
    }

    public TemplateRemark getRemark() {
        return remark;
    }

    public void setRemark(TemplateRemark remark) {
        this.remark = remark;
    }

    public TemplateButton getButton() {
        return button;
    }

    public void setButton(TemplateButton button) {
        this.button = button;
    }

    public List<TemplateMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<TemplateMedia> mediaList) {
        this.mediaList = mediaList;
    }
}