package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.entity.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-15 上午 11:34]
 **/
@Data
public class StudentArrayReq implements Serializable {
    private static final long serialVersionUID = 6883316490360983966L;

    private String name;

    private Long age;

    private List<Student> studentList;

}
