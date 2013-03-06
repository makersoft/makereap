/*
 * @(#)Log.java 2013-1-21 下午2:19:53
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log;

/**
 * 级别TRACE > DEBUG > INFO > WARN > ERROR > Fatal....
 * 
 * @version 2013-1-21 下午2:19:53
 * @author Feng Kuok
 */
public interface Log {
	void trace(String msg, Object... args);
    void trace(String msg, Throwable ex, Object... args);
    boolean isTraceEnabled();
    
    void debug(String msg, Object... args);
    void debug(String msg, Throwable ex, Object... args);
    boolean isDebugEnabled();
    
    void info(String msg, Object... args);
    void info(String msg, Throwable ex, Object... args);
    boolean isInfoEnabled();
    
    void warn(String msg, Object... args);
    void warn(String msg, Throwable ex, Object... args);
    boolean isWarnEnabled();
    
    void error(String msg, Object... args);
    void error(String msg, Throwable ex, Object... args);
    boolean isErrorEnabled();
    
    void fatal(String msg, Object... args);
    void fatal(String msg, Throwable ex, Object... args);
    boolean isFatalEnabled();
}
