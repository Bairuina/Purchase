package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-12
 */
@TableName("product_list")
public class ProductList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单商品id
     */
    @TableId(value = "productList_id", type = IdType.AUTO)
    private Integer productlistId;

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

    /**
     * 商品唯一标识
     */
    private String wybs;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ServiceList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    public List<AccessoryList> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<AccessoryList> accessoryList) {
        this.accessoryList = accessoryList;
    }

    private List<ServiceList> serviceList;

    private List<AccessoryList> accessoryList;

    /**
     * 图片
     */
    private String imgpath;

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
    public String getWybs() {
        return wybs;
    }

    public void setWybs(String wybs) {
        this.wybs = wybs;
    }
    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
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
        ", wybs=" + wybs +
        ", serviceList=" + serviceList +
        ", accessoryList=" + accessoryList +
        ", imgpath=" + imgpath +
        "}";
    }
}
