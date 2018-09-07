package cn.acyou.iblogdata.entity;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.io.Serializable;

/**
 * @author youfang
 * @version [1.0.0, 2018-09-07 下午 03:42]
 **/
@Data
@NoArgsConstructor//生成一个无参数的构造方法
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 4730840004241598090L;
    private Long id;//主键
    private String name;//材料名称
    private Integer stockNumber;//库存数量

    /**
     * 入库
     */
    public void inStock(Integer num){
        stockNumber += num;
        System.out.println("入库成功" + stockNumber);
    }

    /**
     * 出库
     */
    public void outStock(Integer num){
        stockNumber += num;
        System.out.println("出库成功" + stockNumber);
    }

}
