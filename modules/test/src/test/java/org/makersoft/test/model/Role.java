/*
 * @(#)Role.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * entity for test
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -7567934109194961307L;

	private Long id;

	private String name;
	
	private String code;

	private List<User> users = Collections.emptyList();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
