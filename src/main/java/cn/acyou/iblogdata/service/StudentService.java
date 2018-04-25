package cn.acyou.iblogdata.service;

import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.so.StudentSo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author youfang
 */
public interface StudentService {

    int addStudent(Student student);

    List<Student> getAllStudent();

    PageInfo<Student> getStudentsByPage(StudentSo studentSo);
}
