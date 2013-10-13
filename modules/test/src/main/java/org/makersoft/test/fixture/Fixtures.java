/*
 * @(#)Fixtures.java 2013-5-7 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.fixture;

import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.introspector.BeanAccess;

/**
 * Class description goes here.
 * 
 * @author Feng Kuok
 */
public class Fixtures {

	public static void loadYaml(String file) {
		FixtureCompactConstructor constructor = new FixtureCompactConstructor();
		Yaml yaml = new Yaml(constructor);
		yaml.setBeanAccess(BeanAccess.FIELD);
		if (!file.startsWith("/")) {
			file = "/" + file;
		}
		
		yaml.load(Fixtures.class.getResourceAsStream(file));

		constructor.print();
	}

	public static void loadModels(String name) {

	}
	
	public static <T> List<T> get(Class<T> clazz) {

		return null;
	}

	public static <T> T get(Class<T> clazz, String name) {

		return null;
	}

}
