/*
 * @(#)User.java Dec 28, 2013 6:06:17 PM
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.domain.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -3188161023260738425L;

	public boolean save() {
		return true;
	}
	
	public static boolean delete(Long id) {
		return false;
	}
}
