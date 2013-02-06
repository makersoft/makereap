/*
 * @(#)Mapping.java 2013-1-28 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.mapping;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 */
public interface Mapping {
	
	public static final String METHOD_INDEX_NAME = "index";
	public static final String METHOD_SHOW_NAME = "show";
	public static final String METHOD_NEW_NAME = "_new";
	public static final String METHOD_EDIT_NAME = "edit";
	public static final String METHOD_CREATE_NAME = "create";
	public static final String METHOD_UPDATE_NAME = "update";
	public static final String METHOD_DESTROY_NAME = "destroy";

	public String getMethodName();
	
	public String[] getValues();

	public RequestMethod[] getRequestMethods();

	public String[] getParams();

	public String[] getHeaders();

	public String[] getConsumes();

	public String[] getProduces();
}
