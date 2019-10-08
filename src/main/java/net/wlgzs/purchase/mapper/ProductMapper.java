package net.wlgzs.purchase.mapper;

import net.wlgzs.purchase.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王耀兴
 * @since 2019-09-28
 */
@org.apache.ibatis.annotations.Mapper
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 查询部分字段lbmc
     *
     */
    @Select("SELECT lbbh,lbmc FROM product")
    public Set<Product> findlbmc();

    /**
     * 查询部分字段pmmc
     *
     */
    @Select("SELECT pmmc FROM product")
    public Set<String> findpmmc();

    /**
     * 查询部分字段ppmc
     *
     */
    @Select("SELECT ppmc FORM product")
    public Set<String> findppmc();

    /**
     * 返回全部品目编号pmbh
     *
     */
    @Select("SELECT distinct pmbh FROM `product`")
    public List<String> findpmbh();
    /**
     * 返回全部型号编号xhbh
     */
    @Select("SELECT distinct xhbh FROM `product`")
    public List<String> findxhbh();

    /**
     * 根据型号xhbh获取商品
     */
    @Select("SELECT * FROM `product` where xhbh=#{xhbh}")
    public Product findProductByXhbh(String xhbh);

    /**
     * 根据品目编号pmbh获取商品编号xhbh
     */
    @Select("SELECT distinct xhbh FROM `product` where pmbh=#{pmbh}")
    public List<String> findXhbhByPmbh(String pmbh);

    /**
     * 获取商品名称根据商品编号
     */
    @Select("SElECT xhmc FROM `product` where xhbh=#{xhbh}")
    public String findXhmcByXhbh(String xhbh);

}
