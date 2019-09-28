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
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    @TableId("contract_id")
    private Integer contractId;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;

    /**
     * 合同的地址（url）
     */
    @TableField("contract_url")
    private String contractUrl;

}
