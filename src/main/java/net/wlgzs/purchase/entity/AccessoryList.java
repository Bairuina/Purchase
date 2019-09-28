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
@TableName("accessory_list")
public class AccessoryList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单配件id
     */
    @TableId("accessoryList_id")
    private Integer accessorylistId;

    /**
     * 配件名称
     */
    @TableField("PJMC")
    private String pjmc;

    /**
     * 配件数量
     */
    @TableField("SL")
    private Integer sl;

    /**
     * 配件单价
     */
    @TableField("PJJG")
    private BigDecimal pjjg;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;

}
