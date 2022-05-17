package com.facishare.open.app.center.api.model.vo.custom.create.template;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zenglb on 2016/3/29.
 */
public class ImageTextMaterialJsonVO implements Serializable {
    private String keyId;
    private List<ImageTextMaterialParamJsonVO> imageTextParams;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public List<ImageTextMaterialParamJsonVO> getImageTextParams() {
        return imageTextParams;
    }

    public void setImageTextParams(List<ImageTextMaterialParamJsonVO> imageTextParams) {
        this.imageTextParams = imageTextParams;
    }
}
