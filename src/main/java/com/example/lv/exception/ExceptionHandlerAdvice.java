package com.example.lv.exception;

import com.example.lv.bean.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 统一异常封装
 * @date 2023/6/22 21:41:56
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    /**
     * 参数格式异常处理 400
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result badRequestException(IllegalArgumentException ex) {
        LOGGER.info("参数格式不合法：{}", ex.getMessage());
        return new Result(HttpStatus.BAD_REQUEST.value() , "参数格式不符！");
    }

    /**
     * 权限不足异常处理 403
     */
    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Result badRequestException(AccessDeniedException ex) {

        return new Result(HttpStatus.FORBIDDEN.value() , ex.getMessage());
    }

    /**
     * 参数缺失异常处理 400
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result badRequestException(Exception ex) {
        return new Result(HttpStatus.BAD_REQUEST.value(), "缺少必填参数！");
    }

    /**
     * 空指针异常 500
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleTypeMismatchException(NullPointerException ex) {
        LOGGER.info("空指针异常，{}", ex.getMessage());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),"空指针异常");
    }

    /**
     * 系统发生异常 500
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleUnexpectedServer(Exception ex) {
        LOGGER.info("系统异常：", ex);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统发生异常，请联系管理员");
    }

    /**
     * 系统异常处理 500
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Throwable throwable) {
        LOGGER.info("系统异常", throwable);
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统异常，请联系管理员！");
    }
}
