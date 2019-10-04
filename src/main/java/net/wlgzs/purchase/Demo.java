//package net.wlgzs.purchase;
//
//import com.alibaba.fastjson.JSON;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.wlgzs.purchase.entity.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class Demo {
//    @Autowired
//    IOrderService iOrderService;
//    public static void main(String[] args) {
//
//        String result = "{\n" +
//                "\"resultFlag\": \"Y\",\n" +
//                "\" resultMessage\": \"根据时间段查询订单列表返回成功\", \n" +
//                "\"count\": \"2\",\n" +
//                "\"orderList\": [\n" +
//                "        {\n" +
//                "\"deliverycity\": \"00390001\",\n" +
//                "\"deliverycounty\": \"003900010001\",\n" +
//                "\"shdd\": \"aaaaa\",\n" +
//                "\"ddbh\": \"WSCG000083\",\n" +
//                "\"cgrmc\": \"测试三\",\n" +
//                "\"xflxrxm\": \"张三\",\n" +
//                "\"xfdh\": \"11111\",\n" +
//                "\"ghsmc\": \"河南电子科技有限公司\",\n" +
//                "\"ddze\": 2150,\n" +
//                "\"cjrq\": 20160510183042,\n" +
//                "\"zt\": 2,\n" +
//                "\"zffs\": \"70080001\",\n" +
//                "\"fptt\": \"测试三\",\n" +
//                "\"nsrsbh\": \"NE0010111\",\n" +
//                "\"sfxyazfw\": 0,\n" +
//                "\"beiz\":\"需要尽快送货\",\n" +
//                "\"shsj\": 0,\n" +
//                "\"shqx\":0,\n" +
//                "\"fpnr\": \"70100001\",\n" +
//                "\"productList\": [\n" +
//                "                {\n" +
//                "\"XHBH\": \"2c9088031fd4e50a011fd549488300cf\",\n" +
//                "\"PPBH\": \"40282a3e177bfb3f01177c8a193c00d4\",\n" +
//                "\"PPMC\": \"惠普\",\n" +
//                "\"SPMC\": \"HP LaserJet P2035\",\n" +
//                "\"SL\": 1,\n" +
//                "\"SJJG\": 2150,\n" +
//                "\"XJJG\": 2150\n" +
//                "            }\n" +
//                "        ],\n" +
//                "\"serviceList\":[{\"FWMC\":\"三年保修\",\"FWJG\":691,\"SL\":5}],\n" +
//                "\"accessoryList\":[\n" +
//                "{\"PJMC\":\"墨盒\",\"SL\":2,\"PJJG\":112.00},{\"PJMC\":\"硒鼓\",\"SL\":1,\"PJJG\":101.11}\n" +
//                "]\n" +
//                "        },\n" +
//                "        {\n" +
//                "\"deliverycity\": \"00390001\",\n" +
//                "\"deliverycounty\": \"003900010001\",\n" +
//                "\"shdd\": \"5555\",\n" +
//                "\"ddbh\": \"WSCG000082\",\n" +
//                "\"cgrmc\": \"测试三\",\n" +
//                "\"xflxrxm\": \"张三\",\n" +
//                "\"xfdh\": \"5555\",\n" +
//                "\"ghsmc\": \"河南电子科技有限公司\",\n" +
//                "\"ddze\": 2150,\n" +
//                "\"cjrq\": 20160510175945,\n" +
//                "\"zt\": 2,\n" +
//                "\"zffs\": \"70080001\",\n" +
//                "\"fptt\": \"测试三\",\n" +
//                "\"fpnr\": \"70100001\",\n" +
//                "\"productList\": [\n" +
//                "                {\n" +
//                "\"XHBH\": \"2c9088031fd4e50a011fd549488300cf\",\n" +
//                "\"PPBH\": \"40282a3e177bfb3f01177c8a193c00d4\",\n" +
//                "\"PPMC\": \"惠普\",\n" +
//                "\"SPMC\": \"HP LaserJet P2035\",\n" +
//                "\"SL\": 1,\n" +
//                "\"SJJG\": 2150,\n" +
//                "\n" +
//                "\"XJJG\": 2150\n" +
//                "                }\n" +
//                "            ]\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//
//        net.sf.json.JSONObject jsonObject = JSONObject.fromObject(result);
//        String flag = jsonObject.get("resultFlag").toString();
//        List<Order> orderList = null;
//        if (jsonObject.get("orderList") != null && !jsonObject.get("orderList").toString().equals("")) {
//            JSONArray jsonArray = jsonObject.getJSONArray("orderList");
//            orderList = JSON.parseArray(jsonArray.toString(), Order.class);
//        }
//        else {
//            System.out.println("这次更新没有发现订单");
//        }
//        if (orderList != null && orderList.size() != 0 && flag.equals("Y")) {
//            for (Order order : orderList) {
//
//                System.out.println(order.toString());
//            }
//        }
//    }
//}
