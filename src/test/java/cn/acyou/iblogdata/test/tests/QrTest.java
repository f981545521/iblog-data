package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.qrcode.QRCodeFactory;
import com.google.zxing.WriterException;

import java.io.IOException;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-31 下午 01:50]
 **/
public class QrTest {
    public static void main(String[] args) {

        String content="大家好，我就是二维码";
        String logUri="F:\\iotest\\images\\1.jpg";
        String outFileUri="F:\\iotest\\qrcode.jpg";
        int[]  size=new int[]{430,430};
        String format = "jpg";

        try {
            new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri, size);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }
}
