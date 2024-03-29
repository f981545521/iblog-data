package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.upload.OSSUploadUtil;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.acyou.iblogdata.vo.OSSVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ModelAndView toUpload() {
        ModelAndView mv = new ModelAndView("/ossUpload");
        List<String> bucketList = OSSUploadUtil.listBuckets();
        mv.addObject("bucketList", bucketList);
        return mv;
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    @ResponseBody
    public ResultInfo greetingView(OSSVo ossUploadVo) {
        String uploadUrl = OSSUploadUtil.uploadOssByVo(ossUploadVo);
        return new ResultInfo(uploadUrl);
    }




}
