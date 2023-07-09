package com.example.lv.bean.base;

import java.util.Map;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 响应结果生成工具
 * @date 2023/6/17 14:18:02
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "成功";

    /**
     * 成功 无对象
     */
    public static Result getSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 成功返回数据
     */
    public static Result getSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    /**
     * 成功返回数据
     */
    public static Result getSuccessResult(Object data, Map<String,Object> bean) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data)
                .setBean(bean);
    }
    /**
     * 失败
     */
    public static Result getFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }
}
