/*
 * @(#)UserController.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.mvc.controllers.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.makersoft.mvc.annotation.Format;
import org.makersoft.mvc.model.Dept;
import org.makersoft.mvc.model.Role;
import org.makersoft.mvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@RESTfull
@Controller
@RequestMapping("/account/user")
public class UserController {
	
	@Format(excludes = {"password", "dept" , "roles"})
	@RequestMapping("/excludes/{id}")
	public User excludes(@PathVariable Long id){
		
		return getUser(id);
	}
	
	@Format(includes = {"id", "username"})
	@RequestMapping("/includes/{id}")
	public User includes(@PathVariable Long id){
		
		return getUser(id);
	}
	
	@Format(excludes = {"password", "dept.id", "dept.users", "roles.*.id", "roles.*.users", "roles.*.name"})
	@RequestMapping("/exclude-entity-attributes/{id}")
	public User excludeEntityAttributes(@PathVariable Long id){
		return getUser(id);
	}
	
	private User getUser(Long id){
		User user = new User();
		user.setId(id);
		user.setUsername("makersoft");
		user.setPassword("password");
		user.setCreatedDate(new Date());
		user.setEnable(true);
		
		Dept dept = new Dept();
		dept.setId(id);
		dept.setName("dept");
		
		user.setDept(dept);
		
		Role admin = new Role();
		admin.setId(id);
		admin.setCode("ROLE_ADMIN");
		
		Role guest = new Role(); 
		guest.setId(id + 1);
		guest.setCode("ROLE_GUEST");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(admin);
		roles.add(guest);
		
		user.setRoles(roles);
		
		return user;
	}

}
