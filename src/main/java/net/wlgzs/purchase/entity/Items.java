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
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 品目id

     */
    @TableId("items_id")
    private Integer itemsId;

    /**
     * 品目编号
     */
    private String pmbh;

    /**
     * 品目名称
     */
    private String pmmc;

}
