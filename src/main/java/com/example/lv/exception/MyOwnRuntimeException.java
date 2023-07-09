package com.example.lv.exception;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 自定义 运行异常
 * @date 2023/6/22 21:55:12
 */
public class MyOwnRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -7113698532297260092L;

    public MyOwnRuntimeException() {
    }

    public MyOwnRuntimeException(String message) {
        super(message);
    }
}
