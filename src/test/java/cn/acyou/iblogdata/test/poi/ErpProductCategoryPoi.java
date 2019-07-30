package cn.acyou.iblogdata.test.poi;

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
import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2019-05-22 17:21]
 * @since [司法公证]
 **/
public class ErpProductCategoryPoi {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\poi\\资产分类.xls");
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
        File directory = new File(path + "\\资产分类.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        //第二个Sheet   0开始
        HSSFSheet hsSheetAt = hs.getSheetAt(1);
        for (int i = 1; i<= hsSheetAt.getLastRowNum(); i++){
            HSSFRow row = hsSheetAt.getRow(i);
            //获取数据
            String categoryCode = row.getCell(0).toString().trim();
            String categoryName = row.getCell(1).toString().trim();
            System.out.println(categoryCode + "|" + categoryName);
            String categoryPath = "";
            String parentCategoryCode = "";
            boolean isTop = false;
            if (categoryCode.length() == 1){
                categoryPath = categoryCode;
                isTop = true;
            }else {
                String categoryCodeOne = categoryCode.substring(0, 1);
                String balanceCode = categoryCode.substring(1);
                if (categoryCode.length() == 3){
                    parentCategoryCode = categoryCodeOne;
                    categoryPath = categoryCodeOne + "|" + balanceCode;
                }else {
                    parentCategoryCode = categoryCode.substring(0, categoryCode.length() - 2);

                    List<String> parentCategoryCodeList = new ArrayList<>();
                    parentCategoryCodeList.add(categoryCodeOne);
                    char[] chars = balanceCode.toCharArray();
                    for (int j = 0; j < chars.length - 1; j+=2) {
                        char c1 = chars[j];
                        char c2 = chars[j + 1];
                        String s21 = c1 + "" + c2;
                        parentCategoryCodeList.add(s21);
                    }
                    categoryPath = StringUtils.join(parentCategoryCodeList, "|");
                }
            }
            if (isTop){
                String sql = "INSERT INTO `service_commodity`.`t_commodity_category` (`category_name`, `category_code`, " +
                        "`picture_url`, `parent_id`, `sort_order`, `category_path`, `status`," +
                        " `create_time`, `create_user`, `last_update_time`, `last_update_user`)" +
                        "SELECT '"+categoryName+"', '"+categoryCode+"'," +
                        " NULL, '0', '0', '"+categoryPath+"', '0', " +
                        "'2019-01-01 00:00:00', '1', NULL, NULL FROM dual;";
                pw.write(sql);
                pw.write("\r\n");
            }else {
                String sql = "INSERT INTO `service_commodity`.`t_commodity_category` (`category_name`, `category_code`, " +
                        "`picture_url`, `parent_id`, `sort_order`, `category_path`, `status`," +
                        " `create_time`, `create_user`, `last_update_time`, `last_update_user`)" +
                        "SELECT '"+categoryName+"', '"+categoryCode+"'," +
                        " NULL, cc.category_id, '0', '"+categoryPath+"', '0', " +
                        "'2019-01-01 00:00:00', '1', NULL, NULL FROM t_commodity_category cc WHERE cc.category_code = '"+parentCategoryCode+"';";
                pw.write(sql);
                pw.write("\r\n");
            }

        }

        pw.flush();
        fw.close();
        pw.close();
    }
}
