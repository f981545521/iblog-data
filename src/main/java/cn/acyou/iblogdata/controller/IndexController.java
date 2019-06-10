package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.spring.ScopeBean;
import cn.acyou.iblogdata.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-07 下午10:33]
 **/
@Slf4j
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private ScopeBean scopeBean;

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String indexPage(){
        return "index/index";
    }

    @RequestMapping(value = "scopeBean", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultInfo scopeBean(){
        System.out.println(scopeBean);
        return new ResultInfo(scopeBean);
    }

}
