
package net.wlgzs.purchase.controller;



import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.entity.Order;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.Result;
import org.apache.ibatis.annotations.Param;
import org.codehaus.xfire.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.net.URL;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王言
 * @since 2019-09-28
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    IRedis iRedis;

    @ApiOperation("更新订单")
    @RequestMapping("/upDataOrder")
    @ResponseBody
    @Scheduled(cron = "*/6 * * * * ?")
    public Result upDataOrder() throws IOException {
        System.out.println("++++++++++"+iOrderService.updateOrderDate("","").toString());
        return null;
    }


    @ApiOperation("查询订单")
    @RequestMapping("/selectDataOrder")
    public ModelAndView selectDataOrder(@Param("kssi")String kssj, @Param("jssj")String jssj, @RequestParam(value = "pageNum", defaultValue = "1")int pageNum){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }


    @ApiOperation("确认订单")
    @RequestMapping("/ensureOrder")
    @ResponseBody
    public void ensureOrder(){

    }


    @ApiOperation("拒绝订单")
    @RequestMapping("/refuseOrder")
    @ResponseBody
    public void refuseOrder(){

    }


}