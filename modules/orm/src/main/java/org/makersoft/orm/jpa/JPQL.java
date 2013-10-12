/*
 * @(#)JPQL.java 2013-4-1 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm.jpa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.apache.commons.lang3.StringUtils;
import org.makersoft.orm.ActiveRecord;
import org.makersoft.orm.hql.JoinParser;
import org.makersoft.orm.hql.Parser;
import org.makersoft.orm.hql.SelectParser;
import org.makersoft.orm.hql.WhereParser;

/**
 * JPQL
 */
public class JPQL {
	
	private static final String EMPTY_STRING = " ";
	
	private final String entityName; 
	private final Set<String> columns;
	private final String alias;
	
	private String select = EMPTY_STRING;
	private String where = EMPTY_STRING;
	private String joins = EMPTY_STRING;
	private String group = EMPTY_STRING;
	private String order = EMPTY_STRING;
	private int offset;
	private int limit;
	private Map<String, Object> parameters = new HashMap<String, Object>();
	
	public static JPQL newInstance(Class<?> clazz){
		return new JPQL(clazz);
	}
	
	private JPQL(Class<?> clazz){
		this.entityName = clazz.getSimpleName();
		this.alias = entityName.toLowerCase();
		this.columns = this.getColumns();
	}
	
	public static EntityManager em() {
		return JPA.em();
	}

	public static long count(Class<?> clazz) {
		return Long.parseLong(em().createQuery("select count(*) from " + clazz.getName() + " e")
				.getSingleResult().toString());
	}

	@SuppressWarnings("rawtypes")
	public static List findAll(String entity) {
		return em().createQuery("select e from " + entity + " e").getResultList();
	}

	public static <T extends ActiveRecord> T findById(Class<T> entityClass, Long id)
			throws Exception {
		return em().find(entityClass, id);
	}

	public static JPAQuery all(String entityName) {
		String hql = createFindByQuery(entityName, entityName, null);
		Query q = em().createQuery(hql);
		
		return new JPAQuery(hql, bindParameters(q));
	}
	
	//conditions
	private String parseWhere(String condition) {

        Parser parser = new WhereParser(columns, alias);
        parser.parse(condition);
        return parser.toHQL();
    }
	
	private String parse(String condition) {
		Parser parser = new SelectParser(columns, alias);
		parser.parse(condition);
        return parser.toHQL();

    }
	
    private String parseJoin(String joins) {
    	Parser parser = new JoinParser(columns, alias);
    	parser.parse(joins);
        return parser.toHQL();
    }
	
    public JPQL from(String from){
    	return this;
    }
    
	public JPQL where(String condition){
		this.where = (StringUtils.isBlank(where) ? "where" : where + " and ") + "(" + parseWhere(condition) + ")";
		return this;
	}
	
	public JPQL where(String hql, Map<String, Object> params){
		this.where(hql);
        this.parameters.putAll(params);
		return this;
	}
	
	public JPQL where(Map<String, Object> params){
		throw new RuntimeException();
//		return this;
	}
	
    public JPQL group(String group) {
        if (group.startsWith("group")) {
            this.group = parse(group);
        } else {
            this.group = "group by " + parse(group);
        }
        return this;
    }
    
    public JPQL having(String having){
//    	this.having
    	return this;
    }
	
    public JPQL joins(String joins) {
        if (joins.contains("join")) {
            this.joins = parseJoin(joins);
        } else {
            this.joins = " inner join fetch " + parseJoin(joins);
        }
        return this;
    }
    
    public Long count(){
    	this.select = "select count(*)";
    	return (Long) single();
    }
    
    public Long count(String columns){
    	
    	return null;
    }
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list() {
//        String hql = select + BLANK_STRING + "from" + BLANK_STRING + entityName + " as " + alias + BLANK_STRING + joins + BLANK_STRING + where + BLANK_STRING + group + BLANK_STRING + order + BLANK_STRING;

        StringBuffer hql = new StringBuffer();
        hql.append(select);
        hql.append(" from ");
        hql.append(entityName);
        hql.append(" as ");
        hql.append(alias);
        hql.append(joins);
        hql.append(where);
        hql.append(group);
        hql.append(order);
        
        System.out.println("===" + hql.toString());
        
        Query query = em().createQuery(hql.toString());

        bindParameters(query, parameters);
        
        query.setFirstResult(offset);
        if (limit > 0) {
        	query.setMaxResults(limit);
        }
        
        return query.getResultList();
    }
	
	
	@SuppressWarnings("unchecked")
	public <T> T single(){
		
		StringBuffer hql = new StringBuffer();
        hql.append(select);
        hql.append(" from ");
        hql.append(entityName);
        hql.append(" as ");
        hql.append(alias);
        hql.append(joins);
        hql.append(where);
        hql.append(group);
        hql.append(order);
        
        Query query = em().createQuery(hql.toString());

        bindParameters(query, parameters);
        
		return (T)query.getSingleResult();
	}
	
    public JPQL select(String columns) {
        this.select = "select " + parse(columns);
        
        return this;
    }
	
    public JPQL order(String columns) {
        this.order = "order by " + parse(columns);
        
        return this;
    }
	
	public JPQL offset(int offset){
		this.offset = offset;
		
		return this;
	}
	
	public JPQL limit(Integer limit){
		this.limit = limit;
		
		return this;
	}

	
	//private method
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Set<String> getColumns() {
        Metamodel metamodel = em().getMetamodel();
        Set<String> columns = new HashSet<String>();
        for(EntityType entityType : metamodel.getEntities()){
        	if (entityName.equals(entityType.getName())) {
                Set<Attribute> attributes = entityType.getAttributes();
                for (Attribute attribute : attributes) {
                    columns.add(attribute.getName());
                }
            }
        }
        
        return columns;
    }
	
	private static String createFindByQuery(String entityName, String entityClass, String query, Object... params) {
		if (query == null || query.trim().length() == 0) {
			return "from " + entityName;
		}
		if (query.matches("^by[A-Z].*$")) {
			return "from " + entityName + " where " + findByToJPQL(query);
		}
		if (query.trim().toLowerCase().startsWith("select ")) {
			return query;
		}
		if (query.trim().toLowerCase().startsWith("from ")) {
			return query;
		}
		if (query.trim().toLowerCase().startsWith("order by ")) {
			return "from " + entityName + " " + query;
		}
		if (query.trim().indexOf(" ") == -1 && query.trim().indexOf("=") == -1 && params != null
				&& params.length == 1) {
			query += " = ?1";
		}
		if (query.trim().indexOf(" ") == -1 && query.trim().indexOf("=") == -1 && params == null) {
			query += " = null";
		}
		return "from " + entityName + " where " + query;
	}
	
    @SuppressWarnings("unchecked")
    public static Query bindParameters(Query q, Object... params) {
        if (params == null) {
            return q;
        }
        if (params.length == 1 && params[0] instanceof Map) {
            return bindParameters(q, (Map<String, Object>) params[0]);
        }
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        return q;
    }
    
    public static Query bindParameters(Query q, Map<String,Object> params) {
        if (params == null) {
            return q;
        }
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        return q;
    }

	public static String findByToJPQL(String findBy) {
		findBy = findBy.substring(2);
		StringBuffer jpql = new StringBuffer();
		String[] parts = findBy.split("And");
		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];
			if (part.endsWith("NotEqual")) {
				String prop = extractProp(part, "NotEqual");
				jpql.append(prop + " <> ?");
			} else if (part.endsWith("Equal")) {
				String prop = extractProp(part, "Equal");
				jpql.append(prop + " = ?");
			} else if (part.endsWith("IsNotNull")) {
				String prop = extractProp(part, "IsNotNull");
				jpql.append(prop + " is not null");
			} else if (part.endsWith("IsNull")) {
				String prop = extractProp(part, "IsNull");
				jpql.append(prop + " is null");
			} else if (part.endsWith("LessThan")) {
				String prop = extractProp(part, "LessThan");
				jpql.append(prop + " < ?");
			} else if (part.endsWith("LessThanEquals")) {
				String prop = extractProp(part, "LessThanEquals");
				jpql.append(prop + " <= ?");
			} else if (part.endsWith("GreaterThan")) {
				String prop = extractProp(part, "GreaterThan");
				jpql.append(prop + " > ?");
			} else if (part.endsWith("GreaterThanEquals")) {
				String prop = extractProp(part, "GreaterThanEquals");
				jpql.append(prop + " >= ?");
			} else if (part.endsWith("Between")) {
				String prop = extractProp(part, "Between");
				jpql.append(prop + " < ? AND " + prop + " > ?");
			} else if (part.endsWith("Like")) {
				String prop = extractProp(part, "Like");
				// HSQL -> LCASE, all other dbs lower
				if (isHSQL()) {
					jpql.append("LCASE(" + prop + ") like ?");
				} else {
					jpql.append("LOWER(" + prop + ") like ?");
				}
			} else if (part.endsWith("Ilike")) {
				String prop = extractProp(part, "Ilike");
				if (isHSQL()) {
					jpql.append("LCASE(" + prop + ") like LCASE(?)");
				} else {
					jpql.append("LOWER(" + prop + ") like LOWER(?)");
				}
			} else if (part.endsWith("Elike")) {
				String prop = extractProp(part, "Elike");
				jpql.append(prop + " like ?");
			} else {
				String prop = extractProp(part, "");
				jpql.append(prop + " = ?");
			}
			if (i < parts.length - 1) {
				jpql.append(" AND ");
			}
		}
		return jpql.toString();
	}

	private static boolean isHSQL() {
		return true;
	}

	protected static String extractProp(String part, String end) {
		String prop = part.substring(0, part.length() - end.length());
		prop = (prop.charAt(0) + "").toLowerCase() + prop.substring(1);
		return prop;
	}
}
