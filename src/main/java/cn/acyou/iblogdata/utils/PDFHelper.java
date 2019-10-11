package cn.acyou.iblogdata.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;

/**
 * @author youfang
 * @version [1.0.0, 2019-10-10 18:11]
 **/
public class PDFHelper {

    public static void main(String[] args) throws Exception {
        String[] files = {"D:\\pdf\\pdf\\123.pdf", "D:\\pdf\\pdf\\pdfword.pdf"};
        String savePath = "D:\\pdf\\pdf\\123pdfWord.pdf";
        mergePdfFiles(files, savePath);
    }


    /**
     * 合并PDF文件
     * @param files 源文件路径列表
     * @param newfilePath 合并的文件路径
     * @return
     * @throws Exception
     */
    public static void mergePdfFiles(String[] files, String newfilePath) throws Exception{
        Document document = new Document(new PdfReader(files[0]).getPageSize(1));
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfilePath));
        document.open();
        for (int i = 0; i < files.length; i++) {
            PdfReader reader = new PdfReader(files[i]);
            int n = reader.getNumberOfPages();
            for (int j = 1; j <= n; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
        }
        document.close();
    }



}
