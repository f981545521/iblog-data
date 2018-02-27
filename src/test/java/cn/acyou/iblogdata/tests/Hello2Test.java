package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.base.BaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author youfang
 * @date 2018-02-27 18:25
 **/
public class Hello2Test extends BaseTest{

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testHello() throws Exception {
        RequestBuilder request = get("/hello/hello");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }
}
