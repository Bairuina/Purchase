package net.wlgzs.purchase.controller;


import com.Enxi;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.entity.ServiceValue;
import net.wlgzs.purchase.mapper.PartsMapper;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.mapper.ServiceValueMapper;
import net.wlgzs.purchase.service.IPartsService;
import net.wlgzs.purchase.service.IProductService;
import net.wlgzs.purchase.service.IServiceValueService;
import net.wlgzs.purchase.service.impl.OrderDataServiceImpl;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import org.codehaus.xfire.client.Client;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;
import net.wlgzs.purchase.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private ReadProperties readProperties;

    private String xhbh="07ef4565964948a9a45292b2241a0563";



    Logger logger = LoggerFactory.getLogger(OrderDataServiceImpl.class);



    //获取商品入库
    @Test
    public void ppp() {
        String result = null;
        int page=586;
        int numpage=1037;
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
    }



    //配件入库
    @Test
    public void pppp(){
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
                    for(Parts parts2:parts1){
                        parts2.setPmbh(pmbh1);
                        parts2.setPmmc(pmmc);
                        boolean b=true;
                        for (String pjbh:parts){
                            if (parts2.getPJBH().equals(pjbh)) {
                                b = false;
                                System.out.println("重复");
                                break;
                            }
                        }
                        if (b){
                            partsService.save(parts2);
                        }
                    }
                    logger.info(parts1+"****************************");

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



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
        JSONObject jso1= JSONObject.fromObject(result);
        String s=jso1.getString("resultFlag");
        if ("N".equals(s)){
            System.out.println(jso1.getString("resultMessage"));
        }else {
            String jsonString = jso1.getString("spList");
            List<Product> product = JSON.parseArray(jsonString, Product.class);
            boolean flg = productService.saveBatch(product);
            if (flg){
                System.out.println("有数据更新");
            }
        }
    }


    //根据品目获取增值服务
    @Test
    public void getService(){
        String pmbh="000400010021000600010001";
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);

        System.out.println(pmbh);
            String jsonstr ="{\"username\":\""+username+"\"," +
                                "\"pwd\":\""+enPwd1+"\"," +
                                "\"pmbh\":\""+pmbh+"\"}";
            JSONObject jsonObject=ClientUtil.getJSONObject(readProperties.getUrl(),readProperties.getFindFwByPmbh(),jsonstr);
        System.out.println(jsonObject.toString());
            if (jsonObject.getString("resultMessage").equals("返回品目增值服务信息成功")&&jsonObject.getString("resultFlag").equals("Y")){
                String jsonStrig=jsonObject.getString("serviceList");
                List<ServiceValue> serviceValues =JSON.parseArray(jsonStrig, ServiceValue.class);
            for (ServiceValue serviceValue:serviceValues){
                serviceValue.setPmbh(pmbh);
                serviceValue.setPmmc(jsonObject.getString("pmmc"));
            }
            System.out.println(serviceValues);
        }
    }


    //商品报价
    @Test
    public void baojiaProdut(){
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String jgsfyy="成本升高";
        Product product = productMapper.findProductByXhbh(xhbh);
        List<Parts> parts=partsMapper.findPartsByPmbh(product.getPmbh());
//        String pjxxList = "[{\"pjmc\":\""+parts.get(0).getPJMC()+"\",\"pjjg\":\"0\",\"sxh\":\""+parts.get(0).getRN()+"\",\"pjbh\":\""+parts.get(0).getPJBH()+"\"}";
//        for (int i = 1; i < parts.size(); i++) {
//            pjxxList+=",{\"pjmc\":\""+parts.get(i).getPJMC()+"\",\"pjjg\":\"0\",\"sxh\":\""+parts.get(i).getRN()+"\",\"pjbh\":\""+parts.get(i).getPJBH()+"\"}";
//        }
//        pjxxList+="]";
        String json = "{\"username\":\"" + username + "\"," +
                "\"pwd\":\"" + enPwd1 + "\"," +
                "\"xhbh\":\"" + xhbh + "\"," +
                "\"xhmc\":\"" + product.getXhmc() + "\"," +
                "\"pmbh\":\"" + product.getPmbh() + "\"," +
                "\"pmmc\":\"" + product.getPmmc() + "\"," +
                "\"ppbh\":\"" + product.getPpbh() + "\"," +
                "\"ppmc\":\"" + product.getPpmc() + "\"," +
                "\"lbbh\":\"" + product.getLbbh() + "\"," +
                "\"lbmc\":\"" + product.getLbmc() + "\"," +
                "\"area\":\"00390019\"," +
                "\"fwcn\":\"郑州以及周边一天内送货，其他地市二天；提供上门安装及后期维修服务\"," +
                "\"sjjg\": \"0.5\"," +
                "\"productlink\":\"http://www.staples.cn/product/1100008501EA\"," +
                "\"pjxxList\":\"\","+
                "\"jgsfyy\":\""+jgsfyy+"\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getExecute(),json);
        System.out.println(readProperties.getExecute());
        logger.info(jsonObject.toString());
    }



    //商品配件报价
    @Test
    public void PjBaojiaByXhbhPjbh(){
        //获取相关数据
        String pjbh = "06688a98a6c8453bbc801f7708d63478";
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        Product product=productMapper.findProductByXhbh(xhbh);
        Parts parts=partsMapper.findPartsByPjbh(pjbh);
        //拼接json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"pmbh\":\""+product.getPmbh()+"\"," +
                "\"pmmc\":\""+product.getPmmc()+"\"," +
                "\"xhbh\": \""+product.getXhbh()+"\"," +
                "\"xhmc\":\""+product.getXhmc()+"\"," +
                "\"pjbh\": \""+parts.getPJBH()+"\"," +
                "\"pjmc\": \""+parts.getPJMC()+"\"," +
                "\"pjjg\":\"300000\"," +
                "\"bjyy\":\"物价上涨\"}";
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getQuotedpricePjByPjbh(),json);
        System.out.println(jsonObject.toString());
    }


    //商品增值服务报价
    @Test
    public void ServiceBaojiaByFwbh(){
        String xhmc=productMapper.findXhmcByXhbh(xhbh);
        String fwbh="8d12afbaa31144f5be878d624ca77e9d";
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        ServiceValue serviceValue=serviceValueMapper.findServiceValueByFWBH(fwbh);
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"pmbh\":\""+serviceValue.getPmbh()+"\"," +
                "\"xhbh\":\""+xhbh+"\","+
                "\"xhmc\":\""+xhmc+"\","+
                "\"pmmc\":\""+serviceValue.getPmmc()+"\"," +
                "\"fwbh\":\""+serviceValue.getFWBH()+"\"," +
                "\"fwmc\":\""+serviceValue.getFwmc()+"\"," +
                "\"fwjg\":\"10\"," +
                "\"bjyy\":\"人工费用变高物价上涨\"," +
                "\"zt\":\""+serviceValue.getZt()+"\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getQuotedpriceFwByFwbh(),json);
        System.out.println(jsonObject.toString());
    }



    //获取商品型号以及相关报价信息
    @Test
    public void getProductBjByXhbh(){
        //获取相关数据
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);

        //拼接json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"xhbh\":\""+xhbh+"\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getFindSpByXhbh(),json);
        System.out.println(jsonObject.toString());
    }


    //获取单个商品报价
    @Test
    public void getProductBjByXhbh2(){
        //获取基本信息

        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);

        //拼接Json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"xhbh\":\""+xhbh+"\"}";
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getFindSpSfbj(),json);
        System.out.println(jsonObject.toString());
    }

    //撤销报价
    @Test
    public void qxProductBj(){
        //获取基本信息

        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);

        //拼json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\": \""+enPwd1+"\",\"" +
                "xhbh\":\""+xhbh+"\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getQxShByXhbh(),json);
        System.out.println(jsonObject);
    }

    //商品上价下价
    @Test
    public void shangxiajia(){
        //获取基本信息
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);

        //拼接Json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"xhbh\":\""+xhbh+"\"," +
                "\"zt\":\"1\"," +
                "\"xjyy\": \"商品没货需要上架\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getExecSpDown(),json);
        System.out.println(jsonObject);
    }


    /**
     * 查询页
     * 需要遍历lbmc，pmmc，ppmc
     * 还有全部商品列表
     * @param lbbh 类别编号
     * @param pmbh 品目编号
     * @param ppbh 品牌编号
     * @param  nr  搜索内容
     */
    @RequestMapping(value = "/{lbbh}/{pmbh}/{ppbh}",method = RequestMethod.GET)
    @ApiOperation(value = "多条件查询",httpMethod = "GET")
    @ApiImplicitParam(name = "nr",dataType = "内容")
    public ModelAndView findallProduct(HttpServletRequest request, @PathVariable("lbbh") String lbbh, @PathVariable("pmbh") String pmbh, @PathVariable("ppbh") String ppbh, @RequestParam(name = "nr",defaultValue = "0")String nr){
        return productService.findallProduct(request,lbbh,pmbh,ppbh,nr);
    }

    /**
     *
     */

//    /**
//     * 传入一个lbmc,返回pmmc 和 lbmc的全部商品
//     */
//    @RequestMapping(value = "/1")
//    public ModelAndView findallProduct1(@RequestParam(value = "lbbh") String lbbh){
//        return productService.findallProduct1(lbbh);
//    }

    /**
     * 传入pmbh 返回商品信息
     * @param xhbh 商品编号
     * @return
     */
    @RequestMapping(value = "/product/{xhbh}",method = RequestMethod.GET)
    @ApiOperation(value = "报价商品页",httpMethod = "GET")
    public ModelAndView getProductByXhbh(@PathVariable("xhbh")String xhbh){
        return productService.getProductByXhbh(xhbh);
    }
}
