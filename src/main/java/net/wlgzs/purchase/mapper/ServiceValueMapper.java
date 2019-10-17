package net.wlgzs.purchase.mapper;

import net.wlgzs.purchase.entity.ServiceValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王耀兴
 * @since 2019-09-28
 */
@org.apache.ibatis.annotations.Mapper
public interface ServiceValueMapper extends BaseMapper<ServiceValue> {
    /**
     * 根据服务编号获取服务
     * @param FWBH 服务编号
     * @return ServiceValue  服务
     */
    @Select("SELECT * FROM service_value where FWBH=#{FWBH}")
    public ServiceValue findServiceValueByFWBH(String FWBH);

    /**
     * 根据pmbh品目编号获取增值服务
     * @param pmbh 品目编号
     *
     */
    @Select("SELECT * FROM service_value where pmbh=#{pmbh}")
    public List<ServiceValue> findServiceValueByPmbh(String pmbh);

    /**
     * 根据服务编号FWBH获取服务集合
     */
    @Select("SELECT * FROM service_value where FWBH=#{FWBH}")
    public List<ServiceValue> findServiceValueByFubh(String FWBH);



}
