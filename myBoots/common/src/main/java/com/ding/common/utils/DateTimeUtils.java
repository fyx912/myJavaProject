package com.ding.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * 日期处理工具类
 * @auther: tinTin
 * @date:
 */
public class DateTimeUtils {
    /**
     *
     * @Description: yyyy-MM-dd hh:mm:ss格式
     * @auther: tinTin
     * @date:
     * @param:
     * @return:
     */
    public static String dateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  dateFormat.format(date);
    }

    public static Date dateFormat(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return  dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
