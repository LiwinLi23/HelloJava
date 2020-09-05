package com.liwinli.app.xiaolingent.customer.analysis.enums;

public class EnumDataChannel {

    public enum FileKeywordAli {
        QIJIANDIAN("旗舰"), TAOBAO("淘宝"), ZHUANYING("专营");

        private String  mKeyword = "淘宝";
        private FileKeywordAli(String value) {
            mKeyword = value;
        }

        public String getFileKeyword() {
            return mKeyword;
        }
    }

    public enum FileKeywordWSC {
        WEISHANGCHENG("微商城");

        private String  mKeyword = "微商城";
        private FileKeywordWSC(String value) {
            mKeyword = value;
        }

        public String getFileKeyword() {
            return mKeyword;
        }
    }

    public enum Platform {
        TIANMAO,
        TAOBAO,
        WEISHANGCHENG;
    }

    public enum TianMaoShopping {
        LKFAMILY_QIJIAN,
        XLTOYSZHUANYING;
    }

    public enum TaoBaoShopping {
        XLTOYSSTORE;
    }

    public enum WeiShangChengShopping {
        XLTOYS_QIJIAN;
    }
}
