package net.wlgzs.purchase;


import com.Enxi;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.ServiceValue;
import net.wlgzs.purchase.mapper.PartsMapper;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.mapper.ServiceValueMapper;
import net.wlgzs.purchase.service.IPartsService;
import net.wlgzs.purchase.service.IProductService;
import net.wlgzs.purchase.service.IServiceValueService;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PurchaseApplication.class)
public class Demo {
    @Resource
    private IProductService productService;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private PartsMapper partsMapper;
    @Resource
    private ServiceValueMapper serviceValueMapper;
    @Resource
    private IPartsService partsService;
    @Resource
    private IServiceValueService serviceValueService;


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


    @Test
    public void getService(){
        List<String> pmbh1=productMapper.findpmbh();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        System.out.println(pmbh1);
        for (String pmbh:pmbh1){
            int pageNum = 1;
            int pageSize = 50;
            int pagecount=1;
            for ( ;pageNum <= pagecount; pageNum++) {
                String jsonstr ="{\"username\":\""+username+"\"," +
                        "\"pwd\":\""+enPwd1+"\"," +
                        "\"pageSize\":\""+pageSize+"\","+
                        "\"pageNum\":\""+pageNum+"\","+
                        "\"pmbh\":\""+pmbh+"\"}";
                System.out.println(jsonstr);
                JSONObject jsonObject=ClientUtil.getJSONObject(readProperties.getUrl(),readProperties.getFindFwByPmbh(),jsonstr);
                System.out.println(jsonObject.toString());
                if (jsonObject.getString("resultMessage").equals("返回品目增值服务信息成功")&&jsonObject.getString("resultFlag").equals("Y")) {
                    String jsonString = jsonObject.getString("serviceList");
                    pagecount=Integer.valueOf(jsonObject.getString("pagecount"));
                    List<ServiceValue> serviceValues = JSON.parseArray(jsonString, ServiceValue.class);
                    for (ServiceValue serviceValue : serviceValues) {
                        String fwbg=serviceValue.getFWBH();
                        List<ServiceValue> serviceValues1=serviceValueMapper.findServiceValueByFubh(fwbg);
                        if (serviceValues1.size()==0){
                            serviceValue.setPmbh(pmbh);
                            serviceValue.setPmmc(jsonObject.getString("pmmc"));
                            if (serviceValueService.save(serviceValue)){
                                System.out.println("添加新服务");
                            }else {
                                System.out.println("添加失败");
                            }

                        }else{
                            serviceValue.setPmbh(pmbh);
                            serviceValue.setPmmc(jsonObject.getString("pmmc"));
                            serviceValue.setServiceId(serviceValues1.get(0).getServiceId());
                            if(serviceValueService.updateById(serviceValue)){
                                System.out.println("更新服务");
                            }else{
                                System.out.println("更新失败");
                            }

                        }

                    }
                    System.out.println(serviceValues);
                }
            }
        }
    }

}
