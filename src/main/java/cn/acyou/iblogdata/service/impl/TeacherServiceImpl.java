package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblogdata.commons.AbstractService;
import cn.acyou.iblogdata.commons.RedisResp;
import cn.acyou.iblogdata.dao.TeacherMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.service.TeacherService;
import cn.acyou.iblogdata.constant.AppRedisKey;
import cn.acyou.iblogdata.utils.JsonUtil;
import cn.acyou.iblogdata.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        Student student = studentService.getStudentById(teacher.getStudentId());
        student.setAge(33);
        logger.info(student.toString());
        try {
            //为了让更新发生异常后可以继续执行，需要try
            studentService.updateStudentWithTransaction(student);
        }catch (RuntimeException e){
            logger.info("更新失败");
        }
        if (student.getId() == 2){
            throw new ServiceException();//父事务发生异常
        }
        return n;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ, timeout = 30, readOnly = false, rollbackFor = ServiceException.class,
            noRollbackFor = NullPointerException.class)
    public int addTeacherWithTransaction2(Teacher teacher) {
        /* 事务隔离级别
         *
         * Isolation.SERIALIZABLE  最严格的级别，事务串行执行，资源消耗最大；(可以避免幻读, 脏读, 不可重复读)
         * Isolation.REPEATABLE_READ  保证了一个事务不会修改已经由另一个事务读取但未提交（回滚）的数据。 for update
         *     避免了“脏读取”和“不可重复读取”的情况，(会出现幻读)但是带来了更多的性能损失。
         * Isolation.READ_COMMITTED  大多数主流数据库的默认事务等级，保证了一个事务不会读到另一个并行事务已修改但未提交的数据，
         *     避免了“脏读取”(会出现不可重复读和幻读)
         * Isolation.READ_UNCOMMITTED  读取未提交数据(会出现幻读, 脏读, 不可重复读) 基本不使用
         *
         * TODO：测试未成功
         *
         */
        int n = teacherMapper.insertSelective(teacher);
        //Isolation.REPEATABLE_READ 读取数据
        Student student = studentService.getStudentByIdForUpdate(teacher.getStudentId());
        student.setAge(66);
        studentService.updateStudentWithTransaction2(student);
        return n;
    }


}
