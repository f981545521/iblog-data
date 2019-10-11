package cn.acyou.iblogdata.test.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2019-09-23 10:37]
 * @since [司法公证]
 **/
public class TableFormat {

    public List<List<String>> data = new ArrayList<>();

    public static void main(String[] args) {

        List<List<String>> data = new ArrayList<>();
        data.add(new ArrayList<String>() {{
            add("time");
            add("size");
            add("name");
            add("url");
        }});

        data.add(new ArrayList<String>() {{
            add("2017-07-05 17:58:52.0");
            add("8.33 MB");
            add("FraudDetection-12.0.jar");
            add("http://myqcloud.com/FraudDetection-12.0.jar");
        }});

        data.add(new ArrayList<String>() {{
            add("2016-12-18 16:24:05.0");
            add("202 MB");
            add("hadoop-2.7.2.tar.gz");
            add("http://myqcloud.com/hadoop-2.7.2.tar.gz");
        }});

        data.add(new ArrayList<String>() {{
            add("2017-07-10 17:07:16.0");
            add("13.03 KB");
            add("stopwords.txt");
            add("http://myqcloud.com/stopwords.txt");
        }});

        data.add(new ArrayList<String>() {{
            add("2017-07-07 08:33:55.0");
            add("192 MB");
            add("text-classifier-10.0.jar");
            add("http://myqcloud.com/text-classifier-10.0.jar");
        }});

        new TableFormat(data).println(10);

    }

    public void println(Integer interval) {
        Integer width = data.get(0).size();
        Integer high = data.size();
        Integer[] maxWidths = getMaxWidth();
        for (int i = 0; i < high; i++) {
            for (int y = 0; y < width; y++) {
                String text = data.get(i).get(y);
                Integer maxWidth = maxWidths[y];
                if (y > 0) {
                    maxWidth+=interval;
                }
                System.out.print(getPlace(text, maxWidth));
            }
            System.out.println();
        }
    }

    public String getPlace(String text, Integer maxWidth) {
        int textSize = text.length();
        for (int i = 0; i < maxWidth - textSize; i++) {
            text = " " + text;
        }
        return text;
    }

    /**
     * 计算每一列每行内容的最大长度
     */
    public Integer[] getMaxWidth() {
        Integer width = data.get(0).size();
        Integer high = data.size();
        Integer[] widthArray = new Integer[width];
        for (int w = 0; w < width; w++) {
            String[] array = new String[high];
            for (int h = 0; h < high; h++) {
                array[h] = data.get(h).get(w);
            }
            widthArray[w] = getLengthMax(array);
        }
        return widthArray;
    }

    /**
     * 获取数组字符串中长度最大的值
     */
    public Integer getLengthMax(String[] arr) {
        Integer max = arr[0].length();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() > max) {
                max = arr[i].length();
            }
        }
        return max;
    }


    public TableFormat() {
    }

    public TableFormat(List<List<String>> data) {
        this.data = data;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
