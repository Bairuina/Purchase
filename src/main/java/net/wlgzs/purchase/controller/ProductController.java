package net.wlgzs.purchase.controller;


import com.Enxi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.service.IProductService;
import org.codehaus.xfire.client.Client;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.net.URL;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王耀兴
 * @since 2019-09-28
 */
@RestController
@RequestMapping("/product")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductController extends BaseController {

    @Resource
    private IProductService productService;


//    //获取商品入库
//    @Test
//    public void ppp() {
//        String result = null;
//        try {
//            Client client = new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
//            String username = "7223";
//            String pwd = "ff8080814a1353ac014a139496110049";
//            String enPwd1 = Enxi.enPwd(username, pwd);
//            String jsonStr = "{\n\"username\":\"7223\",\n" +
//                    "\"pwd\":\"" + enPwd1 + "\",\n" +
//                    "\"sprkkssj\":\"20160913094809\",\n" +
//                    "\"sprkJssj\":\"20170913094809\",\n" +
//                    "\"pageNum\":\"1\",\n" +
//                    "\"pageSize\":\"50\"\n}";
//            Object[] rets = client.invoke("findSprkandParam", new Object[]{jsonStr});
//            result = (String) rets[0];
//            System.out.println(jsonStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);
//        JSONObject jso1 = JSON.parseObject(result);
//        String jsonString = jso1.getString("spList");
//        System.out.println(jsonString);
//        List<Product> product = JSON.parseArray(jsonString, Product.class);
//        for (Product product1 : product) {
//            System.out.println(product1);
//        }
//        System.out.println(productService);
//        productService.saveBatch(product);
//    }

    //根据品目获取配件





    //获取商品春入数据库
    @Scheduled(cron = "0 0 12 * * ?")
    public void getProductList(){
        //商品表更新
        String result =null;
        DateTime dateTime=new DateTime();
        String sprkJssj=dateTime.toString("yyyyMMddHHmmss");
        String sprkkssj=dateTime.minusDays(1).toString("yyyyMMddHHmmss");

        try{
            Client client= new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
            String username="7223";
            String pwd="ff8080814a1353ac014a139496110049";

            String enPwd1= Enxi.enPwd(username,pwd);
            String jsonStr="{\n\"username\":\"7223\",\n" +
                    "\"pwd\":\""+enPwd1+"\",\n" +
                    "\"sprkkssj\":\""+sprkkssj+"\",\n" +
                    "\"sprkJssj\":\""+sprkJssj+"\",\n" +
                    "\"pageNum\":\"1\",\n" +
                    "\"pageSize\":\"3\"\n}";
            Object[] rets=client.invoke("findSprkandParam",new Object[]{jsonStr});
            result=(String) rets[0];
        }catch(Exception e){
            e.printStackTrace();
        }
        JSONObject jso1=JSON.parseObject(result);
        String s=jso1.getString("resultFlag");
        if (s.equals("N")){
            System.out.println(jso1.getString("resultMessage"));
        }else {
            String jsonString = jso1.getString("spList");
            List<Product> product = JSON.parseArray(jsonString, Product.class);
            boolean flg = productService.saveBatch(product);
            if (flg==true){
                System.out.println("有数据更新");
            }
        }

        //配件表更新
        try {
            String url = "http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl";
            String wayName = "findPjByPmbh";
            String pmbh = "ff80808151ae339c0151b31b946c2afb";
            Client client = new Client(new URL(url));
            String username = "7223";
            String pwd = "ff8080814a1353ac014a139496110049";
        }catch(Exception e){
            e.printStackTrace();
        }


    }


    /**
     * 查询页
     * 需要遍历lbmc，pmmc，ppmc
     * 还有全部商品列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView findallProduct(@RequestParam(value = "lbbh",defaultValue = "0")String lbbh,
                                       @RequestParam(value = "pmbh",defaultValue = "0")String pmbh,
                                       @RequestParam(value = "ppbh",defaultValue = "0")String ppbh,
                                       @RequestParam(value = "nr",defaultValue = "0")String nr){

        return productService.findallProduct(lbbh,pmbh,ppbh,nr);
    }

//    /**
//     * 传入一个lbmc,返回pmmc 和 lbmc的全部商品
//     */
//    @RequestMapping(value = "/1")
//    public ModelAndView findallProduct1(@RequestParam(value = "lbbh") String lbbh){
//        return productService.findallProduct1(lbbh);
//    }

    /**
     * 传入pmmc 返回
     */
}
