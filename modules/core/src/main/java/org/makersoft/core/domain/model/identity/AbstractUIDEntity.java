/*
* @(#)AbstractUIDEntity.java 2012-5-8 下午10:21:15
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
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

/**
 * UID主键基类.
 *
 * @version	2012-5-8
 * @author	GuoFeng
 */
@MappedSuperclass
@EntityListeners({AbstractUIDEntity.AbstractEntityListener.class})
public abstract class AbstractUIDEntity implements Serializable {
	
	private static final long serialVersionUID = 5637339046722138126L;

	@Id 
	@GeneratedValue
    private Long id;

    /* "UUID" and "UID" are Oracle reserved keywords -> "ENTITY_UID" */
    @Column(name="ENTITY_UID", unique=true, nullable=false, updatable=false, length=36)
    private String uid;

    @Version
    private Integer version;

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        return (o == this || (o instanceof AbstractUIDEntity && uid().equals(((AbstractUIDEntity)o).uid())));
    }

    @Override
    public int hashCode() {
        return uid().hashCode();
    }

    public static class AbstractEntityListener {

        @PrePersist
        public void onPrePersist(AbstractUIDEntity abstractEntity) {
            abstractEntity.uid();
        }
    }

    private String uid() {
        if (uid == null)
            uid = UUID.randomUUID().toString();
        return uid;
    }
}
