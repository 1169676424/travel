package cn.itcast.travel.service.impl;

import cn.itcast.travel.service.WXPayService;
import cn.itcast.travel.util.PayConfig;
import com.github.wxpay.sdk.WXPay;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ply
 * @date 2021/4/10 - 14:57
 */
@Service
public class WXPayServiceImpl implements WXPayService {


    @Override//创建订单
    public Map<String, String> createNative(String out_trade_no, String total_fee) {
        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");          //商品描述
        data.put("out_trade_no", out_trade_no);//订单号
        data.put("fee_type", "CNY");           //币种 CNY人民币
        data.put("total_fee", total_fee);      //金额
        data.put("spbill_create_ip", "127.0.0.1");  //支持IPV4和IPV6两种格式的IP地址，用户的客户端IP
        data.put("notify_url", "http://www.example.com/wxpay/notify");//通知地址  异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
        data.put("trade_type", "NATIVE"); // 此处指定为扫码支付
        data.put("product_id", "12");     //商品ID  trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override//查询订单状态
    public Map<String, String> queryPayStatus(String out_trade_no) {
        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);

        try {
            Map<String, String> resp = wxpay.orderQuery(data);
            System.out.println(resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override//关闭订单
    public Map<String, String> closeOrder(String out_trade_no) {
        PayConfig config = new PayConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);

        try {
            Map<String, String> resp = wxpay.closeOrder(data);
            System.out.println(resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

  public static void main(String[] args) {
    WXPayServiceImpl wxPayService = new WXPayServiceImpl();
      //wxPayService.createNative("29383376282736453728","1");
      /*wxPayService.closeOrder("2021041115535101288686");
      wxPayService.queryPayStatus("2021041115535101288686");*/

  }
}
