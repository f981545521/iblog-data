package cn.acyou.iblogdata.test.tests;

import cn.acyou.iblogdata.dao.TeacherMapper;
import cn.acyou.iblogdata.entity.Teacher;
import cn.acyou.iblogdata.test.base.BaseTest;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.utils.RandomValue;
import cn.acyou.iblogdata.vo.StudentCallVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:26
 **/
public class MapperTest extends BaseTest{

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test1(){
        List<Student> bossList = studentMapper.selectAll();
        System.out.println(bossList);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testMybatisCache2(){
        SqlSession session1 = sqlSessionFactory.openSession();
        StudentMapper mapper = session1.getMapper(StudentMapper.class);
        //使用session1执行第一次查询
        Student stu = mapper.getStudentById(1);
        System.out.println(stu);
        session1.close();
        //获取session2  查询
        SqlSession session2 = sqlSessionFactory.openSession();
        StudentMapper mapper2 = session2.getMapper(StudentMapper.class);
        Student stu2 = mapper2.getStudentById(1);
        System.out.println(stu2);
        session2.close();
    }

    /**
     * 测试一级缓存：不同的sqlsession
     */
    @Test
    public void testMybatisCache1(){
        SqlSession session1 = sqlSessionFactory.openSession();
        TeacherMapper mapper = session1.getMapper(TeacherMapper.class);
        //使用session1执行第一次查询
        Teacher tea = mapper.getTeacherById(1);
        System.out.println(tea);
        session1.close();
        //获取session2  查询
        SqlSession session2 = sqlSessionFactory.openSession();
        TeacherMapper mapper2 = session2.getMapper(TeacherMapper.class);
        Teacher tea2 = mapper2.getTeacherById(1);
        System.out.println(tea2);

        System.out.println(tea == tea2);//false
        session2.close();
    }

    /**
     * 测试一级缓存：相同的sqlsession
     */
    @Test
    public void testMybatisCacheSameSQLsession(){
        SqlSession session1 = sqlSessionFactory.openSession();
        TeacherMapper mapper = session1.getMapper(TeacherMapper.class);
        //使用session1执行第一次查询
        Teacher tea = mapper.getTeacherById(1);
        System.out.println(tea);
        //使用session1  查询
        Teacher tea2 = mapper.getTeacherById(1);
        System.out.println(tea2);

        System.out.println(tea == tea2);//true
        session1.close();
    }
    /**
     * 测试一级缓存：相同的sqlsession，curd操作
     */
    @Test
    public void testMybatisCacheExctuor(){
        SqlSession session1 = sqlSessionFactory.openSession();
        TeacherMapper mapper = session1.getMapper(TeacherMapper.class);
        //使用session1执行第一次查询
        Teacher tea = mapper.getTeacherById(1);
        System.out.println(tea);
        Teacher teacher = new Teacher();
        teacher.setName(RandomValue.getName());
        teacher.setAge(RandomValue.getAge());
        teacher.setStudentId(1);
        mapper.insertSelective(teacher);//执行insert操作
        //使用session1  查询
        Teacher tea2 = mapper.getTeacherById(1);
        System.out.println(tea2);

        System.out.println(tea == tea2);//false
        session1.close();
    }

    @Test
    public void testCall(){
        StudentCallVo callVo = new StudentCallVo();
        callVo.setAge(50);
        studentMapper.getCallCountLessAge(callVo);
        System.out.println(callVo);
    }
}
