package com.liwinli.app.xiaolingent.customer.analysis.model;

//import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumDataChannel;
//import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;

import java.util.Date;

public class PurchasedCustomerModel {
    public String platform;                  // 需要扩展
    public String shoppingName;
    public String orderNumer;
    public String orderType;
    public String orderStatus;
    public String orderCreateTime;
    public String receiverName;
    public String phone;                                       // 需要扩展
    public String mobilePhone;
    public String province;
    public String city;
    public String area;
    public double cityLevel;
    public String detailAddr;
    public float totalPrice;
    public String refundStatus;                                // 专营店字段：订单关闭原因 {订单未关闭, 买家未付款, 退款}
    // 微商城字段：订单退款状态 {退款成功（部分退款）,退款成功（全额退款）,退款中}
    public float refundAmount;                                  // 阿里系字段： 退款金额； 微商城字段： 订单已退款金额
    public String goodsTitle;
    public int categoryId;
}
