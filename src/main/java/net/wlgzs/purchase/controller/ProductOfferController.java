package net.wlgzs.purchase.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.util.Result;
import org.springframework.web.bind.annotation.*;

import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
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
    public Result productOffer(String xhbh, BigDecimal price, String text){
        return iProductOfferService.productOffer(xhbh,price,text);
    }

    @RequestMapping(value = "/offer",method = RequestMethod.DELETE)
    @ApiOperation(value = "撤销报价根据xhbh",httpMethod = "DELETE")
    @ApiImplicitParam(name = "xhbh",value = "报价商品编号")
    public Result delProductOfferByXhbh(@RequestParam(value = "xhbh",required = true) String xhbh){
        return iProductOfferService.delProductOfferXhbh(xhbh);
    }

    @RequestMapping(value = "/offer/all",method = RequestMethod.GET)
    @ApiOperation(value = "查看已报价商品",httpMethod = "GET")
    public ModelAndView findAllOffer(){
        return iProductOfferService.findAllOffer();
    }

    @RequestMapping(value = "/offer/{xhbh}/{zt}/{text}",method = RequestMethod.GET)
    @ApiOperation(value = "修改报价过商品的状态",httpMethod = "GET")
    public Result changeProductOfferZt(@PathVariable("xhbh") String xhbh,@PathVariable("zt")String zt,@PathVariable("text")String text){
        return iProductOfferService.changeProductOfferZt(xhbh,zt,text);
    }
}
