package net.wlgzs.purchase.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

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
    @Id
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

    @Override
    public String toString() {
        return "Contract{" +
        "contractId=" + contractId +
        ", ddbh=" + ddbh +
        ", contractUrl=" + contractUrl +
        "}";
    }
}
