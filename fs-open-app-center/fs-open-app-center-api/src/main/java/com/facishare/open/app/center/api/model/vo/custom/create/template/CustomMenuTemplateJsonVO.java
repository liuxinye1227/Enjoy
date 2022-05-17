package com.facishare.open.app.center.api.model.vo.custom.create.template;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenglb on 2016/3/29.
 */
public class CustomMenuTemplateJsonVO implements Serializable {

    private String id;

    private String name;

    /**
     * 1 响应消息 2 url跳转 3.图文消息
     */
    private Integer type = 0;

    private String txt = "";

    private String url = "";

    private String materialId = "";

    private Integer isOpenResponse;

    private List<CustomMenuTemplateJsonVO> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIsOpenResponse() {
        return isOpenResponse;
    }

    public void setIsOpenResponse(Integer isOpenResponse) {
        this.isOpenResponse = isOpenResponse;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public List<CustomMenuTemplateJsonVO> getChildren() {
        return children;
    }

    public void setChildren(List<CustomMenuTemplateJsonVO> children) {
        this.children = children;
    }
}
