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
public class Sort implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类别ID
     */
    @TableId("sort_id")
    private Integer sortId;

    /**
     * 类别编号
     */
    private String Ibbh;

    /**
     * 类别名称
     */
    private String Ibmc;

}
