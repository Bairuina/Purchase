package net.wlgzs.purchase.controller;


import com.Enxi;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.ServiceOffer;
import net.wlgzs.purchase.entity.ServiceValue;
import net.wlgzs.purchase.service.IServiceOfferService;
import net.wlgzs.purchase.util.ClientUtil;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@RestController
@RequestMapping("/service-offer")
public class ServiceOfferController extends BaseController {
    @Resource
    private IServiceOfferService iServiceOfferService;


    /**
     * 增值服务报价并存入本地库
     * @param serviceOffer 一个增值服务记录
     * @param Text 增值原因(备注)
     * @return
     */
    @ApiOperation(value = "报价增值服务，增加一条增值服务记录")
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "ServiceOffer",required = true),
            @ApiImplicitParam(name = "Text",required = false)
    })
    @RequestMapping(value = "/Offer",method = RequestMethod.PUT)
    public Result ServiceOffer(ServiceOffer serviceOffer,String Text){
        return iServiceOfferService.ServiceOffer(serviceOffer,Text);
    }

}
