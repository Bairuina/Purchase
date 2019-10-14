package net.wlgzs.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.entity.ProductList;
import net.wlgzs.purchase.util.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.List;

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
    Result delProductListByOrderId(String ddbh);
    //查询订单商品列表
    List<ProductList> selectProductList(String ddbh);
    //查询单个订单商品
    Result selectProduct(int productId);
    //更新订单商品
    Result upDateProductList(ProductList productList);

    //添加商品唯一标识图片字段
    Result upProductListJpg(String wybs, String xhbh, String ddbh, MultipartFile myFileName, HttpServletRequest httpServletRequest) throws MalformedURLException;

}
