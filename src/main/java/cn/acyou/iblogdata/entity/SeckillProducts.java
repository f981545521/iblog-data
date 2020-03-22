package cn.acyou.iblogdata.entity;

import java.io.Serializable;

/**
 * @author acyou
 * @version [1.0.0, 2020-3-22 上午 10:36]
 **/
public class SeckillProducts implements Serializable {

    private static final long serialVersionUID = -1172062199118305828L;
    /**
     * 商品ID
     */
    private Long productsId;
    /**
     * 商品名称
     */
    private String productsName;

    /**
     * 商品库存
     */
    private Long productsStock;

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public Long getProductsStock() {
        return productsStock;
    }

    public void setProductsStock(Long productsStock) {
        this.productsStock = productsStock;
    }
}
