package net.wlgzs.purchase.service.impl;

import com.Enxi;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Parts;
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
    public Result productOffer(String xhbh, BigDecimal price, String text, String fwcn, String productlink, String area){
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
        System.out.println("传出的数据"+json);
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
                productOffer.setXhmc(product.getXhmc());
                productOffer.setLbmc(product.getLbmc());
                productOffer.setPmmc(product.getPmmc());
                productOffer.setPpmc(product.getPpmc());
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
        if(jsonObject.getString("resultFlag").equals("Y")){
            QueryWrapper<ProductOffer> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("xhbh",xhbh);
            ProductOffer productOffer=baseMapper.selectOne(queryWrapper);
            productOffer.setShjg(new BigDecimal("-1"));
            productOffer.setZt("2");
            if (baseMapper.updateById(productOffer)>0){
                return new Result(ResultCode.SUCCESS,"撤销成功，请稍后查询");
            }
        }else if (jsonObject.getString("resultFlag").equals("N")){
            return new Result(ResultCode.FAIL,"没有审核记录");
        }
        return new Result(ResultCode.FAIL,"撤销失败");
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

    //查询类别下的所有品目
    @Override
    public Result SelectLbmc(String lbmc){
        List<String> list=baseMapper.findPmmcByLbmc(lbmc);
        if (list.size()>0) {
            return new Result(ResultCode.SUCCESS, "查询成功", list);
        }
        else {
            return new Result(ResultCode.FAIL, "获取失败");
        }
    }

    //查询品目下的所有品牌
    @Override
    public Result SelectPmmc(String pmmc){
        List<String> list1=baseMapper.findPpmcByPmmc(pmmc);
        if (list1.size()>0){
            return new Result(ResultCode.SUCCESS,"获取成功",list1);
        }else{
            return new Result(ResultCode.FAIL,"获取失败");
        }
    }
    //管理报价页面
    @Override
    public ModelAndView findZt(String zt,String lbmc,String pmmc,String ppmc,String nr,String nowpage){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Allquotedgoods");
        List<ProductOffer> productofferList=new ArrayList<>();
        QueryWrapper<ProductOffer> queryWrapper=new QueryWrapper<>();
        if (!lbmc.equals("0")){
            queryWrapper.eq("lbmc",lbmc);
        }
        if(!pmmc.equals("0")){
            queryWrapper.eq("pmmc",pmmc);
        }
        if (!ppmc.equals("0")){
            queryWrapper.eq("ppmc",ppmc);
        }
        if (!nr.equals("0")){
            queryWrapper.like("xhmc",nr);
        }
        queryWrapper.select("xhbh","xhmc","shjg","price","zt","xhmc","lbmc","pmmc","ppmc");
        Page page =new Page(Integer.valueOf(nowpage),12);
        IPage<ProductOffer> iPage=null;
        iPage=baseMapper.selectPage(page,queryWrapper);
        productofferList=iPage.getRecords();

        modelAndView.addObject("productofferList",productofferList);
        modelAndView.addObject("lbmc",lbmc);
        modelAndView.addObject("pmmc",pmmc);
        modelAndView.addObject("ppmc",ppmc);
        modelAndView.addObject("nr",nr);
        modelAndView.addObject("nowpage",iPage.getCurrent());
        modelAndView.addObject("pagesize",iPage.getPages());
        return modelAndView;
    }
}
