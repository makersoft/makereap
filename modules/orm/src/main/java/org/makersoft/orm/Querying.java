/*
 * @(#)Querying.java 2012-12-27 下午2:35:09
 *
 * Copyright (c) 2011-2012 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm;

import java.util.List;

import org.makersoft.orm.jpa.JPAQuery;

/**
 * Class description goes here.
 * 
 * @version 2012-12-27 下午2:35:09
 * @author Feng Kuok
 */
public interface Querying {
	
	<T> T first();
	
	Querying bind(String name, Object param);
	
	<T> List<T> fetch();
	
	<T> List<T> fetch(int max);
	
	<T> JPAQuery from(int position);
	
	<T> List<T> fetch(int page, int length);
	
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
