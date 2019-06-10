package cn.acyou.iblogdata.test.tests;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

/**
 * @author youfang
 * @version [1.0.0, 2019-06-10 16:46]
 * @since [司法公证]
 **/
public class HuToolTest {

    @Test
    public void test1(){
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);

        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println(bStr);

        double aa = 67556.32;

        //结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(aa);
        System.out.println(digitUppercase);

        System.out.println(IdUtil.objectId());
        System.out.println(IdUtil.objectId());
        System.out.println(IdUtil.objectId());
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());
        System.out.println(snowflake.nextId());


    }
}
