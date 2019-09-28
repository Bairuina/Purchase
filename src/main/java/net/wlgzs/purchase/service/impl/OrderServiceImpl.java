package net.wlgzs.purchase.service.impl;

import net.wlgzs.purchase.entity.Order;
import net.wlgzs.purchase.mapper.OrderMapper;
import net.wlgzs.purchase.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
