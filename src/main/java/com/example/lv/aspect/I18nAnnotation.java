package com.example.lv.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @project xiaobai
 * @description 国际化转换注解
 * @author gxjh2
 * @date 2024/9/21 09:11:37
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface I18nAnnotation {

    //需要替换的key
    String replaceKey() default "";

    //需要替换的对象  例如bean,beans,object
    String returnType() default "";

    //类的全路径
    String name() default "";

}
