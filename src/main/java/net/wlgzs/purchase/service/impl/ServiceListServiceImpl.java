package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.ServiceList;
import net.wlgzs.purchase.mapper.ServiceListMapper;
import net.wlgzs.purchase.service.IServiceListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王言
 * @since 2019-09-28
 */
@Service
public class ServiceListServiceImpl extends ServiceImpl<ServiceListMapper, ServiceList> implements IServiceListService {


    Logger logger= LoggerFactory.getLogger(ServiceListServiceImpl.class);
    @Override
    public Result addService(ServiceList serviceList) {
        int count=baseMapper.insert(serviceList);
        if(count>=0){
            logger.info("订单服务添加成功！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("订单服务添加失败！");
            return new Result(ResultCode.FAIL);
        }

    }

    @Override
    public Result delService(int serviceId) {
        int count=baseMapper.deleteById(serviceId);
        if(count>=0){
            logger.info("该服务删除成功！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("该单服务删除失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public  Result delServiceByOrderId(String ddbh) {
        QueryWrapper<ServiceList> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ddbh",ddbh);
        int count=baseMapper.delete(queryWrapper);
        if(count>=0){
            logger.info("订单服务添加成功！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("订单服务添加失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public  List<ServiceList> selectServiceList(String ddbh) {
        QueryWrapper<ServiceList> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ddbh",ddbh);
        List<ServiceList> lists=baseMapper.selectList(queryWrapper);
        if(lists!=null&&lists.size()!=0) {
            logger.info("订单："+ddbh+" 查询状态：订单服务查询的成功！");
            return lists;
        }
        else {
            logger.info("订单："+ddbh+" 查询状态：订单服务无相关查询结果！");
            return null;
        }
    }

    @Override
    public Result selectService(int serviceId) {
        ServiceList serviceList=baseMapper.selectById(serviceId);
        if(serviceList!=null) {
            logger.info("查询的成功！");
            return new Result(ResultCode.SUCCESS,serviceList);
        }
        else {
            logger.info("无相关查询结果！");
            return new Result(ResultCode.FAIL,"没有相关内容！");
        }
    }

    @Override
    public Result upDataService(ServiceList serviceList) {
        int count=baseMapper.updateById(serviceList);
        if(count>=0){
            logger.info("服务信息更新完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("服务信息更新失败！");
            return new Result(ResultCode.FAIL);
        }
    }


}
