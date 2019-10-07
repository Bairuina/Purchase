package net.wlgzs.purchase.service.impl;

import com.Enxi;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.mapper.PartsMapper;
import net.wlgzs.purchase.service.IPartsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@Service
public class PartsServiceImpl extends ServiceImpl<PartsMapper, Parts> implements IPartsService {
    @Resource
    ReadProperties readProperties;




    @Override
    public String getPjByPmbhUrl(String pmbh){
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);
        String url=readProperties.getUrl();

        String json="{\"username\":\""+username+"\",\"pwd\":\""+enPwd1+"\",\"pmbh\":\""+pmbh+"\",\"pageNum\":\"1\",\"pageSize\":\"10\"}";
        JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getFindPjByPmbh(),json);
        System.out.println(jsonObject.toString());
        String jsonString= jsonObject.getString("accessoryList");
        System.out.println(jsonString);
        return jsonString;
    }
}
