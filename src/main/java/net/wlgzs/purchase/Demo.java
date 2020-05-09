package net.wlgzs.purchase;


import com.Enxi;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PurchaseApplication.class)
public class Demo {

    @Resource
    private ReadProperties readProperties;

//    public static void main(String[] args) {
//        String str="{\n" +
//                "\"username\": 7223, \n" +
//                "\"pwd\": \"ff8080814a1353ac014a139496110049\", (加密后密码)\n" +
//                "\"ddbh\": \"WSCG000074\", \n" +
//                "\"sfcd\": 0, \n" +
//                "\"fczddbh\": \"fc0001\", \n" +
//                "\"kdgs\": \"顺丰速运\", \n" +
//                "\"kddh\": \"0000001\", \n" +
//                "\"ms\": \"离开濮阳县速递物流分公司 发往濮阳县邮政局速递公司\",\n" +
//                "\"kdsj\": 20160120142700\n" +
//                "}\n";
//        JSONObject d=ClientUtil.getJSONObject("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl", "exeLogistics",str);
//        System.out.println(d+"====================================================");
//    }


    //查看某商品信息3.21  findSpByXhbh  3.22  findSpSfbj
    @Test
    public void findSpByXhbh(){
        String xhbh="asdasdasdas";
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String json="{\"username\":\""+username+"\","+
                "\"pwd\":\""+enPwd1+"\","+
                "\"xhbh\":\""+xhbh+"\"}";
        System.out.println("输出的json"+json);
    }

    //配件上下架3.24  delPjbjByPjbh
    @Test
    public void delPjbjByPjbh(){
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String xhbh="asdasdasdas";
        String pjbh="ajksfjkas";
        String zt="1";
        String xjyy="无";
        String json="{\"username\":\""+username+"\","+
                "\"pwd\":\""+enPwd1+"\","+
                "\"xhbh\":\""+xhbh+"\","+
                "\"pjbh\":\""+pjbh+"\","+
                "\"zt\":\""+zt+"\","+
                "\"xjyy\":\""+xjyy+"\"}";
        System.out.println(json);
    }



    //增值服务上下架3.25  delFwbjByFwbh
    @Test
    public void delFwbjByFwbh(){
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String xhbh="asdasdasdas";
        String fwbh="ajksfjkas";
        String zt="1";
        String xjyy="无";
        String json="{\"username\":\""+username+"\","+
                "\"pwd\":\""+enPwd1+"\","+
                "\"xhbh\":\""+xhbh+"\","+
                "\"fwbh\":\""+fwbh+"\","+
                "\"zt\":\""+zt+"\","+
                "\"xjyy\":\""+xjyy+"\"}";
        System.out.println(json);
    }

}
