package com.facishare.open.app.center.api.model.vo;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

/**
 * 图片数据保存 ，用于传递Controller的图片数据到provide。
 * Author: Ansel Qiao
 * Create Time: 15/8/21
 */
public class IconFileVO implements Serializable {

    private static final long serialVersionUID = 3367597021878475010L;

    /**
     * 图片数据
     */
    private byte[] data;

    /**
     * 后缀
     */
    private String ext;

    /**
     * 原图宽
     */
    private int width;

    /**
     * 原图的高
     */
    private int height;

    public IconFileVO() {
    }

    public IconFileVO(byte[] data, String originalFileName, int width, int height) {
        this.data = data;
        setExt(originalFileName);
        this.width = width;
        this.height = height;
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

    private void setExt(String original) {
        int lastIndex = original.lastIndexOf(".");
        if (lastIndex < 1) {
            this.ext = "";
        } else {
            this.ext = original.substring(lastIndex + 1);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static IconFileVO create(MultipartFile original) throws IOException {
        if (original != null && original.getBytes().length > 0) {
            BufferedImage image = ImageIO.read(original.getInputStream());
            return new IconFileVO(original.getBytes(), original.getOriginalFilename(), image.getWidth(),
                    image.getHeight());
        }
        return null;
    }
}
