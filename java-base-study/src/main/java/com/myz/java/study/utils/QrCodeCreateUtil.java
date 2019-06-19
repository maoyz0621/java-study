/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * 二维码生成工具
 *
 * @author maoyz on 2018/4/28
 * @Version: v1.0
 */
public class QrCodeCreateUtil {
    private static final int width = 0;
    private static final int heigth = 0;


    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param outputStream 文件输出流路径
     * @param content      二维码携带信息
     * @param qrCodeSize   二维码图片大小
     * @param imageFormat  二维码的格式
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        BufferedImage image = getBufferedImage(content, qrCodeSize);
        return ImageIO.write(image, imageFormat, outputStream);
    }

    private static BufferedImage getBufferedImage(String content, int qrCodeSize) throws WriterException {
        // 设置二维码纠错级别MAP
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        // 矫错级别
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        // 创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth - 200, matrixWidth - 200, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i - 100, j - 100, 1, 1);
                }
            }
        }
        return image;
    }

    public static BufferedImage returnImg(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        // 设置二维码纠错级别MAP
        BufferedImage image = getBufferedImage(content, qrCodeSize);
        return image;
    }

    /**
     * 读二维码并输出携带的信息
     */
    public static String readQrCode(InputStream inputStream) throws IOException {
        // 从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);
        // 将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null;
        String context = null;
        try {
            result = reader.decode(bitmap);
            context = result.getText();
        } catch (ReaderException e) {
            e.printStackTrace();
        }

        return context;
    }

    public static void main(String[] args) throws IOException, WriterException {
        createQrCode(new FileOutputStream(new File("/home/maoyz0621/myWord/a.jpg")), "www.baidu.com", 600, "JPEG");
        String s = readQrCode(new FileInputStream(new File("/home/maoyz0621/myWord/a.jpg")));
        System.out.println(s);
    }
}
