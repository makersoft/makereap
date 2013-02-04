/*
 * @(#)Format.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记此方法按指定方式格式化输出
 * 
 * @version 2013-1-31 下午4:24:46
 * @author Feng Kuok
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Format {
	
	Render[] render() default Render.JSON;
	
	String[] includes() default "";
	
	String[] excludes() default "";
}
