/*
 * @(#)JoinParser.java 2013-4-3 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.hql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Class description goes here.
 * 
 * @version 2013-4-3 下午5:15:25
 * @author Feng Kuok
 */
public class JoinParser extends Parser {
	
	private static final Set<String> KEYWORDS = new HashSet<String>();
	
	static {
		KEYWORDS.add("join fetch");
		KEYWORDS.add("left join fetch");
		KEYWORDS.add("right join fetch");
		KEYWORDS.add("inner join fetch");
		KEYWORDS.add("outer join fetch");
	}
	
	private List<String> joinClauses = new ArrayList<String>();

	public JoinParser(Set<String> columns, String alias) {
		super(columns, alias);
	}

	@Override
	public void parse(String joins) {
		StringTokenizer tokens = new StringTokenizer(joins, HQL_SEPARATORS, true);
        while (tokens.hasMoreElements()) {
            joinClauses.add(this.token(tokens.nextToken()));
        }
	}

	@Override
	public String toHQL() {
		return join(joinClauses);
	}
	
	private String token(String token) {
        String lcToken = token.toLowerCase().trim();
        if (lcToken.equals("left")
                || lcToken.equals("right")
                || lcToken.equals("inner")
                || lcToken.equals("outer")
                || lcToken.equals("inner")
                ) {
            return token;
        }
        
        if (lcToken.equals("join")) {
            return token + " fetch";
        }
        
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
	
	public static void main(String[] args) {
		Set<String> columns = new HashSet<String>();
		columns.add("name");
		columns.add("age");
		Parser parser = new JoinParser(columns, "t");
		parser.parse("student s on name = s.name");
		System.out.println(parser.toHQL());
	}

}
