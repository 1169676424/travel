package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @author ply
 * @date 2021/4/11 - 8:53
 */
public interface OrderDao {

    int add(Order order);

    int updateByOrderId(Order order);

    Order findOne(@Param(value = "orderid") String orderid);
}
