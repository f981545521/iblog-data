package cn.acyou.iblogdata.test.cycle;

/**
 * @author youfang
 * @version [1.0.0, 2019-05-16 下午 11:37]
 **/
public class CycleProcess {
    public static int start = 0;
    public static int end = 31;

    public static String url_template = "http://sezhanwang.net:8080/20190514/TtvIVZ0f/500kb/hls/4k5Iq3880029.ts";
    public static String url =          "http://sezhanwang.net:8080/20190514/TtvIVZ0f/500kb/hls/4k5Iq38800";

    public static void main(String[] args) {
        for (int i = start; i <= end; i++) {
            String s = String.format("%02d", i);
            System.out.println(url + s + ".ts");
        }
    }
}
