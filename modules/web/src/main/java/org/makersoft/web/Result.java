/*
 * @(#)Result.java Dec 28, 2013 5:28:00 PM
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web;

public abstract class Result {

	public static Result format(Render render) {

		return null;
	}
	
	public static Result status(int status) {
		return null;
	}
	
	public static Result ok() {
		return null;
	}
	
	public static Result error() {
		return null;
	}
	
	public Result json(Object obj) {
		return null;
	}
	
}
