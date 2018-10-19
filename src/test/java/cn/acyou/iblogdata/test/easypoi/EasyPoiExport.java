package cn.acyou.iblogdata.test.easypoi;

import cn.acyou.iblogdata.entity.StudentExportEntity;
import cn.acyou.iblogdata.entity.StudentExportEntityGroup;
import cn.acyou.iblogdata.utils.RandomUtil;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-19 下午 12:01]
 * @since [天天健身/运动模块]
 **/
public class EasyPoiExport {

    @Test
    public void bigDataExport() throws Exception {
        List<StudentExportEntity> list = new ArrayList<StudentExportEntity>();
        Workbook workbook = null;
        Date start = new Date();
        ExportParams params = new ExportParams("大数据测试", "测试");
        for (int i = 0; i < 500; i++) {  //数据量
            StudentExportEntity client = new StudentExportEntity();
            client.setId("1" + i);
            client.setName("小明" + i);
            client.setBirthday(new Date());
            client.setSex(RandomUtil.random01());
            StudentExportEntityGroup group = new StudentExportEntityGroup();
            group.setGroupName("测试" + i);
            client.setGroup(group);
            list.add(client);
            if(list.size() == 100){
                workbook = ExcelExportUtil.exportBigExcel(params, StudentExportEntity.class, list);
                list.clear();
            }
        }
        ExcelExportUtil.closeExportBigExcel();
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:\\excel\\");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:\\excel\\ExcelExportBigData.xlsx");
        workbook.write(fos);
        fos.close();
    }
}
