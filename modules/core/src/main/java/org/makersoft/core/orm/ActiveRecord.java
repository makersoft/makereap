/*
 * @(#)ActiveRecord.java 2012-12-29 下午8:24:00
 *
 * Copyright (c) 2011-2012 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.core.orm;

/**
 * Class implementing the basic properties of all entities.
 *
 * @version	2012-12-29
 * @author Feng Kuok
 */
public abstract class ActiveRecord {

	public boolean save(){
		return false;
	}
	
	public boolean update(){
		return false;
	}
	
	public boolean remove(){
		return false;
	}
	
	public static <T> T find(Long id){
		return null;
	}
	
	
}
