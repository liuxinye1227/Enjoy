package com.facishare.open.app.center.api.model.template;

import com.facishare.open.app.center.api.exception.TemplateParamsException;

import java.io.Serializable;

/**
 * 模板欢迎词
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateFirst implements Serializable {

    private static final long serialVersionUID = -6163779479218164024L;

    /**
     * 模板欢迎词最大长度
     */
    private static final int LENGTH = 255;

    /**
     * 标识内容替换变量
     */
    private String content = "$first.DATA$";

    /**
     * 颜色替换变量
     */
    private String color = "$first.COLOR$";

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
            throw new TemplateParamsException("TemplateFirst params content is wrong");
        }
    }
}