package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.Contract;
import net.wlgzs.purchase.mapper.ContractMapper;
import net.wlgzs.purchase.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王言
 * @since 2019-09-28
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

    //添加合同
    @Override
    public Result addContract(Contract contract) {
        int count=baseMapper.insert(contract);
        if(count>=0){
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    //查询合同
    @Override
    public Result checkContract(String ddbh) {
        QueryWrapper<Contract> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ddbh",ddbh);
        return new Result(ResultCode.SUCCESS,baseMapper.selectOne(queryWrapper));
    }

    //删除合同
    @Override
    public Result delContract(String ddbh) {
        QueryWrapper<Contract> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ddbh",ddbh);
        int count=baseMapper.delete(queryWrapper);
        if(count>=0){
            return new Result(ResultCode.SUCCESS);
        }
        return new Result(ResultCode.FAIL);
    }

    //所有合同
    @Override
    public Result allContract() {
        return new Result(ResultCode.SUCCESS,baseMapper.selectList(null));
    }



    //更新合同
    @Override
    public Result upDateContract(Contract contract) {
        if(contract.getContractId()!=null){
            int count=baseMapper.updateById(contract);
            if(count>=0){
                return new Result(ResultCode.SUCCESS);
            }
            return new Result(ResultCode.FAIL);
        }
        else if(contract.getDdbh()!=null&&!contract.getDdbh().equals("")){
            QueryWrapper<Contract> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("ddbh",contract.getDdbh());
            int count=baseMapper.update(contract,queryWrapper);
            if(count>=0){
                return new Result(ResultCode.SUCCESS);
            }
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.FAIL);
    }

}
