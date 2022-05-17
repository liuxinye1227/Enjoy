package com.facishare.open.manage.controller;

import com.facishare.open.app.center.api.utils.HttpUploadUtils;
import com.facishare.open.common.result.exception.BizException;
import com.facishare.open.manage.ajax.code.AjaxCode;
import com.facishare.open.manage.ajax.result.AjaxResult;
import com.facishare.open.manage.model.AssetVO;
import com.facishare.open.manage.utils.ConfigCenter;
import com.facishare.open.warehouse.api.FileService;
import com.facishare.open.warehouse.api.IconService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by xialf on 12/15/15.
 *
 * @author xialf
 */
@Controller
@RequestMapping("/support/assets/images")
public class FileController extends BaseController {

    @Value("${fs.open.app.center.image.url}")
    private String imageUrl;

    @Resource
    private IconService iconService;

    @Resource
    private FileService fileService;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult uploadImage(AssetVO assetVO)
            throws IOException {
        checkParam(assetVO.getUploadFile(), "请选择上传的文件");
        if (assetVO.getUploadFile().isEmpty() || assetVO.getUploadFile().getSize() == 0) {
            return new AjaxResult(AjaxCode.PARAM_ERROR, "文件不能为空");
        }

        MultipartFile file = assetVO.getUploadFile();
        BufferedImage imgBuff = ImageIO.read(file.getInputStream());
        if (imgBuff == null) {
            throw new BizException(AjaxCode.BIZ_EXCEPTION, "该图片已被损坏");
        }
        return new AjaxResult(ConfigCenter.BJ_IMAGE_VIEW_URL + HttpUploadUtils
                .httpUpload(ConfigCenter.UPLOAD_IMAGE_URL, file.getBytes(), file.getOriginalFilename()));
    }

    @RequestMapping("view")
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
            String ext = getExtName(fileId);
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

    private String getExtName(final String fileName) {
        String ext = "";
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex > -1) {
            ext = fileName.substring(lastIndex + 1);
        }
        return ext;
    }
}
