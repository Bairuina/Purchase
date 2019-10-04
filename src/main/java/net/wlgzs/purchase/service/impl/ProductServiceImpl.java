package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.mapper.ProductMapper;
import net.wlgzs.purchase.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王耀兴
 * @since 2019-09-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Resource
    protected ProductMapper productMapper;

    @Override
    public ModelAndView findallProduct(String lbbh,String pmbh,String ppbh,String nr){
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("lbbh","lbmc");
        List<Product> lbbhlist=new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper)));
        QueryWrapper<Product> queryWrapper1=new QueryWrapper<>();
        queryWrapper1.eq("lbbh",lbbh).select("pmbh","pmmc");
        List<Product> pmbhlist=new ArrayList<>(new HashSet<>(productMapper.selectList(queryWrapper1)));
        QueryWrapper<Product> queryWrapper2=new QueryWrapper<>();
        queryWrapper2.eq("pmbh",pmbh).select("ppbh","ppmc");
        List<Product> ppbhlist=new ArrayList<>(new HashSet<>(productMapper.selectList(queryWrapper2)));
        QueryWrapper<Product> queryWrapper3=new QueryWrapper<>();
        queryWrapper3.eq("lbbh",lbbh).eq("pmbh",pmbh).eq("ppbh",ppbh).like("xhmc",nr)
        .select("product_id","xhbh","xhmc","pmbh","pmmc","ppbh","ppmc","lbbh","lbmc","zt");
        List<Product> productList=new ArrayList<>(new HashSet<>(productMapper.selectList(queryWrapper3)));


        System.out.println(lbbhlist);
        System.out.println(pmbhlist);
        System.out.println(ppbhlist);
        System.out.println(productList);

        ModelAndView modelAndView=new ModelAndView();
        try {
            modelAndView.addObject("lbbhlist", lbbhlist);
            modelAndView.addObject("pmbhlist", pmbhlist);
            modelAndView.addObject("ppbhlist", ppbhlist);
            modelAndView.addObject("productList", productList);
            modelAndView.setViewName("detailsPage");
        }catch (Exception e){
            //返回报错页面
            return new ModelAndView("fghfghghf");
        }
        return modelAndView;
    }

//    @Override
//    public ModelAndView findallProduct1(String lbbh){
//        Set<Product> pmmc1 = productMapper.findpmmc(lbbh);
//        List<Product> pmmc =new ArrayList<>(pmmc1);
//        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
//        queryWrapper.select("xhbh","xhmc","pmbh","pmmc","ppbh","ppmc","lbbh","lbmc","zt");
//        Set<Product> products=new HashSet<>(productMapper.selectList(queryWrapper));
//        System.out.println(products);
//        System.out.println(pmmc);
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("products",products);
//        modelAndView.addObject("pmmc",pmmc);
//        return modelAndView;
//    }
}
