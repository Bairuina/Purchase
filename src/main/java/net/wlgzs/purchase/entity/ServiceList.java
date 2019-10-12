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
@TableName("service_list")
public class ServiceList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单服务id
     */
    @TableId(value = "serviceList_id", type = IdType.AUTO)
    private Integer servicelistId;

    /**
     * 服务名称
     */
    private String fwMC;

    /**
     * 服务数量
     */
    @TableField("SL")
    private Integer sl;

    /**
     * 服务单价
     */
    @TableField("FWJG")
    private BigDecimal fwjg;

    /**
     * 订单id
     */
    private String ddbh;

    @TableField("XHBH")
    private String xhbh;

    @TableField("PPBH")
    private String ppbh;

    public Integer getServicelistId() {
        return servicelistId;
    }

    public void setServicelistId(Integer servicelistId) {
        this.servicelistId = servicelistId;
    }
    public String getFwMC() {
        return fwMC;
    }

    public void setFwMC(String fwMC) {
        this.fwMC = fwMC;
    }
    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }
    public BigDecimal getFwjg() {
        return fwjg;
    }

    public void setFwjg(BigDecimal fwjg) {
        this.fwjg = fwjg;
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
        return "ServiceList{" +
        "servicelistId=" + servicelistId +
        ", fwMC=" + fwMC +
        ", sl=" + sl +
        ", fwjg=" + fwjg +
        ", ddbh=" + ddbh +
        ", xhbh=" + xhbh +
        ", ppbh=" + ppbh +
        "}";
    }
}
