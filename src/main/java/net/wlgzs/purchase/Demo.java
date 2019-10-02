package net.wlgzs.purchase;

import com.Enxi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.wlgzs.purchase.entity.Product;
import org.codehaus.xfire.client.Client;

import java.net.URL;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String result =null;
        try{
            Client client= new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
            String username="7223";
            String pwd="ff8080814a1353ac014a139496110049";

            String enPwd1= Enxi.enPwd(username,pwd);
            String jsonStr="{\n\"username\":\"7223\",\n" +
                    "\"pwd\":\""+enPwd1+"\",\n" +
                    "\"sprkkssj\":\"20160913094809\",\n" +
                    "\"sprkJssj\":\"20170913094809\",\n" +
                    "\"pageNum\":\"1\",\n" +
                    "\"pageSize\":\"3\"\n}";
            Object[] rets=client.invoke("findSprkandParam",new Object[]{jsonStr});
            result=(String) rets[0];
            System.out.println(jsonStr);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
        JSONObject jso1=JSON.parseObject(result);
        String jsonString=jso1.getString("spList");
        System.out.println(jsonString);
        List<Product> product=JSON.parseArray(jsonString,Product.class);
        for (Product product1:product){
            System.out.println(product1);
        }

    }
}
