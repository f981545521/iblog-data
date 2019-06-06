package cn.acyou.iblogdata.test.poi;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

/**
 * @author youfang
 * @version [1.0.0, 2019-06-06 14:37]
 * @since [司法公证]
 **/
public class GzsxPoi {

    private static Map<String, String> keyMap = Maps.newHashMap();

    public static void main(String[] args) throws Exception{
        File file = new File("D:\\poi\\公证事项信息.xls");
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
        File directory = new File(path + "\\gzsx.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        //清空之前的记录重新导入
        //pw.write("DELETE FROM SFGZ_JCSJ_MATTERS WHERE REMARK = 'sync';");
        //pw.write("\r\n");

        HSSFSheet hsSheetAt = hs.getSheetAt(0);
        for (int i = 1; i<= hsSheetAt.getLastRowNum(); i++){
            HSSFRow row = hsSheetAt.getRow(i);
            String currentId = UUID.randomUUID().toString();
            //获取数据
            String code = row.getCell(0).toString();
            String name = row.getCell(1).toString();
            String pcode = "";
            String pid = "";
            if (row.getCell(2) != null){
                pcode = row.getCell(2).toString();
                pid = keyMap.get(pcode);
            }
            String type = row.getCell(3).toString();
            Integer gzlx = Double.valueOf(row.getCell(3).toString()).intValue();
            keyMap.put(code, currentId);

            System.out.println(code + name + type);

            String sql2 = "INSERT /*+ IGNORE_ROW_ON_DUPKEY_INDEX(SFGZ_JCSJ_MATTERS, INDEX_JCSJ_MATTER_GZSXBH) */ INTO \"SFGZ\".\"SFGZ_JCSJ_MATTERS\" (\"ID\", \"GZSXMC\", \"GZSXBH\", \"PARENT_ID\", \"RECORD_SORT\", \"CREATE_TIME\", \"CREATOR\", \"MODIFY_TIME\", \"MODIFIOR\", \"MODIFY_NAME\", " +
                    "\"IS_USE\", \"IS_DEL\", \"NM_LEVEL\", \"SJGZSXBM\", \"REMARK\", \"CREATOR_NAME\", \"GZLX\") VALUES " +
                    "('"+currentId+"', '"+name+"', '"+code+"', '"+pid+"', " +
                    "'1', TO_DATE('2019-05-14 10:26:56', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', TO_DATE('2019-05-22 18:07:28', 'SYYYY-MM-DD HH24:MI:SS')," +
                    " 'developer', 'developer', '1', '0', NULL, '"+pcode+"', 'sync', 'developer', '"+gzlx+"');";
            pw.write(sql2);
            pw.write("\r\n");
        }

        pw.flush();
        fw.close();
        pw.close();
    }
}
