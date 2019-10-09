package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "product_id",type = IdType.AUTO)
    private Integer productId;

    /**
     * 商品编号
     */
    @TableField("xhbh")
    private String xhbh;

    /**
     * 商品名称
     */
    @TableField("xhmc")
    private String xhmc;

    /**
     * 品目编号
     */
    @TableField("pmbh")
    private String pmbh;

    /**
     * 品目名称
     */
    @TableField("pmmc")
    private String pmmc;

    /**
     * 品牌编号
     */
    @TableField("ppbh")
    private String ppbh;

    /**
     * 品牌名称
     */
    @TableField("ppmc")
    private String ppmc;

    /**
     * 类别编号
     */
    @TableField("lbbh")
    private String lbbh;

    /**
     * 类别名称
     */
    @TableField("lbmc")
    private String lbmc;

    /**
     * 商品状态
     */
    @TableField("zt")
    private String zt;

    /**
     * 商品参数
     */
    @TableField("parametersList")
    private String parametersList;

    /**
     * 商品报价
     */
    private BigDecimal price;

    /**
     * 商品审核价格
     */
    private BigDecimal shjg;

}
