package com.facishare.open.manage.controller;

import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.model.AssetVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author chenzengyong
 * @date on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-common.xml"})
@ActiveProfiles("fstest")

public class TestImageFileController {

    @Autowired
    FileController fileController;

    // set vm : -Dprocess.profile=ceshi113

    @Test
    public void testImageTxtControllerSimple() throws Exception {


        int i = 0;

        for (; i < 10; i++) {
            Thread.sleep(1000);
            //        String fileName = "giftest.gif";
            String fileName = "image/test1.jpg";
            String fileExt = "jpg";
            String filePath = "src/test/resources/" + fileName;
            File file = new File(filePath);
            InputStream input = new FileInputStream(file);
            byte[] data = new byte[input.available()];
            input.read(data);

            MultipartFile file1 = new MockMultipartFile("uploadFile", fileName, "image/" + fileExt, data);
            AssetVO assetVO = new AssetVO();
            assetVO.setUploadFile(file1);
            AjaxResult ajaxResult = fileController.uploadImage(assetVO);

            System.out.println(ajaxResult);
        }



    }





}
