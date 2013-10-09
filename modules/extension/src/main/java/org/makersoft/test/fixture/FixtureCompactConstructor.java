/*
 * @(#)FixtureCompactConstructor.java 2013-5-7 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.fixture;

import java.util.Map;

import org.makersoft.core.collect.Maps;
import org.makersoft.test.fixture.ConstructImport.Listener;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.extensions.compactnotation.CompactData;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

/**
 * Class description goes here.
 */
public class FixtureCompactConstructor extends CompactConstructor {

	private final Map<String, Object> entityCache = Maps.newLinkedHashMap();

	private final Persister persister;
	
	private final ConstructPackage constructPackage = new ConstructPackage();
	
	private final ConstructImport constructImport = new ConstructImport(new Listener() {
		
		@Override
		public void onLoadEntities(String fileLocation) {
			loadEntities(fileLocation);
		}
	});

	// constructor
	public FixtureCompactConstructor(Persister persister) {
		this.yamlConstructors.put(new Tag("!import"), constructImport);
		this.yamlConstructors.put(new Tag("!package"), constructPackage);
		this.persister = persister;
	}
	
	public FixtureCompactConstructor() {
		this(null);
	}

	@Override
	protected Object createInstance(ScalarNode node, CompactData data) throws Exception {
		if (!entityCache.containsKey(node.getValue())) {
			data.getArguments().clear();
			Object entity = super.createInstance(node, data);
			entityCache.put(node.getValue(), entity);
		}
		
		return entityCache.get(node.getValue());
	}

	@Override
	protected Class<?> getClassForName(String name) throws ClassNotFoundException {
		if (constructPackage.getPackageName() != null && !"".equals(constructPackage.getPackageName())) {
			try {
				return super.getClassForName(constructPackage.getPackageName() + "." + name);
			} catch (ClassNotFoundException ignored) {
				// ignored
			}
		}

		ClassNotFoundException exceptionToThrow;
		try {
			return super.getClassForName(name);
		} catch (ClassNotFoundException ex) {
			exceptionToThrow = ex;
		}

		try {
			return super.getClassForName("java.lang." + name);
		} catch (ClassNotFoundException ignored) {
			// ignored
		}

		throw exceptionToThrow;
	}
	
	void loadEntities(String... files) {
		Yaml yaml = new Yaml(this);
		yaml.setBeanAccess(BeanAccess.FIELD);
        for(String file : files) {
            if(!file.startsWith("/")) {
                file = "/" + file;
            }
            
            yaml.load(getClass().getResourceAsStream(file));
        }
	}
	
	public void print(){
		for(Map.Entry<String, Object> entry : entityCache.entrySet()){
        	System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
	}
}
