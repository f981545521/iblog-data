package cn.acyou.iblogdata.service.impl;

import cn.acyou.iblogdata.dao.StudentMapper;
import cn.acyou.iblogdata.entity.Student;
import cn.acyou.iblogdata.service.SeckillService;
import cn.acyou.iblogdata.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author youfang
 * @version [1.0.0, 2020/4/17]
 **/
@Slf4j
@Service("studentService2")
public class StudentService2 {

    @Autowired
    StudentService2 studentService2;
    @Autowired
    private SeckillService seckillService;

    @Autowired
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testTranslation() {
        String name = RandomUtil.randomUserName();
        log.info("testTranslation添加:" + name);
        Student student = new Student();
        student.setAge(1);
        student.setName(name);
        studentMapper.insertSelective(student);

        /**
         * 在这里，直接调用，发生异常不会回滚。
         * 使用·studentService2.doOption()·调用才会回滚。
         *
         * 原因
         * AOP使用的是动态代理的机制，它会给类生成一个代理类，事务的相关操作都在代理类上完成。
         * 内部方式使用this调用方式时，使用的是实例调用，并没有通过代理类调用方法，所以会导致事务失效。
         */
        studentService2.doOption();

    }


    public void doOption(){
        String name = RandomUtil.randomUserName();
        log.info("doOption添加:" + name);
        Student student = new Student();
        student.setAge(1);
        student.setName(name);
        studentMapper.insertSelective(student);
        int i = 1/0;
    }

}
