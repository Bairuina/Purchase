package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId("order_id")
    private Integer orderId;

    /**
     * 送货城市
     */
    private String deliverycity;

    /**
     * 送货区/县
     */
    private String deliverycounty;

    /**
     * 送货地址
     */
    private String shdd;

    /**
     * 订单编号
     */
    private String ddbh;

    /**
     * 采购人名称
     */
    private String cgrmc;

    /**
     * 需方联系人
     */
    private String xflxrxm;

    /**
     * 需方电话
     */
    private String xfdh;

    /**
     * 电商名称
     */
    private String ghsmc;

    /**
     * 订单金额
     */
    private BigDecimal ddze;

    /**
     * 订单提交时间
     */
    private String cjrq;

    /**
     * 订单状态
     */
    private String zt;

    /**
     * 支付方式
     */
    private String zffs;

    /**
     * 发票抬头
     */
    private String fptt;

    /**
     * 纳税人识别号
     */
    private String nsrsbh;

    /**
     * 是否需要安装服务
     */
    private String sfxyazfw;

    /**
     * 订单备注说明
     */
    private String beiz;

    /**
     * 收货时间
     */
    private String shsj;

    /**
     * 收货期限
     */
    private String shqx;

    /**
     * 发票内容
     */
    private String fpnr;

}
