package net.wlgzs.purchase.service.impl;

<<<<<<< HEAD
=======
import com.Enxi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
>>>>>>> 4ae3872cb51b1e8b74f2f81504993f908b33dec9
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.Contract;
import net.wlgzs.purchase.mapper.ContractMapper;
import net.wlgzs.purchase.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<<<<<<< HEAD
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
=======
import net.wlgzs.purchase.util.GeneralMethod;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.codehaus.xfire.client.Client;
>>>>>>> 4ae3872cb51b1e8b74f2f81504993f908b33dec9
import org.springframework.stereotype.Service;

import java.net.URL;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 王言
 * @since 2019-09-28
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

<<<<<<< HEAD
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
=======
    @Override
    public Result queryContract(String ddbh) {
        //去合同表中查询
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ddbh", ddbh);
        Contract contract = baseMapper.selectOne(queryWrapper);
        if (contract != null) {
            return new Result(ResultCode.SUCCESS, "查询成功！",contract);
        }
        //本地不存在的时候
        String result = GeneralMethod.queryContract(ddbh);
        JSONObject jsonObject = JSON.parseObject(result);
        if ("Y".equals(jsonObject.getString("resultFlag"))) {
            String url = jsonObject.getString("url");
            //存入本地数据库
            Contract contractOne = new Contract(ddbh, url);
            baseMapper.insert(contractOne);
            return new Result(ResultCode.SUCCESS, "查询成功！", contractOne);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public Result updateContract(String ddbh) {
        //去合同表中查询
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ddbh", ddbh);
        Contract contract = baseMapper.selectOne(queryWrapper);
        //本地不存在的时候
        String result = GeneralMethod.queryContract(ddbh);
        JSONObject jsonObject = JSON.parseObject(result);
        if ("Y".equals(jsonObject.getString("resultFlag"))) {
            String url = jsonObject.getString("url");
            //存入本地数据库
            contract.setContractUrl(url);
            baseMapper.updateById(contract);
            return new Result(ResultCode.SUCCESS, "查询成功！", contract);
        } else {
            return new Result(ResultCode.FAIL);
        }
    }


}
>>>>>>> 4ae3872cb51b1e8b74f2f81504993f908b33dec9
