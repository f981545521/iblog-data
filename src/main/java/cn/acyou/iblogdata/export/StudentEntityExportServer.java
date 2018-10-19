package cn.acyou.iblogdata.export;

import cn.acyou.iblogdata.entity.StudentExportEntity;
import cn.acyou.iblogdata.entity.StudentExportEntityGroup;
import cn.acyou.iblogdata.service.StudentService;
import cn.acyou.iblogdata.utils.RandomUtil;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-19 下午 01:35]
 * @since [天天健身/运动模块]
 **/
@Component
public class StudentEntityExportServer implements IExcelExportServer {

    @Autowired
    private StudentService studentService;

    /**
     * @param  obj    查询条件
     * @param  page   当前页数
     * @return
     */
    @Override
    public List<Object> selectListForExcelExport(Object obj, int page) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StudentExportEntity client = new StudentExportEntity();
            client.setId("1" + i);
            client.setName("小明" + i);
            client.setBirthday(new Date());
            client.setSex(RandomUtil.random01());
            StudentExportEntityGroup group = new StudentExportEntityGroup();
            group.setGroupName("测试" + i);
            client.setGroup(group);
            list.add(client);
        }
        if(page > 10){
            return null;
        }
        return list;
    }

}
