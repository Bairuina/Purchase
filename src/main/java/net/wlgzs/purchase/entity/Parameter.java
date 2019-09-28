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
public class Parameter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数ID
     */
    @TableId("parameter_id")
    private Integer parameterId;

    /**
     * 参数说明
     */
    private String cssm;

    /**
     * 参数值
     */
    private String csz;

    /**
     * 商品id
     */
    @TableField("product_id")
    private Integer productId;

}
