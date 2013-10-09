/*
 * @(#)Dept.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * entity for test.
 */
public class Dept implements Serializable {

	private static final long serialVersionUID = -1665006573884858700L;

	private Long id;

	private String name;

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dept [id=" + id + ", name=" + name + ", users=[");
		for(User user : users){
			sb.append(user);
			sb.append(",");
		}
		sb.append("]]");
		
		return sb.toString();
	}

}
