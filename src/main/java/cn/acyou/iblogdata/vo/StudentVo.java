package cn.acyou.iblogdata.vo;

import cn.acyou.iblog.entity.Teacher;
import cn.acyou.iblogdata.commons.Po;
import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:36
 **/
public class StudentVo extends Po {

    private static final long serialVersionUID = 5350645545628778721L;

    private Integer id;

    private String name;

    private Integer age;

    List<Teacher> teacherList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}
