package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.ParamValid;
import cn.acyou.iblogdata.so.ValidateSo;
import cn.acyou.iblogdata.utils.Result;
import cn.acyou.iblogdata.utils.redis.RedisUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author youfang
 * @version [1.0.0, 2019-04-12 下午 05:55]
 * @since [司法公证]
 **/
@Controller
@RequestMapping("/valid")
@Api(description = "RedisController操作演示")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping(value = "validate5")
    @ResponseBody
    public Result<ValidateSo> validate5(@ParamValid @RequestBody ValidateSo validateSo) {
        redisUtils.hPut("SO:TEST", "validateSo", validateSo);
        return Result.success(validateSo);
    }

    @GetMapping(value = "validate6")
    @ResponseBody
    public Result<ValidateSo> validate6() {
        ValidateSo so = redisUtils.hGet("SO:TEST", "validateSo", ValidateSo.class);
        return Result.success(so);
    }

}
