package cn.acyou.iblogdata.test.cache;

import cn.acyou.iblogdata.test.base.BaseTest;
import cn.acyou.iblogdata.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Redis 集群测试
 * @author youfang
 * @version [1.0.0, 2018-12-05 上午 09:49]
 **/
public class RedisColony extends BaseTest {

    @Autowired
    private RedisUtil redisUtil;

    private String key = "name";

    @Test
    public void test1(){
        String v = redisUtil.getValue(key);
        System.out.println(v);
        if (StringUtils.isEmpty(v)){
            redisUtil.setValue(key, "小三");
        }
        System.out.println(redisUtil.getValue("name"));
    }
}
