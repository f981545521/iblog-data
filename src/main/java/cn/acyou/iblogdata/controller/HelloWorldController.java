package cn.acyou.iblogdata.controller;

import cn.acyou.iblog.model.Sort;
import cn.acyou.iblog.service.SortService;
import cn.acyou.iblogdata.conf.properties.WxProperties;
import cn.acyou.iblogdata.conf.properties.WxProperties2;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.utils.StudentConfig;
import cn.acyou.iblogdata.utils.StudentConfig2;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * RefreshScope 注解：  使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
 *                      然后以post请求的方式来访问http://localhost:8002/refresh 就会更新修改后的配置文件。
 * @author youfang
 * @date 2018-02-27 13:21
 **/
@Api
@Controller
@RequestMapping("/hello")
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired(required = false)
    private SortService sortService;

    @Autowired
    private StudentConfig studentConfig;

    @Autowired(required = false)
    private StudentConfig2 studentConfig2;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WxProperties wxProperties;
    @Autowired
    private WxProperties2 wxProperties2;

    @Value("${name:nothing found}")
    private String configName;//这个是Spring Cloud Config 中的值，默认值：nothing found

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
    public ModelAndView greeting(String name, RedirectAttributes attr) {
        attr.addFlashAttribute("title", "欢迎使用Thymeleaf!");
        attr.addFlashAttribute("name", name);
        return new ModelAndView("redirect:/hello/greetingView");//最终访问：http://localhost:8033/hello/greetingView
    }

    @RequestMapping(value = "/greeting2", method = {RequestMethod.GET})
    public String greeting2(String name, RedirectAttributes attr) {
        attr.addFlashAttribute("title", "欢迎使用Thymeleaf!");
        attr.addFlashAttribute("name", name);
        return "redirect:http://www.baidu.com";//最终访问：http://www.baidu.com
    }
    @RequestMapping(value = "/greeting3", method = {RequestMethod.GET})
    public String greeting3(String name, RedirectAttributes attr) {
        attr.addFlashAttribute("title", "欢迎使用Thymeleaf!");
        attr.addFlashAttribute("name", name);
        return "redirect:hello/greetingView";//最终访问：http://localhost:8033/hello/hello/greetingView
    }
    @RequestMapping(value = "/greetingView", method = {RequestMethod.GET})
    public ModelAndView greetingView(@ModelAttribute("name") String name) {
        ModelAndView mv = new ModelAndView("/greeting");
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
        logger.warn("您输入的 name：" + name);
        return "hello ：" + name + "，producer 接收到了你的请求。";
    }

    @ApiOperation(value = "Thymeleaf 测试")
    @RequestMapping(value = "/thymeleaf", method = {RequestMethod.GET})
    public String thymeleaf(Model model) {
        model.addAttribute("title", "Thymeleaf 测试");
        Student student = new Student();
        student.setId(1);
        student.setName("小王");
        student.setAge(23);
        student.setBirth(DateUtils.create(2008, 8, 8, 8, 8, 8, 8).getTime());
        model.addAttribute("student", student);
        List<Student> studentList = Lists.newLinkedList();
        for (int i = 0; i < 10; i++){
            Student stu = new Student();
            stu.setId(i + 10);
            stu.setName("小王" + i);
            stu.setAge(23 + i);
            stu.setBirth(DateUtils.create(2008 + i, 8, 8, 8, 8, 8, 8).getTime());
            studentList.add(stu);
        }
        model.addAttribute("studentList", studentList);
        return "thymeleaf";
    }


    @RequestMapping(value = "/doMethod", method = {RequestMethod.GET})
    public String doMethod(Model model) {
        String url = "http://localhost:8033/student/students";
        //JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        ResponseEntity<ResultInfo> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, ResultInfo.class);
        List<Student> studentList = JSON.parseArray(JSON.toJSONString(responseEntity.getBody().getData()), Student.class);
        model.addAttribute("studentList", studentList);
        return "/greeting";
    }

    @RequestMapping(value = "springConfig", method = {RequestMethod.GET})
    @ResponseBody
    public String springConfig(){
        return configName;
    }


    @RequestMapping(value = "wxConfig", method = {RequestMethod.GET})
    @ResponseBody
    public ResultInfo wxConfig(){
        return new ResultInfo(wxProperties);
    }
    @RequestMapping(value = "wxConfig2", method = {RequestMethod.GET})
    @ResponseBody
    public ResultInfo wxConfig2(){
        return new ResultInfo(wxProperties2);
    }


}