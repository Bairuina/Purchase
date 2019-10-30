package net.wlgzs.purchase.service.impl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import net.wlgzs.purchase.entity.*;
import net.wlgzs.purchase.mapper.*;
import net.wlgzs.purchase.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.CheckAddress;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    private PartsMapper partsMapper;
    @Resource
    private ServiceValueMapper serviceValueMapper;
    @Resource
    private PartsOfferMapper partsOfferMapper;
    @Resource
    private ServiceOfferMapper serviceOfferMapper;
    @Resource
    private ProductOfferMapper productOfferMapper;

    @Override
    public ModelAndView findallProduct(HttpServletRequest request, String lbmc, String pmmc, String ppmc, String nr,int nowPage){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("detailsPage");
        List<Product> lbbhlist;
        List<Product> pmbhlist;
        List<Product> ppbhlist;
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("lbmc");
        lbbhlist = new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper)));

        QueryWrapper<Product> queryWrapper1=new QueryWrapper<>();
        if (!"0".equals(lbmc)){
            queryWrapper1.eq("lbmc",lbmc);
        }
        queryWrapper1.select("pmmc");
        pmbhlist =new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper1)));

        QueryWrapper<Product> queryWrapper2=new QueryWrapper<>();
        if (!"0".equals(lbmc)){
            queryWrapper2.eq("lbmc",lbmc);
        }
        if (!"0".equals(pmmc)){
            queryWrapper2.eq("pmmc",pmmc);
        }
        queryWrapper2.select("ppmc");
        ppbhlist=new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper2)));



        QueryWrapper<Product> queryWrapper3 = new QueryWrapper<>();
        List<Product> productList;
        if (!"0".equals(lbmc)){
            queryWrapper3.eq("lbmc",lbmc);
        }
        if (!"0".equals(pmmc)){
            queryWrapper3.eq("pmmc",pmmc);
        }
        if (!"0".equals(ppmc)){
            queryWrapper3.eq("ppmc",ppmc);
        }
        queryWrapper3.select("xhbh","xhmc","lbmc","ppmc","pmmc","parametersList");
        Page page = new Page(nowPage, 9);
        IPage<Product> iPage = null;
        iPage=baseMapper.selectPage(page,queryWrapper3);
        productList = iPage.getRecords();

//        Product lbbhProduct=new Product();
//        lbbhProduct.setLbbh(lbbh);
//        lbbhProduct.setLbmc(baseMapper.findLbmcByLbbh(lbbh));
//
//        Product pmbhProduct=new Product();
//        pmbhProduct.setPmbh(pmbh);
//        pmbhProduct.setPmmc(baseMapper.findPmmcByPmbh(pmbh));
//
//        Product ppbhProduct=new Product();
//        ppbhProduct.setPpbh(ppbh);
//        ppbhProduct.setPpmc(baseMapper.findPpmcByPpbh(ppbh));

        //总页数
        modelAndView.addObject("pagesize",iPage.getPages());
        //当前页码
        modelAndView.addObject("pagenumber",iPage.getCurrent());
        //返回当前条件的商品
        modelAndView.addObject("productList", productList);


        //类别集合
        modelAndView.addObject("lbbhlist", lbbhlist);
        //品目集合
        modelAndView.addObject("pmbhlist", pmbhlist);
        //品牌集合
        modelAndView.addObject("ppbhlist", ppbhlist);


        //类别编号及名称
        modelAndView.addObject("lbmc",lbmc);
        //品目编号
        modelAndView.addObject("pmmc",pmmc);
        //品牌编号
        modelAndView.addObject("ppmc",ppmc);
        //内容
        modelAndView.addObject("nr",nr);

        System.out.println("当前品牌"+ppmc);
        System.out.println("当前类别"+lbmc);
        System.out.println("当前品目"+pmmc);
        modelAndView.addObject("msg", new Result(ResultCode.SUCCESS));
        return modelAndView;
    }

    @Override
    public ModelAndView getProductByXhbh(String xhbh){
        ModelAndView modelAndView=new ModelAndView();
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("xhbh",xhbh);
        Product product=baseMapper.selectOne(queryWrapper);
        List<Parts> partsList=partsMapper.findPartsByPmbh(product.getPmbh());
        product.setPrice(productOfferMapper.findProductOfferByXhbh(xhbh).size()==1? productOfferMapper.findProductOfferByXhbh(xhbh).get(0).getPrice():null);
        modelAndView.addObject("product",product);
        List<Parts> pjlist=new ArrayList<>();
        for (Parts parts:partsList){
            Parts parts1=new Parts();
            parts1.setPJMC(parts.getPJMC());
            parts1.setPrice(partsOfferMapper.findPartOffersPriceByXhbhPjbh(xhbh,parts.getPJBH()));
            parts1.setPJBH(parts.getPJBH());
            pjlist.add(parts1);
        }
        modelAndView.addObject("partsList",pjlist);                        //返回所有配件及其曾经报价

        List<ServiceValue> serviceValueList=serviceValueMapper.findServiceValueByPmbh(product.getPmbh());
        List<ServiceValue> fulist=new ArrayList<>();
        for (ServiceValue serviceValue:serviceValueList){
            ServiceValue serviceValue1=new ServiceValue();
            serviceValue1.setFwjg(serviceOfferMapper.findServiceOfferByFubhXhbh(serviceValue.getFWBH(),xhbh));
            serviceValue1.setFWBH(serviceValue.getFWBH());
            serviceValue1.setFwmc(serviceValue.getFwmc());
            fulist.add(serviceValue1);
        }

        modelAndView.addObject("serviceValueList",fulist);                   //返回全部服务及其报价
        List<String[]> areas=CheckAddress.allArea();
        modelAndView.addObject("areas",areas);
        System.out.println(areas.get(0)[0]+areas.get(0)[1]);
        String json=product.getParametersList();
        List<Parameters> parametersList= JSON.parseArray(json,Parameters.class);
        modelAndView.addObject("parametersList",parametersList);
        modelAndView.setViewName("offer");
        return modelAndView;
    }
}
