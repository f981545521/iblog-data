package cn.acyou.iblogdata.tests;

import cn.acyou.iblogdata.base.BaseTest;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:26
 **/
public class MapperTest extends BaseTest{

    @Autowired
    private StudentMapper bossMapper;

    @Test
    public void test1(){
        List<Student> bossList = bossMapper.getAllStudent();
        System.out.println(bossList);
    }
}
