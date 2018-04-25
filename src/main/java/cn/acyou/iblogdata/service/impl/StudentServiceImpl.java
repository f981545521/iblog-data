package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblog.model.test.Boss;
import cn.acyou.iblog.service.BossService;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.so.StudentSo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired(required = false)
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

    @Override
    public PageInfo<Student> getStudentsByPage(StudentSo studentSo) {
        PageHelper.startPage(studentSo.getCurrentPage(), studentSo.getPageSize());
        List<Student> studentList = studentMapper.getStudentsByPage(studentSo);
        PageInfo<Student> page = new PageInfo<>(studentList);
        return page;
    }
}
