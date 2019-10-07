package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.ServiceOffer;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
public interface IServiceOfferService extends IService<ServiceOffer> {

    public Result ServiceOffer(ServiceOffer serviceOffer,String ext);

}
