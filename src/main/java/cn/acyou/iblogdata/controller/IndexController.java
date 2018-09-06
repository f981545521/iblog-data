package cn.acyou.iblogdata.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-07 下午10:33]
 **/
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String indexPage(){
        return "index/index";
    }


}
