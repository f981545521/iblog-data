package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.exception.ServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-16 下午 03:33]
 **/
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{

    @RequestMapping(value = "toPreview", method = {RequestMethod.POST})
    public String toPreview(MultipartFile file){
        System.out.println(file.getOriginalFilename());

        return "demo/preview";
    }
    @RequestMapping(value = "demoPage", method = {RequestMethod.GET})
    public String demoPage(){
        System.out.println(request.getRequestURI());
        String flag = request.getParameter("flag");
        if ("1".equals(flag)){
            throw new ServiceException();
        }
        return "demo/demoPage";
    }

}
