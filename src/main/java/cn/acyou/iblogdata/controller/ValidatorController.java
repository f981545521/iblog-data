package cn.acyou.iblogdata.controller;

import cn.acyou.iblogdata.annotation.ParameterValid;
import cn.acyou.iblogdata.exception.ServiceException;
import cn.acyou.iblogdata.so.ValidateSo;
import cn.acyou.iblogdata.utils.ValidateUtil;
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
public class ValidatorController {

    @RequestMapping(value = "index")
    @ResponseBody
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
}
