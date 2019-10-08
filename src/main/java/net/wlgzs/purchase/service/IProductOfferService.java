package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.ProductOffer;
import com.baomidou.mybatisplus.extension.service.IService;
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
    public ModelAndView productOffer(String xhbh, BigDecimal price, String text);
}
