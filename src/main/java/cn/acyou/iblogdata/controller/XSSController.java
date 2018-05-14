package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author youfang
 * @version [1.0.0, 2018-05-14]
 **/
@Controller
@RequestMapping("/xss")
@Api
public class XSSController {

    @RequestMapping(value = "/xss1",method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "测试XSS")
    @ResponseBody
    public ResultInfo xss1(Student student){
        return new ResultInfo(student);
    }
}
