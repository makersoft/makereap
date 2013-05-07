/*
 * @(#)FormatHandlerMethodReturnValueHandlerTests.java 2013-2-5 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.unit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.makersoft.web.mvc.annotation.Format;
import org.makersoft.web.mvc.method.annotation.FormatHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * unit test for format annotation
 * @see Format
 * @see FormatHandlerMethodReturnValueHandler
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/web/mvc/servlet-context.xml")
public class FormatHandlerMethodReturnValueHandlerTests {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	@BeforeClass
	public static void init() {
	}
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testExcludes() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(get("/account/user/excludes/1").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
		 		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.password").doesNotExist())
				.andExpect(jsonPath("$.dept").doesNotExist())
				.andExpect(jsonPath("$.roles").doesNotExist());
		 
	}
	
	@Test
	public void testExcludeEntityAttributes() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(get("/account/user//exclude-entity-attributes/1").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
		 		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.dept.users").doesNotExist())
				.andExpect(jsonPath("$.roles.users").doesNotExist());
		 
	}
	
	@Test
	public void testIncludes() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(get("/account/user/includes/1").accept(MediaType.APPLICATION_JSON).headers(httpHeaders))
		 		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.password").doesNotExist())
				.andExpect(jsonPath("$.dept").doesNotExist())
				.andExpect(jsonPath("$.roles").doesNotExist());
		 
	}

}
