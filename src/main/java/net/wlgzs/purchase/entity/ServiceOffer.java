package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@TableName("service_offer")
public class ServiceOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 增值服务报价Id
     */
    @TableId(value = "service_offer_id", type = IdType.AUTO)
    private Integer serviceOfferId;

    /**
     * 增值服务编号
     */
    private String fubh;

    /**
     * 增值服务价格
     */
    private BigDecimal fujg;

    /**
     * 商品Id
     */
    private String xhbh;

    public Integer getServiceOfferId() {
        return serviceOfferId;
    }

    public void setServiceOfferId(Integer serviceOfferId) {
        this.serviceOfferId = serviceOfferId;
    }
    public String getFubh() {
        return fubh;
    }

    public void setFubh(String fubh) {
        this.fubh = fubh;
    }
    public BigDecimal getFujg() {
        return fujg;
    }

    public void setFujg(BigDecimal fujg) {
        this.fujg = fujg;
    }
    public String getXhbh() {
        return xhbh;
    }

    public void setXhbh(String xhbh) {
        this.xhbh = xhbh;
    }

    @Override
    public String toString() {
        return "ServiceOffer{" +
        "serviceOfferId=" + serviceOfferId +
        ", fubh=" + fubh +
        ", fujg=" + fujg +
        ", xhbh=" + xhbh +
        "}";
    }
}
