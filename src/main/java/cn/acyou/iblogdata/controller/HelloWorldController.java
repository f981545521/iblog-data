package cn.acyou.iblogdata.controller;

import cn.acyou.iblog.model.Sort;
import cn.acyou.iblog.service.SortService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author youfang
 * @date 2018-02-27 13:21
 **/
@RestController
@RequestMapping("/hello")
@Api
public class HelloWorldController {

    @Autowired(required = false)
    private SortService sortService;

    @ApiOperation(value = "first")
    @RequestMapping(value = "/hello",method = {RequestMethod.GET})
    @ResponseBody
    public String index() {
        return "Hello World";
    }
    @ApiOperation(value = "sort")
    @RequestMapping(value = "/sortList",method = {RequestMethod.GET})
    @ResponseBody
    public List<Sort> getSortList(){
        return sortService.listSorts(4);
    }
}