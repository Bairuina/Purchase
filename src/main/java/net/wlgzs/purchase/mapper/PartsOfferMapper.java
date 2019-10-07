package net.wlgzs.purchase.mapper;

import net.wlgzs.purchase.entity.PartsOffer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
public interface PartsOfferMapper extends BaseMapper<PartsOffer> {

    @Select("SELECT parts_offer_id FROM parts_offer WHERE xhbh=#{xhbh} and pjbh=#{pjbh}")
    public List<Integer> findIdByXhbhPjbh(String xhbh, String pjbh);

}
