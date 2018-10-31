package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.utils.ZxingUtil;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author youfang
 * @version [1.0.0, 2018-10-31 下午 02:11]
 **/
@Controller
@RequestMapping("/qr")
public class QrController {

    @RequestMapping(value = "make", method = RequestMethod.GET)
    public void makeQrCode(HttpServletResponse response,Integer size,Integer margin,String level,
                          String format,String content) throws WriterException, IOException {
        Integer width,height;
        if(size == null){
            width = 200;
            height = 200;
        }else{
            width = size;
            height = size;
        }
        if(margin == null) margin = 0;
        if(level == null) level = "Q";
        if(format == null) format = "gif";
        if(content == null) content = "我就是二维码的内容";
        ZxingUtil.createZxing(response, width, height, margin, level, format, content);
    }

    @RequestMapping(value = "readUrl", method = RequestMethod.POST)
    @ResponseBody
    public String readQrCodeFromUrl(String url) throws NotFoundException, IOException {
        return ZxingUtil.readZxing(url);
    }

    @RequestMapping(value = "readFile", method = RequestMethod.POST)
    @ResponseBody
    public String readQrCodeFromFile(MultipartFile file) throws NotFoundException, IOException {
        InputStream is = file.getInputStream();
        return ZxingUtil.readZxing(is);
    }

}
