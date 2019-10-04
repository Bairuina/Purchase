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
 * @author 胡亚星
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
     * 根据lbmc返回pmmc和pmbh
     */
    @Select("SELECT pmbh,pmmc,lbbh,lbmc FROM product where lbbh=#{lbbh}")
    public Set<Product> findpmmc(String lbbh);

}
