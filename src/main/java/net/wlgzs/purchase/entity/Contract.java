package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-03
 */
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 合同id
     */
    @TableId("contract_id")
    private Integer contractId;

    /**
     * 订单编号
     */
    private String ddbh;

    /**
     * 合同的地址（url）
     */
    @TableField("contract_url")
    private String contractUrl;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }
    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    @Override
    public String toString() {
        return "Contract{" +
        "contractId=" + contractId +
        ", ddbh=" + ddbh +
        ", contractUrl=" + contractUrl +
        "}";
    }
}
