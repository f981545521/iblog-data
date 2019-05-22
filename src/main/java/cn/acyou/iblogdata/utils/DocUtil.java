package cn.acyou.iblogdata.utils;

import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author youfang
 * @version [1.0.0, 2019-05-22 13:58]
 **/
public class DocUtil {
    /**
     * 读取doc文件内容
     *
     * @param fs
     *            想要读取的文件对象流
     * @return 返回文件内容
     * @throws IOException
     */
    public static String doc2String(FileInputStream fs) throws IOException {
        StringBuilder result = new StringBuilder();
        WordExtractor re = new WordExtractor(fs);
        result.append(re.getText());
        re.close();
        return result.toString();
    }

    public static String doc2String(File file) throws IOException {
        return doc2String(new FileInputStream(file));
    }



}
