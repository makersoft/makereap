/*
 * @(#)CollectionUtils.java 2013-4-3 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm.utils;

import java.util.Collection;

public abstract class CollectionUtils {

    public static String join(Collection<String> collection) {
    	if(collection != null && collection.size() != 0){
            StringBuffer sb = new StringBuffer();
            for(String item : collection){
            	sb.append(item);
            }
            
            return sb.toString();
    	}
    	
    	return null;
    }
}
