/*
 * @(#)UpdateMapping.java 2013-1-28 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.mvc.mapping.impl;

import org.makersoft.mvc.mapping.AbstractMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
public class UpdateMapping extends AbstractMapping {

	private String[] values = new String[] { "/{id}" };

	private RequestMethod[] requestMethods = { RequestMethod.PUT };
	
	public UpdateMapping() {
		super(METHOD_UPDATE_NAME);
	}

	@Override
	public String[] getValues() {
		return values;
	}

	@Override
	public RequestMethod[] getRequestMethods() {
		return requestMethods;
	}

}
