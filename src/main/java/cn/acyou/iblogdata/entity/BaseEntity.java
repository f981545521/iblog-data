package cn.acyou.iblogdata.entity;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * @author youfang
 * @version [1.0.0, 2020/3/19]
 **/
public class BaseEntity implements Serializable {

    /**
     * 排序值
     */
    @Column(name = "recordSort")
    private Integer	recordSort;
    /**
     * 是否禁用：0-否 1-是，默认0
     */
    @Column(name = "is_use")
    private Integer	isUse;
    /**
     * 是否删除：0-否 1-是，默认0
     */
    @Column(name = "is_delete")
    private Integer	isDelete;
    /**
     * 创建时间,默认当前时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 创建人ID
     */
    @Column(name = "create_user")
    private Long	createUser;
    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Date	updateTime;
    /**
     * 最后修改人
     */
    @Column(name = "update_user")
    private Long	updateUser;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String	remark;

    public Integer getRecordSort() {
        return recordSort;
    }

    public void setRecordSort(Integer recordSort) {
        this.recordSort = recordSort;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
