package cn.acyou.iblogdata.utils;

import com.google.zxing.Result;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-31 下午 02:02]
 **/
public class ZxingUtil {

    /**
     * 生成二维码
     * @param response
     * @param width 宽
     * @param height 高
     * @param format 格式
     * @param content 内容
     * @throws WriterException
     * @throws IOException
     */
    public static void createZxing(HttpServletResponse response, int width, int height, int margin, String level
            , String format, String content) throws WriterException, IOException {
        ServletOutputStream stream = null;
        try {
            QRCodeWriter writer = new QRCodeWriter();
            Hashtable hints = new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.valueOf(level));// 纠错等级L,M,Q,H
            hints.put(EncodeHintType.MARGIN, margin); // 边距
            BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE,height, width, hints);
            stream = response.getOutputStream();
            //将图片输出给浏览器
            response.setContentType("image/png");
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
        } catch (WriterException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    /**
     * 读取二维码
     * @throws IOException
     * @throws NotFoundException
     */
    public static String readZxing(String qrcodeUrl) throws IOException, NotFoundException {
        URL url = new URL(qrcodeUrl);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        httpUrl.connect();
        return readZxing(httpUrl.getInputStream());
    }

    /**
     * 读取二维码
     * @param is InputStream
     * @return 信息
     * @throws IOException
     * @throws NotFoundException
     */
    public static String readZxing(InputStream is) throws IOException, NotFoundException{
        MultiFormatReader read = new MultiFormatReader();
        BufferedImage image = ImageIO.read(is);
        Binarizer binarizer = new HybridBinarizer(new BufferedImageLuminanceSource(image));
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
        Result decode = read.decode(binaryBitmap);
        return decode.toString();
    }
}
