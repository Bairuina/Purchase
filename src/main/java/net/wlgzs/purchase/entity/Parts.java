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
public class Parts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配件ID
     */
    @TableId("parts_id")
    private Integer partsId;

    /**
     * 配件编号
     */
    @TableField("PJBH")
    private String pjbh;

    /**
     * 配件名称
     */
    @TableField("PJMC")
    private String pjmc;

    /**
     * 配件描述
     */
    @TableField("OJMS")
    private String ojms;

    /**
     * 品目编号
     */
    private String pmbh;

    /**
     * 品目名称
     */
    private String pmmc;

}
