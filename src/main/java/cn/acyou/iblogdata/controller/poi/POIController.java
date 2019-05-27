package cn.acyou.iblogdata.controller.poi;

import cn.acyou.iblog.utility.DateUtil;
import cn.acyou.iblogdata.controller.BaseController;
import cn.acyou.iblogdata.entity.StudentExportEntity;
import cn.acyou.iblogdata.entity.StudentExportEntityGroup;
import cn.acyou.iblogdata.entity.poi.DataDictionary;
import cn.acyou.iblogdata.export.StudentEntityExportServer;
import cn.acyou.iblogdata.upload.OSSUploadUtil;
import cn.acyou.iblogdata.utils.RandomUtil;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.utils.ResultInfoGenerate;
import cn.afterturn.easypoi.entity.vo.BigExcelConstants;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.*;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-26 上午 10:24]
 **/
@Slf4j
@Controller
@RequestMapping("poi")
public class POIController extends BaseController {

    @Autowired
    private StudentEntityExportServer studentEntityExportServer;

    @RequestMapping(value = "bigDataExport", method = {RequestMethod.GET})
    @ApiOperation("easy poi 大数据导出")
    public void downloadByPoiBaseView(ModelMap map, HttpServletRequest request,
                                      HttpServletResponse response, String name) {
        System.out.println(name);
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setFreezeCol(2);
        map.put(BigExcelConstants.CLASS, StudentExportEntity.class);
        map.put(BigExcelConstants.PARAMS, params);
        //就是我们的查询参数,会带到接口中,供接口查询使用
        map.put(BigExcelConstants.DATA_PARAMS, name);
        map.put(BigExcelConstants.DATA_INTER, studentEntityExportServer);
        PoiBaseView.render(map, request, response, BigExcelConstants.EASYPOI_BIG_EXCEL_VIEW);
    }

    @RequestMapping(value = "simpleExport", method = {RequestMethod.GET})
    @ApiOperation("easy poi 简单List导出")
    public void simpleExport(ModelMap map, HttpServletResponse response) throws Exception {
        List<StudentExportEntity> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StudentExportEntity client = new StudentExportEntity();
            client.setId("2" + i);
            client.setName("小明" + i);
            client.setBirthday(DateUtil.randomDate());
            client.setRegistrationDate(DateUtil.parseDate("2010-12-08","yyyy-MM-dd"));
            client.setSex(RandomUtil.random01());
            StudentExportEntityGroup group = new StudentExportEntityGroup();
            group.setGroupName("测试" + i);
            client.setGroup(group);
            list.add(client);
        }
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setFreezeCol(2);
        map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
        map.put(NormalExcelConstants.CLASS, StudentExportEntity.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "测试文件");//文件名称

        //方式1 ： View 返回
        //PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
        //方式2 ： 直接保存文件
        //Workbook workbook = ExcelExportUtil.exportExcel(params, StudentExportEntity.class, list );
        //FileOutputStream os = new FileOutputStream("F:\\iotest\\测试.xlsx");
        //workbook.write(os);
        //os.close();
        //方式3 ： 保存到OSS
        Workbook workbook = ExcelExportUtil.exportExcel(params, StudentExportEntity.class, list );
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        String result = OSSUploadUtil.uploadWithProgress("测试.xlsx", is);
        System.out.println(result);
        //return NormalExcelConstants.EASYPOI_EXCEL_VIEW;//View名称
    }


    @ApiOperation("easy poi 导入")
    @RequestMapping(value = "import", method = {RequestMethod.POST})
    @ResponseBody
    public ResultInfo importExcel(MultipartFile file) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = new Date().getTime();
        InputStream fileStream = file.getInputStream();
        List<StudentExportEntity> list = ExcelImportUtil.importExcel(fileStream, StudentExportEntity.class, params);
        System.out.println(new Date().getTime() - start);
        System.out.println(list.size());
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
        return ResultInfoGenerate.generateSuccess();
    }

    private static final String SYS_CATEGORY_FOLDID = "df422ffe-eca8-4174-9c70-5c24220adecc";

    @ApiOperation(value = "easy poi  数据字典导入", notes = "只支持XLS格式，会覆盖所有记录")
    @RequestMapping(value = "importDictionary", method = {RequestMethod.POST})
    @ResponseBody
    public ResultInfo importDictionary(MultipartFile file) throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
        HSSFWorkbook hs = new HSSFWorkbook(fs);
        //hs.getSheetAt(3).getRow(3).cells[0].toString()
        //输出到文件   --- 获取当前用户桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String path = fsv.getHomeDirectory().toString();
        File directory = new File(path + "\\数据字典.sql");
        FileWriter fw = new FileWriter(directory);
        PrintWriter pw = new PrintWriter(fw);
        //清空之前的记录重新导入
        pw.write("DELETE FROM SYS_CATEGORY WHERE FOLDID = '" + SYS_CATEGORY_FOLDID + "';");
        pw.write("\r\n");
        pw.write("DELETE FROM SYS_CATEGORY_VALUE WHERE CATEGORY_ID IN (SELECT ID FROM SYS_CATEGORY WHERE FOLDID = '" + SYS_CATEGORY_FOLDID + "');");
        pw.write("\r\n");

        for (int i = 2; i < hs.getNumberOfSheets(); i++) {
            HSSFSheet sheetAt = hs.getSheetAt(i);
            //表头
            HSSFRow rowHead = sheetAt.getRow(0);
            String categoryId = UUID.randomUUID().toString();

            for (int j = 1; j <= sheetAt.getLastRowNum(); j++) {
                HSSFRow row = sheetAt.getRow(j);
                HSSFCell cell0 = row.getCell(0);
                if (cell0 == null){
                    log.warn("null Point Exception");
                    continue;
                }
                HSSFCell cell = row.getCell(2);
                CellType numberType = cell.getCellTypeEnum();
                log.info("类型：" + (CellType.NUMERIC == numberType));

                String zdValue = "";
                if (CellType.NUMERIC == numberType) {
                    Double numericCellValue = cell.getNumericCellValue();
                    zdValue = String.format("%02d", numericCellValue.intValue());
                } else {
                    zdValue = row.getCell(2).toString();
                }
                String zdmc = row.getCell(0).toString();
                String zdCode = row.getCell(1).toString();
                String desc = row.getCell(3).toString();
                if (j == 1){
                    String cataSql = "INSERT INTO \"SFGZ\".\"SYS_CATEGORY\" (\"ID\", \"CODE\", \"NAME\", \"ISSYS\", \"FOLDID\", \"CREATE_TIME\", \"CREATOR\", \"CREATOR_NAME\", \"MODIFY_TIME\", \"MODIFIOR\", \"MODIFY_NAME\", \"RECORD_SORT\", \"IS_USE\") VALUES ('"+categoryId+"', '"+zdCode+"', '"+zdmc+"', '1', '"+SYS_CATEGORY_FOLDID+"', TO_DATE('2019-05-09 18:12:01', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', 'developer', TO_DATE('2019-05-09 18:12:01', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', 'developer', '93', '1');";
                    pw.write(cataSql);
                    pw.write("\r\n");
                }
                String sql = "INSERT INTO \"SFGZ\".\"SYS_CATEGORY_VALUE\" (\"ID\", \"NAME\", \"CODE\", \"EXT_VALUE\", \"PARENT_ID\", \"CATEGORY_ID\", \"CREATE_TIME\", \"CREATOR\", \"CREATOR_NAME\", \"MODIFY_TIME\", \"MODIFIOR\", \"MODIFY_NAME\", \"RECORD_SORT\", \"IS_USE\") VALUES ('"+UUID.randomUUID().toString()+"', '"+desc+"', '"+zdValue+"', NULL, NULL, '"+categoryId+"', TO_DATE('2019-05-09 18:13:31', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', 'developer', TO_DATE('2019-05-09 18:13:31', 'SYYYY-MM-DD HH24:MI:SS'), 'developer', 'developer', '3861', '1');";
                pw.write(sql);
                pw.write("\r\n");
                log.info(new DataDictionary(zdmc, zdCode, zdValue, desc).toString());
            }
        }
        pw.flush();
        fw.close();
        pw.close();


        //从第三开始
        //for (int i = 3; i < hs.getNumberOfSheets(); i++) {
        //    params.setStartSheetIndex(i);
        //    List<DataDictionary> list = ExcelImportUtil.importExcel(file.getInputStream(), DataDictionary.class, params);
        //    if (CollectionUtils.isNotEmpty(list)) {
        //        log.info(list.get(0).toString());
        //    }else {
        //        break;
        //    }
        //}

        return ResultInfoGenerate.generateSuccess();
    }

}
