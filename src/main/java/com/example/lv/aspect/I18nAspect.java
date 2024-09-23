package com.example.lv.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.lv.bean.base.Result;
import com.example.lv.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @project xiaobai
 * @description 国际化转换切面
 * @author gxjh2
 * @date 2024/9/21 09:19:57
 * @version 1.0
 */
@Aspect
@Component
public class I18nAspect {

    /**
     * 切入点 注解的位置
     */
    @Pointcut("@annotation(com.example.lv.aspect.I18nAnnotation)")
    public void i18nPointCut() {}

    @AfterReturning(value = "i18nPointCut()", returning = "returnObject")
    public Object AfterReturning(JoinPoint joinPoint, Object returnObject) {

        I18nAnnotation i18nAnnotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(I18nAnnotation.class);

        String className = i18nAnnotation.name();

        String replaceKey = i18nAnnotation.replaceKey();

        String returnType = i18nAnnotation.returnType();

        List<String> replaceKeys = Arrays.asList(replaceKey.split(","));
        if (returnObject instanceof Result) {
            try {
                Class<?> aClass = Class.forName(className);
                Result result = (Result) returnObject;
                List<String> list = Arrays.asList(returnType.split(","));



                if (list.contains("data")) {

                    Object data =  result.getData();
                    if (data instanceof List) {
                        JSONArray jsonArray = new JSONArray();
                        jsonArray = JSON.parseArray(JSONObject.toJSONString(data));
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                            for (String key : replaceKeys) {
                                String val = (String) jsonObject.get(key);
                                System.out.println(val);
                                jsonObject.put(key, "11");
                            }

                        }
                        result.setData(jsonArray);

                    } else if (data instanceof Map) {

                        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(data));
                        for (String key : replaceKeys) {
                            String val = (String) itemJSONObj.get(key);
                            System.out.println(val);
                            itemJSONObj.put(key, "11");
                        }
                        result.setData(itemJSONObj);

                    } else {
                        JSONObject jo = (JSONObject) JSONObject.toJSON(data);
                        for (String key : replaceKeys) {
                            String val = (String) jo.get(key);
                            System.out.println(val);
                            jo.put(key, "11");
                        }
                        result.setData(jo);
                    }
                } else if (list.contains("bean")) {
                    Map<String, Object> bean = result.getBean();

                    for (String key : replaceKeys) {
                        String val = (String) bean.get(key);
                        System.out.println(val);
                        bean.put(key, "11");
                    }
                    result.setBean(bean);
                }
                return result;
            } catch (Exception e) {
                return returnObject;

            }


        } else if (returnObject instanceof List) {
            JSONArray jsonArray = new JSONArray();
            jsonArray = JSON.parseArray(JSONObject.toJSONString(returnObject));
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                for (String key : replaceKeys) {
                    String val = (String) jsonObject.get(key);
                    System.out.println(val);
                    jsonObject.put(key, "11");
                }

            }
        } else if (returnObject instanceof Map) {

            JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(returnObject));
            for (String key : replaceKeys) {
                String val = (String) itemJSONObj.get(key);
                System.out.println(val);
                itemJSONObj.put(key, "11");
            }

        }

        return returnObject;
    }

}
