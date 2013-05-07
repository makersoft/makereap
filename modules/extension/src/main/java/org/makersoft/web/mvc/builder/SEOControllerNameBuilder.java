/*
 * @(#)SEOControllerNameBuilder.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.builder;

import org.apache.commons.lang3.StringUtils;
import org.makersoft.log.Log;
import org.makersoft.log.LoggerFactory;

/**
 * 此类用于根据Controller名称构造一个符合SEO规则的URL
 * 
 * This class is used to meet the SEO rules according to the the Controller name of structure a URL
 */
public class SEOControllerNameBuilder implements ControllerNameBuilder {

	private static final Log LOG = LoggerFactory.getLogger(SEOControllerNameBuilder.class);

	private final String controllerSuffix;

	private final boolean lowerCase = true;
	private final String separator = "-";

	/**
	 * @param controllerSuffix controller suffix
	 */
	public SEOControllerNameBuilder(String controllerSuffix) {
		this.controllerSuffix = StringUtils.isBlank(controllerSuffix) ?  "Controller" : controllerSuffix;
	}

	public String build(String className) {
		String controllerName = className;

		if (controllerName.equals(controllerSuffix))
			throw new IllegalStateException(
					"The controller name cannot be the same as the action suffix ["
							+ controllerSuffix + "]");

		// Truncate Controller suffix if found
		if (controllerName.endsWith(controllerSuffix)) {
			controllerName = controllerName.substring(0,
					controllerName.length() - controllerSuffix.length());
		}

		 //Convert to underscores
//		char[] ca = controllerName.toCharArray();
//		StringBuilder build = new StringBuilder("" + ca[0]);
//		boolean lower = true;
//		for (int i = 1; i < ca.length; i++) {
//			char c = ca[i];
//			if (Character.isUpperCase(c) && lower) {
//				build.append(separator);
//				lower = false;
//			} else if (!Character.isUpperCase(c)) {
//				lower = true;
//			}
//
//			build.append(c);
//		}

//		 controllerName = build.toString();
		controllerName = StringUtils.join(controllerName.split("(?<=[a-z])(?=[A-Z])"), separator);
		if (lowerCase) {
			controllerName = controllerName.toLowerCase();
		}

		if (LOG.isTraceEnabled()) {
			LOG.trace("Changed controller name from [#0] to [#1]", className, controllerName);
		}

		return controllerName;
	}

}