package net.wlgzs.purchase.service.impl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.Parameters;
import net.wlgzs.purchase.entity.Parts;
import net.wlgzs.purchase.entity.Product;
import net.wlgzs.purchase.entity.ServiceValue;
import net.wlgzs.purchase.mapper.*;
import net.wlgzs.purchase.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.CheckAddress;
import net.wlgzs.purchase.util.Page;
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
    public ModelAndView findallProduct(HttpServletRequest request, String lbbh, String pmbh, String ppbh, String nr,int nowPage){
        ModelAndView modelAndView=new ModelAndView();
        HttpSession session=request.getSession(true);
        if (lbbh.equals("0") && pmbh.equals("0") &&ppbh.equals("0") && nr.equals("0")){
            session.setAttribute("lbbh",0);
            session.setAttribute("pmbh",0);
            session.setAttribute("ppbh",0);
            session.setAttribute("nr",null);
        }
        else{
            session.setAttribute("lbbh",lbbh);
            session.setAttribute("pmbh",pmbh);
            session.setAttribute("ppbh",ppbh);
            if (!nr.equals("0")){
                session.setAttribute("nr",nr);
            }
        }
        modelAndView.setViewName("detailsPage");
        try {
            List<Product> lbbhlist;
            List<Product> pmbhlist;
            List<Product> ppbhlist;
            QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("lbbh", "lbmc");
            lbbhlist = new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper)));

            QueryWrapper<Product> queryWrapper1=new QueryWrapper<>();
            queryWrapper1.select("pmbh","pmmc");
            queryWrapper1.eq("lbbh","0".equals(lbbh)? lbbhlist.get(0).getLbbh():lbbh);
            pmbhlist =new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper1)));

            QueryWrapper<Product> queryWrapper2=new QueryWrapper<>();
            queryWrapper2.select("ppbh","ppmc");
            queryWrapper2.eq("pmbh","0".equals(pmbh)? pmbhlist.get(0).getPmbh():pmbh);
            ppbhlist=new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper2)));


            QueryWrapper<Product> queryWrapper3 = new QueryWrapper<>();
            List<Product> productList;
            if (lbbh.equals("0")) {
                queryWrapper3.like("xhmc", nr.equals("0") ? "" : nr)
                        .select("product_id", "xhbh", "xhmc", "pmbh", "pmmc", "ppbh", "ppmc", "lbbh", "lbmc", "zt");
                System.out.println(nr);
                productList = new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper3)));
            } else if (!lbbh.equals("0") && pmbh.equals("0")) {
                queryWrapper3.eq("lbbh", lbbh).like("xhmc", nr.equals("0") ? "" : nr)
                        .select("product_id", "xhbh", "xhmc", "pmbh", "pmmc", "ppbh", "ppmc", "lbbh", "lbmc", "zt");
                productList = new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper3)));
            } else if (!lbbh.equals("0") && !pmbh.equals("0") && ppbh.equals("0")) {
                queryWrapper3.eq("lbbh", lbbh).eq("pmbh", pmbh).like("xhmc", nr.equals("0") ? "" : nr)
                        .select("product_id", "xhbh", "xhmc", "pmbh", "pmmc", "ppbh", "ppmc", "lbbh", "lbmc", "zt");
                productList = new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper3)));
            } else {
                queryWrapper3.eq("lbbh", lbbh).eq("pmbh", pmbh).eq("ppbh", ppbh).like("xhmc", nr.equals("0") ? "" : nr)
                        .select("product_id", "xhbh", "xhmc", "pmbh", "pmmc", "ppbh", "ppmc", "lbbh", "lbmc", "zt");
                productList = new ArrayList<>(new HashSet<>(baseMapper.selectList(queryWrapper3)));
            }

            System.out.println(productList);
            System.out.println("***");
            System.out.println(lbbh);
            System.out.println(lbbhlist);
            System.out.println("******");
            System.out.println(pmbh);
            System.out.println(pmbhlist);
            System.out.println("*****");
            System.out.println(ppbh);
            System.out.println(ppbhlist);


            Page<Product> page =new Page<Product>();
            page.setDate(productList);   //传入数据
            page.setLength(5);          //设置每页数量
            page.setSize();              //获取总页数
            productList=page.getDateByYs(nowPage);
            //总页数
            modelAndView.addObject("pagesize",page.getSize());
            //当前页码
            modelAndView.addObject("pagenumber",nowPage);
            //返回当前条件的商品
            modelAndView.addObject("productList", productList);


            //类别集合
            modelAndView.addObject("lbbhlist", lbbhlist);
            //品目集合
            modelAndView.addObject("pmbhlist", pmbhlist);
            //品牌集合
            modelAndView.addObject("ppbhlist", ppbhlist);

            //类别编号
            modelAndView.addObject("lbbh",lbbh);
            //品目编号
            modelAndView.addObject("pmbh",pmbh);
            //品牌编号
            modelAndView.addObject("ppbh",ppbh);
            //内容
            modelAndView.addObject("nr",nr);









        } catch (Exception e) {
            //返回报错页面
            modelAndView.addObject("msg", new Result(ResultCode.FAIL));
        }
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
