package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.OrderDao;
import cn.itcast.travel.domain.Order;
import cn.itcast.travel.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ply
 * @date 2021/4/11 - 8:52
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public int add(Order order) {
        order.setStatus("0");
        Date createtime = new Date();
        order.setCreatetime(createtime);
        order.setUpdatetime(createtime);
        int i = orderDao.add(order);
        return i;
    }

    @Override
    public int updateStatusSuccess(String orderId) {
        //更新状态为成功状态
        Order order = new Order();
        order.setOrderid(orderId);
        order.setStatus("1");
        Date updateTime = new Date();
        order.setUpdatetime(updateTime);
        order.setPaymenttime(updateTime);
        int i = orderDao.updateByOrderId(order);
        return i;
    }

    @Override
    public Order findOne(String orderId) {
        //查询订单
        Order order = orderDao.findOne(orderId);
        return order;
    }

    @Override
    public int updateStatusTimeOut(String orderId) {
        //更新状态为超时未支付
        Order order = new Order();
        order.setOrderid(orderId);
        order.setStatus("2");
        Date updateTime = new Date();
        order.setUpdatetime(updateTime);
        order.setClosetime(updateTime);
        int i = orderDao.updateByOrderId(order);
        return i;
    }


}
