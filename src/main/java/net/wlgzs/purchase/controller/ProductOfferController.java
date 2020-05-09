package net.wlgzs.purchase.controller;



import com.Enxi;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
public class    ProductOfferController extends BaseController {


    /**
     * 报价商品
     * @param xhbh 商品编号
     * @param price 报价价格
     * @param text 改价原因（可无）
     * @param fwcn 服务承诺
     * @param productlink 商品在自有网站的的绝对地址
     */
    @RequestMapping(value = "/offer",method = RequestMethod.PUT)
    @ApiOperation(value = "商品报价",httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xhbh",value = "商品编号"),
            @ApiImplicitParam(name = "price",value = "商品价格"),
            @ApiImplicitParam(name = "text",value = "备注"),
            @ApiImplicitParam(name = "fwcn",value = "服务承诺"),
            @ApiImplicitParam(name = "productlink",value = "商品在自有网站的的绝对地址"),
            @ApiImplicitParam(name = "area",value = "地区")
    })
    public Result productOffer(@RequestParam(name = "xhbh") String xhbh,
                               @RequestParam(name = "price") BigDecimal price,
                               @RequestParam(name = "text") String text,
                               @RequestParam(name = "fwcn") String fwcn,
                               @RequestParam(name = "productlink") String productlink,
                               @RequestParam(name = "area") String area){
        return iProductOfferService.productOffer(xhbh,price,text,fwcn,productlink,area);
    }

    @RequestMapping(value = "/offer",method = RequestMethod.DELETE)
    @ApiOperation(value = "撤销报价根据xhbh",httpMethod = "DELETE")
    @ApiImplicitParam(name = "xhbh",value = "报价商品编号")
    public Result delProductOfferByXhbh(@RequestParam(value = "xhbh",required = true) String xhbh){
        return iProductOfferService.delProductOfferXhbh(xhbh);
    }


    @RequestMapping(value = "/offer/{xhbh}/{zt}/{text}",method = RequestMethod.GET)
    @ApiOperation(value = "修改报价过商品的状态",httpMethod = "GET")
    public Result changeProductOfferZt(@PathVariable("xhbh") String xhbh,@PathVariable("zt")String zt,@PathVariable("text")String text){
        return iProductOfferService.changeProductOfferZt(xhbh,zt,text);
    }

    /**
     *
     * @param lbmc
     * @return
     */
    @RequestMapping(value = "/offer/lbmc",method = RequestMethod.GET)
    @ApiOperation(value = "选择完类别，显示全部品目")
    @ApiImplicitParam(name = "lbmc",value = "类别名称")
    public Result SelectLbmc(@RequestParam(value = "lbmc") String lbmc){
        return iProductOfferService.SelectLbmc(lbmc);
    }

    @RequestMapping(value = "/offer/pmmc",method = RequestMethod.GET)
    @ApiOperation(value = "选择完品目，获取其下的品牌名称")
    @ApiImplicitParam(name = "pmmc",value = "选择的品目名称")
    public Result SelectPmmc(@RequestParam(value = "pmmc")String pmmc){
        return iProductOfferService.SelectPmmc(pmmc);
    }

    /**
     * 管理报价
     * @param zt
     * @param lbmc
     * @param pmmc
     * @param ppmc
     * @param nr
     * @param nowpage
     * @return
     */
    @RequestMapping(value = "/offer/select/{zt}/{lbmc}/{pmmc}/{ppmc}/{nowpage}",method = RequestMethod.GET)
    @ApiParam(value = "内容")
    @ApiOperation(value = "管理报价",httpMethod = "GET")
    public ModelAndView findZt(@PathVariable("zt")String zt,
                               @PathVariable("lbmc")String lbmc,
                               @PathVariable("pmmc")String pmmc,
                               @PathVariable("ppmc")String ppmc,
                               @PathVariable("nowpage")String nowpage,
                               @RequestParam(name = "nr",defaultValue = "0")String nr){
        return iProductOfferService.findZt(zt, lbmc, pmmc, ppmc, nr, nowpage);
    }
}
