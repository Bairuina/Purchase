package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.AccessoryList;
import net.wlgzs.purchase.mapper.AccessoryListMapper;
import net.wlgzs.purchase.service.IAccessoryListService;
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
public class AccessoryListServiceImpl extends ServiceImpl<AccessoryListMapper, AccessoryList> implements IAccessoryListService {


    Logger logger= LoggerFactory.getLogger(AccessoryListServiceImpl.class);

    @Override
    public Result addAccessory(AccessoryList accessoryList) {
        int count=baseMapper.insert(accessoryList);
        if(count>=0){
            logger.info("配件信息添加完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("配件信息添加失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public Result delAccessoryList(String ddbh) {
        QueryWrapper<AccessoryList> queryWrapper=new QueryWrapper();
        queryWrapper.eq("ddbh",ddbh);
        int count=baseMapper.delete(queryWrapper);
        if(count>=0){
            logger.info("该订单配件信息删除完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("该订单配件信息删除失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public Result delAccessory(int accessoryId) {
        int count=baseMapper.deleteById(accessoryId);
        if(count>=0){
            logger.info("配件信息删除完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("配件信息删除失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public List<AccessoryList> selectAccessoryList(String ddbh) {
        QueryWrapper<AccessoryList> queryWrapper=new QueryWrapper();
        queryWrapper.eq("ddbh",ddbh);
        List<AccessoryList> lists=baseMapper.selectList(queryWrapper);
        if(lists!=null&&lists.size()!=0) {
            logger.info("订单："+ddbh+" 查询状态：订单配件查询的成功！");
            return lists;
        }
        else {
            logger.info("订单："+ddbh+" 查询状态：订单配件无相关查询结果！");
            return null;
        }
    }

    @Override
    public Result selectAccessory(int accessoryId) {
        AccessoryList accessoryList=baseMapper.selectById(accessoryId);
        if(accessoryList!=null) {
            logger.info("查询的成功！");
            return new Result(ResultCode.SUCCESS,accessoryList);
        }
        else {
            logger.info("无相关查询结果！");
            return new Result(ResultCode.FAIL,"没有相关内容！");
        }
    }

    @Override
    public Result upDateAccessory(AccessoryList accessoryList) {
        int count=baseMapper.updateById(accessoryList);
        if(count>=0){
            logger.info("配件信息更新完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("配件信息更新失败！");
            return new Result(ResultCode.FAIL);
        }

    }

    //根据商品查询订单配件
    @Override
    public List<AccessoryList> getDateAccessoryByProduce(String ddbh, String xhbh, String ppbh) {
        QueryWrapper<AccessoryList> queryWrapper=new QueryWrapper();
        queryWrapper.eq("ddbh",ddbh);
        queryWrapper.eq("XHBH",xhbh);
        queryWrapper.eq("PPBH",ppbh);
        return baseMapper.selectList(queryWrapper);
    }
}
