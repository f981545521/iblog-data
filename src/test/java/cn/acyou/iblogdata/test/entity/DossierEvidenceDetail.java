package cn.acyou.iblogdata.test.entity;



import cn.acyou.iblogdata.annotation.valid.BaseValid;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author youfang
 */
@Entity
@Table(name = "SFGZ_YWBL_EVIDENCE_DETAIL")
public class DossierEvidenceDetail {

    @Id
    @Column(name = "ID")
    private String id;


    @Column(name = "JZXXID")
    private String jzxxid;

    @Column(name = "ZJXXXID")
    @BaseValid(notNull = true, message = "证据项信息ID不能为空")
    private String zjxxxid;

    @Column(name = "ZJMC")
    @BaseValid(notNull = true, message = "证据名称不能为空")
    private String zjmc;

    @Column(name = "YXQ")
    private String yxq;

    @Column(name = "DSRID")
    private String dsrid;

    @Column(name = "ZMCLBM")
    private String zmclbm;

    @Column(name = "ZMCLLY")
    private String zmclly;

    @Column(name = "SFWGWS")
    private Integer sfwgws;

    @Column(name = "GZXX")
    private String gzxx;

    @Column(name = "LRFS")
    private Integer lrfs;

    @Column(name = "BCLJ")
    private String bclj;

    /**
     * 当事人名称
     */
    @Transient
    private String xm;

    public DossierEvidenceDetail() {
    }

    public DossierEvidenceDetail(String id) {
        this.id = id;
    }

    public DossierEvidenceDetail(String id, String jzxxid, String zjxxxid, String zjmc, String yxq, String dsrid, String zmclbm, String zmclly, Integer sfwgws, String gzxx, Integer lrfs, String bclj, String xm, Date createTime) {
        this.id = id;
        this.jzxxid = jzxxid;
        this.zjxxxid = zjxxxid;
        this.zjmc = zjmc;
        this.yxq = yxq;
        this.dsrid = dsrid;
        this.zmclbm = zmclbm;
        this.zmclly = zmclly;
        this.sfwgws = sfwgws;
        this.gzxx = gzxx;
        this.lrfs = lrfs;
        this.bclj = bclj;
        this.xm = xm;
        this.createTime = createTime;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getJzxxid() {
        return jzxxid;
    }

    public void setJzxxid(String jzxxid) {
        this.jzxxid = jzxxid;
    }

    public String getZmclbm() {
        return zmclbm;
    }

    public void setZmclbm(String zmclbm) {
        this.zmclbm = zmclbm;
    }

    public Integer getLrfs() {
        return lrfs;
    }

    public void setLrfs(Integer lrfs) {
        this.lrfs = lrfs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZjxxxid() {
        return zjxxxid;
    }

    public void setZjxxxid(String zjxxxid) {
        this.zjxxxid = zjxxxid;
    }

    public String getZjmc() {
        return zjmc;
    }

    public void setZjmc(String zjmc) {
        this.zjmc = zjmc;
    }

    public String getYxq() {
        return yxq;
    }

    public void setYxq(String yxq) {
        this.yxq = yxq;
    }

    public String getDsrid() {
        return dsrid;
    }

    public void setDsrid(String dsrid) {
        this.dsrid = dsrid;
    }

    public String getZmclly() {
        return zmclly;
    }

    public void setZmclly(String zmclly) {
        this.zmclly = zmclly;
    }

    public Integer getSfwgws() {
        return sfwgws;
    }

    public void setSfwgws(Integer sfwgws) {
        this.sfwgws = sfwgws;
    }

    public String getGzxx() {
        return gzxx;
    }

    public void setGzxx(String gzxx) {
        this.gzxx = gzxx;
    }

    public String getBclj() {
        return bclj;
    }

    public void setBclj(String bclj) {
        this.bclj = bclj;
    }

    // =============系统默认字段==================================
    private static final long serialVersionUID = 1L;
    /**
     * 创建时间，不用set,hibernate会自动把当前时间写入
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;
    /**
     * 创建人名称
     */
    @Column(name = "CREATOR_NAME")
    private String creatorName;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;
    /**
     * 修改人
     */
    @Column(name = "MODIFIOR")
    private String modifior;
    /**
     * 修改人名称
     */
    @Column(name = "MODIFY_NAME")
    private String modifyName;
    /**
     * 排序号
     */
    @Column(name = "RECORD_SORT")
    private Double recordSort;
    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;
    /**
     * 状态标志：启用/禁用
     */
    @Column(name = "IS_USE")
    private Integer isUse;
    /**
     * 是否删除
     */
    @Column(name = "IS_DEL")
    private Integer isDel;

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifior() {
        return modifior;
    }

    public void setModifior(String modifior) {
        this.modifior = modifior;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Double getRecordSort() {
        return recordSort;
    }

    public void setRecordSort(Double recordSort) {
        this.recordSort = recordSort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
