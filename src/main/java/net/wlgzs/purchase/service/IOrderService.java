package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
public interface IOrderService extends IService<Order> {
    //定时更新接口
    ResultCode updateOrderDate(String userName,String pwd);
    //查询订单
    Result selectOrder();
}
