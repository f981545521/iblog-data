package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.export.StudentEntityExportServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-16 下午 03:33]
 **/
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{

    @Value("${ab.name: none found}")
    private String pomName;

    @RequestMapping(value = "toPreview", method = {RequestMethod.POST})
    public String toPreview(MultipartFile file){
        System.out.println(file.getOriginalFilename());

        return "demo/preview";
    }

    @RequestMapping(value = "demoPage", method = {RequestMethod.GET})
    public String demoPage(){
        String flag = request.getParameter("flag");
        if ("1".equals(flag)){
            throw new ServiceException("FLAG 不能是1呦！");
        }
        return "demo/demoPage";
    }

    @RequestMapping(value = "pom", method = {RequestMethod.GET})
    @ResponseBody
    public String pomName(){
        return pomName;
    }

}
