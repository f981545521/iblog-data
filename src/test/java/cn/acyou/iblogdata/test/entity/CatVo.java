package cn.acyou.iblogdata.test.entity;

import cn.acyou.iblog.utility.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author youfang
 * @version [1.0.0, 2019-07-09 9:30]
 **/
@Data
public class CatVo implements Serializable {
    private static final long serialVersionUID = 4232045119110408465L;

    private Long id;

    private String name;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birth;

    private List<String> interest = new ArrayList<>();

    public String nameAndAge(){
        return name + age;
    }

    public static void main(String[] args) {
        CatVo catVo = new CatVo();
        catVo.setName("小喵！");
        catVo.setBirth(new Date());
        catVo.setInterest(Lists.newArrayList("xx","oo"));
        System.out.println(catVo);
        System.out.println(JSON.toJSONStringWithDateFormat(catVo, DateUtil.SPECIFIC_DATE_FORMAT_PATTERN, SerializerFeature.WriteMapNullValue));
    }
}
