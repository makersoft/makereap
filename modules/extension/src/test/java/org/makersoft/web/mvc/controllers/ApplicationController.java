/*
 * @(#)ApplicationController.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.controllers;

import org.makersoft.web.mvc.annotation.RESTfull;
import org.springframework.stereotype.Controller;

/**
 * user controller for test
 */

@RESTfull
@Controller
public class ApplicationController {

	public String index(){
		
		return "index";
	}
}
