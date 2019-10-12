package net.wlgzs.purchase.service.impl;

import com.Enxi;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.entity.PartsOffer;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.entity.ProductOffer;
import net.wlgzs.purchase.mapper.PartsMapper;
import net.wlgzs.purchase.mapper.PartsOfferMapper;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.mapper.ProductOfferMapper;
import net.wlgzs.purchase.service.IProductOfferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@Service
public class ProductOfferServiceImpl extends ServiceImpl<ProductOfferMapper, ProductOffer> implements IProductOfferService {
    @Resource
    private ReadProperties readProperties;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private PartsMapper partsMapper;
    @Resource
    private PartsOfferMapper partsOfferMapper;

    //报价一个商品
    @Override
    public Result productOffer(String xhbh, BigDecimal price, String text,String fwcn,String productlink,String area){
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        Product product = productMapper.findProductByXhbh(xhbh);
        List<Parts> parts=partsMapper.findPartsByPmbh(product.getPmbh());
        List<BigDecimal> pjjgList=partsOfferMapper.findPartsPriceByXhbh(xhbh);
        String pjxxList;
        if (parts.size()==0){
            pjxxList="\"\"";
        }else if(pjjgList.size()==0){
            pjxxList = "[{\"pjmc\":\"" + parts.get(0).getPJMC() + "\",\"pjjg\":\"0\",\"sxh\":\"" + parts.get(0).getRN() + "\",\"pjbh\":\"" + parts.get(0).getPJBH() + "\"}";
            for (int i = 1; i < parts.size(); i++) {
                pjxxList += ",{\"pjmc\":\"" + parts.get(i).getPJMC() + "\",\"pjjg\":\"0\",\"sxh\":\"" + parts.get(i).getRN() + "\",\"pjbh\":\"" + parts.get(i).getPJBH() + "\"}";
            }
            pjxxList += "]";
        } else{
            pjxxList = "[{\"pjmc\":\"" + parts.get(0).getPJMC() + "\",\"pjjg\":\""+pjjgList.get(0)+"\",\"sxh\":\"" + parts.get(0).getRN() + "\",\"pjbh\":\"" + parts.get(0).getPJBH() + "\"}";
            for (int i = 1; i < parts.size(); i++) {
                pjxxList += ",{\"pjmc\":\"" + parts.get(i).getPJMC() + "\",\"pjjg\":\""+pjjgList.get(i)+"\",\"sxh\":\"" + parts.get(i).getRN() + "\",\"pjbh\":\"" + parts.get(i).getPJBH() + "\"}";
            }
            pjxxList += "]";
        }
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
                "\"area\":\""+area+"\"," +
                "\"fwcn\":\""+fwcn+"\"," +
                "\"sjjg\": \""+price+"\"," +
                "\"productlink\":\""+productlink+"\"," +
                "\"pjxxList\":"+ pjxxList +","+
                "\"jgsfyy\":\""+text+"\"}";
        JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getExecute(),json);
        System.out.println(jsonObject);
        if(jsonObject.getString("resultFlag").equals("Y")){
            List<ProductOffer> jg=baseMapper.findProductOfferByXhbh(xhbh);
            ProductOffer productOffer=new ProductOffer();
            productOffer.setPrice(price);
            productOffer.setXhbh(xhbh);
            if (jsonObject.getString("resultMessage").equals("商品上架成功,商品详细信息已返回")){
                productOffer.setZt("1");
                productOffer.setArea(area);
                if (jg.size()==0){
                    if(baseMapper.insert(productOffer)>0){
                        return new Result(ResultCode.SUCCESS,"报价成功");
                    }
                }else{
                    productOffer.setProductOfferId(jg.get(0).getProductOfferId());
                    if (baseMapper.updateById(productOffer)>0){
                        return new Result(ResultCode.SUCCESS,"更改价格成功");
                    }
                }

            }else if(jsonObject.getString("resultMessage").equals("维护的价格高于原价格，请等待进行审核")){
                ProductOffer productOffer1=jg.get(0);
                productOffer1.setShjg(price);
                productOffer1.setZt("3");
                System.out.println(productOffer1);
                if (baseMapper.updateById(productOffer1)>0){
                    return new Result(ResultCode.SUCCESS,"维护的价格高于原价格，请等待进行审核");
                }
            }

        }
        else if (jsonObject.getString("resultMessage").equals("商品已有报价未审核记录，不能重复报价")){
            return new Result(ResultCode.FAIL,"该商品还在审核中");
        }
        return new Result(ResultCode.FAIL,"异常，报价失败");
    }

    //撤销报价
    @Override
    public Result delProductOfferXhbh(String xhbh){
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
        if(jsonObject.getString("resultMessage").equals("取消审核成功")){
            QueryWrapper<ProductOffer> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("xhbh",xhbh);
            ProductOffer productOffer=baseMapper.selectOne(queryWrapper);
            productOffer.setShjg(new BigDecimal("-1"));
            productOffer.setZt("2");
            if (baseMapper.updateById(productOffer)>0){
                return new Result(ResultCode.SUCCESS,"撤销成功，商品待上架");
            }
        }else if (jsonObject.getString("resultMessage").equals("没有对应的审核记录")){
            return new Result(ResultCode.FAIL,"没有审核记录");
        }
        return new Result(ResultCode.FAIL,"撤销失败");
    }

    //查看所有已报价

    @Override
    public ModelAndView findAllOffer(int nowPage1,int nowPage2,int nowPage3){
        List<ProductOffer> productOfferList1= baseMapper.findProductOffersByZt("1");
        List<ProductOffer> productOfferList2= baseMapper.findProductOffersByZt("2");
        List<ProductOffer> productOfferList3= baseMapper.findProductOffersByZt("3");
        List<Product> productList1=new ArrayList<>();
        for (ProductOffer productOffer:productOfferList1){
            Product product=productMapper.findProductByXhbh(productOffer.getXhbh());
            product.setPrice(productOffer.getPrice());
            product.setZt("1");
            productList1.add(product);
        }
        List<Product> productList2=new ArrayList<>();
        for (ProductOffer productOffer:productOfferList2){
            Product product=productMapper.findProductByXhbh(productOffer.getXhbh());
            product.setPrice(productOffer.getPrice());
            product.setZt("2");
            productList2.add(product);
        }
        List<Product> productList3=new ArrayList<>();
        for (ProductOffer productOffer:productOfferList3){
            Product product=productMapper.findProductByXhbh(productOffer.getXhbh());
            product.setPrice(productOffer.getPrice());
            product.setShjg(productOffer.getShjg());
            product.setZt("3");
            productList3.add(product);
        }
        ModelAndView modelAndView=new ModelAndView();
        Page<Product> page=new Page<Product>();
        page.setLength(5);
        page.setDate(productList1);
        page.setSize();
        productList1=page.getDateByYs(nowPage1);
        modelAndView.addObject("length1",page.getSize());  //返回上架总页数
        page.setDate(productList2);
        page.setSize();
        productList2=page.getDateByYs(nowPage2);
        modelAndView.addObject("length2",page.getSize());  //返回下架总页数
        page.setDate(productList3);
        page.setSize();
        productList3=page.getDateByYs(nowPage3);
        modelAndView.addObject("length3",page.getSize());  //返回待审核总页数
        modelAndView.addObject("productList1",productList1);
        modelAndView.addObject("productList2",productList2);
        modelAndView.addObject("productList3",productList3);
        modelAndView.setViewName("Allquotedgoods");
        modelAndView.addObject("nowPage1",nowPage1);    //上架当前页
        modelAndView.addObject("nowPage2",nowPage2);    //下架当前页
        modelAndView.addObject("nowPage3",nowPage3);    //待审核当前页
        return modelAndView;
    }

    //商品上下架
    @Override
    public Result changeProductOfferZt(String xhbh,String zt,String text){
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        //拼接Json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"xhbh\":\""+xhbh+"\"," +
                "\"zt\":\""+zt+"\"," +
                "\"xjyy\": \""+text+"\"}";
        System.out.println(json);
        JSONObject jsonObject=ClientUtil.getJSONObject(url,readProperties.getExecSpDown(),json);
        System.out.println(jsonObject);
        ProductOffer productOffer=baseMapper.findProductOfferByXhbh(xhbh).get(0);
        if (jsonObject.getString("resultMessage").equals("商品下架成功")){
            productOffer.setZt("2");
            if(baseMapper.updateById(productOffer)>0){
                return new Result(ResultCode.SUCCESS,"商品下架成功");
            }
        }else if(jsonObject.getString("resultMessage").equals("商品上架成功")){
            productOffer.setZt("1");
            if(baseMapper.updateById(productOffer)>0){
                return new Result(ResultCode.SUCCESS,"商品上架成功");
            }
        }
        return new Result(ResultCode.FAIL,"失败");
    }
}
