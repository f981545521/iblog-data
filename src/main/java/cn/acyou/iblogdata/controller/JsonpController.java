package cn.acyou.iblogdata.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youfang
 * @date 2018-03-20 下午 10:08
 */
@RestController
@RequestMapping("/jsonp")
@Api
public class JsonpController {

    @RequestMapping(value = "/category",method = {RequestMethod.GET})
    @ApiOperation(value = "测试JSONP")
    public List<String> getcategoryList(){
        List<String> categoryList = new ArrayList<>();
        categoryList.add("we coun't hava all");
        categoryList.add("怎么大风越狠");
        return categoryList;
    }


}
