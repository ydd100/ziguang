package com.ruoyi.project.managementbackground.activity.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * 作用：二维码识别（图片）
 * 类名：QRCodeUtils
 **/
public class QRCodeUtils {
    /**
     * 解析二维码,此方法解析一个路径的二维码图片
     * path:图片路径
     */
    public static String deEncodeByPath(String path) {
        String content = null;
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);//解码
            System.out.println("图片中内容：  ");
            System.out.println("content： " + result.getText());
            content = result.getText();
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void main(String[] args) {

        String aa = "/profile/unlimit-code/all_1631256309793.png".replaceAll("/profile","D:/ruoyi/uploadPath");
        deEncodeByPath(aa);
    }
}

