package cn.acyou.iblogdata.utils.pdf;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2019-10-11 10:24]
 * @since [司法公证]
 **/
public class PdfSealUtil {

    public static void main(String[] args) throws Exception {

        // 模板文件路径
        String templatePath = "D:\\pdf\\word\\曾用名（公证书）2.pdf";
        // 生成的文件路径
        String targetPath = "D:\\pdf\\word\\target.pdf";
        // 书签名
        String fieldName = "[盖章]";
        // 图片路径
        String imagePath = "D:\\pdf\\yinzhang.png";

        // 读取模板文件
        InputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));

        int pagecount = reader.getNumberOfPages();
        MatchItem matchItem = matchPage(reader, pagecount, fieldName);
        System.out.println(matchItem);
        // 读图片
        Image image = Image.getInstance(imagePath);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(pagecount);
        // 根据域的大小缩放图片
        image.scaleToFit(120, 120);
        // 添加图片
        float offset = 60f;
        image.setAbsolutePosition(matchItem.getX() - offset, matchItem.getY() - offset);
        under.addImage(image);

        stamper.close();
        reader.close();
    }

    /**
     * 在文件中寻找指定的文字内容
     *
     * @param reader
     * @param pageNumber
     * @param keyword
     * @return
     * @throws Exception  
     */
    public static MatchItem matchPage(PdfReader reader,
                                      Integer pageNumber, String keyword) throws Exception {
        KeyWordPositionListener renderListener = new KeyWordPositionListener();
        renderListener.setKeyword(keyword);
        PdfReaderContentParser parse = new PdfReaderContentParser(reader);
        Rectangle rectangle = reader.getPageSize(pageNumber);
        renderListener.setPageNumber(pageNumber);
        renderListener.setCurPageSize(rectangle);
        parse.processContent(pageNumber, renderListener);
        MatchItem matchItem = findKeywordItems(renderListener, keyword);

        //当前页找不到，到前一页查询 
        if (null == matchItem) {
            pageNumber = pageNumber - 1;
            renderListener.setPageNumber(pageNumber);
            renderListener.setCurPageSize(rectangle);
            parse.processContent(pageNumber, renderListener);
            matchItem = findKeywordItems(renderListener, keyword);
        }
        matchItem.setPageNum(pageNumber);
        return matchItem;
    }

    /**
     * 找到匹配的关键词块
     *
     * @param renderListener
     * @param keyword
     * @return 
     */
    public static MatchItem findKeywordItems(KeyWordPositionListener renderListener,
                                             String keyword) {
        // 先判断本页中是否存在关键词
        // 所有块LIST
        List<MatchItem> allItems = renderListener.getAllItems();
        StringBuffer sbtemp = new StringBuffer("");
        for (int i = 0; i < allItems.size(); i++) {
            // 将一页中所有的块内容连接起来组成一个字符串。
            sbtemp.append((allItems.get(i)).getContent());
        }
        if (sbtemp.toString().lastIndexOf(keyword) == -1) {
            // 一页组成的字符串没有关键词，直接return
            return renderListener.getMatches().get(0);
        }

        // 多个块内容拼成一个关键词，则一个一个来匹配，组装成一个关键词
        sbtemp = new StringBuffer("");
        List<MatchItem> tempItems = new ArrayList();

        for (MatchItem matchItem : allItems) {
            String content = matchItem.getContent();
            if (StringUtils.isNotEmpty(content) && keyword.contains(content)) {
                tempItems.add(matchItem);
                sbtemp.append(content);
                if (!keyword.contains(sbtemp.toString())) {
                    // 如果暂存的字符串和关键词 不再匹配时
                    sbtemp = new StringBuffer(content);
                    tempItems.clear();
                    tempItems.add(matchItem);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(tempItems)) {
            //取X和Y中心
            MatchItem matchItemStart = tempItems.get(0);
            MatchItem matchItemEnd = tempItems.get(tempItems.size() - 1);
            float x = (matchItemStart.getX() + matchItemEnd.getX()) / 2;
            float y = (matchItemStart.getY() + matchItemEnd.getY()) / 2;
            matchItemStart.setX(x);
            matchItemStart.setY(y);
            return matchItemStart;
        }
        return null;

    }
}
