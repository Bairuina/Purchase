package net.wlgzs.purchase.controller;


import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigInteger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王言
 * @since 2019-10-03
 */
@RestController
@RequestMapping("/order-data")
public class OrderDataController extends BaseController {
    @Autowired
    IRedis iRedis;

    @Autowired
    ReadProperties readProperties;
    @ApiOperation("更新订单")
    @RequestMapping("/upDataOrder")
    @ResponseBody
    @Scheduled(cron = "0 0 0,3,6,9,12,15,18,21 * * ?")
    public Result upDataOrder() throws IOException {
        return  iOrderService.updateOrderDate(1);
    }


    @ApiOperation("显示所有订单")
    @RequestMapping("/selectDataOrder")
    public ModelAndView selectDataOrder(@RequestParam(value = "pageSize", defaultValue = "15")Integer pageSize,@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("orderDara",iOrderService.selectAllOrder(pageSize,pageNum));
//        modelAndView.addObject("upDataTime",iRedis.get("upDataTime"));
        System.out.println(modelAndView);
        return modelAndView;
    }

    //查询不同状态的订单  2-供应商待确认3-待验收 4-订单已取消 5-验收通过
    @ApiOperation("显示取消或未确认订单")
    @RequestMapping("/selectStatusDataOrder")
    public ModelAndView selectStatusDataOrder(@Param("status")String status,@RequestParam(value = "pageSize", defaultValue = "15")Integer pageSize,@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("订单列表页");
        modelAndView.addObject("orderDara",iOrderService.selectStatusDataOrder(pageSize,pageNum,status));
        modelAndView.addObject("upDataTime",iRedis.get("upDataTime"));
        return modelAndView;
    }

    @ApiOperation(" 确认或取消订单")
    @RequestMapping("/ensureORefuseOrder")
    @ResponseBody
    public Result ensureORefuseOrder(@Param("ddbh") String ddbh, @Param("qrzt")int qrzt){
        return iOrderService.ensureORefuseOrder(ddbh,qrzt);
    }


    @ApiOperation("查看单个订单详情")
    @RequestMapping("/selectOneOrder")
    public ModelAndView checkDetailedOrder(@Param("ddbh") String ddbh){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("订单详情页");
        modelAndView.addObject("allData",iOrderService.selectOneOrder(ddbh));
        return modelAndView;
    }


    @ApiOperation("订单签收时间信息推送")
    @ResponseBody
    @RequestMapping("/ensureOrderTimeSubmit")
    public Result ensureOrderTimeSubmit(@Param("ddbh") String ddbh,@Param("sfcd")int sfcd,@Param("fczddbh")String fczddbh){
        return iOrderService.ensureOrderTimeSubmit(ddbh,sfcd,fczddbh,new BigInteger(new DateTime().toString("yyyyMMddHHmmss")));
    }


    @ApiOperation("查看采购单位对当前订单的验收情况")
    @ResponseBody
    @RequestMapping("/checkOrderStatus")
    public Result checkOrderStatus(@Param("ddbh")String ddbh){
        return iOrderService.checkOrderStatus(ddbh);
    }


    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @RequestMapping("/invoiceStaTimeSubmit")
    public Result invoiceStaTimeSubmit(@Param("ddbh")String ddbh){
        return iOrderService.invoiceStaTimeSubmit(ddbh,new BigInteger(new DateTime().toString("yyyyMMddHHmmss")));
    }

    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @RequestMapping("/invoiceEndTimeSubmit")
    public Result invoiceEndTimeSubmit(@Param("ddbh")String ddbh){
        return iOrderService.invoiceEndTimeSubmit(ddbh,new BigInteger(new DateTime().toString("yyyyMMddHHmmss")));
    }

    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @RequestMapping("/ensureOrderArrive")
    public Result ensureOrderArrive(@Param("ddbh") String ddbh,@Param("sfcd")int sfcd,@Param("fczddbh")String fczddbh,@Param("kdgs")String kdgs,@Param("kddh")String kddh,@Param("ms")String ms,@Param("kdsj")String kdsj){
        return iOrderService.ensureOrderArrive(ddbh,sfcd,fczddbh,kdgs,kddh,ms,new BigInteger(kdsj));
    }



}
