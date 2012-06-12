/*
* @(#)AbstractUUIDEntity.java 2012-5-8 下午10:17:04
*
* Copyright (c) 2011-2012 Makersoft.org all rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
*
*/
package org.makersoft.core.domain.model.identity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NaturalId;

/**
 * 
 * UUID主键基类.
 *
 * @version	2012-6-12
 * @author	GuoFeng
 */
@MappedSuperclass
public abstract class AbstractUUIDEntity implements Serializable{
	
	private static final long serialVersionUID = -9075060116000471209L;

	@NaturalId(mutable=true) 
	@Column(length=36)
	private String id;
	
	/**
	 * Sets the uuid for the instance
	 */
	public AbstractUUIDEntity() {
		id = UUID.randomUUID().toString();
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
