package cn.acyou.iblogdata.test.other;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-23 上午 09:21]
 * @since [天天健身/运动模块]
 **/
public class DateTest {
    @Test
    public void test1(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(cal.getTime()));
        cal.add(Calendar.SECOND, 60 * 5);
        System.out.println(sdf.format(cal.getTime()));
    }
}
