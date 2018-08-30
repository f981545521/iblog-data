package cn.acyou.iblogdata.service;

import cn.acyou.iblogdata.commons.Service;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.so.StudentSo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author youfang
 */
public interface StudentService extends Service<Student, Integer> {

    int addStudent(Student student);

    int updateStudent(Student student);

    List<Student> getAllStudent();

    PageInfo<Student> getStudentsByPage(StudentSo studentSo);

    Student getStudentById(Integer id);

    Student getStudentByIdForUpdate(Integer id);

    Student getStudent4MybatisCache(String id);

    List<Student> selectByCondition();

    int updateStudentWithTransaction(Student student);

    int updateStudentWithTransaction2(Student student);
}
