package net.wlgzs.purchase.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.WebSocketServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王言
 * @since 2019-10-01
 */
@Api("订单模块")
@RestController
@Slf4j
@RequestMapping("/order-data")
public class OrderDataController extends BaseController {
    @Autowired
    IRedis iRedis;

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    ReadProperties readProperties;

    @ApiOperation("更新订单")
    @PostMapping("/upDataOrder")
    @ResponseBody
//    @Scheduled(cron = "0 0 0,3,6,9,12,15,18,21 * * ?")
    public Result upDataOrder(@Param("kssjData")String kssjData) throws IOException {
        log.info(kssjData);
        kssjData=kssjData.replace("-","").replace(" ","").replace(":","");
        Result result=iOrderService.updateOrderDate(1,kssjData);
        return  result;
    }


    //查询不同状态的订单  2-供应商待确认3-待验收 4-订单已取消 5-验收通过
    @ApiOperation("显示取消或未确认订单")
    @GetMapping("/selectStatusDataOrder")
    public ModelAndView selectStatusDataOrder(@Param("status")String status,@RequestParam(value = "pageSize", defaultValue = "15")Integer pageSize,@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("订单列表页");
        modelAndView.addObject("orderDara",iOrderService.selectStatusDataOrder(pageSize,pageNum,status));
        return modelAndView;
    }

    @ApiOperation("确认或取消订单")
    @PostMapping("/ensureORefuseOrder")
    @ResponseBody
    public Result ensureORefuseOrder(@Param("ddbh") String ddbh, @Param("qrzt")Integer qrzt){
        log.info(ddbh+"===="+qrzt);
        return iOrderService.ensureORefuseOrder(ddbh,qrzt);
    }

    @ApiOperation("确认或取消订单(已经确定的订单)")
    @PostMapping("/deletEnsureOrder")
    @ResponseBody
    public Result deletEnsureOrder(@Param("ddbh") String ddbh, @Param("qxyy")String qxyy){
        log.info(ddbh+"===="+qxyy);
        return iOrderService.deletEnsureOrder(ddbh,qxyy);
    }


    //result.data
    @ApiOperation("查看单个订单详情")
    @GetMapping("/selectOneOrder")
    public ModelAndView checkDetailedOrder(@Param("ddbh") String ddbh){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orderDataTwo");
        modelAndView.addObject("allData",iOrderService.selectOneOrder(ddbh));
        log.info(modelAndView+"");
        return modelAndView;
    }

    @ApiOperation("电商已经收到采购单位的付款,将收款标志、收款金额、收款时间提交")
    @PostMapping("/selectDataNumberSubmit")
    @ResponseBody
    public Result getMoneyDataSubmit(@Param("ddbh") String ddbh, @Param("skbz")Integer skbz, @Param("skje") BigDecimal skje, @Param("sksj")BigInteger sksj){
        log.info(ddbh+"=="+skbz+"=="+skje+"=="+sksj);
        return iOrderService.getMoneyDataSubmit(ddbh,skbz,skje,sksj);
    }


    @ApiOperation("订单签收时间信息推送")
    @ResponseBody
    @PostMapping("/ensureOrderTimeSubmit")
    public Result ensureOrderTimeSubmit(@Param("ddbh") String ddbh){
        int sfcd=1;
        String fczddbh="";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String sj=simpleDateFormat.format(Calendar.getInstance().getTime());
        System.out.println(ddbh+"=="+sfcd+"=="+fczddbh+"=="+sj);
        return iOrderService.ensureOrderTimeSubmit(ddbh,sfcd,fczddbh,new BigInteger(sj));
    }


    @ApiOperation("查看采购单位对当前订单的验收情况")
    @ResponseBody
    @GetMapping("/checkOrderStatus")
    public Result checkOrderStatus(@Param("ddbh")String ddbh){
        return iOrderService.checkOrderStatus(ddbh);
    }


    @ApiOperation("订单发票开始开具时间信息推送")
    @ResponseBody
    @PostMapping("/invoiceStaTimeSubmit")
    public Result invoiceStaTimeSubmit(@Param("ddbh")String ddbh,@Param("sj")String sj){
        log.info(ddbh+"======11======="+sj);
        return iOrderService.invoiceStaTimeSubmit(ddbh,new BigInteger(sj));
    }

    @ApiOperation("订单发票结束开具时间信息推送")
    @ResponseBody
    @PostMapping("/invoiceEndTimeSubmit")
    public Result invoiceEndTimeSubmit(@Param("ddbh")String ddbh,@Param("sj")String sj){
        log.info(ddbh+"======22======="+sj);
        return iOrderService.invoiceEndTimeSubmit(ddbh,new BigInteger(sj));
    }

    @ApiOperation("订单物流信息推送")
    @ResponseBody
    @GetMapping("/ensureOrderArrive")
    public Result ensureOrderArrive(@Param("ddbh") String ddbh,@Param("sfcd")Integer sfcd,@Param("fczddbh")String fczddbh,@Param("kdgs")String kdgs,@Param("kddh")String kddh,@Param("ms")String ms,@Param("kdsj")String kdsj){
        log.info(ddbh+"=="+sfcd+"=="+fczddbh+"=="+kdgs+"=="+kddh+"=="+ms+"=="+kdsj);
        return iOrderService.ensureOrderArrive(ddbh,sfcd,fczddbh,kdgs,kddh,ms,new BigInteger(kdsj));
    }

    @ApiOperation("根据信息搜索")
    @GetMapping("/select")
    public ModelAndView selectOrderByData(@Param("zt")String zt, @Param("mapData") Map mapData, @Param("data")String data, @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        ModelAndView modelAndView=new ModelAndView();
        if(data!=null&& "null".equals(data)){
            data=null;
        }
        modelAndView.addObject("orderDara",iOrderService.selectOrderListByData(data,zt,pageSize,pageNum,mapData));
        modelAndView.addObject("zt",zt);
        modelAndView.addObject("data",data);
        modelAndView.setViewName("orderList");
        System.out.println(modelAndView);
        return modelAndView;
    }

    @ApiOperation("物流消息推送")
    @GetMapping("/transportDataSubmit")
    public Result transportDataSubmit(String ddbh,Integer sfcd,String fczddbh,String kdgs,String kddh,String ms,BigInteger kdsj){
        return iOrderService.ensureOrderArrive(ddbh,sfcd,fczddbh,kdgs,kddh,ms,kdsj);
    }


    @ApiOperation("/删除已取消的订单或者签收的订单")
    @DeleteMapping("deleOrder")
    public Result delectOrder(){
        return null;
    }


    @ApiOperation("更新单个订单")
    @PostMapping("upOrderData")
    public Result upOrderData(@Param("ddbh")String ddbh){
        return iOrderService.upDataOneData(ddbh);
    }


    @ApiOperation("查看验收单")
    @GetMapping("/checkShowPage")
    @ResponseBody
    public Result checkShowPage(@Param("ddbh")String ddbh){
        log.info("订单编号："+ddbh);
        return iOrderService.checkShowPage(ddbh);
    }

}
