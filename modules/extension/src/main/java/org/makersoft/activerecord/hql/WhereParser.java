/*
 * @(#)WhereParser.java 2013-4-3 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.hql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/**
 */
public class WhereParser extends Parser {
	
	private List<String> whereConditions = new ArrayList<String>();
    private boolean open = false;
    private boolean close = true;
    private List<String> smallExpression = new ArrayList<String>();

	public WhereParser(Set<String> columns, String alias) {
		super(columns, alias);
	}

	@Override
	public void parse(String wheres) {
		StringTokenizer tokens = new StringTokenizer(wheres, HQL_SEPARATORS, true);
        while (tokens.hasMoreElements()) {
            String token = tokens.nextToken();
            whereConditions.add(this.token(token));
        }

	}

	@Override
	public String toHQL() {
		return join(whereConditions);
	}
	
    private String token(String token) {
        String lcToken = token.toLowerCase().trim();
        if (open && !close) {
            if (lcToken.equals("'") || lcToken.equals("\"")) {
                open = false;
                close = true;
                smallExpression.add(token);
                return join(smallExpression);
            }
            smallExpression.add(token);
            return "";
        }

        if (lcToken.trim().isEmpty()
                || lcToken.startsWith(":")
                || EXPRESSION_TERMINATORS.contains(lcToken)
                || BOOLEAN_OPERATORS.contains(lcToken)
                )
            return token;

        if ((lcToken.equals("'") || lcToken.equals("\"")) && (!open && close)) {
            open = true;
            close = false;
            smallExpression.clear();
            smallExpression.add(token);
            return "";
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


    private static final Set<String> EXPRESSION_TERMINATORS = new HashSet<String>();   //tokens that close a sub expression
    private static final Set<String> EXPRESSION_OPENERS = new HashSet<String>();       //tokens that open a sub expression
    private static final Set<String> BOOLEAN_OPERATORS = new HashSet<String>();        //tokens that would indicate a sub expression is a boolean expression
    private static final Map<String,String> NEGATIONS = new HashMap<String,String>();

    static {
        EXPRESSION_TERMINATORS.add("and");
        EXPRESSION_TERMINATORS.add("or");
        EXPRESSION_TERMINATORS.add(")");
        //expressionTerminators.add(","); // deliberately excluded

        EXPRESSION_OPENERS.add("and");
        EXPRESSION_OPENERS.add("or");
        EXPRESSION_OPENERS.add("(");
        //expressionOpeners.add(","); // deliberately excluded

        BOOLEAN_OPERATORS.add("<");
        BOOLEAN_OPERATORS.add("=");
        BOOLEAN_OPERATORS.add(">");
        BOOLEAN_OPERATORS.add("#");
        BOOLEAN_OPERATORS.add("~");
        BOOLEAN_OPERATORS.add("like");
        BOOLEAN_OPERATORS.add("ilike");
        BOOLEAN_OPERATORS.add("regexp");
        BOOLEAN_OPERATORS.add("rlike");
        BOOLEAN_OPERATORS.add("is");
        BOOLEAN_OPERATORS.add("in");
        BOOLEAN_OPERATORS.add("any");
        BOOLEAN_OPERATORS.add("some");
        BOOLEAN_OPERATORS.add("all");
        BOOLEAN_OPERATORS.add("exists");
        BOOLEAN_OPERATORS.add("between");
        BOOLEAN_OPERATORS.add("<=");
        BOOLEAN_OPERATORS.add(">=");
        BOOLEAN_OPERATORS.add("=>");
        BOOLEAN_OPERATORS.add("=<");
        BOOLEAN_OPERATORS.add("!=");
        BOOLEAN_OPERATORS.add("<>");
        BOOLEAN_OPERATORS.add("!#");
        BOOLEAN_OPERATORS.add("!~");
        BOOLEAN_OPERATORS.add("!<");
        BOOLEAN_OPERATORS.add("!>");
        BOOLEAN_OPERATORS.add("is not");
        BOOLEAN_OPERATORS.add("not like");
        BOOLEAN_OPERATORS.add("not ilike");
        BOOLEAN_OPERATORS.add("not regexp");
        BOOLEAN_OPERATORS.add("not rlike");
        BOOLEAN_OPERATORS.add("not in");
        BOOLEAN_OPERATORS.add("not between");
        BOOLEAN_OPERATORS.add("not exists");

        NEGATIONS.put("and", "or");
        NEGATIONS.put("or", "and");
        NEGATIONS.put("<", ">=");
        NEGATIONS.put("=", "<>");
        NEGATIONS.put(">", "<=");
        NEGATIONS.put("#", "!#");
        NEGATIONS.put("~", "!~");
        NEGATIONS.put("like", "not like");
        NEGATIONS.put("ilike", "not ilike");
        NEGATIONS.put("regexp", "not regexp");
        NEGATIONS.put("rlike", "not rlike");
        NEGATIONS.put("is", "is not");
        NEGATIONS.put("in", "not in");
        NEGATIONS.put("exists", "not exists");
        NEGATIONS.put("between", "not between");
        NEGATIONS.put("<=", ">");
        NEGATIONS.put(">=", "<");
        NEGATIONS.put("=>", "<");
        NEGATIONS.put("=<", ">");
        NEGATIONS.put("!=", "=");
        NEGATIONS.put("<>", "=");
        NEGATIONS.put("!#", "#");
        NEGATIONS.put("!~", "~");
        NEGATIONS.put("!<", "<");
        NEGATIONS.put("!>", ">");
        NEGATIONS.put("is not", "is");
        NEGATIONS.put("not like", "like");
        NEGATIONS.put("not ilike", "ilike");
        NEGATIONS.put("not regexp", "regexp");
        NEGATIONS.put("not rlike", "rlike");
        NEGATIONS.put("not in", "in");
        NEGATIONS.put("not between", "between");
        NEGATIONS.put("not exists", "exists");

    }

}
