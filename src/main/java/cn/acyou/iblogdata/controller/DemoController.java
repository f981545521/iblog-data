package cn.acyou.iblogdata.controller;

import cn.acyou.iblog.utility.DateUtil;
import cn.acyou.iblogdata.entity.StudentExportEntity;
import cn.acyou.iblogdata.entity.StudentExportEntityGroup;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.export.StudentEntityExportServer;
import cn.acyou.iblogdata.upload.OSSUploadUtil;
import cn.acyou.iblogdata.utils.RandomUtil;
import cn.acyou.iblogdata.utils.StreamConvertUtil;
import cn.acyou.iblogdata.vo.OSSVo;
import cn.afterturn.easypoi.entity.vo.BigExcelConstants;
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-16 下午 03:33]
 **/
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{

    @Value("${ab.name: none found}")
    private String pomName;

    @Autowired
    private StudentEntityExportServer studentEntityExportServer;

    @RequestMapping(value = "toPreview", method = {RequestMethod.POST})
    public String toPreview(MultipartFile file){
        System.out.println(file.getOriginalFilename());

        return "demo/preview";
    }

    @RequestMapping(value = "demoPage", method = {RequestMethod.GET})
    public String demoPage(){
        String flag = request.getParameter("flag");
        if ("1".equals(flag)){
            throw new ServiceException();
        }
        return "demo/demoPage";
    }

    @RequestMapping(value = "pom", method = {RequestMethod.GET})
    @ResponseBody
    public String pomName(){
        return pomName;
    }


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
        OutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        InputStream is = StreamConvertUtil.parse(os);
        String result = OSSUploadUtil.uploadWithProgress("测试.xlsx", is);
        System.out.println(result);
        //return NormalExcelConstants.EASYPOI_EXCEL_VIEW;//View名称
    }




}
