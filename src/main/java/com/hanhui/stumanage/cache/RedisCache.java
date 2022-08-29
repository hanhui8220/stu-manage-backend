package com.hanhui.stumanage.cache;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)         //注解在方法中使用
@Retention(RetentionPolicy.RUNTIME) //运行期有效
public @interface RedisCache {

    String name();              // 缓存 名称

    String key();              //1.设定key  spel 表达式
}
