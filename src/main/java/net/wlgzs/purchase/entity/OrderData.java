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
 * @since 2019-10-03
 */
@TableName("order_data")
public class OrderData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 送货城市
     */
    private String deliverycity;

    /**
     * 送货区/县
     */
    private String deliverycounty;

    /**
     * 送货地址
     */
    private String shdd;

    /**
     * 订单编号
     */
    private String ddbh;

    /**
     * 采购人名称
     */
    private String cgrmc;

    /**
     * 需方联系人
     */
    private String xflxrxm;

    /**
     * 需方电话
     */
    private String xfdh;

    /**
     * 电商名称
     */
    private String ghsmc;

    /**
     * 订单金额
     */
    private BigDecimal ddze;

    /**
     * 订单提交时间
     */
    private String cjrq;

    /**
     * 订单状态
     */
    private String zt;

    /**
     * 支付方式
     */
    private String zffs;

    /**
     * 发票抬头
     */
    private String fptt;

    /**
     * 纳税人识别号
     */
    private String nsrsbh;

    /**
     * 是否需要安装服务
     */
    private String sfxyazfw;

    /**
     * 订单备注说明
     */
    private String beiz;

    /**
     * 收货时间
     */
    private String shsj;

    /**
     * 收货期限
     */
    private String shqx;

    private Integer skbz;

    /**
     * 发票内容
     */
    private String fpnr;

    private Integer fpkjsj;

    private Integer fpsdsj;

    private Integer sksj;

    private Integer skje;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getDeliverycity() {
        return deliverycity;
    }

    public void setDeliverycity(String deliverycity) {
        this.deliverycity = deliverycity;
    }
    public String getDeliverycounty() {
        return deliverycounty;
    }

    public void setDeliverycounty(String deliverycounty) {
        this.deliverycounty = deliverycounty;
    }
    public String getShdd() {
        return shdd;
    }

    public void setShdd(String shdd) {
        this.shdd = shdd;
    }
    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }
    public String getCgrmc() {
        return cgrmc;
    }

    public void setCgrmc(String cgrmc) {
        this.cgrmc = cgrmc;
    }
    public String getXflxrxm() {
        return xflxrxm;
    }

    public void setXflxrxm(String xflxrxm) {
        this.xflxrxm = xflxrxm;
    }
    public String getXfdh() {
        return xfdh;
    }

    public void setXfdh(String xfdh) {
        this.xfdh = xfdh;
    }
    public String getGhsmc() {
        return ghsmc;
    }

    public void setGhsmc(String ghsmc) {
        this.ghsmc = ghsmc;
    }
    public BigDecimal getDdze() {
        return ddze;
    }

    public void setDdze(BigDecimal ddze) {
        this.ddze = ddze;
    }
    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
    }
    public String getFptt() {
        return fptt;
    }

    public void setFptt(String fptt) {
        this.fptt = fptt;
    }
    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }
    public String getSfxyazfw() {
        return sfxyazfw;
    }

    public void setSfxyazfw(String sfxyazfw) {
        this.sfxyazfw = sfxyazfw;
    }
    public String getBeiz() {
        return beiz;
    }

    public void setBeiz(String beiz) {
        this.beiz = beiz;
    }
    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }
    public String getShqx() {
        return shqx;
    }

    public void setShqx(String shqx) {
        this.shqx = shqx;
    }
    public Integer getSkbz() {
        return skbz;
    }

    public void setSkbz(Integer skbz) {
        this.skbz = skbz;
    }
    public String getFpnr() {
        return fpnr;
    }

    public void setFpnr(String fpnr) {
        this.fpnr = fpnr;
    }
    public Integer getFpkjsj() {
        return fpkjsj;
    }

    public void setFpkjsj(Integer fpkjsj) {
        this.fpkjsj = fpkjsj;
    }
    public Integer getFpsdsj() {
        return fpsdsj;
    }

    public void setFpsdsj(Integer fpsdsj) {
        this.fpsdsj = fpsdsj;
    }
    public Integer getSksj() {
        return sksj;
    }

    public void setSksj(Integer sksj) {
        this.sksj = sksj;
    }
    public Integer getSkje() {
        return skje;
    }

    public void setSkje(Integer skje) {
        this.skje = skje;
    }

    @Override
    public String toString() {
        return "OrderData{" +
        "orderId=" + orderId +
        ", deliverycity=" + deliverycity +
        ", deliverycounty=" + deliverycounty +
        ", shdd=" + shdd +
        ", ddbh=" + ddbh +
        ", cgrmc=" + cgrmc +
        ", xflxrxm=" + xflxrxm +
        ", xfdh=" + xfdh +
        ", ghsmc=" + ghsmc +
        ", ddze=" + ddze +
        ", cjrq=" + cjrq +
        ", zt=" + zt +
        ", zffs=" + zffs +
        ", fptt=" + fptt +
        ", nsrsbh=" + nsrsbh +
        ", sfxyazfw=" + sfxyazfw +
        ", beiz=" + beiz +
        ", shsj=" + shsj +
        ", shqx=" + shqx +
        ", skbz=" + skbz +
        ", fpnr=" + fpnr +
        ", fpkjsj=" + fpkjsj +
        ", fpsdsj=" + fpsdsj +
        ", sksj=" + sksj +
        ", skje=" + skje +
        "}";
    }
}
