package com.example.lv.bean.base;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 状态码枚举
 * @date 2023/6/17 14:14:30
 */
public enum ResultCode {
    //成功
    SUCCESS(200),
    //失败
    FAIL(-1),
    //未认证（签名错误）
    UNAUTHORIZED(401),
    //接口不存在
    NOT_FOUND(404),
    //服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
