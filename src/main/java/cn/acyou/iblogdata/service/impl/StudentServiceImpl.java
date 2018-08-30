package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblog.model.test.Boss;
import cn.acyou.iblog.orika.OrikaMapper;
import cn.acyou.iblog.service.BossService;
import cn.acyou.iblogdata.commons.AbstractService;
import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.so.StudentSo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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

    @Autowired
    private CacheChannel cacheChannel;

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
/*        if (true){
            throw new ServiceException("抱歉，服务出错啦~");
        }*/
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
    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student getStudent4MybatisCache(String id) {
        log.info("第一次查询");
        Student stu1 = studentMapper.selectByPrimaryKey(id);
        log.info("第二次查询");
        Student stu2 = studentMapper.selectByPrimaryKey(id);
        return stu1;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED,
            isolation = Isolation.DEFAULT, timeout = 30, readOnly = false, rollbackFor = ServiceException.class)
    public int updateStudentWithTransaction(Student student) {
        log.info("transaction - 执行更新操作");
        /*
         * 事务的传播性
         * Propagation.REQUIRED  使用该级别的特点是，如果上下文中已经存在事务，那么就加入到事务中执行，如果当前上下文中不存在事务，则新建事务执行
         * 这里抛出异常后会回滚全部
         * Propagation.SUPPORTS  如果上下文存在事务，则支持当前事务，加入到事务执行，如果没有事务，则使用非事务的方式执行。
         * 这里抛出异常后会回滚全部
         * Propagation.MANDATORY 要求上下文中必须要存在事务，否则就会抛出异常！
         * 这里抛出异常后会回滚全部
         * Propagation.REQUIRES_NEW  每次都要一个新的事务，该传播级别的特点是，每次都会新建一个事务，并且同时将上下文中的事务挂起，当新建事务执行完成以后，上下文事务再恢复执行。
         * 这里抛出异常后会回滚这里的方法，不影响调用者的事务
         * Propagation.NOT_SUPPORTED 如果上下文中存在事务，则挂起事务，执行当前逻辑，结束后恢复上下文的事务。
         * 非事务运行，发生异常后不会回滚，不影响调用者的事务
         * Propagation.NEVER 该事务更严格，要求上下文中不能存在事务，一旦有事务，就抛出runtime异常，强制停止执行！
         * 没有事务的时候，直接抛出异常
         * Propagation.NESTED 如果上下文中存在事务，则嵌套事务执行，如果不存在事务，则新建事务。
         * 嵌套事务：
         *      子事务是父事务的一部分，在进入子事务之前，父事务建立一个回滚点，叫save point，然后执行子事务，这个子事务的执行也算是父事务的一部分，然后子事务执行结束，父事务继续执行。
         *      子事务回滚，父事务会回滚到进入子事务前建立的save point，然后尝试其他的事务或者其他的业务逻辑
         *      父事务回滚，子事务也会跟着回滚！
         */

        /*
         * 直接抛出异常 - 会回滚，不会继续执行
         * 捕获异常，但不处理，不抛出    - 不会回滚，会继续执行
         * 捕获异常，并抛出RuntimeException异常  - 会回滚，不会继续执行
         */
        int n = studentMapper.updateByPrimaryKeySelective(student);
        if (student.getId().equals(1)){
            throw new ServiceException();
        }
        return n;
    }
}
