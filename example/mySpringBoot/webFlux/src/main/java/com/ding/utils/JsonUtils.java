package com.ding.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.K;

import java.util.Map;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-10-22 17:46:13
 **/
public class JsonUtils {

    public static Map<String,Object> ObjectToMap(Object object){
        return  new ObjectMapper().convertValue(object, new  TypeReference<Map<String,Object>>() {});
    }
}
