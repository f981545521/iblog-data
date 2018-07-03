package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.service.TeacherService;
import cn.acyou.iblogdata.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:30]
 **/
@RestController
@RequestMapping("/teacher")
@Api
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("teachers")
    @ApiOperation("获取所有老师")
    public ResultInfo getAllTeacher(){
        List<Teacher> teachers = teacherService.getAllTeacher();
        return new ResultInfo(teachers);
    }

    @PostMapping("teacher")
    @ApiOperation("增加一个老师")
    public ResultInfo addTeacher(Teacher teacher){
        teacherService.addTeacher(teacher);
        return new ResultInfo();
    }

    @PutMapping("teacher")
    @ApiOperation("修改一个老师")
    public ResultInfo updateTeacher(Teacher teacher){
        teacherService.updateTeacher(teacher);
        return new ResultInfo();
    }

    @GetMapping("teacher")
    @ApiOperation("获取一个老师")
    public ResultInfo getTeacher(Integer id){
        Teacher teacher = teacherService.getStudentById(id);
        return new ResultInfo(teacher);
    }

    @DeleteMapping("teacher")
    @ApiOperation("删除一个老师")
    public ResultInfo deleteTeacher(Integer id){
        teacherService.deleteStudentById(id);
        return new ResultInfo();
    }

}
