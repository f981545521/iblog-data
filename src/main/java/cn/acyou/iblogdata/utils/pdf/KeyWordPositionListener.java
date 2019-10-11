package cn.acyou.iblogdata.utils.pdf;

import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.util.ArrayList;
import java.util.List;

public class KeyWordPositionListener implements RenderListener {

    private List<MatchItem> matches = new ArrayList<MatchItem>();
    private List<MatchItem> allItems = new ArrayList<MatchItem>();
    private Rectangle curPageSize;


    /**
     * 匹配的关键字
     */
    private String keyword;
    /**
     * 匹配的当前页
     */
    private Integer pageNumber;

    @Override
    public void beginTextBlock() {
        //do nothing
    }

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        String content = renderInfo.getText();
        content = content.replace("<", "").replace("《", "").replace("(", "").replace("（", "").replace("\"", "").replace("'", "")
                .replace(">", "").replace("》", "").replace(")", "").replace("）", "").replace("、", "").replace(".", "")
                .replace("：", "").replace(":", "").replace(" ", "");
        Rectangle2D.Float textRectangle = renderInfo.getDescentLine().getBoundingRectange();
        MatchItem item = new MatchItem();
        item.setContent(content);
        item.setPageNum(pageNumber);
        item.setPageWidth(curPageSize.getWidth());
        item.setPageHeight(curPageSize.getHeight());
        item.setX((float) textRectangle.getX());
        item.setY((float) textRectangle.getY());
        if (content != null && content != "") {
            if (content.equalsIgnoreCase(keyword)) {
                matches.add(item);
            }
        } else {
            item.setContent("空字符串");
        }
        allItems.add(item);//先保存所有的项
    }


    @Override
    public void endTextBlock() {
        //do nothing
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        //do nothing
    }


    /**
     * 设置需要匹配的当前页
     *
     * @param pageNumber
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


    /**
     * 设置需要匹配的关键字，忽略大小写
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    /**
     * 返回匹配的结果列表
     *
     * @return
     */
    public List<MatchItem> getMatches() {
        return matches;
    }


    void setCurPageSize(Rectangle rect) {
        this.curPageSize = rect;
    }


    public List<MatchItem> getAllItems() {
        return allItems;
    }


    public void setAllItems(List<MatchItem> allItems) {
        this.allItems = allItems;
    }
}

