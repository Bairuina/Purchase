package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@RestController
@RequestMapping("/productList")
public class ProductListController extends BaseController {


    /**
     * 推送商品唯一标识
     * @param wybs 商品唯一标识
     * @param xhbh 商品编号
     * @param ddbh 订单编号
     * @param myFileName 图片流
     */
    @RequestMapping(value = "/product",method = RequestMethod.PUT)
    @ApiOperation(value = "返回商品唯一标识",httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wybs",value = "商品唯一标识"),
            @ApiImplicitParam(name = "xhbh",value = "商品编号"),
            @ApiImplicitParam(name = "ddbh",value = "订单编号"),
            @ApiImplicitParam(name = "myFileName",value = "图片")
    })
    public Result pushXhbhDdbh(@RequestParam("wybs") String wybs,
                               @RequestParam("xhbh") String xhbh,
                               @RequestParam("ddbh") String ddbh,
                               @RequestParam("imgpath") MultipartFile myFileName){
        return iProductListService.upProductListJpg(wybs, xhbh, ddbh,myFileName);
    }

}
