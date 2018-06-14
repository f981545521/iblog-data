/*
 * 文 件 名:  TestRedis
 * 版    权:  Copyright 2018 南京慕冉信息科技有限公司,  All rights reserved
 * 描    述:  <描述>
 * 版    本： <1.0.0>
 * 创 建 人:  youfang
 * 创建时间:   2018-06-14

 */
package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.test.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author youfang
 * @version [1.0.0, 2018-06-14 下午 02:28]
 * @since [小倦鸟/远方模块]
 **/
public class TestRedis extends BaseTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        RequestBuilder request;
        request = MockMvcRequestBuilders.get("/student/stu?id=3");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }
}
