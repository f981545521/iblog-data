package cn.acyou.iblogdata.entity.poi;


import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @author 86180
 */
public class DataDictionary implements Serializable {

    private static final long serialVersionUID = 8717603323302157028L;

    public DataDictionary() {
    }

    public DataDictionary(String zdmc, String zdCode, String zdValue, String desc) {
        this.zdmc = zdmc;
        this.zdCode = zdCode;
        this.zdValue = zdValue;
        this.desc = desc;
    }

    /**
     * 字典名称
     */
    @Excel(name = "字典名称", height = 20, width = 30, isImportField = "true_st")
    private String zdmc;
    /**
     * 字典编码
     */
    @Excel(name = "字典CODE", height = 20, width = 30, isImportField = "true_st")
    private String zdCode;
    /**
     * 字典值
     */
    @Excel(name = "字典值", height = 20, width = 30, isImportField = "true_st")
    private String zdValue;
    /**
     * 值描述
     */
    @Excel(name = "值描述", height = 20, width = 30, isImportField = "true_st")
    private String desc;

    public String getZdmc() {
        return zdmc;
    }

    public void setZdmc(String zdmc) {
        this.zdmc = zdmc;
    }

    public String getZdCode() {
        return zdCode;
    }

    public void setZdCode(String zdCode) {
        this.zdCode = zdCode;
    }

    public String getZdValue() {
        return zdValue;
    }

    public void setZdValue(String zdValue) {
        this.zdValue = zdValue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "DataDictionary{" +
                "zdmc='" + zdmc + '\'' +
                ", zdCode='" + zdCode + '\'' +
                ", zdValue='" + zdValue + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
