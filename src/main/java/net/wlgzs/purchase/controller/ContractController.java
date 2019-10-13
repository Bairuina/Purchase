package net.wlgzs.purchase.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.wlgzs.purchase.entity.Contract;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.wlgzs.purchase.base.BaseController;

/**
 * <p>
 *  合同模块 前端控制器
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@RestController
@RequestMapping("/contract")
@Api(value = "合同管理接口", tags = "合同管理接口信息", description = "合同相关接口")
public class ContractController extends BaseController {

    /**
     * 首先通过本地数据库去查询,不存在则远程查询
     * 通过订单编号查看合同
     * @param ddbh 订单编号
     * @return
     */
    @ApiOperation(value = "通过订单编号查看合同")
    @ApiImplicitParam(name = "ddbh",value = "订单编号",required = true)
    @RequestMapping(value = "queryContract",method = RequestMethod.GET)
    public Result queryContract(String ddbh){
        Result result = iContractService.queryContract(ddbh);
        return result;
    }

    /**
     * 更新合同信息
     * @param ddbh 订单编号
     * @return
     */
    @ApiOperation(value = "更新合同信息")
    @ApiImplicitParam(name = "ddbh",value = "订单编号",required = true)
    @RequestMapping(value = "updateContract",method = RequestMethod.PUT)
    public Result updateContract(String ddbh){
        Result result = iContractService.updateContract(ddbh);
        return result;
    }

}

