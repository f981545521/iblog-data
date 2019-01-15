package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.WithoutLog;
import cn.acyou.iblogdata.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author youfang
 * @version [1.0.0, 2018-08-30 下午 01:47]
 **/
@Api
@Slf4j
@WithoutLog
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController{


    /**
     * 上传一个文件
     * @param file 文件
     * @param imageType @RequestParam(required = true)  Integer imageType时，不传这个参数会报错：
     *                                             Required Integer parameter 'imageType' is not present
     *                  @RequestParam(required = false) Integer imageType时，可以不传
     * @return
     */
    @PostMapping("image")
    @ApiOperation("上传一个文件")
    public ResultInfo uploadFile(MultipartFile file, @RequestParam(required = false) Integer imageType){
        log.info("上传图片类型：" + imageType + "，大小：" + file.getSize() );
        return new ResultInfo("上传成功");
    }
}
