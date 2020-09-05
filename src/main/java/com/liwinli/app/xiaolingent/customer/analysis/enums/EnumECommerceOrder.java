package com.liwinli.app.xiaolingent.customer.analysis.enums;

public class EnumECommerceOrder {
    public enum Type {
        NORMAL,                         // 普通订单
        GROUP_BOOKING;                  // 拼团
    }

    public enum Status {
        PREPARE_DELIVER,
        TRANSACTION_CLOSE,
        DELIVERED,
        TRANSACTION_FINISH;
    }

    public enum RefundStatus {
        NO_PURCHASED, NO_CLOSE, REFUNDING, REFUNDED_PORTION, REFUNDED_ALL;
    }

    public enum BrushShopping {
        TAOBAO("淘宝店"), QIJIAN("旗舰店"), ZHUANYING("专营店"), NULL_BS("空刷店");

        private String  mName = "";
        private BrushShopping(String name) {
            mName = name;
        }

        public String getName() {
            return mName;
        }
    }
}
