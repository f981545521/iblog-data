package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.upload.OSSUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2018-07-05 下午 04:03]
 **/
@Controller
@RequestMapping("oss")
public class OssController {

    @RequestMapping(value = "/upload", method = {RequestMethod.GET})
    public ModelAndView greetingView() {
        ModelAndView mv = new ModelAndView("/ossUpload");
        List<String> bucketList = OSSUploadUtil.listBuckets();
        mv.addObject("bucketList", bucketList);
        return mv;
    }


}
