package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("service_list")
public class ServiceList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单服务id
     */
    @TableId("serviceList_id")
    private Integer servicelistId;

    /**
     * 服务名称
     */
    private String fwMC;

    /**
     * 服务数量
     */
    @TableField("SL")
    private Integer sl;

    /**
     * 服务单价
     */
    @TableField("FWJG")
    private BigDecimal fwjg;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;

}
