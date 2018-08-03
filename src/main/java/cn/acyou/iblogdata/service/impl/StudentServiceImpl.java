package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblog.model.test.Boss;
import cn.acyou.iblog.orika.OrikaMapper;
import cn.acyou.iblog.service.BossService;
import cn.acyou.iblogdata.commons.AbstractService;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.so.StudentSo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author youfang
 * @date 2018-04-15 下午 09:38
 **/
@Slf4j
@Service("studentService")
public class StudentServiceImpl extends AbstractService<Student, Integer> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired(required = false)
    private BossService bossService;

    @Override
    @Transactional
    public int addStudent(Student student) {
        Boss boss = new Boss();
        boss.setName(student.getName());
        boss.setAge(student.getAge());
        log.debug(boss.toString());
        //bossService.addBoss(boss);
        int n = studentMapper.insert(student);
/*        if (n == 1){
            throw new RuntimeException();
        }*/
        return n;
    }

    @Override
    //@CacheEvict(value="student", key="T(cn.acyou.iblogdata.entity.Student).id")
    //@CacheEvict(value="student", key="#student.id")
    //@CacheEvict(value="student", key="T(java.lang.String).valueOf(#student.id)")
    @Caching(evict = {
            @CacheEvict(value="student", key="T(java.lang.String).valueOf(#student.id)")
    })
    public int updateStudent(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> allStudent = studentMapper.selectAll();
        logger.info("allStudent : ", allStudent);
        return allStudent;
    }

    @Override
    public List<Student> selectByCondition() {
        //Condition方法和Example方法作用完全一样，只是为了避免Example带来的歧义，提供的的Condition方法


        Condition condition = new Condition(Student.class);
        condition.createCriteria().andCondition("name like '%范%'");
        condition.setOrderByClause("age desc");
        List<Student> students = studentMapper.selectByCondition(condition);

        Example example = new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("name like '%范%'");
        example.setOrderByClause("age desc");
        List<Student> students2 = studentMapper.selectByExample(example);

        return students2;
    }

    @Override
    public PageInfo<Student> getStudentsByPage(StudentSo studentSo) {
        PageHelper.startPage(studentSo.getCurrentPage(), studentSo.getPageSize());
        List<Student> studentList = studentMapper.getStudentsByPage(studentSo);
        PageInfo<Student> page = new PageInfo<>(studentList);
        OrikaMapper orikaMapper = new OrikaMapper();
        Student student = orikaMapper.convert(studentSo, Student.class);
        logger.debug(student.toString());
        return page;
    }

    @Override
    @Cacheable(value="student", key="#id")
    public Student getStudentById(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
