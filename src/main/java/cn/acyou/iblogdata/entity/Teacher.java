package cn.acyou.iblogdata.entity;

import cn.acyou.iblogdata.commons.Po;
import lombok.Data;

import javax.persistence.Id;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:36
 **/
@Data
public class Teacher extends Po {

    private static final long serialVersionUID = 5350645545698778721L;

    /**
     * tk.mybatis.mapper.MapperException:
     * 继承 deleteByIds 方法的实体类[cn.acyou.iblogdata.entity.Teacher]中必须只有一个带有 @Id 注解的字段
     */
    @Id
    private Integer id;

    private String name;

    private Integer age;

    private Integer studentId;

}
