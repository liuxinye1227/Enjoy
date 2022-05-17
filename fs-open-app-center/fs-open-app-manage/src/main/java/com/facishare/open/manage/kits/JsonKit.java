package com.facishare.open.manage.kits;

import com.google.gson.Gson;

public class JsonKit {
    public static final String object2json(Object object) {
        if (null == object) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static final <T> T json2Object(String json, Class<T> clazz) {
        if (null == json || "".equals(json) || null == clazz) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
