/*
* @(#)AbstractEntity.java 2012-5-8 下午10:17:04
*
* Copyright (c) 2011-2012 Makersoft.org all rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
*
*/
package org.makersoft.core.domain.model.identity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Class implementing the basic properties of all entities.
 *
 * @version	2012-5-8
 * @author	GuoFeng
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 4493632404082395301L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@Basic
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastChange;

	public final Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public final Date getLastChange() {
		return lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

}