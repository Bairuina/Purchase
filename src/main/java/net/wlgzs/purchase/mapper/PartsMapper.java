package net.wlgzs.purchase.mapper;

import net.wlgzs.purchase.entity.Parts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface PartsMapper extends BaseMapper<Parts> {
    /**
     * 获取配件编号
     * @return List<String> 所有配件编号
     */
    @Select("SELECT PJBH FROM parts")
    public List<String> findPJBH();

    /**
     * 获取所有配件pmbh
     * @return List<String> 所有配件品目编号
     */
    @Select("SELECT distinct pmbh FROM parts")
    public List<String> findpmbh();

    /**
     * 根据品目编号pmbh获取配件
     * @param pmbh 品目编号
     * @return 该品目编号下的所有配件
     */
    @Select("SELECT * FROM parts where pmbh=#{pmbh}")
    public List<Parts> findPartsByPmbh(String pmbh);

    /**
     * 根据配件编号获取配件
     * @param pjbh 配件编号
     * @return 编号对应的配件
     */
    @Select("SELECT * FROM parts where PJBH=#{pjbh}")
    public Parts findPartsByPjbh(String pjbh);

}
