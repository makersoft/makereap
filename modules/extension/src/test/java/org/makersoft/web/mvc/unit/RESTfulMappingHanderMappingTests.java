/*
 * @(#)RESTfulMappingHanderMappingTests.java 2013-5-6 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Class description goes here.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/web/mvc/servlet-context.xml")
public class RESTfulMappingHanderMappingTests {

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
	public void testRoot() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(get("/").accept(MediaType.TEXT_HTML).headers(httpHeaders)).andDo(print())
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(forwardedUrl("/WEB-INF/views/index.jsp"));

	}

	@Test
	public void testIndex() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(get("/account/dept").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("account/dept/index"))
				.andExpect(forwardedUrl("/WEB-INF/views/account/dept/index.jsp"));

	}

	@Test
	public void testShow() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(get("/account/dept/1").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("account/dept/show"))
				.andExpect(forwardedUrl("/WEB-INF/views/account/dept/show.jsp"));

	}

	@Test
	public void testNew() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(get("/account/dept/new").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("account/dept/_form"))
				.andExpect(forwardedUrl("/WEB-INF/views/account/dept/_form.jsp"));

	}

	@Test
	public void testCreate() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(post("/account/dept").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("/account/dept"));

	}

	@Test
	public void testEdit() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(
				get("/account/dept/1/edit").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("account/dept/_form"))
				.andExpect(forwardedUrl("/WEB-INF/views/account/dept/_form.jsp"));

	}

	@Test
	public void testUpdate() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(put("/account/dept/1").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("/account/dept"));

	}

	@Test
	public void testDestroy() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.TEXT_HTML);
		mockMvc.perform(delete("/account/dept/1").accept(MediaType.TEXT_HTML).headers(httpHeaders))
				.andDo(print()).andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("/account/dept"));

	}

}
