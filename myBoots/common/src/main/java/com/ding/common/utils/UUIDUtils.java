package com.ding.common.utils;

import java.util.UUID;

/**
 * @author ding
 * @create 27 23:50
 * @description
 */
public class UUIDUtils {

    public static String createUUID(){
       return UUID.randomUUID().toString().replace("-","");
    }
}
