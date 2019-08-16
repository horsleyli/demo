package com.cjbs.demo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author shj
 */
@Retention(RetentionPolicy.RUNTIME)//保留期限
@Target({ElementType.METHOD,ElementType.TYPE})//目标类型
public @interface MyAnnotation {
    boolean value() default true;//声明成员变量
}
