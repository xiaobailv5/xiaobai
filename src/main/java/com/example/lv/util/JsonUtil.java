package com.example.lv.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @project xiaobai
 * @description json工具类
 * @author gxjh2
 * @date 2024/9/22 08:48:35
 * @version 1.0
 */
public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapper;

    private static ObjectMapper MAPPER = new ObjectMapper();
    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 对象转json字符串
     * @param object
     * @return
     */
    public static String convertObject2Json(Object object) {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("convertObject2Json", "convert json error" + object.toString(), e);
        }
        return null;
    }

    /**
     * json转换为对象
     * @param json
     * @param cls
     * @return
     */
    public static Object convertJson2Object(String json, Class cls) {

        try {
            return objectMapper.readValue(json, cls);
        }catch (Exception e) {
            logger.error("convertJson2Object", "convert json error" + json, e);
        }

        return null;
    }

    /**
     * 对象转换为指定对象
     * @param object
     * @param cls
     * @return
     */
    public static Object convertObject2Object(Object object, Class cls) {

        try {
            return objectMapper.convertValue(object, cls);
        }catch (Exception e) {
            logger.error("convertObject2Object", "convert json error" + object, e);
        }

        return null;
    }

    /**
     * json转换成map
     * @param str
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Map<K,V> toMap(String str) {

        try {
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            return toObject(str, new TypeReference<Map<K, V>>() {});

        } catch (Exception e) {
            logger.error("toMap", e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toObject(String str, TypeReference<T> typeReference) {

        try {
            if (StringUtils.isEmpty(str) || null == typeReference) {
                return null;
            }
            return (T) (typeReference.getType().equals(String.class) ? str : MAPPER.readValue(str, typeReference));
        }catch (Exception e) {
            logger.error("toObject", e.getMessage(), e);
        }
        return null;
    }
}
