package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.test.base.BaseTest;
import cn.acyou.iblogdata.utils.PostHelper;
import cn.acyou.iblogdata.utils.RedisUtil;
import cn.acyou.iblogdata.utils.ResultInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author youfang
 * @date 2018-05-24 下午 09:30
 */
public class UtilTest extends BaseTest{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PostHelper postHelper;

    @Autowired
    private HttpServletRequest request;

    @Test
    public void test1(){
        redisUtil.setValue("name", "小飞飞");
        System.out.println(redisUtil.containsKey("name"));
        System.out.println(redisUtil.getValue("name"));
    }

    @Test
    public void test2(){
        ResponseEntity<ResultInfo> resq =  postHelper.post(request, "http://localhost:8034/hello/paramStr", "123");
        System.out.println(resq.getBody());
    }


}
