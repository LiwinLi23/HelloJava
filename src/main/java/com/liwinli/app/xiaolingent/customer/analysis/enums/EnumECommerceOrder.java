package com.liwinli.app.xiaolingent.customer.analysis.enums;

public class EnumECommerceOrder {
    public enum Type {
        NORMAL("普通订单"),
        GROUP_BOOKING("拼团");

        private String mType;

        private Type(String t) {
            mType = t;
        }

        public String getType() {
            return mType;
        }
    }

    public enum Status {
        WAIT_SEND("待发货"), PREPARE_SEND("准备发货"), CLOSED("交易关闭"), SENDED("已发货"), SENDED_AND_WAITING_CONFIRM("卖家已发货，等待买家确认"),
        SUCCESS("交易成功"), FINISHED("交易完成");

        private String mStatus;

        private Status(String status) {
            mStatus = status;
        }

        public String getStatus() {
            return mStatus;
        }
    }

    public enum AliCloseReason {   // 愚蠢的产品经理把它作为退款状态来使用
        NO_PURCHASED("买家未付款"), NO_CLOSE("订单未关闭"), REFUND("退款");

        private String mReason;

        private AliCloseReason(String reason) {
            mReason = reason;
        }

        public String getReason() {
            return mReason;
        }
    }

    public enum WscRefundStatus {
        REFUNDING("退款中"), REFUNDED_PORTION("退款成功（部分退款）"), REFUNDED_ALL("退款成功（全额退款）");

        private String mStatus;

        private WscRefundStatus(String status) {
            mStatus = status;
        }

        public String getStatus() {
            return mStatus;
        }
    }


    public enum BrushShopping {
        TAOBAO("淘宝店"), QIJIAN("旗舰店"), ZHUANYING("专营店"), NULL_BS("空刷店");

        private String mName = "";

        private BrushShopping(String name) {
            mName = name;
        }

        public String getName() {
            return mName;
        }
    }
}
