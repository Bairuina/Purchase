package net.wlgzs.purchase.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.WebSocketServer;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

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
@Api("订单模块")
@RestController
@RequestMapping("/order-data")
public class OrderDataController extends BaseController {
    @Autowired
    IRedis iRedis;

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    ReadProperties readProperties;

    @ApiOperation("更新订单")
    @GetMapping("/upDataOrder")
    @ResponseBody
    @Scheduled(cron = "0 0 0,3,6,9,12,15,18,21 * * ?")
    public Result upDataOrder() throws IOException {
        Result result=iOrderService.updateOrderDate(1);
        return  result;
    }


    @ApiOperation("显示所有订单")
    @GetMapping
    public ModelAndView selectDataOrder(@RequestParam(value = "pageSize", defaultValue = "15")Integer pageSize,@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("orderDara",iOrderService.selectAllOrder(pageSize,pageNum));
        modelAndView.addObject("upDataTime",iRedis.get("upDataTime"));
        System.out.println(modelAndView);
        return modelAndView;
    }

    //查询不同状态的订单  2-供应商待确认3-待验收 4-订单已取消 5-验收通过
    @ApiOperation("显示取消或未确认订单")
    @GetMapping("/selectStatusDataOrder")
    public ModelAndView selectStatusDataOrder(@Param("status")String status,@RequestParam(value = "pageSize", defaultValue = "15")Integer pageSize,@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("订单列表页");
        modelAndView.addObject("orderDara",iOrderService.selectStatusDataOrder(pageSize,pageNum,status));
        modelAndView.addObject("upDataTime",iRedis.get("upDataTime"));
        return modelAndView;
    }

    @ApiOperation("确认或取消订单")
    @PutMapping("/ensureORefuseOrder")
    @ResponseBody
    public Result ensureORefuseOrder(@Param("ddbh") String ddbh, @Param("qrzt")int qrzt){
        return iOrderService.ensureORefuseOrder(ddbh,qrzt);
    }


    //result.data
    @ApiOperation("查看单个订单详情")
    @GetMapping("/selectOneOrder")
    public ModelAndView checkDetailedOrder(@Param("ddbh") String ddbh){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orderData");
        modelAndView.addObject("allData",iOrderService.selectOneOrder(ddbh));
        System.out.println(modelAndView);
        return modelAndView;
    }


    @ApiOperation("订单签收时间信息推送")
    @ResponseBody
    @PutMapping("/ensureOrderTimeSubmit")
    public Result ensureOrderTimeSubmit(@Param("ddbh") String ddbh,@Param("sfcd")int sfcd,@Param("fczddbh")String fczddbh){
        return iOrderService.ensureOrderTimeSubmit(ddbh,sfcd,fczddbh,new BigInteger(new DateTime().toString("yyyyMMddHHmmss")));
    }


    @ApiOperation("查看采购单位对当前订单的验收情况")
    @ResponseBody
    @PutMapping("/checkOrderStatus")
    public Result checkOrderStatus(@Param("ddbh")String ddbh){
        return iOrderService.checkOrderStatus(ddbh);
    }


    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @PutMapping("/invoiceStaTimeSubmit")
    public Result invoiceStaTimeSubmit(@Param("ddbh")String ddbh){
        return iOrderService.invoiceStaTimeSubmit(ddbh,new BigInteger(new DateTime().toString("yyyyMMddHHmmss")));
    }

    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @PutMapping("/invoiceEndTimeSubmit")
    public Result invoiceEndTimeSubmit(@Param("ddbh")String ddbh){
        return iOrderService.invoiceEndTimeSubmit(ddbh,new BigInteger(new DateTime().toString("yyyyMMddHHmmss")));
    }

    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @PutMapping("/ensureOrderArrive")
    public Result ensureOrderArrive(@Param("ddbh") String ddbh,@Param("sfcd")int sfcd,@Param("fczddbh")String fczddbh,@Param("kdgs")String kdgs,@Param("kddh")String kddh,@Param("ms")String ms,@Param("kdsj")String kdsj){
        return iOrderService.ensureOrderArrive(ddbh,sfcd,fczddbh,kdgs,kddh,ms,new BigInteger(kdsj));
    }

//    @ApiOperation("")
//    @GetMapping
//    public ModelAndView selectOrderByData(@Param("data")String data){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("orderListByData","");
//        iOrderService.selectOrderListByData(data);
//        modelAndView.setViewName("");
//        return modelAndView;
//    }


}
