package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.ParameterValid;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.so.ValidateSo;
import cn.acyou.iblogdata.so.ValidateSo2;
import cn.acyou.iblogdata.so.ValidateSo3;
import cn.acyou.iblogdata.utils.EnhanceValidUtil;
import cn.acyou.iblogdata.utils.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author youfang
 * @version [1.0.0, 2019-04-12 下午 05:55]
 * @since [司法公证]
 **/
@Controller
@RequestMapping("/valid")
@Api(description = "参数校验演示")
public class ValidatorController {

    @RequestMapping(value = "index")
    @ResponseBody
    @ApiOperation(value = "参数校验 - 主页")
    public String index(ValidateSo validateSo){
        String validateResult = ValidateUtil.valid(validateSo);
        if (StringUtils.isNotEmpty(validateResult)){
            throw new ServiceException(validateResult);
        }
        System.out.println(validateResult);
        return "好的";
    }

    @RequestMapping(value = "validate2")
    @ResponseBody
    @ParameterValid
    public String validate2(ValidateSo validateSo){
        return "好的";
    }

    @RequestMapping(value = "validate3")
    @ResponseBody
    public String validate3(ValidateSo2 validateSo){
        return "好的";
    }

    @RequestMapping(value = "enhanceValidate")
    @ResponseBody
    public String enhanceValidate(ValidateSo3 validateSo){
        EnhanceValidUtil.valid(validateSo);
        return "好的";
    }
}
