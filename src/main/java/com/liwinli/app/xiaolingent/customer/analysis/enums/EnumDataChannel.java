package com.liwinli.app.xiaolingent.customer.analysis.enums;

public class EnumDataChannel {

    public enum FileKeywordAli {
        QIJIANDIAN("旗舰"), TAOBAO("淘宝"), ZHUANYING("专营");

        private String  mKeyword = "淘宝";
        FileKeywordAli(String value) {
            mKeyword = value;
        }

        public String getFileKeyword() {
            return mKeyword;
        }
    }

    public enum FileKeywordWSC {
        WEISHANGCHENG("微商城");

        private String  mKeyword = "微商城";
        FileKeywordWSC(String value) {
            mKeyword = value;
        }

        public String getFileKeyword() {
            return mKeyword;
        }
    }

    public enum Platform {
        TIANMAO("天猫"),
        TAOBAO("淘宝"),
        WEISHANGCHENG("微商城");

        private String mName;
        Platform(String name) { mName = name; }
        public String getName() { return mName; }
    }

    public enum TianMaoShopping {
        LKFAMILY_QIJIAN("伶可家族旗舰店"),
        XLTOYSZHUANYING("小伶玩具专营店");

        private String mName;
        TianMaoShopping(String name) { mName = name; }
        public String getName() { return mName; }
    }

    public enum TaoBaoShopping {
        XLTOYSSTORE("小伶玩具商店");

        private String mName;
        TaoBaoShopping(String name) { mName = name; }
        public String getName() { return mName; }
    }

    public enum WeiShangChengShopping {
        XLTOYS_QIJIAN("小伶玩具旗舰店");

        private String mName;
        WeiShangChengShopping(String name) { mName = name; }
        public String getName() { return mName; }
    }
}
