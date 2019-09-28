package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId("product_id")
    private Integer productId;

    /**
     * 商品编号
     */
    private String xhbh;

    /**
     * 商品名称
     */
    private String xhmc;

    /**
     * 品目编号
     */
    private String pmbh;

    /**
     * 品目名称
     */
    private String pmmc;

    /**
     * 品牌编号
     */
    private String ppbh;

    /**
     * 品牌名称
     */
    private String ppmc;

    /**
     * 类别编号
     */
    private String ibbh;

    /**
     * 类别名称
     */
    @TableField("product_ibmc")
    private String productIbmc;

    /**
     * 商品状态
     */
    @TableField("product_zt")
    private String productZt;

}
