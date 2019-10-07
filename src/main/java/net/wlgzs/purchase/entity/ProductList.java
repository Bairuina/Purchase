package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-04
 */
@TableName("product_list")
public class ProductList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单商品id
     */
    @TableId("productList_id")
    private int productlistId;

    /**
     * 型号编号
     */
    @TableField("XHBH")
    private String xhbh;

    /**
     * 型号名称
     */
    @TableField("XHMC")
    private String xhmc;

    /**
     * 品牌编号
     */
    @TableField("PPBH")
    private String ppbh;

    /**
     * 品牌名称
     */
    @TableField("PPMC")
    private String ppmc;

    /**
     * 购买数量
     */
    @TableField("SL")
    private Integer sl;

    /**
     * 商品的实际价格
     */
    @TableField("SJJG")
    private BigDecimal sjjg;

    /**
     * 小计价格
     */
    @TableField("XJJG")
    private BigDecimal xjjg;

    /**
     * 订单id
     */
    private String ddbh;

    public Integer getProductlistId() {
        return productlistId;
    }

    public void setProductlistId(Integer productlistId) {
        this.productlistId = productlistId;
    }
    public String getXhbh() {
        return xhbh;
    }

    public void setXhbh(String xhbh) {
        this.xhbh = xhbh;
    }
    public String getXhmc() {
        return xhmc;
    }

    public void setXhmc(String xhmc) {
        this.xhmc = xhmc;
    }
    public String getPpbh() {
        return ppbh;
    }

    public void setPpbh(String ppbh) {
        this.ppbh = ppbh;
    }
    public String getPpmc() {
        return ppmc;
    }

    public void setPpmc(String ppmc) {
        this.ppmc = ppmc;
    }
    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }
    public BigDecimal getSjjg() {
        return sjjg;
    }

    public void setSjjg(BigDecimal sjjg) {
        this.sjjg = sjjg;
    }
    public BigDecimal getXjjg() {
        return xjjg;
    }

    public void setXjjg(BigDecimal xjjg) {
        this.xjjg = xjjg;
    }
    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    @Override
    public String toString() {
        return "ProductList{" +
        "productlistId=" + productlistId +
        ", xhbh=" + xhbh +
        ", xhmc=" + xhmc +
        ", ppbh=" + ppbh +
        ", ppmc=" + ppmc +
        ", sl=" + sl +
        ", sjjg=" + sjjg +
        ", xjjg=" + xjjg +
        ", ddbh=" + ddbh +
        "}";
    }
}
