package com.facishare.open.app.center.api.model.template;

import com.facishare.open.app.center.api.exception.TemplateParamsException;

import java.io.Serializable;

/**
 * 模板结束语
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateRemark implements Serializable {

    private static final long serialVersionUID = -6163779479218164024L;

    /**
     * 模板结束语最大字符长度
     */
    private static final int LENGTH = 255;

    /**
     * 模板结束语替换值
     */
    private String content = "$remark.DATA$";

    /**
     * 颜色样式替换值
     */
    private String color = "$remark.COLOR$";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        validate();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void validate() {
        if (content == null || content.length() > LENGTH) {
            throw new TemplateParamsException("TemplateRemark params content is wrong");
        }
    }
}