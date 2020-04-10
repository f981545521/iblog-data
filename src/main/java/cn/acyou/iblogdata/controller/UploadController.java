package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.WithoutLog;
import cn.acyou.iblogdata.utils.ResultInfo;
import cn.hutool.core.io.IoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

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
     *
     * @param file      文件
     * @param imageType @RequestParam(required = true)  Integer imageType时，不传这个参数会报错：
     *                  Required Integer parameter 'imageType' is not present
     * @return
     * @RequestParam(required = false) Integer imageType时，可以不传
     */
    @PostMapping("image")
    @ApiOperation("上传一个文件")
    public ResultInfo uploadimageFile(MultipartFile file, @RequestParam(required = false) Integer imageType) throws Exception {
        log.info("上传图片类型：" + imageType + "，大小：" + file.getSize());
        FileOutputStream fileOutputStream = new FileOutputStream("D://temp//test");
        IoUtil.copy(file.getInputStream(), fileOutputStream);
        return new ResultInfo("上传成功");
    }

    /**
     * 上传一个文件
     *
     * @param file 文件
     * @return
     */
    @PostMapping("fileupload")
    @ApiOperation("上传一个文件")
    public ResultInfo uploadCommonFile(MultipartFile file){
        log.info("上传文件：" + file.getOriginalFilename() + "，大小：" + file.getSize() );
        try {
            log.info("正在上传...");
            Thread.sleep(50000);
            log.info("上传结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultInfo("上传成功");
    }
    /**
     * 上查看传一个文件的进度
     * @param fileName 文件
     * @return
     */
    @PostMapping("fileuploadProcess")
    @ApiOperation("上传一个文件")
    public ResultInfo uploadCommonFileProcess(String fileName){
        try {
            log.info("正在上传...");
            Thread.sleep(50000);
            log.info("上传结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultInfo("上传成功");
    }
}
