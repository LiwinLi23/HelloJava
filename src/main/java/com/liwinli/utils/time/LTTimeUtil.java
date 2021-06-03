package com.liwinli.utils.time;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LTTimeUtil {
    public static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间

    public static Map<String, Integer> getYMD(String string) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (StringUtils.isBlank(string)) {
            return map;
        }

        Date date = null;
        try {
            date = sDateFormat.parse(string);
        } catch (Exception e) {
            return map;
        }

        if (null != date) {
            int year = date.getYear() + 1900;
            int month = date.getMonth() + 1;
            map.put("year", Integer.valueOf(year));
            map.put("month", Integer.valueOf(month));
        }

        return map;
    }

    public static Date getDate(String dateStr) {
        Date date = null;
        try {
            date = sDateFormat.parse(dateStr);
        } catch (Exception e) {
            ;
        }

        return date;
    }
}
