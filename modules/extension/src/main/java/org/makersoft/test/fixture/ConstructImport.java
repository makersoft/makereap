/*
 * @(#)ConstructImport.java 2013-5-7 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.fixture;

import java.util.List;

import org.makersoft.core.collect.Lists;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

/**
 * 
 */
class ConstructImport extends AbstractConstruct {
    private final Listener listener;
    private final List<String> importedPackages = Lists.newArrayList();
    
    public ConstructImport(Listener listener) {
        this.listener = listener;
    }
    
    @Override 
    public Object construct(Node node) {
        String location = ((ScalarNode) node).getValue();
        if(!importedPackages.contains(location)) {
            importedPackages.add(location);
            listener.onLoadEntities(location);
        }
        return null;
    }
    
    public interface Listener{
    	
    	void onLoadEntities(String fileLocation);
    } 

}
