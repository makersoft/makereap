/*
 * @(#)MakerEAPException.java 2013-1-29 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.exceptions;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The super class of all MakerEAP exceptions 
 */
public abstract class MakerEAPException extends RuntimeException{

	private static final long serialVersionUID = -4482768297604611995L;

	static AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis());
    String id;
	
	public MakerEAPException() {
        super();
        setId();
    }

    public MakerEAPException(String message) {
        super(message);
        setId();
    }

    public MakerEAPException(String message, Throwable cause) {
        super(message, cause);
        setId();
    }

    void setId() {
        long nid = atomicLong.incrementAndGet();
        id = Long.toString(nid, 26);
    }

    public abstract String getErrorTitle();

    public abstract String getErrorDescription();
    
    public static StackTraceElement getInterestingStrackTraceElement(Throwable cause) {
        for (StackTraceElement stackTraceElement : cause.getStackTrace()) {
//            if (stackTraceElement.getLineNumber() > 0 && Play.classes.hasClass(stackTraceElement.getClassName())) {
                return stackTraceElement;
//            }
        }
        
        return null;
    }
}
