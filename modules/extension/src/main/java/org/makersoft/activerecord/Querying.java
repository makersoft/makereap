/*
 * @(#)Querying.java 2012-12-27 下午2:35:09
 *
 * Copyright (c) 2011-2012 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord;

import java.util.List;

/**
 * Class description goes here.
 * 
 * @version 2012-12-27 下午2:35:09
 * @author Feng Kuok
 */
public interface Querying {
	
	List<?> list();
	
	<E> E first();
	
	Querying setParameter(String name, Object value);
	
	Querying setParameter(int index, Object value);
	
	Querying setMaxResults(int maxResults);

	Querying setFirstResult(int firstResult);

}
