package cn.itcast.travel.controller;

import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.service.OrderService;
import cn.itcast.travel.service.WXPayService;
import cn.itcast.travel.util.PayUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * @author ply
 * @date 2021/4/11 - 8:51
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private WXPayService wxPayService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultInfo add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //1.获取订单信息
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2.封装order对象
        Order order = new Order();
        BeanUtils.populate(order, parameterMap);

        //3、添加订单信息到数据库
        String id = PayUtil.getId("01");//生成订单ID,商户订单号
        order.setOrderid(id);
        int i = orderService.add(order);
        if (i <= 0) {
            return new ResultInfo(false,"创建订单失败");
        }

        //4、调用微信API,获取支付二维码链接
        String payment = request.getParameter("payment");
        String money = PayUtil.getMoney(payment); //支付金额单位分
        Map<String,String> map = wxPayService.createNative(id, money);
        map.put("orderId", id);
        map.put("total_fee", money);
        return new ResultInfo(true, map, "");
    }

    @RequestMapping(value = "/findPayStatus",method = RequestMethod.POST)
    public ResultInfo findPayStatus(String orderId){
        Map<String,String> map = wxPayService.queryPayStatus(orderId);
        if (map == null) {
            return new ResultInfo(false,"-1","支付发生错误");
        }

        //订单是否支付成功
        if ("SUCCESS".equals(map.get("trade_state"))) {
            orderService.updateStatusSuccess(orderId);
            return new ResultInfo(true,"1","支付成功");
        }

        //订单是否超时
        Order order = orderService.findOne(orderId);
        Date createtime = order.getCreatetime();
        if (new Date().getTime() - createtime.getTime() > 1000 * 20) {
            ResultInfo resultInfo = close(orderId);
            return resultInfo;
        }
        return new ResultInfo(false,"0","未支付");
    }

    //关闭订单
    private ResultInfo close(String orderId) {
        Map<String,String> map = wxPayService.closeOrder(orderId);
        if (map == null){
            return new ResultInfo(false,"-1","支付发生错误");
        }

        String result_code = map.get("result_code");
        String err_code = map.get("err_code");
        if ("FALL".equals(result_code) && "ORDERPAID".equals(err_code)) {
            orderService.updateStatusSuccess(orderId);
            return new ResultInfo(true,"1","支付成功");
        }

        //返回结果只要不是支付成功，都认为关闭成功
        orderService.updateStatusTimeOut(orderId);
        return new ResultInfo(false,"2","订单超时");
    }
}
