package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配件ID
     */
    @TableId(value = "parts_id",type = IdType.AUTO)
    private Integer partsId;

    /**
     * 配件编号
     */
    @TableField("PJBH")
    private String PJBH;

    /**
     * 配件名称
     */
    @TableField("PJMC")
    private String PJMC;

    /**
     * 配件描述
     */
    @TableField("PJMS")
    private String PJMS;

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
     * 配件明细
     */
    @TableField("accessoryListmx")
    private String accessoryListmx;

}
