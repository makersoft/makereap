/*
 * @(#)UserModel.java 2013-2-17 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.makersoft.activerecord.ActiveRecord;

/**
 * model for test
 */
@Entity
@Table(name = "t_user")
public class UserModel extends ActiveRecord {

	private static final long serialVersionUID = 8478806430101921161L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(length = 64)
	public String username;
	
	@Column(length = 32)
	public String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static UserModel findByUsernameAndPassword(String username, String password){
		UserModel entity = new UserModel();
		entity.username = username;
		entity.password = password;
		
		return null;
		
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
