package net.wlgzs.purchase.service.impl;

import com.Enxi;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.ServiceOffer;
import net.wlgzs.purchase.entity.ServiceValue;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.mapper.ServiceOfferMapper;
import net.wlgzs.purchase.mapper.ServiceValueMapper;
import net.wlgzs.purchase.service.IServiceOfferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ServiceOfferServiceImpl extends ServiceImpl<ServiceOfferMapper, ServiceOffer> implements IServiceOfferService {

    @Resource
    private ReadProperties readProperties;
    @Resource
    private ServiceValueMapper serviceValueMapper;
    @Resource
    private ProductMapper productMapper;


    @Override
    public Result ServiceOffer(ServiceOffer serviceOffer,String Text){
        String xhbh=serviceOffer.getXhbh();
        String xhmc=productMapper.findXhmcByXhbh(xhbh);
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        ServiceValue serviceValue=serviceValueMapper.findServiceValueByFWBH(serviceOffer.getFubh());
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"pmbh\":\""+serviceValue.getPmbh()+"\"," +
                "\"xhbh\":\""+xhbh+"\","+
                "\"xhmc\":\""+xhmc+"\","+
                "\"pmmc\":\""+serviceValue.getPmmc()+"\"," +
                "\"fwbh\":\""+serviceValue.getFWBH()+"\"," +
                "\"fwmc\":\""+serviceValue.getFwmc()+"\"," +
                "\"fwjg\":\""+serviceOffer.getFujg()+"\"," +
                "\"bjyy\":\""+Text+"\"," +
                "\"zt\":\""+serviceValue.getZt()+"\"}";
        System.out.println(json);
        JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getQuotedpriceFwByFwbh(),json);
        if(jsonObject.getString("resultMessage").equals("服务报价成功")){
            List<Integer> id=baseMapper.findIdByFubhXhbh(serviceOffer.getFubh(),serviceOffer.getXhbh());
            if (id.size()==0){
                if (baseMapper.insert(serviceOffer)>0) {
                    return new Result(ResultCode.SUCCESS, "服务报价成功");
                }
            }else {
                serviceOffer.setServiceOfferId(id.get(0));
                if (baseMapper.updateById(serviceOffer)>0){
                    return new Result(ResultCode.SUCCESS,"修改服务报价成功");
                }
            }
            return new Result(ResultCode.FAIL,"本地存记录失败");
        }else if (jsonObject.get("resultMessage").equals("未找到对应的商品型号报价信息")){
            return new Result(ResultCode.FAIL,"请先对该商品报价");
        }
        return new Result(ResultCode.FAIL,"报价失败");
    }
}
