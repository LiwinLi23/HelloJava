package com.liwinli.app.xiaolingent.customer.analysis.constant;

import java.text.SimpleDateFormat;

public class Constants {
    public static final String BRUSH_FILE_KEYWORD = "店铺刷单";
    public static final String CITY_LEVEL_FILE_KEYWORD = "城市等级编号";

    public static final String CITY_LEVEL_SHEET_KEYWORD = "标注";

    public static final String[] ALITableColumns = {"订单编号", "买家会员名", "买家支付宝账号", "支付单号", "支付详情", "买家应付货款", "买家应付邮费",
            "买家支付积分", "总金额", "返点积分", "买家实际支付金额", "买家实际支付积分", "订单状态", "买家留言", "收货人姓名", "收货地址", "运送方式",
            "联系电话", "联系手机", "订单创建时间", "订单付款时间", "宝贝标题", "宝贝种类", "物流单号", "物流公司", "订单备注", "宝贝总数量",
            "店铺Id", "店铺名称", "订单关闭原因", "卖家服务费", "买家服务费", "发票抬头", "是否手机订单", "分阶段订单信息", "特权订金订单id",
            "是否上传合同照片", "是否上传小票", "是否代付", "定金排名", "修改后的sku", "修改后的收货地址", "异常信息", "天猫卡券抵扣", "集分宝抵扣",
            "是否是O2O交易", "满返红包金额", "新零售交易类型", "新零售导购门店名称", "新零售导购门店id", "新零售发货门店名称", "新零售发货门店id",
            "退款金额", "预约门店", "确认收货时间", "打款商家金额", "含应开票给个人的个人红包", "是否信用购", "体验期结束时间", "前N有礼", "配送类型",
            "直播返现状态", "返现金额", "新零售成交门店昵称", "新零售成交门店id", "新零售成交经销商id", "主订单编号"};

    public static final String ALI_ORDER_SN_COLUMN = "订单编号";

    public static final String[] ALI_BRUSH_ORDER_COLUMNS = {"序号", "接单时间", "旺旺名称", "实际发生金额", "买手垫付金额", "商家支付总佣金", "淘宝订单编号"};

    public static final String[] CITY_LEVEL_COLUMNS = {"编号", "邮编", "省/直辖市/特别行政区", "省会城市/下属市", "下属区", "批注", "城市等级编号"};


    public static final String[] WSCTableColumns = {"订单号", "归属店铺", "订单类型", "订单状态", "推广方式", "订单来源", "订单创建时间", "买家付款时间", "交易成功时间",
            "付款方式", "外部订单号", "支付流水号", "商品金额合计", "运费", "店铺优惠合计", "支付营销优惠", "应收订单金额", "订单实付金额", "店铺优惠方式", "全部商品名称",
            "商品种类数", "订单配送方式", "预约同城送达时间/预约自提时间", "收货人/提货人", "收货人手机号/提货人手机号", "收货人省份", "收货人城市", "收货人地区",
            "详细收货地址/提货地址", "买家备注", "订单星级", "买家姓名", "买家是否会员", "买家手机号", "下单网点", "订单退款状态", "订单已退款金额", "商家订单备注", "优惠券码名称"};

    public static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
}
