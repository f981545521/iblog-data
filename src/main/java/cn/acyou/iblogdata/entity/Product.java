package cn.acyou.iblogdata.entity;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;

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
     * 关于BigDecimal:
     * [强制] 小数类型为 decimal，禁止使用 float 和 double。
     * 说明：float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不
     * 正确的结果。如果存储的数据范围超过 decimal 的范围，建议将数据拆成整数和小数分开存储。
     *
     * 此外：
     * <strong>不要用 Double 初始化一个 BigDecimal 对象</strong>
     */
    private BigDecimal price;//售价

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
