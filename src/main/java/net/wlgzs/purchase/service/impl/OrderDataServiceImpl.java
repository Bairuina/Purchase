package net.wlgzs.purchase.service.impl;

import com.Enxi;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.OrderData;
import net.wlgzs.purchase.mapper.OrderDataMapper;
import net.wlgzs.purchase.service.IOrderDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.service.IRedis;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.codehaus.xfire.client.Client;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-03
 */
@Service
public class OrderDataServiceImpl extends ServiceImpl<OrderDataMapper, OrderData> implements IOrderDataService {
    @Autowired
    IRedis iRedis;

    Logger logger = LoggerFactory.getLogger(OrderDataServiceImpl.class);


    //更新订单
    @Override
    public Result updateOrderDate(String userName, String pwd,int pageNum) {
        DateTime dateTime=new DateTime();
//        userName = "7223";
//        pwd = "ff8080814a1353ac014a139496110049";
        String enPwd = Enxi.enPwd(userName, pwd);
        String jssj =dateTime.toString("yyyyMMddHHmmss") ;
        String kssj = dateTime.minusHours(3).toString("yyyyMMddHHmmss");
        logger.info("kssj(开始时间):" + kssj);
        logger.info("jssj(结束时间):" + jssj);

        int pageSize = 50;
        String result = null;

        try {
            Client client = new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
            String jsonStr = "{\"username\":\"" + userName + "\",\"pwd\":\"" + pwd + "\",\"zt\":2,\"kssj\":\"" + kssj + "\",\"jssj\":\"" + jssj + "\",\"pageNum\":\"" + pageNum + "\",\"pageSize\":\"" + pageSize + "\"}";
            jsonStr = "{\n" +
                    "\"username\":\""+userName+"\",\n" +
                    "\"pwd\":\"" + enPwd + "\",\n" +
                    "\"kssj\":\""+kssj+"\",\n" +
                    "\"jssj\":\""+jssj+"\",\n" +
                    "\"pageNum\":\""+pageNum+"\",\n" +
                    "\"pageSize\":\"50\"\n}";
            logger.info(jsonStr);
            Object[] rets = client.invoke("findOrder", new Object[]{jsonStr});
            result = rets[0].toString();
            logger.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.fromObject(result);
        String flag = jsonObject.get("resultFlag").toString();
        int count = Integer.parseInt(jsonObject.get("count").toString());
        List<OrderData> orderList = null;
        if (jsonObject.get("orderList") != null && !jsonObject.get("orderList").toString().equals("")) {
            JSONArray jsonArray = jsonObject.getJSONArray("orderList");
            orderList = JSON.parseArray(jsonArray.toString(), OrderData.class);
        } else {
            logger.info("这次更新没有发现订单");
        }
        if (orderList != null && orderList.size() != 0 && flag.equals("Y")) {
            for (OrderData order : orderList) {
                order.setOrderId(1);
                logger.info(order.toString());
                baseMapper.insert(order);
            }
            int totalPageNum = (count  + 14) / 15;
            if(totalPageNum>pageNum){
                pageNum++;
                updateOrderDate(userName,pwd,pageNum);
            }

            logger.info("更新完成！");
            return new Result(ResultCode.SUCCESS);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }


    //查询所有订单
    @Override
    public Result selectAllOrder(int pageNum) {
        List<OrderData> orderList = baseMapper.selectList(null);
        if(orderList==null||orderList.size()==0){
            return new Result(ResultCode.FAIL);
        }
        int count=orderList.size();
        int totalPageNum = (count  + 14) / 15;
        logger.info(orderList.toString());
        if(pageNum==totalPageNum){
            orderList=orderList.subList((pageNum - 1) * 15 - 1,orderList.size()-1);
        }
        else {
            orderList = orderList.subList((pageNum - 1) * 15 - 1, pageNum * 15 - 1);
        }
        Result result=new Result(ResultCode.SUCCESS,"查询成功",orderList,totalPageNum,pageNum);
        return result;
    }

    //查看订单详情
    @Override
    public Result selectOneOrder(String ddbh) {
        QueryWrapper<OrderData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ddbh", ddbh);
        OrderData orderData = baseMapper.selectOne(queryWrapper);
        return new Result(ResultCode.SUCCESS,orderData);
    }

    //确定或拒绝订单
    @Override
    public Result ensureORefuseOrder(String ddbh, String username, String pwd, int qrzt) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\", \n" +
                "\"ddbh\": \""+ddbh+"\"，\n" +
                "\"qrzt\": "+qrzt+"\n" +
                "}\n";
        try {
            Client client=new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
            Object[] rets=client.invoke("execGysOrderQr",new Object[]{jsonStr});
            String result=rets[0].toString();
            JSONObject jsonObject=JSONObject.fromObject(result);
            if(jsonObject.get("resultFlag")!=null&&jsonObject.get("resultFlag").equals("Y")){
                String ddbhData=jsonObject.get("ddbh").toString();
                OrderData orderData=new OrderData();
                if(qrzt==1) {
                    orderData.setZt("4");
                }
                else if(qrzt==0){
                    orderData.setZt("3");
                }
                return  upDateTwo(ddbh,orderData);
            }
            else {
                logger.info("操作订单失败！");
                return new Result(ResultCode.FAIL);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("操作订单失败！");
        return new Result(ResultCode.FAIL);
    }


    //订单签收物流信息推送
    @Override
    public Result ensureOrderArrive(String ddbh, String username, String pwd, int sfcd, String fczddbh, String kdgs, String kddh, String ms, String kdsj) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "\"sfcd\": \""+sfcd+"\", \n" +
                "\"fczddbh\": \""+fczddbh+"\", \n" +
                "\"kdgs\": \""+kdgs+"\", \n" +
                "\"kddh\": \""+kddh+"\", \n" +
                "\"ms\": \""+ms+"\",\n" +
                "\"kdsj\": \""+kdsj+"\"\n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","exeLogistics",jsonStr);
        if(jsonObject!=null&&jsonObject.get("resultFlag").equals("Y")){
            logger.info("物流消息发送成功！");
        }
        else {
            logger.info("物流消息发送失败！");
        }
        return null;
    }

    //订单签收时间信息推送
    @Override
    public Result ensureOrderTimeSubmit(String ddbh, String username, String pwd, int sfcd, String fczddbh, String shsj) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "\"shsj\": \""+shsj+"\",\n" +
                "\"sfcd\": \""+sfcd+"\",\n" +
                "\"fczddbh\": \""+fczddbh+"\"\n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","execQssj",jsonStr);

        if(jsonObject.get("resultFlag")!=null&&jsonObject.get("resultFlag").equals("Y")) {
            QueryWrapper<OrderData> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("ddbh",ddbh);
            OrderData orderData=new OrderData();
            orderData.setZt("5");
            return  upDateTwo(ddbh,orderData);
        }
        return new Result(ResultCode.FAIL);

    }

    //订单发票开始开具时间信息推送
    @Override
    public Result invoiceStaTimeSubmit(String ddbh, String username, String pwd, String fpkjsj) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "\"fpkjsj\":\""+fpkjsj+"\"\n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("JSONObject jsonObject=ClientUtil.getJSONObject","execFpkjsjByOrder",jsonStr);
        return null;
    }

    //订单发票开具结束时间信息推送
    @Override
    public Result invoiceEndTimeSubmit(String ddbh, String username, String pwd, String fpsdsj) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "\"fpsdsj\": \""+fpsdsj+"\"\n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","execfpsdsjByorder",jsonStr);
        return null;

    }


    //电商已经收到采购单位的付款,将收款标志、收款金额、收款时间提交
    @Override
    public Result getMoneyDataSubmit(String ddbh, String username, String pwd, int skbz, String skje, String sksj) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "\"skbz\": \""+skbz+"\",\n" +
                "\"skje\": \""+skje+"\"，\n" +
                "\"sksj\": \""+sksj+"\"\n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","execSkqk",jsonStr);
        return null;
    }




    //取消订单(已经对进行订单确认)
    @Override
    public Result deletEnsureOrder(String ddbh, String username, String pwd, String qxyy) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "\"qxyy\":\""+qxyy+"\"\n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","execDsZfdd",jsonStr);
        return null;
    }





    //查看采购单位对当前订单的验收情况(可以不存入数据库，只展示)
    @Override
    public Result checkOrderStatus(String ddbh, String username, String pwd) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","findYsByOrder",jsonStr);
        return null;
    }

    //更新合同
    @Override
    public Result contractWork(String ddbh, String username, String pwd) {
        String enPwd = Enxi.enPwd(username, pwd);
        String jsonStr="{\n" +
                "\"username\": \""+username+"\", \n" +
                "\"pwd\": \""+enPwd+"\",\n" +
                "\"ddbh\": \""+ddbh+"\", \n" +
                "}\n";
        JSONObject jsonObject=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl","findOrderHt",jsonStr);
        return null;
    }


    Result upDateTwo(String ddbh,OrderData orderData){
       QueryWrapper<OrderData> queryWrapper=new QueryWrapper<>();
       queryWrapper.eq("ddbh",ddbh);
        int data=baseMapper.update(orderData,queryWrapper);
        if(data>=0){
            logger.info("操作订单成功！");
            return new Result(ResultCode.SUCCESS,"操作订单成功!");
        }
        else {
            logger.info("本地数据库操作订单失败！");
            return new Result(ResultCode.FAIL,"本地数据库操作订单失败！");
        }
    }


}
