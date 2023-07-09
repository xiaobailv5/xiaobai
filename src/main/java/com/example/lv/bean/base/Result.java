package com.example.lv.bean.base;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 统一返回结果
 * @date 2023/6/17 14:07:00
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -3974099753716737369L;

    private int code;

    private String message;

    private Object data;

    private Map<String,Object> bean;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Result setCode(ResultCode resultCode){
        this.code = resultCode.code;
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(Object data){
        this.data = data;
        return this;
    }

    public Map<String, Object> getBean() {
        return bean;
    }

    public Result setBean(Map<String, Object> bean) {
        this.bean = bean;
        return this;
    }
    public Result(){

    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
