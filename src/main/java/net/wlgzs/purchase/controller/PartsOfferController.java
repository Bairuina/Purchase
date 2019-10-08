package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.util.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@RestController
@RequestMapping("/parts-offer")
public class PartsOfferController extends BaseController {
    /**
     * 配件报价并存入本地库
     * @param pjbh 配件编号
     * @param xhbh 商品编号
     * @param price 配件价格
     * @param text 备注
     *
     */
    @ApiOperation(value = "配件报价(单个配件)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pjbh",value = "配件编号"),
            @ApiImplicitParam(name = "xhbh",value = "商品名称"),
            @ApiImplicitParam(name = "price",value = "配件价格"),
            @ApiImplicitParam(name = "text",value = "备注")
    })
    @RequestMapping(value = "/offer",method = RequestMethod.PUT)
    public Result PartsOffer(String pjbh,String xhbh,int price,String text){
        return iPartsOfferService.offerPart(pjbh, xhbh, price, text);
    }


}
