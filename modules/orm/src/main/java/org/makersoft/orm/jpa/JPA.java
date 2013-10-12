/*
 * @(#)JPA.java 2013-1-19 下午11:31:30
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.makersoft.orm.ActiveRecordException;
import org.springframework.stereotype.Component;

/**
 * JPA Support.
 * 
 * @version 2013-1-19 下午11:31:30
 * @author Feng Kuok
 */
@Component
public class JPA {

	public static ThreadLocal<JPA> local = new ThreadLocal<JPA>();

	private static EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager){
		em = entityManager;
	}

	public JPA() {
		System.out.println("JPA init");
	}
	
    static JPA get() {
        if (local.get() == null) {
        	//"The JPA context is not initialized. JPA Entity Manager automatically start when one or more classes annotated with the @javax.persistence.Entity annotation are found in the application."
            throw new ActiveRecordException();
        }
        return local.get();
    }

	public static EntityManager em() {
		if (em == null) {
			throw new ActiveRecordException();
		}

		return em;
	}
}
