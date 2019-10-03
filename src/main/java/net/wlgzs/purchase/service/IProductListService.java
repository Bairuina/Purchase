package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.ProductList;
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
public interface IProductListService extends IService<ProductList> {
    //添加订单商品
    Result addProductList(ProductList productList);
    //删除订单商品
    Result delProductList(int productId);
    //根据订单删除订单商品
    Result delProductListByOrderId(int orderId);
    //查询订单商品列表
    Result selectProductList(int orderId);
    //查询单个订单商品
    Result selectProduct(int productId);
    //更新订单商品
    Result upDateProductList(ProductList productList);

}
