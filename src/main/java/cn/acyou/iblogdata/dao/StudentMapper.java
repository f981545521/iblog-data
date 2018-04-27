package cn.acyou.iblogdata.dao;

import cn.acyou.iblogdata.commons.Mapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.so.StudentSo;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:37
 **/
public interface StudentMapper extends Mapper<Student, Integer> {

    List<Student> getStudentsByPage(StudentSo studentSo);

}
