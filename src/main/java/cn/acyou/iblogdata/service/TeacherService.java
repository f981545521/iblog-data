package cn.acyou.iblogdata.service;

import cn.acyou.iblogdata.commons.Service;
import cn.acyou.iblogdata.entity.Teacher;

import java.util.List;

/**
 * @author youfang
 */
public interface TeacherService extends Service<Teacher, Integer> {

    List<Teacher> getAllTeacher();

    int addTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    Teacher getStudentById(Integer id);

    int deleteStudentById(Integer id);

    int addTeacherWithTransaction(Teacher teacher);
}
