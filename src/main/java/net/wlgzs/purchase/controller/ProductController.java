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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
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

    private String xhbh="40282a3e177bfb3f01177c8d379300e8";



    Logger logger = LoggerFactory.getLogger(OrderDataServiceImpl.class);



    //获取商品入库
    @Test
    public void ppp() {
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String result = null;
        int page=1;
        int numpage=2;
        for (int i = page; i < numpage; i++) {
            try {
                Client client = new Client(new URL(url));
                String jsonStr = "{\n\"username\":\""+username+"\",\n" +
                        "\"pwd\":\"" + enPwd1 + "\",\n" +
                        "\"sprkkssj\":\"20170913094809\",\n" +
                        "\"sprkJssj\":\"20191016000000\",\n" +
                        "\"pageNum\":\""+i+"\",\n" +
                        "\"pageSize\":\"50\"\n}";
                Object[] rets = client.invoke("findSprkandParam", new Object[]{jsonStr});
                result = (String) rets[0];
                System.out.println(jsonStr+"****************");
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jso1 = JSONObject.fromObject(result);
            System.out.println(Integer.parseInt(jso1.getString("pagecount"))*50+"总数据***************************************");
            String jsonString = jso1.getString("spList");
            System.out.println(jsonString);
            List<Product> product = JSON.parseArray(jsonString, Product.class);
            List<String> xhbh=productMapper.findxhbh();
            for (Product product1 : product) {
                boolean b=true;
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
        try {
            String username=readProperties.getUsername();
            String pwd=readProperties.getPwd();
            String enPwd1= Enxi.enPwd(username,pwd);
            String url=readProperties.getUrl();
            for (int i = 0; i < pmbh.size(); i++) {
                String json="{\"username\":\""+username+"\",\"pwd\":\""+enPwd1+"\",\"pmbh\":\""+pmbh.get(i)+"\",\"pageNum\":\"1\",\"pageSize\":\"10\"}";
                System.out.println(json);
                JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getFindPjByPmbh(),json);

                System.out.println(jsonObject);
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
                        List<Parts> parts=partsMapper.findPartssByPjbh(parts2.getPJBH());
                        if(parts.size()==0){
                            partsService.save(parts2);
                        }else{
                            parts2.setPartsId(parts.get(0).getPartsId());
                            partsService.updateById(parts2);
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
    @Test
    public void getProductList(){
        //商品表更新
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String result =null;
        DateTime dateTime=new DateTime();
        //总页数
        int pagecount=1;
        //当前页
        int nowpage=305;
        String sprkJssj=dateTime.toString("yyyyMMddHHmmss");
        String sprkkssj=dateTime.minusDays(1).toString("yyyyMMddHHmmss");
        sprkkssj="20170913094809";
        do {
            try {
                Client client = new Client(new URL(url));
                String jsonStr = "{\n\"username\":\"" + username + "\",\n" +
                        "\"pwd\":\"" + enPwd1 + "\",\n" +
                        "\"sprkkssj\":\"" + sprkkssj + "\",\n" +
                        "\"sprkJssj\":\"" + sprkJssj + "\",\n" +
                        "\"pageNum\":\"" + nowpage + "\",\n" +
                        "\"pageSize\":\"50\"\n}";
                Object[] rets = client.invoke("findSprkandParam", new Object[]{jsonStr});
                result = (String) rets[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject jso1 = JSONObject.fromObject(result);
            String s = jso1.getString("resultFlag");
            if ("N".equals(s)) {
                System.out.println(jso1.getString("resultMessage"));
            } else {
                pagecount = Integer.valueOf(jso1.getString("pagecount"));
                String jsonString = jso1.getString("spList");
                System.out.println(jsonString);//记得删
                List<Product> product = JSON.parseArray(jsonString, Product.class);
                for (Product product1 : product) {
                    String Xhbh = product1.getXhbh();
                    List<Product> productList = productMapper.findProductsByXhbh(Xhbh);
                    if (productList.size() == 0) {
                        if (productService.save(product1)) {
                            System.out.println("存入新商品");
                        }
                    } else {
                        product1.setProductId(productList.get(0).getProductId());
                        if (productMapper.updateById(product1) > 0) {
                            System.out.println(product1.getPmmc()+"更新信息");
                        }
                    }
                }
            }
            System.out.println("当前页"+nowpage+",总页数"+pagecount);
            nowpage++;

        }while(nowpage<pagecount);
    }


    //根据品目获取增值服务
    @Test
    public void getService(){
        List<String> pmbh1=productMapper.findpmbh();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        System.out.println(pmbh1);
        for (String pmbh:pmbh1){
            String jsonstr ="{\"username\":\""+username+"\"," +
                                "\"pwd\":\""+enPwd1+"\"," +
                                "\"pmbh\":\""+pmbh+"\"}";
            JSONObject jsonObject=ClientUtil.getJSONObject(readProperties.getUrl(),readProperties.getFindFwByPmbh(),jsonstr);
            System.out.println(jsonObject.toString());
            if (jsonObject.getString("resultMessage").equals("返回品目增值服务信息成功")&&jsonObject.getString("resultFlag").equals("Y")) {
                String jsonString = jsonObject.getString("serviceList");
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
                "\"xjyy\": \"河南飞鸾电子科技有限公司\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getExecSpDown(),json);
        System.out.println(jsonObject);
    }

    //推送商品唯一标识
    @Test
    public void ProductWybs() throws IOException {
        String ddbh="WSCG201910100854540938320003730";
        String wybs="fghfghfgh";
        String imgpath="C:\\Users\\Wyx\\Desktop\\1#138\\{L`22OB{`58JNV(G64D[HHS.jpg";
        //获取基本信息
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);


        File picFile=new File(imgpath);
        FileInputStream fis=new FileInputStream(picFile);
        byte[] arr =new byte[1024*1024*5];
        byte[] arrByte=new byte[1024*1024*5];
        int read=fis.read(arr);
        String pic= Base64.getEncoder().encodeToString(arr);

        //拼接Json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"ddbh\":\""+ddbh+"\"," +
                "\"xhbh\":\""+xhbh+"\"," +
                "\"wybs\":\""+wybs+"\"," +
                "\"pic\": \""+pic+"\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getExecTsWybs(),json);
        System.out.println(jsonObject);

    }


    /**
     * 查询页
     * 需要遍历lbmc，pmmc，ppmc
     * 还有全部商品列表
     * @param lbmc 类别名称
     * @param pmmc 品目名称
     * @param ppmc 品牌名称
     * @param nowPage 当前页
     * @param  nr  搜索内容
     */
    @RequestMapping(value = "/{lbmc}/{pmmc}/{ppmc}/{page}",method = RequestMethod.GET)
    @ApiOperation(value = "多条件查询",httpMethod = "GET")
    @ApiImplicitParam(name = "nr",dataType = "内容")
    public ModelAndView findallProduct(HttpServletRequest request,
                                       @PathVariable("lbmc") String lbmc,
                                       @PathVariable("pmmc") String pmmc,
                                       @PathVariable("ppmc") String ppmc,
                                       @RequestParam(name = "nr",defaultValue = "0")String nr,
                                       @PathVariable("page") int nowPage){
        return productService.findallProduct(request,lbmc,pmmc,ppmc,nr,nowPage);
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


    /**
     * 获取审核商品信息 查看是否审核成功
     * @param xhbh 商品编号
     *
     */
//    @Test
//    public void
}
