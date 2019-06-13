package cn.acyou.iblogdata.test.poi;

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
 * @version [1.0.0, 2019-06-06 14:37]
 * @since [司法公证]
 **/
public class AreaPoi {

    public static void main(String[] args) throws Exception{
        File file = new File("D:\\poi\\地区2.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
        HSSFWorkbook hs = new HSSFWorkbook(fs);
        //hs.getSheetAt(3).getRow(3).cells[0].toString()
        //输出到文件   --- 获取当前用户桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();
        File directory = new File(path + "\\ares.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        //清空之前的记录重新导入
        //pw.write("DELETE FROM SFGZ_JCSJ_MATTERS WHERE REMARK = 'sync';");
        //pw.write("\r\n");

        HSSFSheet hsSheetAt = hs.getSheetAt(0);
        for (int i = 1; i<= hsSheetAt.getLastRowNum(); i++){
            HSSFRow row = hsSheetAt.getRow(i);
            //获取数据
            String currentId = row.getCell(0).toString();
            String pid = row.getCell(1).toString();
            String name = row.getCell(2).toString();
            String allName = row.getCell(3).toString();
            String shortName = row.getCell(4).toString();
            String dqjb = row.getCell(6).toString();
            String diququhao = "";
            if (row.getCell(7) != null){
                diququhao = row.getCell(7).toString();
            }
            String diquyoubian = "";
            if (row.getCell(8) != null){
                diququhao = row.getCell(8).toString();
            }
            String diqupinyin = row.getCell(9).toString();
            String diqujianpin = row.getCell(10).toString();
            String diqushouzimu = row.getCell(11).toString();
            String diqujingdu = row.getCell(12).toString();
            String diquweidu = row.getCell(13).toString();


            String sql2 = "INSERT INTO \"SFGZ\".\"SFGZ_JCSJ_AREA\" (\"ID\", \"FJID\", \"DQMC\", \"DQJHQC\", \"DQJC\", \"DQJB\", \"DQQH\", \"DQYB\", \"DQPY\", \"DQJP\", \"DQSZM\", \"DQJD\", \"DQWD\", \"IS_USE\", \"IS_DEL\", \"REMARK\", \"RECORD_SORT\", \"CREATE_TIME\", \"CREATOR\", \"CREATOR_NAME\", \"MODIFY_TIME\", \"MODIFIOR\", \"MODIFY_NAME\") VALUES " +
                    "('"+currentId+"', '"+pid+"', '"+name+"', '"+allName+"', '"+shortName+"', '"+dqjb+"', '"+diququhao+"', '"+diquyoubian+"', '"+diqupinyin+"', " +
                    "'"+diqujianpin+"', '"+diqushouzimu+"', '"+diqujingdu+"', '"+diquweidu+"', '1', '0', NULL, '6', TO_DATE('2019-05-21 13:52:10', 'SYYYY-MM-DD HH24:MI:SS'), " +
                    "'sync', 'sync', TO_DATE('2019-06-11 11:15:35', 'SYYYY-MM-DD HH24:MI:SS'), 'sync', 'sync');";
            pw.write(sql2);
            pw.write("\r\n");
        }

        pw.flush();
        fw.close();
        pw.close();
    }
}
