package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-12
 */
@Data
@TableName("accessory_list")
public class AccessoryList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单配件id
     */
    @TableId(value = "accessoryList_id", type = IdType.AUTO)
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
    private String ddbh;

    @TableField("XHBH")
    private String xhbh;

    @TableField("PPBH")
    private String ppbh;

    @TableField("PJBH")
    private String pjbh;
}
