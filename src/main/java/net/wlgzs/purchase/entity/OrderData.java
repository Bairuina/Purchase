package net.wlgzs.purchase.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-04
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
    private BigInteger shsj;

    /**
     * 收货期限
     */
    private String shqx;

    /**
     * 快递公司
     */
    private String kdgs;

    /**
     * skbz 1-正常收款，2-未收款
     */
    private Integer skbz;

    /**
     * 发票内容
     */
    private String fpnr;

    /**
     * 发票开具时间
     */
    private BigInteger fpkjsj;

    /**
     * 收到发票时间
     */
    private BigInteger fpsdsj;

    /**
     * 收款时间
     */
    private BigInteger sksj;

    /**
     * 收款金额
     */
    private BigDecimal skje;

    /**
     * 验收标志 验收标志（1-自动验收，2-人工验收）
     */
    private Integer ysbz;

    /**
     * 验收时间
     */
    private BigInteger yssj;

    /**
     * 是否拆单 是否拆单（0-拆单 1-不拆单）
     */
    private Integer sfcd;

    /**
     * 分拆子订单编号  子订单编号，配对一个物流信息（sfcd为0的时候需要填写分拆子订单编号，不拆单的话子订单编号参数不用加）
     */
    private String fczddbh;

    private List<ProductList> productList;

    private List<AccessoryList> accessoryList;

    private List<ServiceList> serviceList;

    /**
     * 快递单号
     */
    private String kddh;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public BigInteger getShsj() {
        return shsj;
    }

    public void setShsj(BigInteger shsj) {
        this.shsj = shsj;
    }

    public String getShqx() {
        return shqx;
    }

    public void setShqx(String shqx) {
        this.shqx = shqx;
    }

    public String getKdgs() {
        return kdgs;
    }

    public void setKdgs(String kdgs) {
        this.kdgs = kdgs;
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

    public BigInteger getFpkjsj() {
        return fpkjsj;
    }

    public void setFpkjsj(BigInteger fpkjsj) {
        this.fpkjsj = fpkjsj;
    }

    public BigInteger getFpsdsj() {
        return fpsdsj;
    }

    public void setFpsdsj(BigInteger fpsdsj) {
        this.fpsdsj = fpsdsj;
    }

    public BigInteger getSksj() {
        return sksj;
    }

    public void setSksj(BigInteger sksj) {
        this.sksj = sksj;
    }

    public BigDecimal getSkje() {
        return skje;
    }

    public void setSkje(BigDecimal skje) {
        this.skje = skje;
    }

    public Integer getYsbz() {
        return ysbz;
    }

    public void setYsbz(Integer ysbz) {
        this.ysbz = ysbz;
    }

    public BigInteger getYssj() {
        return yssj;
    }

    public void setYssj(BigInteger yssj) {
        this.yssj = yssj;
    }

    public Integer getSfcd() {
        return sfcd;
    }

    public void setSfcd(Integer sfcd) {
        this.sfcd = sfcd;
    }

    public String getFczddbh() {
        return fczddbh;
    }

    public void setFczddbh(String fczddbh) {
        this.fczddbh = fczddbh;
    }

    public List<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductList> productList) {
        this.productList = productList;
    }

    public List<AccessoryList> getAccessoryList() {
        return accessoryList;
    }

    public void setAccessoryList(List<AccessoryList> accessoryList) {
        this.accessoryList = accessoryList;
    }

    public List<ServiceList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceList> serviceList) {
        this.serviceList = serviceList;
    }

    public String getKddh() {
        return kddh;
    }

    public void setKddh(String kddh) {
        this.kddh = kddh;
    }

    public BigInteger getKdsj() {
        return kdsj;
    }

    public void setKdsj(BigInteger kdsj) {
        this.kdsj = kdsj;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    /**
     * 快递时间
     */
    private BigInteger kdsj;

    /**
     * 快递描述
     */
    private String ms;





    @Override
    public String toString() {
        return "OrderData{" +
        "orderId=" + orderId +
        ", \ndeliverycity=" + deliverycity +
        ", \ndeliverycounty=" + deliverycounty +
        ", \nshdd=" + shdd +
        ", \nddbh=" + ddbh +
        ", \ncgrmc=" + cgrmc +
        ", \nxflxrxm=" + xflxrxm +
        ", \nxfdh=" + xfdh +
        ", \nghsmc=" + ghsmc +
        ", \nddze=" + ddze +
        ", \ncjrq=" + cjrq +
        ", \nzt=" + zt +
        ", \nzffs=" + zffs +
        ", \nfptt=" + fptt +
        ", \nnsrsbh=" + nsrsbh +
        ", \nsfxyazfw=" + sfxyazfw +
        ", \nbeiz=" + beiz +
        ", \nshsj=" + shsj +
        ", \nshqx=" + shqx +
        ", \nkdgs=" + kdgs +
        ", \nskbz=" + skbz +
        ", \nfpnr=" + fpnr +
        ", \nfpkjsj=" + fpkjsj +
        ", \nfpsdsj=" + fpsdsj +
        ", \nsksj=" + sksj +
        ", \nskje=" + skje +
        ", \nysbz=" + ysbz +
        ", \nyssj=" + yssj +
        ", \nsfcd=" + sfcd +
        ", \nfczddbh=" + fczddbh +
        ", \nproductList=" + productList +
        ", \naccessoryList=" + accessoryList +
        ", \nserviceList=" + serviceList +
        ", \nkddh=" + kddh +
        ", \nkdsj=" + kdsj +
        ", \nms=" + ms +
        "}";
    }
}
