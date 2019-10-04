package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-03
 */
@RestController
@RequestMapping("/order-data")
public class OrderDataController extends BaseController {
    @Autowired
    IRedis iRedis;

    @ApiOperation("更新订单")
    @RequestMapping("/upDataOrder")
    @ResponseBody
//    @Scheduled(cron = "0 0 0,3,6,9,12,15,18,21 * * ?")
    public Result upDataOrder(@Param("username")String username,@Param("pwd")String pwd) throws IOException {
        return  iOrderService.updateOrderDate(username,pwd,1);
    }


    @ApiOperation("显示所有订单")
    @RequestMapping("/selectDataOrder")
    @ResponseBody
    public ModelAndView selectDataOrder(@RequestParam(value = "pageNum", defaultValue = "1")int pageNum){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("");
        iOrderService.selectAllOrder(pageNum);
        modelAndView.addObject("orderDara",iOrderService.selectAllOrder(pageNum));
        return modelAndView;
    }


    @ApiOperation("确认订单")
    @RequestMapping("/ensureORefuseOrder")
    @ResponseBody
    public Result ensureORefuseOrder(@Param("ddbh") String ddbh, @Param("username")String username, @Param("pwd")String pwd, @Param("qrzt")int qrzt){
        return iOrderService.ensureORefuseOrder(ddbh,username,pwd,qrzt);
    }


    @ApiOperation("查看单个订单详情")
    @ResponseBody
    @RequestMapping("/selectOneOrder")
    public Result checkDetailedOrder(@Param("ddbh") String ddbh){
        return iOrderService.selectOneOrder(ddbh);
    }


    @ApiOperation("订单签收时间信息推送")
    @ResponseBody
    @RequestMapping("/ensureOrderTimeSubmit")
    public Result ensureOrderTimeSubmit(@Param("ddbh") String ddbh,@Param("username") String username,@Param("")String pwd,@Param("")int sfcd,String fczddbh,String shsj){
        return iOrderService.ensureOrderTimeSubmit(ddbh,username,pwd,sfcd,fczddbh,shsj);
    }


}
