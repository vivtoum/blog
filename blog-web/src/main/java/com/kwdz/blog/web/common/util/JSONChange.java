package com.kwdz.blog.web.common.util;


import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author LvLvFeng
 * 操作json的封装方法
 * use:jackson
 */
public class JSONChange {
    /**
     * 001.json转换成对象
     *
     * @param targetType 传入对象，json 字符串
     * @return Object
     */
    public static <T> T jsonToObj(Class<T> targetType, String jsonStr) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        T target = null;
//            target = targetType.newInstance();
        target = mapper.readValue(jsonStr, targetType);
        return target;
    }

    /**
     * 002.对象转换成json
     *
     * @param:传入对象
     * @return:json字符串
     */
    public static String objToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}