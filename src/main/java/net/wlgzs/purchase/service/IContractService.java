package net.wlgzs.purchase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.entity.Contract;
import net.wlgzs.purchase.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
public interface IContractService extends IService<Contract> {
    //添加合同
    Result addContract(Contract contract);
    //查询合同
    Result checkContract(String ddbh);
    //删除合同
    Result delContract(String ddbh);
    //查询所有合同
    Result allContract();
    //更新合同
    Result upDateContract(Contract contract);
}
