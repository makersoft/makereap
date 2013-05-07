/*
 * @(#)ControllerNameBuilder.java 2013-1-28 下午23:33:42
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.builder;

/**
 * <p>
 * This interface defines the method that is used to create controller
 * names based on the name of a class.
 * </p>
 */
public interface ControllerNameBuilder {
	
	/**
     * Given the name of the class, this method should build an controller name.
     *
     * @param   className The class name.
     * @return  The action name and never null.
     */
    String build(String className);
}
