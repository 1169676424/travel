package cn.itcast.travel.service;

import cn.itcast.travel.domain.Order;

/**
 * @author ply
 * @date 2021/4/11 - 8:52
 */
public interface OrderService {

    //添加订单
    int add(Order order);

    //更新状态为成功状态
    int updateStatusSuccess(String orderId);

    //查询订单
    Order findOne(String orderId);

    //更新状态为关闭状态
    int updateStatusTimeOut(String orderId);


}
