package cn.acyou.iblogdata.entity;

import cn.acyou.iblogdata.commons.Po;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author youfang
 * @date 2018-04-15 下午 07:36
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "student")
public class Student extends Po {

    private static final long serialVersionUID = 5350645545628778721L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "birth")
    private Date birth;

}
