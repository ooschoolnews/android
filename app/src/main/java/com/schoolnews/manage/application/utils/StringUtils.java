package com.schoolnews.manage.application.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 字符串相关工具类
 */


public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * @param time 时间毫秒值
     */
    public static String timeFormat(long time) {
        return timeFormat("yyyy-MM-dd HH:mm:ss", time);
    }

    /**
     * @param formatStr
     * @param time      时间毫秒值
     * @return
     */
    public static String timeFormat(String formatStr, long time) {
        if (time <= 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return sdf.format(calendar.getTime());
    }

    /**
     * 判断字符串是否为null或长度为0
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }
}
