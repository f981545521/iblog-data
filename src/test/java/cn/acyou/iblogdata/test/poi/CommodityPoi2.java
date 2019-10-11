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

/**
 * @author youfang
 * @version [1.0.0, 2019-09-03 15:05]
 * @since [司法公证]
 **/
public class CommodityPoi2 {
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\poi\\sp2.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
        HSSFWorkbook hs = new HSSFWorkbook(fs);
        //hs.getSheetAt(3).getRow(3).cells[0].toString()
        //输出到文件   --- 获取当前用户桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();
        File directory = new File(path + "\\sp2_3.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        HSSFSheet hsSheetAt = hs.getSheetAt(0);
        Long id = 100000L;
        for (int j =1; j<= hsSheetAt.getLastRowNum(); j++){
            HSSFRow row = hsSheetAt.getRow(j);
            //名称
            String mc = row.getCell(1).toString();
            //品牌
            String pp = row.getCell(2).toString();
            //规格
            String jscs = row.getCell(3).toString();
            //单位
            String dw = row.getCell(4).toString();
            //数量
            String sl = row.getCell(5).toString();
            //单价
            String dj = row.getCell(6).toString();
            //一级标签
            String yjbq = row.getCell(8).toString();
            //二级标签
            String ejbq = row.getCell(9).toString();
            //类别
            String lb = row.getCell(10).toString();
            String reUse = "1";
            if (lb.equals("一次性易耗品")){
                reUse = "0";
            }

            System.out.println(mc + "-" + pp + "-" + jscs + "-" + dw + "-" + dj);
            String sql = "INSERT INTO `service_commodity`.`t_product_spu` (`spu_code`, `product_no`, \n" +
                    "`category_id`, `brand_id`, `brand_name`, `brand_logo_url`, `origin`, `product_name`, `product_desc`, \n" +
                    "`product_desc_ext`, `product_keywords`, `product_type`, `settlement_price`, `cost_price`, `price`, \n" +
                    "`tag_price`, `profit`, `style_num`, `params_json`, `have_spec`, `spec_value`, `begin_time`, `end_time`, \n" +
                    "`online_status`, `volume`, `weight`, `unit`, `remark`, `external_product_code`, `external_group_code`, \n" +
                    "`sort`, `grade`, `is_spec_images`, `thumbnail`, `is_pack`, `is_hide_stock`, `is_give_point`, `is_point_deduction`, \n" +
                    "`is_reuse`, `buy_quota`, `seller_id`, `shop_id`, `shop_name`, `shop_cat_id`, `freight_template_id`, \n" +
                    "`bear_freight`, `depot_id`, `status`, `auth_message`, `disabled`, `is_delete`, `ext`, \n" +
                    "`create_time`, `create_user`, `last_update_time`, `last_update_user`)\n" +
                    "SELECT '"+String.valueOf(id)+"', '', \n" +
                    "'3850', cb.brand_id, cb.brand_name, '', NULL, '"+mc+"', '"+mc+"', \n" +
                    "NULL, '', '1', NULL, '"+dj+"', '"+dj+"', \n" +
                    "NULL, NULL, '"+jscs+"', NULL, '0', NULL, NULL, NULL, \n" +
                    "'1', '', '', '"+dw+"', '', NULL, NULL, '1', '1', '0', '', '0', '0', '0', '0', '"+reUse+"', '0', '10', '10', NULL, NULL, NULL,\n" +
                    " '0', NULL, '3', NULL, '0', '0', NULL, '2019-07-31 20:54:04', '7', NULL, NULL FROM t_commodity_brand cb WHERE cb.brand_name = '"+pp+"';";

            pw.write(sql);
            pw.write("\r\n");
            //pw.write("-- 资产标签");
            //pw.write("\r\n");
            //String labelSql = "INSERT INTO `service_commodity`.`t_product_spu_label` " +
            //        "(`label_id`, `spu_code`) SELECT label_id, '"+id+"' FROM t_product_label WHERE label_name = '"+yjbq+"';";
            //pw.write(labelSql);
            //pw.write("\r\n");
            //String labelSql2 = "INSERT INTO `service_commodity`.`t_product_spu_label` " +
            //        "(`label_id`, `spu_code`) SELECT label_id, '"+id+"' FROM t_product_label WHERE label_name = '"+ejbq+"';";
            //pw.write(labelSql2);
            //pw.write("\r\n");

            id++;

        }







        pw.flush();
        fw.close();
        pw.close();
    }
}
