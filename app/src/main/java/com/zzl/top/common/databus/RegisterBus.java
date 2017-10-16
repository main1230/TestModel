package com.zzl.top.common.databus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangzl
 * time: 2017/10/16 20:28.
 * 描述：自定义注解，用于标记观察者的方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RegisterBus {
}
