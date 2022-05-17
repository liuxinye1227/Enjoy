package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 微信消息.
 * Created by zenglb on 2016/11/3.
 */
public class WeChatMessageVO implements Serializable {
    private static final long serialVersionUID = 4942701911434002025L;
    /**
     * 内容.
     */
    private String content;

    public WeChatMessageVO() {
    }

    public WeChatMessageVO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WeChatMessageVO{" +
                "content='" + content + '\'' +
                '}';
    }
}
