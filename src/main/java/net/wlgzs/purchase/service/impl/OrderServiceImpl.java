package net.wlgzs.purchase.service.impl;




import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Order;
import net.wlgzs.purchase.mapper.OrderMapper;
import net.wlgzs.purchase.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王言
 * @since 2019-09-28
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    IRedis iRedis;

    Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    QueryWrapper<Order> queryWrapper=new QueryWrapper<>();

    //更新订单
    @Override
    public ResultCode updateOrderDate( String userName,String pwd) {
         userName="7223";
         pwd="ff8080814a1353ac014a139496110049";
//        Calendar calendar=Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
//        Date date=calendar.getTime();
//        String jssj=simpleDateFormat.format(date);
//        Date staDate=new Date();
//        staDate.setTime(date.getTime()-1000*60*60*3);
//        String kssj=simpleDateFormat.format(staDate);
        String jssj="20160428112038";
        String kssj="20160428112038";
        logger.info("kssj:"+kssj);
        logger.info("jssj:"+jssj);

        int pageNum=1;
        int pageSize=50;
        String result=null;

        try {
            Client client=new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
            String jsonStr="{\"username\":\""+userName+"\",\"pwd\":\""+pwd+"\",\"zt\":2,\"kssj\":\""+kssj+"\",\"jssj\":\""+jssj+"\",\"pageNum\":\""+pageNum+"\",\"pageSize\":\""+pageSize+"\"}";
            jsonStr="{\n" +
                    "\"username\": 7223, \n" +
                    "\"pwd\": \"ff8080814a1353ac014a139496110049\", \n" +
                    "\"kssj\": \"20160428112038\", \n" +
                    "\"jssj\": \"20160428112038\", \n" +
                    "\"pageNum\": \"1\", \n" +
                    "\"pageSize\": \"10\" \n" +
                    "}\n";
            logger.info(jsonStr);
            Object[] rets=client.invoke("findOrder",new Object[]{jsonStr});
            result=rets.toString();
            logger.info(result);


        } catch (Exception e) {
            e.printStackTrace();
        }
//        result=upDataOne.toString();
        JSONObject jsonObject= JSONObject.fromObject(result);
        String flag=jsonObject.get("resultFlag").toString();
        JSONArray jsonArray=jsonObject.getJSONArray("orderList");
        List<Order> orderList= JSON.parseArray(jsonArray.toString(),Order.class);
        if(orderList!=null&&flag.equals("Y")){
            for(Order order:orderList){
                logger.info("++++++++"+order);
                baseMapper.insert(order);
            }
            return ResultCode.SUCCESS;
        }
        else {
            return  ResultCode.FAIL;
        }
    }



    //查询订单
    @Override
    public Result selectOrder() {
        return null;
    }


}