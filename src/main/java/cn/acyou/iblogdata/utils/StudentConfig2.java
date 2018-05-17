package cn.acyou.iblogdata.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author youfang
 * @date 2018-05-17 下午 08:46
 */
@Slf4j
public class StudentConfig2 {

    private Integer id;

    private String name;

    private Integer age;

    public StudentConfig2() {
        log.info("StudentConfig2无参构造");
    }

    public StudentConfig2(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
        log.info("StudentConfig2全参构造");
    }

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
        return "StudentConfig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
