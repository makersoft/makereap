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
import org.slf4j.Logger;

/**
 * Class description goes here.
 * 
 * @version 2013-1-21 下午3:29:15
 * @author Feng Kuok
 */
public class Sl4jLogger implements Log{

	private final Logger logger;
	
	public Sl4jLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String format, Object... args) {
		logger.trace(format, args);
		
	}

	@Override
	public void trace(String msg, Throwable ex, Object... args) {
		logger.trace(msg, ex);
		
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void debug(String format, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String msg, Throwable ex, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void info(String format, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void info(String msg, Throwable ex, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void warn(String format, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String msg, Throwable ex, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void error(String format, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String msg, Throwable ex, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fatal(String format, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fatal(String msg, Throwable ex, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFatalEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
