/*
 * @(#)Sl4jLoggerFactory.java 2013-1-21 下午3:29:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log.sl4j;

import org.makersoft.log.Log;
import org.makersoft.log.LoggerFactory;

/**
 * log4j logger factory.
 */
public class Sl4jLoggerFactory extends LoggerFactory{

	@Override
	protected Log getLoggerImpl(Class<?> cls) {
		return new Sl4jLogger(org.slf4j.LoggerFactory.getLogger(cls));
	}

	@Override
	protected Log getLoggerImpl(String name) {
		return new Sl4jLogger(org.slf4j.LoggerFactory.getLogger(name));
	}

}
