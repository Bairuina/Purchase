package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.ServiceList;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
public interface IServiceListService extends IService<ServiceList> {
    //添加订单服务
    Result addService(ServiceList serviceList);
    //删除订单服务
    Result delService(int serviceId);
    //根据订单id删除订单服务
    Result delServiceByOrderId(int orderId);
    //查询订单服务列表
    Result selectServiceList(int orderId);
    //查询单个订单服务
    Result selectService(int serviceId);
    //更新订单服务
    Result upDataService(ServiceList serviceList);

}
