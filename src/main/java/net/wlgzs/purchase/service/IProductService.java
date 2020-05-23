package net.wlgzs.purchase.service;


import net.sf.json.JSONObject;
import net.wlgzs.purchase.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */

public interface IProductService extends IService<Product> {

    /**
     * 查询页
     * 需要遍历lbmc，pmmc，ppmc
     * 还有全部商品列表
     */
    public ModelAndView findallProduct(HttpServletRequest request,String lbmc, String pmmc, String ppmc, String nr,int nowPage);

    public ModelAndView getProductByXhbh(String xhbh);

//    public JSONObject FindSpByXhbh(String xhbh);
//
//    public Result updateByFindSpByXhbh(JSONObject jsonObject);
}
