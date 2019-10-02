package net.wlgzs.purchase.controller;


import com.Enxi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.wlgzs.purchase.PurchaseApplication;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.service.IProductService;
import org.codehaus.xfire.client.Client;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    //获取商品春入数据库
    @Test
    @Scheduled(cron = "0 0 12 * * ?")
    public void getProductList(){
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
    }

}
