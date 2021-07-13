package com.boot.shop.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 指定继承的注解
@Target(ElementType.FIELD) // 指定作用范围：FIELD属性
public @interface NotNull {  // 自定义注解
	
	String fieldName() default "";
	
}
