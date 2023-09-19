package com.example.mzpspringboot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * mzp: 自定义的aopLog注解。 持有此注解的方法，会被aop日志拦截（在aop切面中配置的拦截指定的注解的方法）。
 *
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2023/1/6 11:26
 * @File : AopLog
 * @Software: IntelliJ IDEA 2019.2.04
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 作用在类和方法上
@Retention(RetentionPolicy.RUNTIME)  // 运行时
@Documented  // javadoc使用的
@Inherited  // 可以继承注解
public @interface AopLog {
}
