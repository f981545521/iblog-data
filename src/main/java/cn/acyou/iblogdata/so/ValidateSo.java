package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.annotation.valid.BaseValid;
import cn.acyou.iblogdata.annotation.valid.DateValidType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author youfang
 * @date 2018-04-25 下午 02:23
 **/
public class ValidateSo implements BaseValidateEntity, Serializable {

    private static final long serialVersionUID = -828326546547153332L;

    private Integer id;

    @BaseValid(notNull = true, message = "ValidateSo姓名不能为空！")
    private String name;

    /**
     * 性别
     */
    @BaseValid(notInRange = {"1","2"}, message = "ValidateSo性别填写不正确")
    private String sex;

    private Boolean useNow;

    @BaseValid(notEmpty = true)
    private List<String> interestList;

    @BaseValid(notEmpty = true, message = "ValidateSo朋友不能为空呦！")
    private Map<String, String> friendMap;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @BaseValid(dateValid = DateValidType.if_afterNow, message = "startDate不能大于当前日期")
    private Date startDate;

    @BaseValid(dateValid = DateValidType.if_beforeSpecifyDate,
            specifyDateFieldName = "startDate", message = "endDate不能大于startDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Map<String, String> getFriendMap() {
        return friendMap;
    }

    public void setFriendMap(Map<String, String> friendMap) {
        this.friendMap = friendMap;
    }

    public List<String> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<String> interestList) {
        this.interestList = interestList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUseNow() {
        return useNow;
    }

    public void setUseNow(Boolean useNow) {
        this.useNow = useNow;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
