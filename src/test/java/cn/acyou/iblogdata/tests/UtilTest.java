package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.base.BaseTest;
import cn.acyou.iblogdata.utils.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author youfang
 * @date 2018-05-24 下午 09:30
 */
public class UtilTest extends BaseTest{

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1(){
        redisUtil.setValue("name", "小飞飞");
        System.out.println(redisUtil.containsKey("name"));
        System.out.println(redisUtil.getValue("name"));
    }


}
