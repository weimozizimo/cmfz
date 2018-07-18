package com.baizhi.cmfz.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Description
 * @Author weizimo
 * @Time 2018/7/18 17:00.
 */
public class HandleMtpFileSize {
    public static String getHeight(MultipartFile file) throws IOException {
        BufferedImage img = ImageIO.read(file.getInputStream());
        if(img!=null){
            return String.valueOf(img.getHeight());
        }
        return null;
    }
    public static String getWidth(MultipartFile file) throws IOException {
        BufferedImage img = ImageIO.read(file.getInputStream());
        if(img!=null){
            return String.valueOf(img.getWidth());
        }
        return null;
    }
}
