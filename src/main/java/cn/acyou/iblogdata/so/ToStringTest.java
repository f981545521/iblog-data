package cn.acyou.iblogdata.so;

import java.util.UUID;

/**
 * @author youfang
 * @version [1.0.0, 2019-01-27 下午 03:46]
 * @since [天天健身/促销模块]
 **/
public class ToStringTest {
    public static void main(String[] args) {
        TeacherReq teacherReq = new TeacherReq();
        teacherReq.setAge(22);
        teacherReq.setName("小王");
        teacherReq.setLogId(UUID.randomUUID().toString());
        System.out.println(teacherReq);
    }
}
