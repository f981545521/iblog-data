package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.utils.ResultInfo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@RestController
@RequestMapping("/boss")
@Api
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "students", method = {RequestMethod.POST})
    @ApiOperation("增加一个学生")
    @ResponseBody
    public ResultInfo addBoss(Student student){
        studentService.addStudent(student);
        return new ResultInfo();
    }

    @RequestMapping(value = "students", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getAllStudent(){
        List<Student> data = studentService.getAllStudent();
        return ResultInfo.generateSuccess(data);
    }

    @RequestMapping(value = "student", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getStudentByPage(){
        PageInfo<Student> data = studentService.getStudentsByPage();
        return ResultInfo.generateSuccess(data);
    }

}
