package com.example.lv.aspect;

import java.lang.annotation.*;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description 日志自定义注解 参数写法:数据类型 参数名称() default 默认值：默认值
 * @date 2023/6/17 10:22:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperationAnnotation {
    //内容
    String content() default "";
    //系统类型 (管理平台，App端)
    String sysType() default "0";
    //操作类型 0登录 1增加 2删除 3修改 4查询 5查看
    String opType() default "0";
    //功能名称
    String action() default "";
}
