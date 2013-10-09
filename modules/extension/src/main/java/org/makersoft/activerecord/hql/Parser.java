/*
 * @(#)Parser.java 2013-4-3 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.hql;

import java.util.Collection;
import java.util.Set;

/**
 * HQL解析器
 */
public abstract class Parser {
	// 将where子句进行切分
	public static final String HQL_SEPARATORS = " \n\r\f\t,()=<>&|+-=/*'^![]#~\\";
	
	private final Set<String> columns;
	private final String alias;
	
	public Parser(Set<String> columns, String alias){
		this.columns = columns;
		this.alias = alias;
	}

	public abstract void parse(String hql);

	public abstract String toHQL();

	
	public Set<String> getColumns() {
		return columns;
	}

	public String getAlias() {
		return alias;
	}
	
    public static String root(String qualifiedName) {
        int loc = qualifiedName.indexOf(".");
        return (loc < 0) ? qualifiedName : qualifiedName.substring(0, loc);
    }
    
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
