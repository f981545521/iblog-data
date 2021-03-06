package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.ParamValid;
import cn.acyou.iblogdata.so.ValidateSo;
import cn.acyou.iblogdata.utils.Result;
import cn.acyou.iblogdata.utils.redis.RedisUtils;
import cn.acyou.iblogdata.utils.redis.ZSetItem;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2019-04-12 下午 05:55]
 * @since [司法公证]
 **/
@Controller
@RequestMapping("/valid")
@Api(description = "RedisController操作演示")
public class RedisController {

    private final static String HOT_CITY = "hotCity:";
    private final static Double score = 1D;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping(value = "validate5")
    @ResponseBody
    public Result<ValidateSo> validate5(@ParamValid @RequestBody ValidateSo validateSo) {
        redisUtils.hashPut("SO:TEST", "validateSo", validateSo);
        return Result.success(validateSo);
    }

    @GetMapping(value = "validate6")
    @ResponseBody
    public Result<ValidateSo> validate6() {
        ValidateSo so = redisUtils.hashGet("SO:TEST", "validateSo", ValidateSo.class);
        return Result.success(so);
    }

    @GetMapping(value = "zSetadd")
    @ResponseBody
    public Result zSetadd(String cityCode) {
        redisUtils.zSetAdd(HOT_CITY, cityCode, score);
        return Result.success();
    }
    @GetMapping(value = "zSetincrementScore")
    @ResponseBody
    public Result zSetincrementScore(String cityCode, Double deal) {
        redisUtils.zSetIncrementScore(HOT_CITY, cityCode, deal);
        return Result.success();
    }
    @GetMapping(value = "zSetRandeScorre")
    @ResponseBody
    public Result rangeByScoreWithScores() {
        List<ZSetItem> zSetItems = redisUtils.zSetReverseRangeWithScores(HOT_CITY, 0, 6);
        return Result.success(zSetItems);
    }

}
