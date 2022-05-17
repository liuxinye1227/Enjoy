package com.facishare.open.manage.web;

import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.warehouse.api.FileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller("imageController")
@RequestMapping("/open/manager/image")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping("/view")
    public void view(HttpServletResponse response, @RequestParam("fileId") String fileId)
            throws IOException {
        try {
            if (StringUtils.isBlank(fileId)) {
                throw new IllegalArgumentException("params is illegal");
            }

            com.facishare.open.warehouse.result.BaseResult<byte[]> result = fileService.download(fileId);
            if (!result.isSuccess()) {
                throw new BizException(result);
            }
            ServletOutputStream out = response.getOutputStream();
            String ext = "";
            String fileName = fileId;
            int lastIndex = fileName.lastIndexOf(".");
            if (lastIndex > -1) {
                ext = fileName.substring(lastIndex + 1);
            }
            response.setContentType("image/" + ext);
            response.setContentLength(result.getResult().length);
            out.write(result.getResult());
            out.flush();
        } catch (IllegalArgumentException e) {
            response.sendError(HttpStatus.SC_NOT_FOUND, "params is illegal");
        } catch (Exception e) {
            logger.error("down load app logo error：fileId[" + fileId + "]", e);
            response.sendError(HttpStatus.SC_NOT_FOUND, "fail");
        }
    }
}
