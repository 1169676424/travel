package cn.itcast.travel.domain;

import java.util.Date;

public class Order {
    private String orderid;   //订单id, 商户订单号
    private String transactionid; //微信订单号
    private double payment;  //订单金额
    private String paymenttype; //订单类型
    private String status;  // 状态,是否支付 0  待支付,1 已支付 2,订单超时关闭,3,客户主动取消关闭
    private Date createtime; //创建时间
    private Date updatetime; //更新时间
    private Date paymenttime; //支付时间
    private Date closetime;   //关闭时间
    private String userid;    //用户ID
    private String seller;   //商家
    private String goods;    //商品

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid='" + orderid + '\'' +
                ", transactionid='" + transactionid + '\'' +
                ", payment=" + payment +
                ", paymenttype='" + paymenttype + '\'' +
                ", status='" + status + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", paymenttime=" + paymenttime +
                ", closetime=" + closetime +
                ", userid='" + userid + '\'' +
                ", seller='" + seller + '\'' +
                ", goods='" + goods + '\'' +
                '}';
    }
}