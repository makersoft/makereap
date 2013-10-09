/*
 * @(#)Core.java 2013-5-7 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.fixture;

import java.util.Map;

import org.makersoft.core.collect.Maps;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.extensions.compactnotation.CompactData;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;

/**
 * Class description goes here.
 * 
 * @version 2013-5-7 下午1:42:41
 * @author Feng Kuok
 */
public class Core extends CompactConstructor {
	
	private final Map<String, Object> entityCache = Maps.newLinkedHashMap();
    private final Persister persister;
    private final String defaultPackage;
    private String packageName;
    
    public Core(Persister persister, String defaultPackage) {
//        this.yamlConstructors.put(new Tag("!import"), new ConstructImport(this));
//        this.yamlConstructors.put(new Tag("!package"), new ConstructPackage(this));
        this.defaultPackage = defaultPackage;
        this.packageName = defaultPackage;
        this.persister = persister;
    }
    
    public Core(Persister persister) {
        this(persister, "");
    }
    
    public Core() {
        this(null, "");
    }
    

	@Override
	protected Object createInstance(ScalarNode node, CompactData data) throws Exception {
		if(!entityCache.containsKey(node.getValue())) {
            data.getArguments().clear();
            Object entity = super.createInstance(node, data);
            entityCache.put(node.getValue(), entity);
        }
        return entityCache.get(node.getValue());
	}

	@Override
	protected Class<?> getClassForName(String name) throws ClassNotFoundException {
		if(packageName != null && !"".equals(packageName.trim())) {
            try {
                return super.getClassForName(packageName + "." + name);
            } catch (ClassNotFoundException ignored) { 
            	//ignored
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
        	//ignored
        }
        
        throw exceptionToThrow;
	}

	void setPackage(String packageName) {
		this.packageName = packageName;
	}

	void loadEntities(String... files) {
		Yaml yaml = new Yaml(this);
		yaml.setBeanAccess(BeanAccess.FIELD);
        for(String file : files) {
            if(!file.startsWith("/")) {
                file = "/" + file;
            }
            
            String origPackage = this.packageName;
            this.packageName = this.defaultPackage;
            yaml.load(getClass().getResourceAsStream(file));
            this.packageName = origPackage;
        }
	}

    public void load(String... files) {
        loadEntities(files);
        for(Map.Entry<String, Object> entry : entityCache.entrySet()){
        	System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
        
//        persistEntities();
    }
    
}
