/*
 * @(#)Sl4jLogger.java 2013-1-21 下午3:29:15
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log.sl4j;

import org.makersoft.log.Log;
import org.makersoft.log.LoggerUtils;
import org.slf4j.Logger;

/**
 * sl4j implimentation.
 */
public class Sl4jLogger implements Log{

	private final Logger logger;
	
	public Sl4jLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String msg, Object... args) {
		logger.trace(LoggerUtils.format(msg, args));
		
	}

	@Override
	public void trace(String msg, Throwable ex, Object... args) {
		logger.trace(LoggerUtils.format(msg, args), ex);
		
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void debug(String msg, Object... args) {
		logger.debug(LoggerUtils.format(msg, args));
		
	}

	@Override
	public void debug(String msg, Throwable ex, Object... args) {
		logger.debug(LoggerUtils.format(msg, args), ex);
		
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void info(String msg, Object... args) {
		logger.info(LoggerUtils.format(msg, args));
		
	}

	@Override
	public void info(String msg, Throwable ex, Object... args) {
		logger.info(LoggerUtils.format(msg, args), ex);
		
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void warn(String msg, Object... args) {
		logger.warn(LoggerUtils.format(msg, args));
		
	}

	@Override
	public void warn(String msg, Throwable ex, Object... args) {
		logger.warn(LoggerUtils.format(msg, args), ex);
		
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void error(String msg, Object... args) {
		logger.error(LoggerUtils.format(msg, args));
		
	}

	@Override
	public void error(String msg, Throwable ex, Object... args) {
		logger.error(LoggerUtils.format(msg, args), ex);
		
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void fatal(String msg, Object... args) {
		logger.error(LoggerUtils.format(msg, args));
		
	}

	@Override
	public void fatal(String msg, Throwable ex, Object... args) {
		logger.error(LoggerUtils.format(msg, args), ex);
		
	}

	@Override
	public boolean isFatalEnabled() {
		return logger.isErrorEnabled();
	}

}
