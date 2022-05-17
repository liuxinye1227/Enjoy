package com.facishare.open.app.center.api.model.template;

import com.facishare.open.app.center.api.exception.TemplateParamsException;

import java.io.Serializable;

/**
 * 模板跳转部分数据
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateButton implements Serializable {

    private static final long serialVersionUID = -6163779479218164024L;

    /**
     * 模板参数最大长度
     */
    private static final int LENGTH = 255;

    /**
     * 模板跳转显示lable
     */
    private String title = "详情";

    /**
     * 跳转url
     */
    private String url = "$button.URL$";

    /**
     * 字体颜色
     */
    private String color = "$button.COLOR$";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        validate();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void validate() {
        if (url == null || url.length() > LENGTH) {
            throw new TemplateParamsException("TemplateButton params url is wrong");
        }
    }
}