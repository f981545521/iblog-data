package cn.acyou.iblogdata.so;

import cn.acyou.iblogdata.annotation.BaseValid;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author youfang
 * @date 2018-04-25 下午 02:23
 **/
public class ValidateSo implements Serializable {

    private static final long serialVersionUID = -828326546547153332L;

    private Integer id;

    @BaseValid(message = "姓名不能为空！")
    private String name;

    private Boolean useNow;

    @BaseValid(notEmpty = true)
    private List<String> interestList;

    @BaseValid(notEmpty = true, message = "朋友不能为空呦！")
    private Map<String, String> friendMap;

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
}