package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.spring.ScopeBean;
import cn.acyou.iblogdata.spring.UniqueBean;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.utils.SequenceUtils;
import cn.acyou.iblogdata.utils.StaticUtil;
import com.alibaba.dubbo.common.utils.IOUtils;
import com.google.common.io.Resources;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

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

    @Autowired
    private SequenceUtils sequenceUtils;

    @Autowired
    private ScopeBean scopeBean;

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

    @RequestMapping(value = "staticMethod", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultInfo staticMethod(){
        StaticUtil.staticMethod();
        return new ResultInfo();
    }


    @RequestMapping(value = "sequence", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultInfo sequence(String jgjc){
        String id = sequenceUtils.getInceId();
        String bh = sequenceUtils.getGzsbh(jgjc);
        System.out.println(bh);
        return new ResultInfo(id);
    }



    @RequestMapping(value = "scopeBean", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultInfo scopeBean(){
        System.out.println(scopeBean);
        return new ResultInfo(scopeBean);
    }




    @RequestMapping(value = "readResources", method = {RequestMethod.GET})
    @ResponseBody
    public void readResources(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //读取资源文件
        URL resource = Resources.getResource("dist/config.txt");
        InputStream is = resource.openStream();

        File file = new File(resource.toURI());

        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        System.out.println("___________________________");
        URL resource1 = this.getClass().getClassLoader().getResource("dist/config.txt");
        InputStream is1 = resource1.openStream();
        Scanner scanner1 = new Scanner(is1);
        while (scanner1.hasNextLine()) {
            System.out.println(scanner1.nextLine());
        }
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is3 = classloader.getResourceAsStream("dist/config.txt");

        //读取properties文件
        Properties prop = new Properties();
        prop.load(classloader.getResourceAsStream("dist/config.txt"));

        System.out.println("OK");

        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType(ContentType.TEXT_PLAIN.getMimeType());
        IOUtils.write(is, outputStream);
    }






}
