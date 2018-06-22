package cn.acyou.iblogdata.controller;

import cn.acyou.iblog.model.Sort;
import cn.acyou.iblog.service.SortService;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.utils.StudentConfig;
import cn.acyou.iblogdata.utils.StudentConfig2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author youfang
 * @date 2018-02-27 13:21
 **/
@RestController
@RequestMapping("/hello")
@Api
public class HelloWorldController {

    @Autowired(required = false)
    private SortService sortService;

    @Autowired
    private StudentConfig studentConfig;

    @Autowired(required = false)
    private StudentConfig2 studentConfig2;

    @RequestMapping(value = "/setSession",method = {RequestMethod.GET})
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("user", "youfang");
        return "设置成功";
    }
    @RequestMapping(value = "/getSession",method = {RequestMethod.GET})
    @ResponseBody
    public String getSession(HttpServletRequest request, HttpSession session) {
        Object obj = session.getAttribute("user");
        return (String) obj;
    }

    @ApiOperation(value = "sort")
    @RequestMapping(value = "/sortList",method = {RequestMethod.GET})
    @ResponseBody
    public List<Sort> getSortList(){
        return sortService.listSorts(4);
    }

    @RequestMapping(value = "/greeting", method = {RequestMethod.GET})
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("greeting");
        mv.addObject("title", "欢迎使用Thymeleaf!");
        return mv;
    }


    @RequestMapping(value = "doConfig", method = {RequestMethod.GET})
    public ResultInfo doConfig(){
        return new ResultInfo(studentConfig);
    }
    @RequestMapping(value = "doConfig2", method = {RequestMethod.GET})
    public ResultInfo doConfig2(){
        return new ResultInfo(studentConfig2);
    }
    @RequestMapping(value = "/greetTo",  method = {RequestMethod.GET})
    public ModelAndView greetTo() {
        //重定向
        return new ModelAndView("redirect:/hello/greeting");
    }

    @ApiOperation(value = "Spring-cloud 调用测试")
    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    @ResponseBody
    public String index(@RequestParam String name) {
        return "hello ：" + name + "，producer 接收到了你的请求。";
    }
}