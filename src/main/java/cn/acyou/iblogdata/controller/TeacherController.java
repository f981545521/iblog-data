package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.service.TeacherService;
import cn.acyou.iblogdata.so.TeacherReq;
import cn.acyou.iblogdata.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:30]
 **/
@Api
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("teachers")
    @ApiOperation("获取所有老师")
    public ResultInfo getAllTeacher(@RequestBody TeacherReq teacherReq){
        log.info("[{}]|测试方法：getAllTeacher：{}", teacherReq.getLogId(), teacherReq);
        List<Teacher> teachers = teacherService.getAllTeacher();
        return new ResultInfo(teachers);
    }


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

    @RequestMapping(value = "teacher2", method = {RequestMethod.POST})
    @ApiOperation("spring 事务测试 事务的传播性")
    public ResultInfo propagation(Teacher teacher){
        teacherService.addTeacherWithTransaction(teacher);
        return new ResultInfo();
    }

    @RequestMapping(value = "teacher3", method = {RequestMethod.POST})
    @ApiOperation("spring 事务测试 事务的隔离级别")
    public ResultInfo isolation(Teacher teacher){
        teacherService.addTeacherWithTransaction2(teacher);
        return new ResultInfo();
    }

}
