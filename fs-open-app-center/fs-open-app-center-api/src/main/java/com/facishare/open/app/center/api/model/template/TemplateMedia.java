package com.facishare.open.app.center.api.model.template;

import java.io.Serializable;

/**
 * Created by songk on 2016/1/6.
 */
public class TemplateMedia implements Serializable {

    private static final long serialVersionUID = 396590880967511337L;

    /**
     * 类型  1:图片  2:音频
     */
    private String type = "$$";

    /**
     * http链接(图片、音频等)
     */
    private String url = "$$";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
