package net.wlgzs.purchase.service.impl;

import net.wlgzs.purchase.entity.ProductList;
import net.wlgzs.purchase.mapper.ProductListMapper;
import net.wlgzs.purchase.service.IProductListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@Service
public class ProductListServiceImpl extends ServiceImpl<ProductListMapper, ProductList> implements IProductListService {

    @Override
    public Result addProductList(ProductList productList) {
        return null;
    }

    @Override
    public Result delProductList(int productId) {
        return null;
    }

    @Override
    public Result delProductListByOrderId(int orderId) {
        return null;
    }

    @Override
    public Result selectProductList(int orderId) {
        return null;
    }

    @Override
    public Result selectProduct(int productId) {
        return null;
    }

    @Override
    public Result upDateProductList(ProductList productList) {
        return null;
    }
}
