package net.wlgzs.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.entity.ServiceList;
import net.wlgzs.purchase.util.Result;

import java.util.List;

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
    Result delServiceByOrderId(String ddbh);
    //查询订单服务列表
    List<ServiceList> selectServiceList(String ddbh);
    //查询单个订单服务
    Result selectService(int serviceId);
    //更新订单服务
    Result upDataService(ServiceList serviceList);

}
