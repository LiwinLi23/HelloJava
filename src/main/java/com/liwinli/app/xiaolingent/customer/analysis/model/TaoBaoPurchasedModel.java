package com.liwinli.app.xiaolingent.customer.analysis.model;

import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumDataChannel;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;

import java.util.Date;

public class TaoBaoPurchasedModel {
    private EnumDataChannel.Platform platform = EnumDataChannel.Platform.TAOBAO;
    private Object shoppingName = EnumDataChannel.TaoBaoShopping.XLTOYSSTORE;
    private String orderNumer;
    private EnumECommerceOrder.Type orderType;
    private EnumECommerceOrder.Status orderStatus;
    private Date orderCreateTime;
    private String receiverName;
    private String phone;                                       // 需要扩展
    private String mobilePhone;
    private String province;
    private String city;
    private String area;
    private String detailAddr;
    private float  totalPrice;
    private String refundStatus;
    private float refundAmount;
    private String goodsTitle;
    private int categoryId;
}
