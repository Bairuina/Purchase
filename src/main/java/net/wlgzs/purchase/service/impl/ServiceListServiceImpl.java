package net.wlgzs.purchase.service.impl;

import net.wlgzs.purchase.entity.ServiceList;
import net.wlgzs.purchase.mapper.ServiceListMapper;
import net.wlgzs.purchase.service.IServiceListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@Service
public class ServiceListServiceImpl extends ServiceImpl<ServiceListMapper, ServiceList> implements IServiceListService {

    @Override
    public Result addService(ServiceList serviceList) {
        return null;
    }

    @Override
    public Result delService(int serviceId) {
        return null;
    }

    @Override
    public Result delServiceByOrderId(int orderId) {
        return null;
    }

    @Override
    public Result selectServiceList(int orderId) {
        return null;
    }

    @Override
    public Result selectService(int serviceId) {
        return null;
    }

    @Override
    public Result upDataService(ServiceList serviceList) {
        return null;
    }
}
