package cn.acyou.iblogdata.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-29 下午 04:02]
 **/
public class ImageCompressUtil {
    /**
     * 直接指定压缩后的宽高：
     * (先保存原文件，再压缩、上传)
     * 壹拍项目中用于二维码压缩
     * @param oldFile 要进行压缩的文件全路径
     * @param width 压缩后的宽度
     * @param height 压缩后的高度
     * @param quality 压缩质量
     * @param smallIcon 文件名的小小后缀(注意，非文件后缀名称),入压缩文件名是yasuo.jpg,则压缩后文件名是yasuo(+smallIcon).jpg
     * @return 返回压缩后的文件的全路径
     */
    public static String zipImageFile(String oldFile, int width, int height,
                                      float quality, String smallIcon) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            /*对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(new File(oldFile));
            /* 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
            /* 压缩后的文件名 */
            newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());
            /* 压缩之后临时存放位置 */
            FileOutputStream out = new FileOutputStream(newImage);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /* 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }

    /**
     * 保存文件到服务器临时路径(用于文件上传)
     * @param fileName 文件名
     * @return 文件全路径
     */
    public static String writeFile(String fileName, InputStream is) {
        if (fileName == null || fileName.trim().length() == 0) {
            return null;
        }
        try {
            /* 首先保存到临时文件 */
            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] readBytes = new byte[512];// 缓冲大小
            int readed;
            while ((readed = is.read(readBytes)) > 0) {
                fos.write(readBytes, 0, readed);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 等比例压缩算法：
     * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
     * @param srcURL 原图地址
     * @param deskURL 缩略图地址
     * @param comBase 压缩基数
     * @param scale 压缩限制(宽/高)比例  一般用1：
     * 当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale<1,缩略图width=comBase,height按原图宽高比例
     */
    public static void saveMinPhoto(String srcURL, String deskURL, double comBase,
                                    double scale) throws Exception {
        File srcFile = new java.io.File(srcURL);
        Image src = ImageIO.read(srcFile);
        int srcHeight = src.getHeight(null);
        int srcWidth = src.getWidth(null);
        int deskHeight;// 缩略图高
        int deskWidth;// 缩略图宽
        double srcScale = (double) srcHeight / srcWidth;
        /*缩略图宽高算法*/
        if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
            if (srcScale >= scale || 1 / srcScale > scale) {
                if (srcScale >= scale) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            } else {
                if ((double) srcHeight > comBase) {
                    deskHeight = (int) comBase;
                    deskWidth = srcWidth * deskHeight / srcHeight;
                } else {
                    deskWidth = (int) comBase;
                    deskHeight = srcHeight * deskWidth / srcWidth;
                }
            }
        } else {
            deskHeight = srcHeight;
            deskWidth = srcWidth;
        }
        BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
        tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); //绘制缩小后的图
        FileOutputStream deskImage = new FileOutputStream(deskURL); //输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
        encoder.encode(tag); //近JPEG编码
        deskImage.close();
    }

    public static void main(String args[]) throws Exception {
        File fromFile = new File("F:\\iotest\\images","1.jpeg");
        File targetFile = new File("F:\\iotest\\images","target.jpeg");
        File waterPic = new File("F:\\iotest\\images","water.png");//水印图片
        if (targetFile.exists()){
            targetFile.delete();
        }
        //Thumbnails.Builder<File> builder = Thumbnails.of(file);
        //将图片放入内存
        //Thumbnails.of(file).scale(1.0).outputQuality(1.0).toFile(targetFile);

        //Thumbnails.of(fromFile).size(400,500).toFile(targetFile);//变为400*300,遵循原图比例缩或放到400*某个高度

        //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
        //Thumbnails.of(fromFile).scale(1f).outputQuality(0.25f).toFile(targetFile);

        //给图片加水印，watermark(位置，水印图，透明度)Positions.CENTER表示加在中间
        //Thumbnails.of(fromFile).size(400,400).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(waterPic),0.5f).outputQuality(0.8f).toFile(targetFile);

        //用sourceRegion()实现图片裁剪
        //图片中心300*300的区域,Positions.CENTER表示中心，还有许多其他位置可选
        //Thumbnails.of(fromFile).sourceRegion(Positions.CENTER,300,300).size(300,300).toFile(targetFile);

        //用outputFormat(图像格式)转换图片格式，保持原尺寸不变
        Thumbnails.of(fromFile).scale(1f).outputFormat("png").toFile("F:\\iotest\\images\\png格式的图片.png");

    }
}
