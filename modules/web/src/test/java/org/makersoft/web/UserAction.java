/*
 * @(#)UserAction.java Dec 28, 2013 5:30:36 PM
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web;

import org.makersoft.web.domain.model.User;

public class UserAction extends Action{

	private static final long serialVersionUID = 4658497438292617105L;

	static {
		
		get("/", new Handler() {
			
			@Override
			public Result excute() {
				return null;
			}
		});
		
		post("/", new Handler(){
			
			@Override
			public Result excute() {
				final User user = params(User.class);
				
				if(!user.save()) {
					return Result.error();
				}
				
				return Result.ok().json(user);
			}
		});
		
		delete("/{id}", new Handler(){
			
			@Override
			public Result excute() {
				Long id = params("id");
				if(User.delete(id)) {
					
				} 
				
				return Result.format(new Render(){
					@Override
					public void json() {
					}
				});
			}
		});
		
		put("/{id}", new Handler(){
			
			@Override
			public Result excute() {
				return null;
			}
		});
		
	}
}
