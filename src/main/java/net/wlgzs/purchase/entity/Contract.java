package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-03
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
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
    @TableField("ddbh")
    private String ddbh;

    /**
     * 合同的地址（url）
     */
    @TableField("contract_url")
    private String contractUrl;

    public Contract(String ddbh, String contractUrl) {
        this.ddbh = ddbh;
        this.contractUrl = contractUrl;
    }

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