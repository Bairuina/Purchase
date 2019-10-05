package net.wlgzs.purchase.controller;


import com.Enxi;


import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.JsonArray;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.mapper.PartsMapper;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.service.IPartsService;
import net.wlgzs.purchase.service.IProductService;
import net.wlgzs.purchase.service.impl.OrderDataServiceImpl;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import org.codehaus.xfire.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.net.URL;
import java.util.List;
import java.util.Set;


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

    @Resource
    private ProductMapper productMapper;
    @Resource
    private PartsMapper partsMapper;

    @Autowired
    private ReadProperties readProperties;

    @Resource
    private IPartsService partsService;

    Logger logger = LoggerFactory.getLogger(OrderDataServiceImpl.class);

    //获取商品入库
    @Test
    public void ppp() {
        String result = null;
        int page=100;
        int numpage=100;
        for (int i = page; i < numpage; i++) {
            try {
                Client client = new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
                String username = "7223";
                String pwd = "ff8080814a1353ac014a139496110049";
                String enPwd1 = Enxi.enPwd(username, pwd);
                String jsonStr = "{\n\"username\":\"7223\",\n" +
                        "\"pwd\":\"" + enPwd1 + "\",\n" +
                        "\"sprkkssj\":\"20160913094809\",\n" +
                        "\"sprkJssj\":\"20170913094809\",\n" +
                        "\"pageNum\":\""+i+"\",\n" +
                        "\"pageSize\":\"50\"\n}";
                Object[] rets = client.invoke("findSprkandParam", new Object[]{jsonStr});
                result = (String) rets[0];
                System.out.println(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jso1 = JSONObject.fromObject(result);
            System.out.println(Integer.parseInt(jso1.getString("pagecount"))*50+"总数据***************************************");
            String jsonString = jso1.getString("spList");
            System.out.println(jsonString);
            List<Product> product = JSON.parseArray(jsonString, Product.class);
            for (Product product1 : product) {
                boolean b=true;
                List<String> xhbh=productMapper.findxhbh();
                for (String xhbh1:xhbh){
                    if (xhbh1.equals(product1.getXhbh())){
                        b=false;
                        System.out.println("数据重复");
                        break;

                    }
                }
                if (b){
                    productService.save(product1);
                }
            }
        }
//            try {
//                Client client = new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
//                String username = "7223";
//                String pwd = "ff8080814a1353ac014a139496110049";
//                String enPwd1 = Enxi.enPwd(username, pwd);
//                String jsonStr = "{\n\"username\":\"7223\",\n" +
//                        "\"pwd\":\"" + enPwd1 + "\",\n" +
//                        "\"sprkkssj\":\"20160913094809\",\n" +
//                        "\"sprkJssj\":\"20170913094809\",\n" +
//                        "\"pageNum\":\"3\",\n" +
//                        "\"pageSize\":\"50\"\n}";
//                Object[] rets = client.invoke("findSprkandParam", new Object[]{jsonStr});
//                result = (String) rets[0];
//                System.out.println(jsonStr);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        JSONObject jso1 = JSONObject.fromObject(result);
//        System.out.println(Integer.parseInt(jso1.getString("pagecount"))*50+"总数据***************************************");
//        String jsonString = jso1.getString("spList");
//        System.out.println(jsonString);
//        List<Product> product = JSON.parseArray(jsonString, Product.class);
//        for (Product product1 : product) {
//            System.out.println(product1);
//        }
//        System.out.println(productService);
//        productService.saveBatch(product);



        //根据品目获取配件
        List<String> pmbh=productMapper.findpmbh();
        System.out.println(pmbh);
        List<String> parts= partsMapper.findPJBH();
        try {
            String username=readProperties.getUsername();
            String pwd=readProperties.getPwd();
            String enPwd1= Enxi.enPwd(username,pwd);
            String url=readProperties.getUrl();
            for (int i = 0; i < pmbh.size(); i++) {
                String json="{\"username\":\""+username+"\",\"pwd\":\""+enPwd1+"\",\"pmbh\":\""+pmbh.get(i)+"\",\"pageNum\":\"1\",\"pageSize\":\"10\"}";
                JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getFindPjByPmbh(),json);
                if (jsonObject.getString("resultFlag").equals("Y")&&jsonObject.getString("resultMessage").equals("返回品目配件信息成功")){
                        String jsonString=jsonObject.getString("accessoryList");
                        System.out.println(jsonString);
                        String pmbh1=jsonObject.getString("pmbh");
                        String pmmc=jsonObject.getString("pmmc");
                        logger.info(jsonString);
                        List<Parts> parts1=JSON.parseArray(jsonString,Parts.class);
//                        for(Parts parts2:parts1){
//                            parts2.setPmbh(pmbh1);
//                            parts2.setPmmc(pmmc);
//                        }
//                        logger.info(parts1+"****************************");
//                        partsService.saveBatch(parts1);
                }

            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }








//    //获取商品春入数据库
//    @Scheduled(cron = "0 0 12 * * ?")
//    public void getProductList(){
//        //商品表更新
//        String result =null;
//        DateTime dateTime=new DateTime();
//        String sprkJssj=dateTime.toString("yyyyMMddHHmmss");
//        String sprkkssj=dateTime.minusDays(1).toString("yyyyMMddHHmmss");
//
//        try{
//            Client client= new Client(new URL("http://222.143.21.205:8091/wsscservices_test/services/wsscWebService?wsdl"));
//            String username="7223";
//            String pwd="ff8080814a1353ac014a139496110049";
//
//            String enPwd1= Enxi.enPwd(username,pwd);
//            String jsonStr="{\n\"username\":\"7223\",\n" +
//                    "\"pwd\":\""+enPwd1+"\",\n" +
//                    "\"sprkkssj\":\""+sprkkssj+"\",\n" +
//                    "\"sprkJssj\":\""+sprkJssj+"\",\n" +
//                    "\"pageNum\":\"1\",\n" +
//                    "\"pageSize\":\"3\"\n}";
//            Object[] rets=client.invoke("findSprkandParam",new Object[]{jsonStr});
//            result=(String) rets[0];
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        JSONObject jso1= JSON.parseObject(result);
//        String s=jso1.getString("resultFlag");
//        if (s.equals("N")){
//            System.out.println(jso1.getString("resultMessage"));
//        }else {
//            String jsonString = jso1.getString("spList");
//            List<Product> product = JSON.parseArray(jsonString, Product.class);
//            boolean flg = productService.saveBatch(product);
//            if (flg==true){
//                System.out.println("有数据更新");
//            }
//        }
//    }


    /**
     * 查询页
     * 需要遍历lbmc，pmmc，ppmc
     * 还有全部商品列表
     * @param lbbh 类别编号
     * @param pmbh 品目编号
     * @param ppbh 品牌编号
     * @param  nr  搜索内容
     */
    @RequestMapping(value = "/{lbbh}/{pmbh}/{ppbh}/{nr}",method = RequestMethod.GET)
    @ApiOperation(value = "多条件查询",httpMethod = "GET")
    public ModelAndView findallProduct(@PathVariable("lbbh") String lbbh,@PathVariable("pmbh") String pmbh,@PathVariable("ppbh") String ppbh,@PathVariable("nr") String nr){
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
