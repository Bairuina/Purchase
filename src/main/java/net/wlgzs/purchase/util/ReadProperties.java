package net.wlgzs.purchase.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author HYStar
 * @Date 2019/10/4 9:12
 */
@Component
@Data
@ConfigurationProperties(prefix = "my")
@PropertySource("classpath:my.properties")
public class ReadProperties {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 地址
     */
    private String url;

    /**
     * 获取商品以及参数信息
     */
    private String findSprkandParam;

    /**
     * 获取品目配件信息
     */
    private String findPjByPmbh;

    /**
     * 获取品目增值服务信息
     */
    private String findFwByPmbh;

    /**
     * 商品报价
     */
    private String execute;

    /**
     * 商品配件报价
     */
    private String quotedpricePjByPjbh;

    /**
     * 增值服务报价
     */
    private String quotedpriceFwByFwbh;

    /**
     * 查询订单信息
     */
    private String findOrder;

    /**
     * 确认订单信息
     */
    private String execGysOrderQr;

    /**
     * 物流信息
     */
    private String exeLogistics;

    /**
     * 订单签收信息
     */
    private String execQssj;

    /**
     * 订单发票开具信息
     */
    private String execFpkjsjByOrder;

    /**
     * 收到订单发票时间
     */
    private String execfpsdsjByorder;

    /**
     * 订单合同获取
     */
    private String findOrderHt;

    /**
     * 订单收款信息
     */
    private String execSkqk;

    /**
     * 取消订单信息
     */
    private String execDsZfdd;

    /**
     * 获取验收时间及验收方式
     */
    private String findYsByOrder;

    /**
     * 商品上/下架
     */
    private String execSpDown;

    /**
     * 推送商品唯一标识码
     */
    private String execTsWybs;

    /**
     * 查询订单信息
     */
    private String findDdxxByddbh;

    /**
     * 获取商品型号信息以及相关报价
     */
    private String findSpByXhbh;

    /**
     * 获取商品报价
     */
    private String findSpSfbj;

    /**
     * 撤消商品报价
     */
    private String qxShByXhbh;

}
