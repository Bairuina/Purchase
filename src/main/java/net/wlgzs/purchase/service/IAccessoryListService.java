package net.wlgzs.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.entity.AccessoryList;
import net.wlgzs.purchase.util.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
public interface IAccessoryListService extends IService<AccessoryList> {
    //添加订单配件
    Result addAccessory(AccessoryList accessoryList);
    //删除订单的订单配件
    Result delAccessoryList(String ddbh);
    //删除单个订单配件
    Result delAccessory(int AccessoryId);
    //查询订单配件列表
    List<AccessoryList> selectAccessoryList(String ddbh);
    //查询订单配件
    Result selectAccessory(int AccessoryId);
    //更新订单配件
    Result upDateAccessory(AccessoryList accessoryList);

}
