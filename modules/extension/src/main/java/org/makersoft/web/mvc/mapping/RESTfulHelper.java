/*
 * @(#)RESTfulHelper.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.mapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.makersoft.web.mvc.mapping.impl.CreateMapping;
import org.makersoft.web.mvc.mapping.impl.DestroyMapping;
import org.makersoft.web.mvc.mapping.impl.EditMapping;
import org.makersoft.web.mvc.mapping.impl.IndexMapping;
import org.makersoft.web.mvc.mapping.impl.NewMapping;
import org.makersoft.web.mvc.mapping.impl.ShowMapping;
import org.makersoft.web.mvc.mapping.impl.UpdateMapping;

/**
 * Class description goes here.
 * 
 * @version 2013-1-31 下午4:00:51
 * @author Feng Kuok
 */
public abstract class RESTfulHelper {
	private static final Map<String, Mapping> METHOD_URL_MAPPING = new HashMap<String, Mapping>();

	static {
		METHOD_URL_MAPPING.put(Mapping.METHOD_INDEX_NAME, new IndexMapping());
		METHOD_URL_MAPPING.put(Mapping.METHOD_SHOW_NAME, new ShowMapping());
		METHOD_URL_MAPPING.put(Mapping.METHOD_NEW_NAME, new NewMapping());
		METHOD_URL_MAPPING.put(Mapping.METHOD_EDIT_NAME, new EditMapping());
		METHOD_URL_MAPPING.put(Mapping.METHOD_CREATE_NAME, new CreateMapping());
		METHOD_URL_MAPPING.put(Mapping.METHOD_UPDATE_NAME, new UpdateMapping());
		METHOD_URL_MAPPING.put(Mapping.METHOD_DESTROY_NAME, new DestroyMapping());
	}

	/**
	 * 方法名与Mapping匹配，并返回Mapping
	 * 
	 * @param methodName
	 *            方法名
	 * @return 匹配到的Mapping
	 * @see Mapping
	 */
	public static Mapping matchMapping(String methodName) {
		return METHOD_URL_MAPPING.get(methodName);
	}

	/**
	 * 方法与Mapping匹配，并返回Mapping
	 * 
	 * @param method
	 * @return 匹配到的Mapping
	 * @see Mapping
	 */
	public static Mapping matchMapping(Method method) {
		return METHOD_URL_MAPPING.get(method.getName());
	}
}
