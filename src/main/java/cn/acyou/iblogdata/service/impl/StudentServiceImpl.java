package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblog.model.test.Boss;
import cn.acyou.iblog.service.BossService;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@Service("studentService")
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private BossService bossService;
    @Override
    public int addStudent(Student student) {
        Boss boss = new Boss();
        boss.setName(student.getName());
        boss.setAge(student.getAge());
        bossService.addBoss(boss);
        studentMapper.insert(student);
        return 1;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentMapper.selectAll();
    }
}
