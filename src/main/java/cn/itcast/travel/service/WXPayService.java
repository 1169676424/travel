package cn.itcast.travel.service;

import java.util.Map;

/**
 * @author ply
 * @date 2021/4/10 - 14:56
 */
public interface WXPayService {

    //创建订单
    Map<String,String> createNative(String out_trade_no,String total_fee);

    //查询订单状态
    Map<String,String> queryPayStatus(String out_trade_no);

    //关闭订单
    Map<String,String> closeOrder(String out_trade_no);
}
