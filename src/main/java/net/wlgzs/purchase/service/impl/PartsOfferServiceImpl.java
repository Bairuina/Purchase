package net.wlgzs.purchase.service.impl;

import com.Enxi;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.entity.PartsOffer;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.mapper.PartsMapper;
import net.wlgzs.purchase.mapper.PartsOfferMapper;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.service.IPartsOfferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PartsOfferServiceImpl extends ServiceImpl<PartsOfferMapper, PartsOffer> implements IPartsOfferService {
    @Autowired
    private ReadProperties readProperties;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PartsMapper partsMapper;
    @Autowired
    private PartsOfferMapper partsOfferMapper;
    @Override
    public Result offerPart(String pjbh, String xhbh, int pjjg, String text){
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
                "\"pjjg\":\""+pjjg+"\"," +
                "\"bjyy\":\""+text+"\"}";
        System.out.println(json);
        JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getQuotedpricePjByPjbh(),json);
        System.out.println(jsonObject);
        if (jsonObject.getString("resultMessage").equals("配件报价成功")){
            PartsOffer partsOffer=new PartsOffer();
            partsOffer.setOfferPrice(BigDecimal.valueOf(pjjg));
            partsOffer.setPjbh(pjbh);
            partsOffer.setXhbh(xhbh);
            List<Integer> Id=baseMapper.findIdByXhbhPjbh(xhbh,pjbh);
            if (Id.size()==0){
                if(baseMapper.insert(partsOffer)>0){
                    return new Result(ResultCode.SUCCESS,"报价成功");
                }
            }else {
                partsOffer.setPartsOfferId(Id.get(0));
                if (baseMapper.updateById(partsOffer) > 0) {
                    return new Result(ResultCode.SUCCESS, "修改报价成功");
                }
            }
        }
        return new Result(ResultCode.FAIL);
    }
}