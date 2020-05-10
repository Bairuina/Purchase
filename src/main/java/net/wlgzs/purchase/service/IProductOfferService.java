package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.ProductOffer;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
public interface IProductOfferService extends IService<ProductOffer> {
    //报价
    public Result productOffer(String xhbh, BigDecimal price, String text,String fwcn,String productlink,String zyjg,String area);

    //撤销报价
    public Result delProductOfferXhbh(String xhbh);


    //修改已报价商品状态
    public Result changeProductOfferZt(String xhbh,String zt,String text);

    /**
     * 根据第一个类别返回全部品目
     *
     */
    public Result SelectLbmc(String lbmc);

    public Result SelectPmmc(String pmmc);

    public ModelAndView findZt(String zt,String lbmc,String pmmc,String ppmc,String nr,String nowpage);
}
