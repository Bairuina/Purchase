package net.wlgzs.purchase.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.Contract;
import net.wlgzs.purchase.mapper.ContractMapper;
import net.wlgzs.purchase.service.IContractService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.GeneralMethod;
import net.wlgzs.purchase.util.ReadProperties;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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

    @Autowired
    private ReadProperties readProperties;

    @Override
    public Result queryContract(String ddbh) {
        //去合同表中查询
        QueryWrapper<Contract> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ddbh", ddbh);
        Contract contract = baseMapper.selectOne(queryWrapper);
        if (contract != null) {
            return new Result(ResultCode.SUCCESS, "查询成功！", contract);
        }
        //本地不存在的时候
        String result = GeneralMethod.queryContract(ddbh, readProperties);
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
        String result = GeneralMethod.queryContract(ddbh, readProperties);
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
