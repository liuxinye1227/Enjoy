package com.facishare.open.manage;

import com.facishare.open.app.center.api.utils.HttpUploadUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author chenzengyong
 * @date on 2016/12/17.
 */
@RunWith(JUnit4.class)
public class TestUtils {



    @Test
    public void testHttpUploadUtils() throws Exception {

//        String fileName = "image/test1.jpg";
//        String fileExt = "jpg";
//        String filePath = "src/test/resources/" + fileName;
//        File file = new File(filePath);
//        InputStream input = new FileInputStream(file);
//        byte[] data = new byte[input.available()];
//        input.read(data);
//
//        String url = "https://www.ceshi113.com/open/assets/server/bj/iconUpload";
//
//        String s = HttpUploadUtils.httpUpload(url, data, "test1.jpg");
//        System.out.println(s);

//        String original = "jsdklf.jpg";
//
//        int lastIndex = original.lastIndexOf(".");
//
//        System.out.println(original.substring(lastIndex + 1));   ;

        String appLogo = "ao.ceshi11.com/sdfj/sdfj?path=group_kjdf238974.jpg&ea=lkdjf";
       String  masterId = appLogo.substring(appLogo.indexOf("=group") + 1);
        if (masterId.contains("&")) {
            masterId = masterId.substring(0, masterId.indexOf("&"));
        }

        System.out.println(masterId);


    }


}