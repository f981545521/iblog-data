package cn.acyou.iblogdata.entity;

import cn.acyou.iblogdata.commons.Po;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:36
 **/
public class Teacher extends Po {

    private static final long serialVersionUID = 5350645545698778721L;

    private Integer id;

    private String name;

    private Integer age;

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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
