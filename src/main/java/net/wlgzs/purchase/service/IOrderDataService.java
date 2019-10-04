package net.wlgzs.purchase.service;


import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.entity.OrderData;
import net.wlgzs.purchase.util.Result;

import java.math.BigInteger;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-10-03
 */
public interface IOrderDataService extends IService<OrderData> {
    //定时更新接口
    Result updateOrderDate(int pageNum);

    //查询订单
    Result selectAllOrder(Integer pageSize,Integer pageNum);

    //查看单个订单详情
    Result selectOneOrder(String ddbh);

    //确认或取消订单
    Result ensureORefuseOrder(String ddbh,int qrzt);

    //订单签收物流信息推送
    Result ensureOrderArrive(String ddbh,int sfcd,String fczddbh,String kdgs,String kddh,String ms,String kdsj);

    //订单签收时间信息推送
    Result ensureOrderTimeSubmit(String ddbh,int sfcd,String fczddbh,BigInteger shsj);

    //订单发票开具时间信息推送
    Result invoiceStaTimeSubmit(String ddbh, BigInteger fpkjsj);

    //订单发票收到开具信息推送
    Result invoiceEndTimeSubmit(String ddbh,BigInteger fpsdsj);

    //电商已经收到采购单位的付款,将收款标志、收款金额、收款时间提交
    Result getMoneyDataSubmit(String ddbh,int skbz,Integer skje,BigInteger sksj);

    //取消订单(已经对进行订单确认)
    Result deletEnsureOrder(String ddbh,String qxyy);

    //查看采购单位对当前订单的验收情况(可以不存入数据库，只展示)
    Result checkOrderStatus(String ddbh);

    //订单的合同处理
    Result contractWork(String ddbh);


}
