package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.ProductList;
import net.wlgzs.purchase.mapper.ProductListMapper;
import net.wlgzs.purchase.service.IProductListService;
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
public class ProductListServiceImpl extends ServiceImpl<ProductListMapper, ProductList> implements IProductListService {

    Logger logger= LoggerFactory.getLogger(ProductListServiceImpl.class);
    @Override
    public Result addProductList(ProductList productList) {
        int count=baseMapper.insert(productList);
        if(count>=0){
            logger.info("订单商品信息添加完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("订单商品信息添加失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public Result delProductList(int productId) {
        int count=baseMapper.deleteById(productId);
        if(count>=0){
            logger.info("订单商品信息删除完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("订单商品信息删除失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public Result delProductListByOrderId(String ddbh) {
        QueryWrapper<ProductList> queryWrapper=new QueryWrapper();
        queryWrapper.eq("ddbh",ddbh);
        int count=baseMapper.delete(queryWrapper);
        if(count>=0){
            logger.info("该订单商品信息删除完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("该订单商品信息删除失败！");
            return new Result(ResultCode.FAIL);
        }
    }

    @Override
    public List<ProductList> selectProductList(String ddbh) {
        QueryWrapper<ProductList> queryWrapper=new QueryWrapper();
        queryWrapper.eq("ddbh",ddbh);
        List<ProductList> lists=baseMapper.selectList(queryWrapper);
        if(lists!=null&&lists.size()!=0) {
            logger.info("该订单商品查询的成功！");
            return lists;
        }
        else {
            logger.info("无相关查询结果！");
            return null;
        }
    }

    @Override
    public Result selectProduct(int productId) {
        ProductList productList=baseMapper.selectById(productId);
        if(productList!=null) {
            logger.info("订单商品查询的成功！");
            return new Result(ResultCode.SUCCESS,productList);
        }
        else {
            logger.info("无相关查询结果！");
            return new Result(ResultCode.FAIL,"没有相关内容！");
        }
    }

    @Override
    public Result upDateProductList(ProductList productList) {
        int count=baseMapper.updateById(productList);
        if(count>=0){
            logger.info("配件信息更新完毕！");
            return new Result(ResultCode.SUCCESS);
        }
        else {
            logger.info("配件信息更新失败！");
            return new Result(ResultCode.FAIL);
        }

    }
}
