package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblogdata.commons.AbstractService;
import cn.acyou.iblogdata.commons.RedisResp;
import cn.acyou.iblogdata.dao.TeacherMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.service.TeacherService;
import cn.acyou.iblogdata.utils.AppRedisKey;
import cn.acyou.iblogdata.utils.JsonUtil;
import cn.acyou.iblogdata.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:25]
 **/
@Service
public class TeacherServiceImpl  extends AbstractService<Teacher, Integer> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        int count = teacherMapper.updateTeacher(teacher);
        if (count > 0){
            redisUtil.hDel(AppRedisKey.IBLOGDATA_STUDENT_INFO, teacher.getId().toString());
        }
        return count;
    }

    @Override
    public Teacher getStudentById(Integer id) {
        Teacher teacher;
        RedisResp redisResp = redisUtil.hGet(AppRedisKey.IBLOGDATA_STUDENT_INFO, id.toString());
        String redisValue = redisResp.getData();
        if (StringUtils.isNotBlank(redisValue)) {
            teacher = JsonUtil.readValue(redisValue, Teacher.class);
            return teacher;
        }
        teacher = teacherMapper.getTeacherById(id);
        if (teacher != null){
            redisUtil.hPut(AppRedisKey.IBLOGDATA_STUDENT_INFO, id.toString(), JsonUtil.toJSonString(teacher));
        }
        return teacher;
    }

    @Override
    public int deleteStudentById(Integer id) {
        int count =  teacherMapper.deleteTeaher(id);
        if (count > 0){
            redisUtil.hDel(AppRedisKey.IBLOGDATA_STUDENT_INFO, id.toString());
        }
        return count;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT, timeout = 30, readOnly = false, rollbackFor = ServiceException.class,
            noRollbackFor = NullPointerException.class)
    public int addTeacherWithTransaction(Teacher teacher) {
        int n = teacherMapper.insertSelective(teacher);
        Student student = studentService.getStudentById("1");
        student.setAge(33);
        logger.info(student.toString());
        //studentService.updateStudentWithTransaction(student);
        return n;
    }


}
