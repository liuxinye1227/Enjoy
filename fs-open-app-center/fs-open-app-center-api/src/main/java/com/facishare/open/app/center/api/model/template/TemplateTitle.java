package com.facishare.open.app.center.api.model.template;

import com.facishare.open.app.center.api.exception.TemplateParamsException;

import java.io.Serializable;

/**
 * 模板标题
 *
 * @author songk
 * @date 2015年8月28日
 */
public class TemplateTitle implements Serializable {

    private static final long serialVersionUID = 523439272816245323L;

    /**
     * 标题最大长度
     */
    private static final int LENGTH_MAX = 60;

    /**
     * 标题最小长度
     */
    private static final int LENGTH_MIN = 2;

    /**
     * 标题值
     */
    private String content = "$title.DATA$";

    /**
     * 标题颜色样式替换值
     */
    private String color = "$title.COLOR$";

    /**
     * 时间替换值
     */
    private String time = "$title.TIME$";

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void validate() {
        if (content == null || content.length() > LENGTH_MAX || content.length() < LENGTH_MIN) {
            throw new TemplateParamsException("TemplateTitle params content is wrong");
        }
    }
}