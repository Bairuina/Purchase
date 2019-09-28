package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_list")
public class ProductList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单商品id
     */
    @TableId("productList_id")
    private Integer productlistId;

    /**
     * 型号编号
     */
    @TableField("XHBH")
    private String xhbh;

    /**
     * 型号名称
     */
    @TableField("XHMC")
    private String xhmc;

    /**
     * 品牌编号
     */
    @TableField("PPBH")
    private String ppbh;

    /**
     * 品牌名称
     */
    @TableField("PPMC")
    private String ppmc;

    /**
     * 购买数量
     */
    @TableField("SL")
    private Integer sl;

    /**
     * 商品的实际价格
     */
    @TableField("SJJG")
    private BigDecimal sjjg;

    /**
     * 小计价格
     */
    @TableField("XJJG")
    private BigDecimal xjjg;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;

}
