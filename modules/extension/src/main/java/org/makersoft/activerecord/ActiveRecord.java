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

/**
 * Active record.
 * 
 * @version 2013-1-19 上午11:49:06
 * @author Feng Kuok
 */
public abstract class ActiveRecord implements Serializable {

	private static final long serialVersionUID = -5798422500008732404L;

	// http://guides.rubyonrails.org/active_record_validations_callbacks.html
	// create
	// create!
	// save
	// save!
	// update
	// update_attributes
	// update_attributes!

	// decrement!
	// decrement_counter
	// increment!
	// increment_counter
	// toggle!
	// touch
	// update_all
	// update_attribute
	// update_column
	// update_counters

	// --- persist method ---
	public boolean save() {
		return true;
	}

	public boolean update() {
		return true;
	}

//	public static <T extends ActiveRecord> boolean updateAttributes(T entity) {
//		return true;
//	}

	public static boolean remove(Long id) {
		return true;
	}

	public void flush() {
		throw new UnsupportedOperationException();
	}

	public void merge() {
		throw new UnsupportedOperationException();
	}

//	public <T> List<T> find() {
//		throw new UnsupportedOperationException();
//	}

//	// --- query method ---
//	public static java.lang.Object find(Long id) {
//		throw new UnsupportedOperationException();
//	}
//
//	public static List<? extends ActiveRecord> find(Long... ids) {
//		throw new UnsupportedOperationException();
//	}
//
//	public static long count() {
//		throw new UnsupportedOperationException();
//	}
//
//	public static List<? extends ActiveRecord> findAll() {
//		throw new UnsupportedOperationException();
//	}
//
//	public static List<? extends ActiveRecord> findEntityEntries(int firstResult, int maxResults) {
//		throw new UnsupportedOperationException();
//	}
//
//	// condition
//	// -----------
//	protected static Querying where(String hql) {
//		return null;
//	}
//
//	protected static Querying where(String hql, Map<String, Object> properties) {
//		return null;
//	}
//
//	protected static <E> Querying where(String hql, E e) {
//		return null;
//	}
//
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
}
