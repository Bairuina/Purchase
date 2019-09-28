package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 型号ID
     */
    @TableId("type_id")
    private Integer typeId;

    /**
     * 型号编号
     */
    private String xhbh;

    /**
     * 型号名称
     */
    private String xhmc;

}
