package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.ParamValid;
import cn.acyou.iblogdata.so.ValidateSo;
import cn.acyou.iblogdata.utils.Result;
import cn.acyou.iblogdata.utils.UnloadClass;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author youfang
 * @version [1.0.0, 2020/5/14]
 **/
@Api(tags = "系统的方法")
@RestController
@RequestMapping("/os")
public class SystemController {

    @PostMapping(value = "gc")
    @ResponseBody
    @ApiOperation(value = "GC")
    public Result doGcMethod() {
        System.gc();
        return Result.success();
    }
    @PostMapping(value = "unloadFlowableUtil")
    @ResponseBody
    @ApiOperation(value = "unloadFlowableUtil")
    public Result unloadFlowableUtil() {
        UnloadClass.unloadFlowableUtil();
        return Result.success();
    }
}
