package net.wlgzs.purchase.mapper;

import net.wlgzs.purchase.entity.ServiceOffer;
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
public interface ServiceOfferMapper extends BaseMapper<ServiceOffer> {
    @Select("SELECT service_offer_id FROM service_offer where fubh=#{fubh} and xhbh=#{xhbh}")
    public List<Integer> findIdByFubhXhbh(String fubh,String xhbh);
}
