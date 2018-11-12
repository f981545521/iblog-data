package cn.acyou.iblogdata.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author youfang
 * @version [1.0.0, 2018-11-01 上午 10:26]
 **/
@Controller
@RequestMapping("mvc")
public class MvcController {

    private int singletonInt = 1;

    @RequestMapping(value = "/test")
    @ResponseBody
    public String singleton(HttpServletRequest request) throws Exception {
        String data = request.getParameter("data");
        if (data != null && data.length() > 0) {
            try {
                int paramInt = Integer.parseInt(data);
                singletonInt = singletonInt + paramInt;
            } catch (Exception ex) {
                singletonInt += 10;
            }
        } else {
            singletonInt += 1000;
        }
        return String.valueOf(singletonInt);
    }

    /**
     * http://localhost:8033/mvc/sleepdata?sleep=on
     * http://localhost:8033/mvc/sleepdata?sleep=test
     * @return result
     * @throws Exception e
     */
    @RequestMapping(value = "/sleepdata")
    @ResponseBody
    public String switcher(HttpServletRequest request) throws Exception {
        String sleep = request.getParameter("sleep");
        if (sleep.equals("on")) {
            Thread.currentThread().sleep(100000);
            return "sleep on";
        } else {
            return sleep;
        }
    }

}
