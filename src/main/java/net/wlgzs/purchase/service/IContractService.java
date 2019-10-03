package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.Contract;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-29
 */
public interface IContractService extends IService<Contract> {

    /**
    /**
     * 首先通过本地数据库去查询,不存在则远程查询
     * 通过订单编号查看合同
     * @param ddbh 订单编号
     * @return
     */
    Result queryContract(String ddbh);

    /**
     * 更新合同信息
     * @param ddbh 订单编号
     * @return
     */
    Result updateContract(String ddbh);

}