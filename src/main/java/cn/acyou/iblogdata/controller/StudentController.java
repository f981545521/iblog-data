package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@RestController
@RequestMapping("/boss")
@Api
public class StudentController {

    @Autowired
    private StudentService bossService;

    @RequestMapping(value = "boss", method = {RequestMethod.POST})
    @ApiOperation("增加一个老板")
    @ResponseBody
    public ResultInfo addBoss(Student student){
        bossService.addStudent(student);
        return new ResultInfo();
    }


}
