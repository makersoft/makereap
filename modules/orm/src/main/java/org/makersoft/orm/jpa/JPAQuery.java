/*
 * @(#)JPAQuery.java 2013-4-1 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm.jpa;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.makersoft.orm.Querying;
import org.makersoft.orm.jpa.exception.JPAQueryException;

/**
 * Class description goes here.
 * 
 * @version 2013-4-1 下午7:40:38
 * @author Feng Kuok
 */
public class JPAQuery implements Querying{
	public Query query;
	public String sq;

	public JPAQuery(String sq, Query query) {
		this.query = query;
		this.sq = sq;
	}

	public JPAQuery(Query query) {
		this.query = query;
		this.sq = query.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T first() {
		try {
			List<T> results = query.setMaxResults(1).getResultList();
			if (results.isEmpty()) {
				return null;
			}
			return results.get(0);
		} catch (Exception e) {
			throw new JPAQueryException("Error while executing query <strong>" + sq + "</strong>",
					JPAQueryException.findBestCause(e));
		}
	}

	/**
	 * Bind a JPQL named parameter to the current query.
	 */
	@Override
	public JPAQuery bind(String name, Object param) {
		if (param.getClass().isArray()) {
			param = Arrays.asList((Object[]) param);
		}
		if (param instanceof Integer) {
			param = ((Integer) param).longValue();
		}
		query.setParameter(name, param);
		return this;
	}

	/**
	 * Retrieve all results of the query
	 * 
	 * @return A list of entities
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> fetch() {
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new JPAQueryException("Error while executing query <strong>" + sq + "</strong>",
					JPAQueryException.findBestCause(e));
		}
	}

	/**
	 * Retrieve results of the query
	 * 
	 * @param max
	 *            Max results to fetch
	 * @return A list of entities
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> fetch(int max) {
		try {
			query.setMaxResults(max);
			return query.getResultList();
		} catch (Exception e) {
			throw new JPAQueryException("Error while executing query <strong>" + sq + "</strong>",
					JPAQueryException.findBestCause(e));
		}
	}

	/**
	 * Set the position to start
	 * 
	 * @param position
	 *            Position of the first element
	 * @return A new query
	 */
	@Override
	public <T> JPAQuery from(int position) {
		query.setFirstResult(position);
		return this;
	}

	/**
	 * Retrieve a page of result
	 * 
	 * @param page
	 *            Page number (start at 1)
	 * @param length
	 *            (page length)
	 * @return a list of entities
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> fetch(int page, int length) {
		if (page < 1) {
			page = 1;
		}
		query.setFirstResult((page - 1) * length);
		query.setMaxResults(length);
		try {
			return query.getResultList();
		} catch (Exception e) {
			throw new JPAQueryException("Error while executing query <strong>" + sq + "</strong>",
					JPAQueryException.findBestCause(e));
		}
	}
}
