package net.wlgzs.purchase.service.impl;

import com.Enxi;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.ProductList;
import net.wlgzs.purchase.mapper.ProductListMapper;
import net.wlgzs.purchase.service.IProductListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
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

    @Resource
    private ReadProperties readProperties;


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
            logger.info("订单："+ddbh+" 商品查询状态：该订单商品查询的成功！");
            return lists;
        }
        else {
            logger.info("订单："+ddbh+" 商品查询状态：该订单商品无相关商品查询结果！");
            return null;
        }
    }

    @Override
    public Result selectProduct(int productId) {
        ProductList productList=baseMapper.selectById(productId);
        if(productList!=null) {
            logger.info("该商品查询的成功！");
            return new Result(ResultCode.SUCCESS,productList);
        }
        else {
            logger.info("该商品无相关查询结果！");
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
    @Override
    public Result upProductListJpg(String wybs, String xhbh, String ddbh,MultipartFile myFileName){
        List<ProductList> list=baseMapper.findProductListByXhbhDdbh(xhbh, ddbh);

        //准备工作
        String url=readProperties.getUrl();
        String username=readProperties.getUsername();
        String pwd=readProperties.getPwd();
        String enPwd1= Enxi.enPwd(username,pwd);

        String pic="";

        try {
            File picFile = null;
            if (myFileName.equals("")||myFileName.getSize()<=0){
                myFileName=null;
            }else{
                InputStream ins =null;
                ins= myFileName.getInputStream();
                picFile =new File(myFileName.getOriginalFilename());

            }
            FileInputStream fis = new FileInputStream(picFile);
            byte[] arr = new byte[1024 * 1024 * 5];
            byte[] arrByte = new byte[1024 * 1024 * 5];
            int read = fis.read(arr);
            pic = Base64.getEncoder().encodeToString(arr);
        }catch (IOException e){
            System.out.println("数据异常");
        }

        //拼接Json
        String json="{\"username\":\""+username+"\"," +
                "\"pwd\":\""+enPwd1+"\"," +
                "\"ddbh\":\""+ddbh+"\"," +
                "\"xhbh\":\""+xhbh+"\"," +
                "\"wybs\":\""+wybs+"\"," +
                "\"pic\": \""+pic+"\"}";
        System.out.println(json);
        JSONObject jsonObject= ClientUtil.getJSONObject(url,readProperties.getExecTsWybs(),json);
        System.out.println(jsonObject);
        if ("Y".equals(jsonObject.getString("resultFlag"))&&"推送唯一标识成功".equals(jsonObject.getString("resultMessage"))){

        }
        return new Result(ResultCode.SUCCESS,"成功");
    }
}
