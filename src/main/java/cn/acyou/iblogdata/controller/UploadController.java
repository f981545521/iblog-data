package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.WithoutLog;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("image")
    @ApiOperation("上传一个文件")
    public ResultInfo uploadFile(MultipartFile file, Integer imageType){
        if (file.getSize() > (1024 * 20)){
            throw new ServiceException("文件大小超过了20M哦");
        }
        log.info("上传图片类型：" + imageType + "，大小：" + file.getSize() );
        return new ResultInfo("上传成功");
    }
}
