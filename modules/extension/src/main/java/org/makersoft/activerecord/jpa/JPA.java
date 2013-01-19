/*
 * @(#)JPA.java 2013-1-19 下午1:31:30
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.jpa;

import javax.persistence.EntityManager;

import org.makersoft.activerecord.ActiveRecordException;

/**
 * JPA Support.
 * 
 * @version 2013-1-19 下午1:31:30
 * @author Feng Kuok
 */
public class JPA {

	public static EntityManager em() {
        throw new ActiveRecordException();
    }
}
