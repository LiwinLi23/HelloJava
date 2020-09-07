package com.liwinli.app.xiaolingent.customer.analysis.enums;

public class EnumECommerceOrder {
    public enum Type {
        NORMAL,                         // 普通订单
        GROUP_BOOKING;                  // 拼团
    }

    public enum Status {
        准备发货("准备发货"), 交易关闭("交易关闭"), 已发货("已发货"), 交易成功("交易成功");

        private String mStatus;
        private Status(String status) { mStatus = status; }
        public String getStatus() { return mStatus; }
    }

    public enum AliCloseReason {   // 愚蠢的产品经理把它作为退款状态来使用
        NO_PURCHASED("买家未付款"), NO_CLOSE("订单未关闭"), REFUND("退款");

        private String mReason;
        private AliCloseReason(String reason) { mReason = reason; }
        public String getReason() { return mReason; }
    }

    public enum WscRefundStatus {
        REFUNDING("退款中"), REFUNDED_PORTION("退款成功（部分退款）"), REFUNDED_ALL("退款成功（全额退款）");

        private String mStatus;
        private WscRefundStatus(String status) { mStatus = status; }
        public String getStatus() { return mStatus; }
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
