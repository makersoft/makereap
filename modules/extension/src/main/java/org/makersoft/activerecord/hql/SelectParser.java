/*
 * @(#)SelectParser.java 2013-4-3 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.hql;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 */
public class SelectParser extends Parser {
	
	private List<String> selectConditions = new ArrayList<String>();

	public SelectParser(Set<String> columns, String alias) {
		super(columns, alias);
	}
	
	private String token(String token) {
		String prefixName = root(token);
		if (super.getColumns().contains(prefixName)) {
			if (prefixName.equals(super.getAlias())) {
				return token;
			} else {
				return super.getAlias() + "." + token;
			}
		}
		return token;
	}

	@Override
	public void parse(String selects) {
		StringTokenizer tokens = new StringTokenizer(selects, HQL_SEPARATORS, true);
		while (tokens.hasMoreElements()) {
			String token = tokens.nextToken();
			selectConditions.add(this.token(token));
		}
	}

	@Override
	public String toHQL() {
		return join(selectConditions);
	}
	
}
