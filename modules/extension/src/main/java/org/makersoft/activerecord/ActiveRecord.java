/*
 * @(#)ActiveRecord.java 2013-1-19 上午11:49:06
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.makersoft.activerecord.jpa.JPQL;

/**
 * Active record.
 * 
 * @version 2013-1-19 上午11:49:06
 * @author Feng Kuok
 */
public abstract class ActiveRecord implements Serializable {

	private static final long serialVersionUID = -5798422500008732404L;

	// --- persist method ---
	public boolean save() {
		throw new UnsupportedOperationException();
	}

	public boolean update() {
		throw new UnsupportedOperationException();
	}

	//和update一个样
	public void merge() {
		throw new UnsupportedOperationException();
	}

	public static boolean delete(Long id) {
		throw new UnsupportedOperationException();
	}

	public static void flush() {
		throw new UnsupportedOperationException();
	}

//	public <T> List<T> find() {
//		throw new UnsupportedOperationException();
//	}

//	// --- query method ---
	public static <T> T findById(Long id) {
		throw new UnsupportedOperationException();
	}

//	public static JPQL find(Long... ids) {
//		throw new UnsupportedOperationException();
//	}

	public static long count() {
		throw new UnsupportedOperationException();
	}

	public static <T> List<T> findAll() {
		throw new UnsupportedOperationException();
	}

	public static Querying all() {
		throw new UnsupportedOperationException();
	}

	// condition
	// -----------
	public static <E> JPQL select(String select) {
		throw new UnsupportedOperationException();
	}

	public static JPQL where(String hql) {
		throw new UnsupportedOperationException();
	}

	public static JPQL where(String hql, Map<String, Object> parameters) {
		throw new UnsupportedOperationException();
	}

	public static <E> JPQL where(Map<String, Object> parameters) {
		throw new UnsupportedOperationException();
	}
	
	public static <E> JPQL group(String group) {
		throw new UnsupportedOperationException();
	}
	

//	// Student.joins(:schools).where(:schools => { :category => 'public' })
//	// Student.joins(:schools).where('schools.category' => 'public' )
//
//	public static Querying joins(Class<? extends ActiveRecord> clazz) {
//
//		return null;
//	}
//
//	public static Querying order(String columns) {
//
//		return null;
//	}
	
//	where
//	select
//	group
//	order
	//	reorder
	//	reverse_order
//	limit
//	offset
//	joins
//	includes
	//	lock
	//	readonly
//	from
//	having

}
