package com.ziheng.utils.face;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Auther: ziheng
 * @Date: 2022/12/27 16:54
 * @Description: web端 采集人脸数据--人脸拍照，转为byte字节数据存入数据库student表，base64位图转成byte[]
 */
public class ImageUtils {
    public static byte[] base64ToByte(String baseStr) {
        if (baseStr == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(baseStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 该方法不需使用
     * <p>
     * <p>
     * base64字符串转换成图片 (对字节数组字符串进行Base64解码并生成图片)
     *
     * @param imgStr      base64字符串
     * @param imgFilePath 指定图片存放路径  （注意：带文件名）
     * @return
     */
    public static boolean Base64ToImage(String imgStr, String imgFilePath) {
        if (imgStr == null) { // 图像数据为空
            return false;
        }
        try {
            // Base64解码
            byte[] b = Base64Utils.decodeFromString(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
