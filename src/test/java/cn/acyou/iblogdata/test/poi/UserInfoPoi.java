package cn.acyou.iblogdata.test.poi;

import cn.acyou.iblogdata.utils.Md5Util;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author youfang
 * @version [1.0.0, 2019-07-23 11:59]
 * @since [ERP服务]
 **/
public class UserInfoPoi {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\poi\\userInfo.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
        HSSFWorkbook hs = new HSSFWorkbook(fs);
        //hs.getSheetAt(3).getRow(3).cells[0].toString()
        //输出到文件   --- 获取当前用户桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();
        File directory = new File(path + "\\userInfo.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        HSSFSheet hsSheetAt = hs.getSheetAt(0);
        for (int i = 1; i<= hsSheetAt.getLastRowNum(); i++){
            HSSFRow row = hsSheetAt.getRow(i);
            //获取数据
            String userName = row.getCell(0).toString();
            String password = StringUtils.strip(row.getCell(1).toString(), ".0");
            String realName = row.getCell(2).toString();
            String md5Password = Md5Util.getMD5(password);


            String sql2 = "INSERT INTO `erp_manager`.`sys_user` (`user_id`, `login_name`, `password`, `login_count`, `state`, `last_login_time`, `last_login_ip`, `create_user`, `create_time`, `update_user`, `update_time`) " +
                    "VALUES (default, '"+userName+"', '"+md5Password+"', '0', 'NORMAL', NULL, NULL, NULL, '2019-07-23 11:16:00', NULL, NULL);";

            String sql3 = "INSERT INTO `erp_manager`.`sys_user_info` (`user_id`, `user_no`, `realname`, `nickname`, `avatar`, `sex`, `mobilephone`, `email`, `telephone`, " +
                    "`address`, `duty`, `identity`, `foreign_enterprise_name`, `foreign_duty`, `sort`, `create_user`, `create_time`, `update_user`, `update_time`)" +
                    "SELECT user_id,  NULL, '"+realName+"', '"+realName+"', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2019-06-17', NULL, NULL" +
                    " FROM sys_user WHERE login_name = '"+userName+"';";
            pw.write(sql2);
            pw.write("\r\n");
            pw.write(sql3);
            pw.write("\r\n");
        }

        pw.flush();
        fw.close();
        pw.close();


    }
}
