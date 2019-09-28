package net.wlgzs.purchase.service.impl;

import net.wlgzs.purchase.entity.Items;
import net.wlgzs.purchase.mapper.ItemsMapper;
import net.wlgzs.purchase.service.IItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

}
