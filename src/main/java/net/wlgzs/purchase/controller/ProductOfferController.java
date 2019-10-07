package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@RestController
@RequestMapping("/productoffer")
public class ProductOfferController extends BaseController {

    /**
     * 报价商品
     * @param xhbh 商品编号
     * @param price 报价价格
     * @param text 改价原因（可无）
     */
    @RequestMapping(value = "/offer",method = RequestMethod.PUT)
    @ApiOperation(value = "商品报价",httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xhbh",value = "商品编号"),
            @ApiImplicitParam(name = "price",value = "商品价格"),
            @ApiImplicitParam(name = "text",value = "备注")
    })
    public ModelAndView productOffer(String xhbh, BigDecimal price, String text){
        return iProductOfferService.productOffer(xhbh,price,text);
    }

}
