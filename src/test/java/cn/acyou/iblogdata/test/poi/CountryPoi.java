package cn.acyou.iblogdata.test.poi;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * @author youfang
 * @version [1.0.0, 2019-05-22 17:21]
 * @since [司法公证]
 **/
public class CountryPoi {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\country.xls");
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
        File directory = new File(path + "\\country.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        HSSFSheet hsSheetAt = hs.getSheetAt(0);
        for (int i =2; i<= hsSheetAt.getLastRowNum(); i++){
            HSSFRow row = hsSheetAt.getRow(i);
            Double index = row.getCell(0).getNumericCellValue();
            String cnName = row.getCell(1).toString();
            String enName = row.getCell(2).toString();
            String code2 = row.getCell(3).toString();
            String code3 = row.getCell(4).toString();
            String codeNum = row.getCell(5).toString();
            String allName = row.getCell(6).toString();

            System.out.println(index.intValue() + cnName);
            String sql2 = "INSERT INTO \"SFGZ\".\"SFGZ_JCSJ_COUNTRY\" (\"ID\", \"GJMC\", \"GJDM\", \"YWMC\", \"SFXS\", \"SFQSH\", \"YWWZ\", \"REMARK\", \"CREATE_TIME\", \"CREATOR\", \"CREATOR_NAME\", \"MODIFY_TIME\", \"MODIFIOR\", \"MODIFY_NAME\", \"RECORD_SORT\", \"IS_USE\", \"IS_DEL\", \"GJID\") VALUES ('"+ UUID.randomUUID().toString()+"', '"+cnName+"', '"+codeNum+"', '"+enName+"', '1', '0', '', NULL, TO_DATE('2019-05-20 17:32:53', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', 'developer', TO_DATE('2019-05-20 17:32:53', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', 'developer', '2', '1', '0', NULL);";
            pw.write(sql2);
            pw.write("\r\n");
        }

        pw.flush();
        fw.close();
        pw.close();
    }
}
