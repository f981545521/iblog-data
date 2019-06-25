package cn.acyou.iblogdata.test.poi;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

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
public class ErpPoi {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\erp.xls");
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
        File directory = new File(path + "\\erp.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        int indexId = 1;
        for (int i = 1; i < hs.getNumberOfSheets(); i++) {
            HSSFSheet hsSheetAt = hs.getSheetAt(i);
            for (int j =1; j<= hsSheetAt.getLastRowNum(); j++){
                HSSFRow row = hsSheetAt.getRow(j);
                String zdmc = row.getCell(0).toString();
                String zdcode = row.getCell(1).toString();
                String zdValue = row.getCell(2).toString();
                String zdDesc = row.getCell(3).toString();
                if (j == 1){
                    String sql1 = "INSERT INTO `service_tool`.`t_tool_datadictionary` (`id`, `ddname`, `ddcode`, `dddesc`) VALUES ('"+indexId+"', '"+zdmc+"', '"+zdcode+"', '');";
                    pw.write(sql1);
                    pw.write("\r\n");
                }
                String sql2 = "INSERT INTO `service_tool`.`t_tool_datadictionary_detail` (`ddcode`, `ddvalue`, `dddisplay`) VALUES ('"+zdcode+"', '"+zdValue+"', '"+zdDesc+"');";
                pw.write(sql2);
                pw.write("\r\n");
                indexId ++;
            }
        }







        pw.flush();
        fw.close();
        pw.close();
    }
}
