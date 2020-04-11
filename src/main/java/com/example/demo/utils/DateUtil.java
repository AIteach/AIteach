package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat formatter;

    static {
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static String dateFormat(Date date) {
        return formatter.format(date);
    }
}
