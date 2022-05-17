package com.facishare.wechat.access.model;

import com.google.gson.Gson;

import java.util.Map;

/**
 * 前后交互数据结构
 * Created by dingc on 2015/11/17.
 */
public class TransData {

    private Map<String, String> header;

    private Map<String, String> data;

    public String getEa() {
        return (String)header.get("ea");
    }

    public Integer getUserId() {
        return Integer.parseInt(header.get("userId"));
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static TransData fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TransData.class);
    }

    @Override
    public String toString() {
        return "TransData{" +
                "header=" + header +
                ", data=" + data +
                '}';
    }

}
