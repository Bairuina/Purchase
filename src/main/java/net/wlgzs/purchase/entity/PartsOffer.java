package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@TableName("parts_offer")
public class PartsOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配件报价Id
     */
    @TableId(value = "parts_offer_id", type = IdType.AUTO)
    private Integer partsOfferId;

    /**
     * 配件报价价格
     */
    @TableField("offer_price")
    private BigDecimal offerPrice;

    /**
     * 配件编号
     */
    private String pjbh;

    /**
     * 商品编号
     */
    private String xhbh;

    public Integer getPartsOfferId() {
        return partsOfferId;
    }

    public void setPartsOfferId(Integer partsOfferId) {
        this.partsOfferId = partsOfferId;
    }
    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }
    public String getPjbh() {
        return pjbh;
    }

    public void setPjbh(String pjbh) {
        this.pjbh = pjbh;
    }
    public String getXhbh() {
        return xhbh;
    }

    public void setXhbh(String xhbh) {
        this.xhbh = xhbh;
    }

    @Override
    public String toString() {
        return "PartsOffer{" +
        "partsOfferId=" + partsOfferId +
        ", offerPrice=" + offerPrice +
        ", pjbh=" + pjbh +
        ", xhbh=" + xhbh +
        "}";
    }
}
