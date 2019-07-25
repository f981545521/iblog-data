package cn.acyou.iblogdata.test.cycle;

/**
 * @author youfang
 * @version [1.0.0, 2019-05-16 下午 11:37]
 **/
public class CycleProcess {
    public static int start = 0;
    public static int end = 705;

    public static String url_template = "http://sezhanwang.net:8080/20190511/C5ipSZWm/500kb/hls/Nd1E4LtJ3470005.ts";
    public static String url =          "http://sezhanwang.net:8080/20190511/C5ipSZWm/500kb/hls/Nd1E4LtJ347";

    public final static String suffix = ".ts";

    public static void main(String[] args) {
        for (int i = start; i <= end; i++) {
            String s = String.format("%04d", i);
            System.out.println(url + s + suffix);
        }
    }
}
