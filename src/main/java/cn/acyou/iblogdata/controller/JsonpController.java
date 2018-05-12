package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.utils.ResultInfoGenerate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @date 2018-03-20 下午 10:08
 */
@Controller
@RequestMapping("/jsonp")
@Api
public class JsonpController {

    /**
     * 如果跨域的话服务器是有返回的，只是ajax出于安全不允许使用这个数据，
     * 可以在服务器的返回header添加 Access-Control-Allow-Origin：* 允许任何域名来解决
     * response.setHeader("Access-Control-Allow-Origin","*");
     * @param response 响应
     * @return JSON
     */
    @RequestMapping(value = "/category",method = {RequestMethod.GET})
    @ApiOperation(value = "测试JSONP")
    @ResponseBody
    public List<String> getcategoryList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        List<String> categoryList = new ArrayList<>();
        categoryList.add("we coun't");
        categoryList.add("hava all");
        return categoryList;
    }

    @RequestMapping(value = "/category2",method = {RequestMethod.GET})
    @ApiOperation(value = "测试JSONP2")
    @ResponseBody
    public List<String> getcategoryList2(){
        List<String> categoryList = new ArrayList<>();
        categoryList.add("we coun't");
        categoryList.add("hava all");
        return categoryList;
    }

    @RequestMapping(value = "/category3",method = {RequestMethod.GET})
    @ApiOperation(value = "测试JSONP3")
    public void getcategoryList3(HttpServletResponse response) throws IOException {
        String message = "message([{'id':2,'name':'xiaosan'}]);";
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(message);
    }

    @RequestMapping(value = "/category4",method = {RequestMethod.GET})
    @ApiOperation(value = "测试跨域")
    @ResponseBody
    public ResultInfo category4(){
        Student student = new Student();
        student.setName("炸弹");
        student.setAge(23);
        return ResultInfoGenerate.generateSuccess(student);
    }

    @RequestMapping(value = "/category5", method = {RequestMethod.POST})
    @ApiOperation(value = "测试跨域")
    @ResponseBody
    public ResultInfo category5(@RequestBody Student student){
        return ResultInfoGenerate.generateSuccess(student);
    }


}
