/*
 * @(#)DeptController.java 2013-2-6 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.mvc.controllers.account;

import org.makersoft.mvc.annotation.RESTfull;
import org.makersoft.mvc.model.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@RESTfull
@Controller
public class DeptController {

	public String index(){
		return "account/dept/index";
	}
	
	public String show(@PathVariable Long id){
		
		return "account/dept/show";
	}
	
	public String _new(){
		return "account/dept/_form";
	}
	
	public String create(Dept dept){
		
		return "redirect:/account/dept";
	}
	
	public String edit(@PathVariable Long id){
		
		return "account/dept/_form";
	}
	
	public String update(@PathVariable Long id, Dept dept){
		return "redirect:/account/dept";
	}
	
	public String destroy(@PathVariable Long id){
		return "redirect:/account/dept";
	}
	
}
