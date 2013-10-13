/*
 * @(#)FixturesTests.java 2013-1-22 下午5:11:14
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.test.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.makersoft.test.Fixtures;
import org.makersoft.test.fixture.Core;
import org.makersoft.test.model.User;
import org.yaml.snakeyaml.Yaml;

/**
 * Class description goes here.
 * 
 * @version 2013-1-22 下午5:11:14
 * @author Feng Kuok
 */
public class FixturesTests {

	@Test
	public void testLoadYaml() throws Exception {
		Object object = Fixtures.loadYaml("/test/fixtures/Users.yml");
		Assert.assertNotNull(object);

		User user = Fixtures.loadYaml("/test/fixtures/User.yml", User.class);
		Assert.assertNotNull(user);
		
		List<User> list = Fixtures.loadYamlAsList("/test/fixtures/Users.yml");
		Assert.assertNotNull(list);
	}

	@Test
	public void testDump() throws Exception {
		Yaml yaml = new Yaml();
		User user = new User();
		user.setId(1L);
		user.setUsername("makersoft");
		user.setEmail("admin@makersoft.org");
		
		Map<String, User> users = new HashMap<String, User>();
		users.put("makersoft", user);
		
		System.out.println(yaml.dump(users));
//		assertEquals(etalon, yaml.dump(user));
//		assertEquals(etalon, yaml.dump(user));
	}
	
	@Test
	public void testLoad(){
		new Core().load("/test/users.yaml");
	}
	
	@Test
	public void testLoads(){
		org.makersoft.test.fixture.Fixtures.loadYaml("/test/users.yaml");
//		org.makersoft.test.fixture.Fixtures.loadYaml("/test/depts.yaml");
	}
	
	@Test
	public void testPetTypes(){
		org.makersoft.test.fixture.Fixtures.loadYaml("/test/pet_types.yaml");
		org.makersoft.test.fixture.Fixtures.loadYaml("/test/depts.yaml");
	}

}
