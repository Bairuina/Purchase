package net.wlgzs.purchase.mapper;

import net.wlgzs.purchase.entity.ProductOffer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
@org.apache.ibatis.annotations.Mapper
public interface ProductOfferMapper extends BaseMapper<ProductOffer> {
    /**
     * 根据xhbh获取报价记录
     *
     */
    @Select("SELECT * FROM product_offer WHERE xhbh=#{xhbh}")
    public List<ProductOffer> findProductOfferByXhbh(String xhbh);

}
