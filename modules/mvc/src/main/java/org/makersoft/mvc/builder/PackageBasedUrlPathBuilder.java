/*
 * @(#)PackageBasedUrlPathBuilder.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.mvc.builder;

import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;
import org.makersoft.log.Log;
import org.makersoft.log.LoggerFactory;

/**
 * Class description goes here.
 */
public class PackageBasedUrlPathBuilder {
	
	private static final Log LOG = LoggerFactory.getLogger(PackageBasedUrlPathBuilder.class);

	/**
	 *  "web", "controller", "action", "controllers"
	 */
	private final String[] packageLocators;

	/**
	 *  "org.makersoft.web.mvc.controllers"
	 */
	private final String[] controllerPackages;

	/**
	 * root path
	 */
	private final String root;
	
	private final ControllerNameBuilder builder;

	/**
	 * 
	 * @param packageLocators
	 * @param controllerPackages
	 * @param controllerSuffix
	 * @param root
	 */
	public PackageBasedUrlPathBuilder(String[] packageLocators, String[] controllerPackages,
			String controllerSuffix, String root) {
		
		this.packageLocators = packageLocators;
		this.controllerPackages = controllerPackages;
		this.root = StringUtils.isBlank(root) ? "application#index" : root;
		this.builder = new SEOControllerNameBuilder(controllerSuffix);
	}

	/**
	 * 根据Controller类，截取URL
	 * 
	 * @param clazz
	 *            Controller类
	 * @return URL Path
	 */
	public String[] buildUrlPath(Class<?> controllerClass, String methodName, String... values) {

		// Skip classes that can't be instantiated
		if (cannotInstantiate(controllerClass)) {
			if (LOG.isTraceEnabled())
				LOG.trace("Class [#0] did not pass the instantiation test and will be ignored",
						controllerClass.getName());
		}

		// Determine the action package
		String controllerPackage = controllerClass.getPackage().getName();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Processing class [#0] in package [#1]", controllerClass.getName(),
					controllerPackage);
		}

		// Determine the default namespace and action name
		String namespace = determineControllerNamespace(controllerClass);

		String defaultControllerName = determineControllerName(controllerClass);

		// reversion
		String[] urlPaths = null;
		
		// fixed root path
		if (root.equals(defaultControllerName + "#" + methodName)) {
			urlPaths = new String[] { "/" };
		} else if (values.length != 0) {
			urlPaths = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				urlPaths[i] = namespace + "/" + defaultControllerName + value;
			}
		} else {
			urlPaths = new String[] { namespace + "/" + defaultControllerName };
		}

		return urlPaths;
	}

	/**
	 * Determines the namespace(s) for the action based on the action class. If there is a {
	 * Namespace} annotation on the class (including parent classes) or on the package that the
	 * class is in, than it is used. Otherwise, the Java package name that the class is in is used
	 * in conjunction with either the <b>struts.convention.action.packages</b> or
	 * <b>struts.convention.package.locators</b> configuration values. These are used to determine
	 * which part of the Java package name should be converted into the namespace for the XWork
	 * PackageConfig.
	 * 
	 * @param actionClass
	 *            The action class.
	 * @return The namespace or an empty string.
	 */
	private String determineControllerNamespace(Class<?> controllerClass) {
		String urlPath;

		String pkg = controllerClass.getPackage().getName();
		String pkgPart = null;
		if (controllerPackages != null) {
			for (String actionPackage : controllerPackages) {
				if (pkg.startsWith(actionPackage)) {
					pkgPart = controllerClass.getName().substring(actionPackage.length() + 1);
				}
			}
		}

		if (pkgPart == null && packageLocators != null) {
			for (String packageLocator : packageLocators) {
				int index = pkg.lastIndexOf(packageLocator);

				// This ensures that the match is at the end, beginning or has a dot on each side of it
				if (index >= 0
						&& (index + packageLocator.length() == pkg.length() || index == 0 || (pkg
								.charAt(index - 1) == '.' && pkg.charAt(index
								+ packageLocator.length()) == '.'))) {
					pkgPart = controllerClass.getName().substring(
							index + packageLocator.length() + 1);
				}
			}
		}

		if (pkgPart != null) {
			final int indexOfDot = pkgPart.lastIndexOf('.');
			if (indexOfDot >= 0) {
				// String convertedNamespace = this.build(pkgPart.substring(0, indexOfDot));
				String convertedNamespace = pkgPart.substring(0, indexOfDot);
				urlPath = "/" + convertedNamespace.replace('.', '/');
				return urlPath;
			}
		}

		return "";
	}

	/**
	 * Converts the class name into an action name using the ActionNameBuilder.
	 * 
	 * @param actionClass
	 *            The action class.
	 * @return The action name.
	 */
	protected String determineControllerName(Class<?> controllerClass) {

		String controllerName = builder.build(controllerClass.getSimpleName());
		if (LOG.isTraceEnabled()) {
			LOG.trace("Got actionName for class [#0] of [#1]", controllerClass.toString(),
					controllerName);
		}

		return controllerName;
	}

	public static String urlPathConvert(Class<?> clazz, String[] packageLocators, String... values) {
		return null;
	}

	/**
	 * Interfaces, enums, annotations, and abstract classes cannot be instantiated.
	 * 
	 * @param controllerClass
	 *            class to check
	 * @return returns true if the class cannot be instantiated or should be ignored
	 */
	protected boolean cannotInstantiate(Class<?> controllerClass) {
		return controllerClass.isAnnotation() || controllerClass.isInterface()
				|| controllerClass.isEnum()
				|| (controllerClass.getModifiers() & Modifier.ABSTRACT) != 0
				|| controllerClass.isAnonymousClass();
	}

}
