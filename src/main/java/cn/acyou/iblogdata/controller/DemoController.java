package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.entity.StudentExportEntity;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.export.StudentEntityExportServer;
import cn.afterturn.easypoi.entity.vo.BigExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

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


    @RequestMapping(value = "export", method = {RequestMethod.GET})
    @ApiOperation("easy poi 大数据导出")
    public void downloadByPoiBaseView(ModelMap map, HttpServletRequest request,
                                      HttpServletResponse response) {
        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
        params.setFreezeCol(2);
        map.put(BigExcelConstants.CLASS, StudentExportEntity.class);
        map.put(BigExcelConstants.PARAMS, params);
        //就是我们的查询参数,会带到接口中,供接口查询使用
        map.put(BigExcelConstants.DATA_PARAMS, new HashMap<String,String>());
        map.put(BigExcelConstants.DATA_INTER, studentEntityExportServer);
        PoiBaseView.render(map, request, response, BigExcelConstants.EASYPOI_BIG_EXCEL_VIEW);
    }




}
