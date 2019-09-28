package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("service_value")
public class ServiceValue implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 增值服务ID
     */
    @TableId("service_id")
    private Integer serviceId;

    /**
     * 服务内容
     */
    private String fwmc;

    /**
     * 服务详情
     */
    private String fwmx;

    /**
     * 状态
     */
    private String zt;

    /**
     * 品目编号
     */
    private String pmbh;

    /**
     * 品目名称
     */
    private String pmmc;

}
