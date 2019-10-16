package cn.acyou.iblogdata.utils.word;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

/**
 * @author youfang
 * @version [1.0.0, 2019-10-12 13:38]
 * @since [司法公证]
 **/
public class WordtoPDF2 {

    public static void main(String[] args) {
        String filepath = "D:\\pdf\\word\\公证申请表(单位、个人) .docx";
        String outpath = "D:\\pdf\\word\\公证申请表pdf2word_new122.pdf";
        //加载word示例文档
        Document document = new Document();
        document.loadFromFile(filepath);
        //保存结果文件
        document.saveToFile(outpath, FileFormat.PDF);
    }

}
