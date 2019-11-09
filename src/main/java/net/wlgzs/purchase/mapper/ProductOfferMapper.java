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

    /**
     * 根据传入状态获取该状态的所有报价
     */
    @Select("SELECT * FROM product_offer WHERE zt=#{zt}")
    public List<ProductOffer> findProductOffersByZt(String zt);

    /**
     * 获取全部类别名称
     */
    @Select("SELECT distinct lbmc FROM product_offer")
    public List<String> findAllLbmc();

    /**
     * 获取该类别下的全部品目名称
     */
    @Select("SELECT distinct pmmc FROM product_offer WHERE lbmc=#{lbmc}")
    public List<String> findPmmcByLbmc(String lbmc);

    /**
     * 获取该品目下的所有品牌名称
     */
    @Select("SELECT distinct ppmc FROM product_offer WHERE pmmc=#{pmmc}")
    public List<String> findPpmcByPmmc(String pmmc);

}
