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
 * @since 2019-10-12
 */
@TableName("accessory_list")
public class AccessoryList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单配件id
     */
    @TableId(value = "accessoryList_id", type = IdType.AUTO)
    private Integer accessorylistId;

    /**
     * 配件名称
     */
    @TableField("PJMC")
    private String pjmc;

    /**
     * 配件数量
     */
    @TableField("SL")
    private Integer sl;

    /**
     * 配件单价
     */
    @TableField("PJJG")
    private BigDecimal pjjg;

    /**
     * 订单id
     */
    private String ddbh;

    @TableField("XHBH")
    private String xhbh;

    @TableField("PPBH")
    private String ppbh;

    public Integer getAccessorylistId() {
        return accessorylistId;
    }

    public void setAccessorylistId(Integer accessorylistId) {
        this.accessorylistId = accessorylistId;
    }
    public String getPjmc() {
        return pjmc;
    }

    public void setPjmc(String pjmc) {
        this.pjmc = pjmc;
    }
    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }
    public BigDecimal getPjjg() {
        return pjjg;
    }

    public void setPjjg(BigDecimal pjjg) {
        this.pjjg = pjjg;
    }
    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }
    public String getXhbh() {
        return xhbh;
    }

    public void setXhbh(String xhbh) {
        this.xhbh = xhbh;
    }
    public String getPpbh() {
        return ppbh;
    }

    public void setPpbh(String ppbh) {
        this.ppbh = ppbh;
    }

    @Override
    public String toString() {
        return "AccessoryList{" +
        "accessorylistId=" + accessorylistId +
        ", pjmc=" + pjmc +
        ", sl=" + sl +
        ", pjjg=" + pjjg +
        ", ddbh=" + ddbh +
        ", xhbh=" + xhbh +
        ", ppbh=" + ppbh +
        "}";
    }
}
