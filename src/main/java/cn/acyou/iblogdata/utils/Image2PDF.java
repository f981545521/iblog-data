package cn.acyou.iblogdata.utils;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Image2PDF {
    /*** @param picturePath 图片地址*/
    private static void createPic(Document document, String picturePath) {
        try {
            Image image = Image.getInstance(picturePath);
            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            float documentHeight = documentWidth / 580 * 320;//重新设置宽高
            image.scaleAbsolute(documentWidth, documentHeight);//重新设置宽高
            document.add(image);
        } catch (Exception ex) {
        }
    }
    public static void image2pdf(String text, String pdf) throws DocumentException, IOException {
        Document document = new Document();
        OutputStream os = new FileOutputStream(new File(pdf));
        PdfWriter.getInstance(document,os);
        document.open();
        createPic(document,text);
        document.close();
    }
    public static void main(String[] args) throws IOException, DocumentException {
        image2pdf("D:\\pdf\\images\\123.jpg","D:\\pdf\\images\\123.pdf");
    }
}
