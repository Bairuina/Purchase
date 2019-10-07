package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.PartsOffer;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-07
 */
public interface IPartsOfferService extends IService<PartsOffer> {
    /**
     * 配件报价
     * @param pjbh 配件编号
     * @param xhbh 商品编号
     * @param pjjg 配件价格
     * @param text 备注信息
     * @return
     */
    public Result offerPart(String pjbh,String xhbh,int pjjg,String text);
}
