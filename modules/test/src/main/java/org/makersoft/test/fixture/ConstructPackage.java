/*
 * @(#)ConstructPackage.java 2013-5-7 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.fixture;

import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;

/**
 */
class ConstructPackage extends AbstractConstruct {
	
	private String packageName;
	
	public String getPackageName(){
		return this.packageName;
	}

	@Override
	public Object construct(Node node) {
		packageName = ((ScalarNode) node).getValue();
		return "";
	}
}
