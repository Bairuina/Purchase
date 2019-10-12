package net.wlgzs.purchase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.wlgzs.purchase.entity.ProductList;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@org.apache.ibatis.annotations.Mapper
public interface ProductListMapper extends BaseMapper<ProductList> {
    /**
     * 根据xhbh 和 ddbh 获取数据库里的商品
     */
    @Select("SELECT * FROM product_list where xhbh=#{xhbh} and ddbh=#{ddbh}")
    public List<ProductList> findProductListByXhbhDdbh(String xhbh, String ddbh);

}
