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
    public Result productOffer(String xhbh, BigDecimal price, String text);

    //撤销报价
    public Result delProductOfferXhbh(String xhbh);

    //查看所有已报价 按照 报价状态区分
    public ModelAndView findAllOffer(int nowPage1,int nowPage2,int nowPage3);

    //修改已报价商品状态
    public Result changeProductOfferZt(String xhbh,String zt,String text);
}
