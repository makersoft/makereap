/*
 * @(#)BaseTest.java 2013-5-9 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test;

import org.junit.Assert;

/**
 * Base test.
 */
public abstract class BaseTest extends Assert{
	
	/**
	 * Pause the current thread
	 */
	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
