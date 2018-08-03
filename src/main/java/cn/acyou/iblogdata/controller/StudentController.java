package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.so.StudentSo;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.utils.ResultInfoGenerate;
import cn.acyou.iblogdata.vo.StudentVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@RestController
@RequestMapping("/student")
@Api
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    @RequestMapping(value = "students", method = {RequestMethod.POST})
    @ApiOperation("增加一个学生")
    @ResponseBody
    public ResultInfo addStudent(Student student){
        studentService.addStudent(student);
        return new ResultInfo();
    }

    @RequestMapping(value = "directStudents", method = {RequestMethod.POST})
    @ApiOperation("增加一个学生")
    @ResponseBody
    public ResultInfo directStudents(@RequestBody Student student){
        //获取Request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        studentMapper.insertSelective(student);
        return new ResultInfo(student);
    }
    @RequestMapping(value = "directStudents", method = {RequestMethod.GET})
    @ApiOperation("查看一个学生")
    @ResponseBody
    public ResultInfo directGetStudents(String id){
        return new ResultInfo(studentMapper.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "students", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getAllStudent(@RequestHeader Map<String, String> headerMap){
        //headerMap可以获取请求头信息
        List<Student> data = studentService.getAllStudent();
        return ResultInfoGenerate.generateSuccess(data);
    }

    @RequestMapping(value = "students2", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getAllStudent2(){
        List<Student> data = studentService.findAll();
        return ResultInfoGenerate.generateSuccess(data);
    }

    @RequestMapping(value = "student", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getStudentByPage(StudentSo studentSo){
        PageInfo<Student> data = studentService.getStudentsByPage(studentSo);
        return ResultInfoGenerate.generateSuccess(data);
    }

    @RequestMapping(value = "studentVo", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getStudentVo(StudentSo studentSo){
        StudentVo studentVo = studentMapper.getStudentVo(studentSo);
        return ResultInfoGenerate.generateSuccess(studentVo);
    }

    @RequestMapping(value = "studentVo2", method = {RequestMethod.GET})
    @ApiOperation("获取所有学生列表")
    @ResponseBody
    public ResultInfo getStudentVo2(StudentSo studentSo){
        StudentVo studentVo = studentMapper.getStudentVo2(studentSo);
        return ResultInfoGenerate.generateSuccess(studentVo);
    }

    @RequestMapping(value = "stu", method = {RequestMethod.GET})
    @ApiOperation("CacheTest 查找")
    @ResponseBody
    public ResultInfo stu(String id){
        Student student = studentService.getStudentById(id);
        return ResultInfoGenerate.generateSuccess(student);
    }

    @RequestMapping(value = "stu", method = {RequestMethod.PUT})
    @ApiOperation("CacheTest 更新")
    @ResponseBody
    public ResultInfo updateStudent(Student student){
        studentService.updateStudent(student);
        return ResultInfoGenerate.generateSuccess();
    }

    @RequestMapping(value = "getCondition", method = {RequestMethod.GET})
    @ApiOperation("Mapper Condition 查找")
    @ResponseBody
    public ResultInfo getCondition(){
        List<Student> studentList = studentService.selectByCondition();
        return ResultInfoGenerate.generateSuccess(studentList);
    }



}
