package cn.acyou.iblogdata.test.other;

/**
 * @author youfang
 * @version [1.0.0, 2020-5-31 下午 09:54]
 **/
public class CASTest {
    public volatile int n;

    public void add() {
        n++;
    }

    public static void main(String[] args) {
        System.out.println("ok");
    }
}
