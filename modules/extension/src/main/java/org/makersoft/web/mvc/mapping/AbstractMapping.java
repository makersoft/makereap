/*
 * @(#)AbstractMapping.java 2013-1-28 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.mapping;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Class description goes here.
 * 
 * @version 2013-1-28 下午3:55:02
 * @author Feng Kuok
 */
public abstract class AbstractMapping implements Mapping {
	
	private String methodName;
	
	private static final String[] CONSUMES = new String[]{MediaType.ALL_VALUE};
	
	private static final String[] PRODUCES = new String[]{MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,MediaType.TEXT_HTML_VALUE};
	
	public AbstractMapping(final String methodName){
		this.methodName = methodName;
	}

	@Override
	public String getMethodName() {
		return methodName;
	}

	@Override
	public String[] getValues() {
		return null;
	}

	@Override
	public RequestMethod[] getRequestMethods() {
		return null;
	}

	@Override
	public String[] getParams() {
		return null;
	}

	@Override
	public String[] getHeaders() {
		return null;
	}

	@Override
	public String[] getConsumes() {
		return CONSUMES;
	}

	@Override
	public String[] getProduces() {
		return PRODUCES;
	}

}
