package com.liwinli.app.xiaolingent.customer.analysis.model;

import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;

import java.util.ArrayList;
import java.util.List;


public class AliBrushOrderModel {
    public EnumECommerceOrder.BrushShopping shopping = EnumECommerceOrder.BrushShopping.NULL_BS;
    public int year;
    public int month;
    public List<String> orderList = new ArrayList<String>();
}
