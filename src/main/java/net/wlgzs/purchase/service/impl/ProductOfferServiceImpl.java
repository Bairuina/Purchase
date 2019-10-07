package net.wlgzs.purchase.service.impl;

import com.Enxi;
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
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
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


    @Override
    public ModelAndView productOffer(String xhbh, BigDecimal price, String text){
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        Product product = productMapper.findProductByXhbh(xhbh);
        List<Parts> parts=partsMapper.findPartsByPmbh(product.getPmbh());
        List<BigDecimal> pjjgList=partsOfferMapper.findPartsPriceByXhbh(xhbh);
        String pjxxList;
        if (parts.size()==0){
            pjxxList="";
        }else {
            pjxxList = "[{\"pjmc\":\"" + parts.get(0).getPJMC() + "\",\"pjjg\":\"0\",\"sxh\":\"" + parts.get(0).getRN() + "\",\"pjbh\":\"" + parts.get(0).getPJBH() + "\"}";
            for (int i = 1; i < parts.size(); i++) {
                pjxxList += ",{\"pjmc\":\"" + parts.get(i).getPJMC() + "\",\"pjjg\":\"0\",\"sxh\":\"" + parts.get(i).getRN() + "\",\"pjbh\":\"" + parts.get(i).getPJBH() + "\"}";
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
                "\"area\":\"00390019\"," +
                "\"fwcn\":\"郑州以及周边一天内送货，其他地市二天；提供上门安装及后期维修服务\"," +
                "\"sjjg\": \""+price+"\"," +
                "\"productlink\":\"http://www.staples.cn/product/1100008501EA\"," +
                "\"pjxxList\":"+ pjxxList +","+
                "\"jgsfyy\":\""+text+"\"}";
        System.out.println(json);
        JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getExecute(),json);



        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("");
        return modelAndView;
    }
}
