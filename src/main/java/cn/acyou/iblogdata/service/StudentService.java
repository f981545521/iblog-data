package cn.acyou.iblogdata.service;

import cn.acyou.iblogdata.entity.Student;

import java.util.List;

/**
 * @author youfang
 */
public interface StudentService {

    int addStudent(Student student);

    List<Student> getAllStudent();
}
