/*
 * @(#)Logger.java 2013-1-21 下午3:06:24
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Logger inject annotation.
 * 
 * 默认Logger名称采用类全称.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Logger {

	/**
	 * 指定logger name
	 */
	String name() default "";
	
}
