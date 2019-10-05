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
     */
    @Select("SELECT PJBH FROM parts")
    public List<String> findPJBH();

}
