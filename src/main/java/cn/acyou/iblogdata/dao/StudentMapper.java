package cn.acyou.iblogdata.dao;

import cn.acyou.iblogdata.commons.Mapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.so.StudentSo;
import cn.acyou.iblogdata.vo.StudentCallVo;
import cn.acyou.iblogdata.vo.StudentVo;

import java.util.List;
import java.util.Map;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:37
 **/
public interface StudentMapper extends Mapper<Student, Integer> {

    List<Student> getStudentsByPage(StudentSo studentSo);

    List<Student> getStudentListByIds(List<Integer> ids);

    int updateList(List<Student> students);

    int deleteByPrimaryKeyList(List<Integer> ids);

    StudentVo getStudentVo(StudentSo studentSo);

    StudentVo getStudentVo2(StudentSo studentSo);

    Student getStudentById(Integer id);

    void getCallCountLessAge(StudentCallVo studentCallVo);
}
