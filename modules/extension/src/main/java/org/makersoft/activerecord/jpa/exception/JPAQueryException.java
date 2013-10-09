/*
 * @(#)JPAQueryException.java 2013-4-1 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.jpa.exception;

import java.sql.SQLException;

/**
 * exceptions
 */
public class JPAQueryException extends RuntimeException {

	private static final long serialVersionUID = 6493309043133095702L;

	public JPAQueryException(String message) {
		super(message);
	}

	public JPAQueryException(String message, Throwable e) {
		super(message + ": " + e.getMessage(), e);
	}

	public static Throwable findBestCause(Throwable e) {
		Throwable best = e;
		Throwable cause = e;
		int it = 0;
		while ((cause = cause.getCause()) != null && it++ < 10) {
			if (cause instanceof ClassCastException) {
				best = cause;
				break;
			}
			if (cause instanceof SQLException) {
				best = cause;
				break;
			}
		}
		return best;
	}
}
