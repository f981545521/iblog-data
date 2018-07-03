package cn.acyou.iblogdata.dao;

import cn.acyou.iblogdata.commons.Mapper;
import cn.acyou.iblogdata.entity.Teacher;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-03 上午 10:12]
 **/
public interface TeacherMapper extends Mapper<Teacher, Integer> {

    List<Teacher> getAllTeacher();

    int updateTeacher(Teacher teacher);

    int addTeacher(Teacher teacher);

    int deleteTeaher(Integer id);

    Teacher getTeacherById(Integer id);
}
