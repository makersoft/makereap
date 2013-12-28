/*
 * @(#)Handler.java Dec 28, 2013 5:27:01 PM
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web;


public abstract class Handler {
	
	public <T> T params(Class<T> clazz) {
		return null;
	}
	
	public <T> T params(String name) {
		return null;
	}

	
	public abstract Result excute();
}
