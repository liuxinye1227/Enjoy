package com.facishare.open.app.center.api.model.template;

import com.facishare.open.app.center.api.exception.TemplateParamsException;

import java.io.Serializable;

/**
 * 模板内容数据项
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateInfo implements Serializable {

    private static final long serialVersionUID = 796590880967511337L;

    /**
     * 模板内容最大字符长度
     */
    private static final int LENGTH = 255;

    /**
     * 标识模板内容数据项lable
     */
    private String label = "";

    /**
     * 模板内容数据项替换值
     */
    private String value = "$$";

    /**
     * 模板内容颜色替换值
     */
    private String color = "$$";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        validate();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void validate() {
        if (value == null || value.length() > LENGTH) {
            throw new TemplateParamsException("TemplateInfo params value is wrong");
        }
    }
}