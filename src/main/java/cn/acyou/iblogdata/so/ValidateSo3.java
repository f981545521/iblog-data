package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.annotation.valid.BaseValid;
import cn.acyou.iblogdata.annotation.valid.EnhanceValid;

import java.io.Serializable;
import java.util.List;

/**
 * @author youfang
 * @date 2018-04-25 下午 02:23
 **/
public class ValidateSo3 implements Serializable {

    private static final long serialVersionUID = -828326546547153332L;

    private Integer id;

    @EnhanceValid({
        @BaseValid(notNull = true, message = "姓名不能为空"),
        @BaseValid(maxLength = 10, message = "姓名过长"),
        @BaseValid(minLength = 2, message = "姓名过短"),
        @BaseValid(notInRange = {"张三", "李四"}, message = "姓名不在范围内")
    })
    private String name;

    @EnhanceValid({
            @BaseValid(notNull = true, message = "学生不能为空"),
            @BaseValid(notEmpty = true, message = "学生不能为空"),
            @BaseValid(entityCollectionValid = true)
    })
    private List<StudentSo> studentSoList;

    @EnhanceValid({
            @BaseValid(notNull = true, message = "validateSo不能为空"),
            @BaseValid(entityValid = true)
    })
    private ValidateSo validateSo;




    public ValidateSo getValidateSo() {
        return validateSo;
    }

    public void setValidateSo(ValidateSo validateSo) {
        this.validateSo = validateSo;
    }

    public List<StudentSo> getStudentSoList() {
        return studentSoList;
    }

    public void setStudentSoList(List<StudentSo> studentSoList) {
        this.studentSoList = studentSoList;
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
}
