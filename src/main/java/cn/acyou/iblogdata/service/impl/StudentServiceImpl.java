package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblog.model.test.Boss;
import cn.acyou.iblog.service.BossService;
import cn.acyou.iblogdata.commons.AbstractService;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.so.StudentSo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@Slf4j
@Service("studentService")
public class StudentServiceImpl extends AbstractService<Student, Integer> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired(required = false)
    private BossService bossService;

    @Override
    @Transactional
    public int addStudent(Student student) {
        Boss boss = new Boss();
        boss.setName(student.getName());
        boss.setAge(student.getAge());
        log.debug(boss.toString());
        //bossService.addBoss(boss);
        int n = studentMapper.insert(student);
/*        if (n == 1){
            throw new RuntimeException();
        }*/
        return n;
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> allStudent = studentMapper.selectAll();
        logger.info("allStudent : ", allStudent);
        return allStudent;
    }

    @Override
    public PageInfo<Student> getStudentsByPage(StudentSo studentSo) {
        PageHelper.startPage(studentSo.getCurrentPage(), studentSo.getPageSize());
        List<Student> studentList = studentMapper.getStudentsByPage(studentSo);
        PageInfo<Student> page = new PageInfo<>(studentList);
        return page;
    }

    @Override
    @Cacheable(value="student", key="#id")
    public Student getStudentById(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
