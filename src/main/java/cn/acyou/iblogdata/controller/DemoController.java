package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.spring.UniqueBean;
import cn.acyou.iblogdata.utils.ResultInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-16 下午 03:33]
 **/
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{

    @Value("${ab.name: none found}")
    private String pomName;

    @Resource(name = "siUniqueBean")
    private UniqueBean uniqueBean;

    @RequestMapping(value = "toPreview", method = {RequestMethod.POST})
    public String toPreview(MultipartFile file){
        System.out.println(file.getOriginalFilename());

        return "demo/preview";
    }

    @RequestMapping(value = "demoPage", method = {RequestMethod.GET})
    public String demoPage(){
        String flag = request.getParameter("flag");
        if ("1".equals(flag)){
            throw new ServiceException("FLAG 不能是1呦！");
        }
        return "demo/demoPage";
    }

    @RequestMapping(value = "pom", method = {RequestMethod.GET})
    @ResponseBody
    public String pomName(){
        return pomName;
    }

    @RequestMapping(value = "setCookie", method = {RequestMethod.GET})
    @ResponseBody
    public ResultInfo setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("name", "小三");
        response.addCookie(cookie);
        return new ResultInfo();
    }
    @RequestMapping(value = "getCookie", method = {RequestMethod.GET})
    @ResponseBody
    public ResultInfo getCookie(@CookieValue String name){
        return new ResultInfo(name);
    }


    @RequestMapping(value = "multiPartParam", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultInfo multiPartParam(Student student, Teacher teacher){
        System.out.println(teacher);
        return new ResultInfo(student);
    }


}
