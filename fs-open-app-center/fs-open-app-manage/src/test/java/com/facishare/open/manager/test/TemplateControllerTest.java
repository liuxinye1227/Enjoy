package com.facishare.open.manager.test;

import com.facishare.common.httpclient.HttpClient;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Ignore
public class TemplateControllerTest {

    private HttpClient client;

    private String url = "http://localhost:8080/fs-open-app-manage/template/getContent";

    @Before
    public void setUp() {
        client = new HttpClient();

    }

    @Test
    public void testPost() {

        Map map = new HashMap<String, String>();
        // freemarker 中带  .DATA   解析报错
        map.put("first.DATA", "商品1111");
        map.put("keynote1.DATA", "商品名称");
        map.put("remark.DATA", "询价remark");
        map.put("url.DATA", "http://www.baidu.com");

        String params = new Gson().toJson(map);
        String templateId = "1111";
        Map m = new HashMap();
        m.put("params", params);
        m.put("templateId", templateId);
        String str = client.post(url, m);
        System.out.println(str);
    }
}

