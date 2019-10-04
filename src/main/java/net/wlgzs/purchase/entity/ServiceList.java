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
@TableName("service_list")
public class ServiceList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单服务id
     */
    @TableId("serviceList_id")
    private int servicelistId;

    /**
     * 服务名称
     */
    @TableField("fwMC")
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
    @TableField("ddbh")
    private String ddbh;

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

    @Override
    public String toString() {
        return "ServiceList{" +
        "servicelistId=" + servicelistId +
        ", fwMC=" + fwMC +
        ", sl=" + sl +
        ", fwjg=" + fwjg +
        ", ddbh=" + ddbh +
        "}";
    }
}
