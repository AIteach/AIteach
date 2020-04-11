package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 84271
 */
public class DateUtils {

    private static final SimpleDateFormat formatter;

    static {
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public static String dateFormat(Date date) {
        return formatter.format(date);
    }
}
