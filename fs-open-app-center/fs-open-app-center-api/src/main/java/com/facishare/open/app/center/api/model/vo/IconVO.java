package com.facishare.open.app.center.api.model.vo;

import java.io.Serializable;

/**
 * 图片数据
 * Author: Ansel Qiao
 * Create Time: 15/8/21
 */
public class IconVO implements Serializable {

    private static final long serialVersionUID = -1459704999887052372L;

    /**
     * 图片数据
     */
    private byte[] data;

    /**
     * icon后缀
     */
    private String ext;

    public IconVO(byte[] data, String iconId) {
        this.data = data;
        setExt(iconId);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getExt() {
        return ext;
    }

    private void setExt(String iconId) {
        if (null != iconId) {
            int lastIndex = iconId.lastIndexOf(".");
            if (lastIndex < 1) {
                this.ext = "";
            } else {
                this.ext = iconId.substring(lastIndex + 1);
            }
        }
    }
}
