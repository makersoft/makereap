/*
 * @(#)JPATest.java 2013-2-18 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm.unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.makersoft.orm.jpa.JPA;
import org.makersoft.orm.model.UserModel;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/activerecord/applicationContext.xml")
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@Transactional
@ActiveProfiles("test")
public class JPATest {
	
	@Resource
	private EntityManagerFactory entityManagerFactory;  
	
	@PersistenceContext
    private EntityManager em;
    
    @Test
    @Transactional
    public void testInit(){
    	
    	Assert.assertNotNull(entityManagerFactory);
    	Assert.assertNotNull(em);
    	Assert.assertNotNull(JPA.em());
    	
    	final UserModel user = new UserModel();
    	user.username = "makersoft";
    	user.password = "fengkuok";
    	
    	em.persist(user);
    	em.remove(em.find(UserModel.class, user.id));
    	em.merge(user);
    	
    	System.out.println(em.contains(user));
    	
    	Query q = em.createQuery("select count(*) from UserModel");
    	System.out.println(q.getSingleResult());
    	
    	System.out.println(user.getId());
    }
    
    @Test
    @Transactional
    public void testActiveRecord(){
    	final UserModel user = new UserModel();
    	user.username = "makersoft";
    	user.password = "fengkuok";
    	
    	System.out.println(user.save());
    	System.out.println(user.id);
    	
//    	user.username = "xfsdfa";
    	
//    	UserModel.delete(user.id);
    	
    	final UserModel user2 = new UserModel();
    	user2.id = user.id;
    	user2.username = "user2";
    	
    	user2.merge();
    	
    	System.out.println(UserModel.count());
    	
    	List<UserModel> users = UserModel.findAll();
    	
    	for(UserModel entity : users){
    		System.out.println(entity);
    	}
    	
    	UserModel u1 = UserModel.findById(1L);
    	System.out.println("find by id=1" + u1);
    	
    	users = UserModel.all().fetch();
    	for(UserModel entity : users){
    		System.out.println(entity);
    	}
    }
    
    
    @Test
    @Transactional
    public void testWhere() throws Exception {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", "%maker%");
    	List<UserModel> users = UserModel.where("username like :username", parameters).order("id desc").list();
    	
    	for(UserModel entity : users){
    		System.out.println(entity);
    	}
    	
    	Map<String, Object> parameters2 = new HashMap<String, Object>();
    	parameters2.put("id", 1L);
    	UserModel user = UserModel.where("id =  :id", parameters2).single();
    	System.out.println(user);
    }
    
    @Test
    @Transactional
    public void testOrder(){
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", "%maker%");
    	List<UserModel> users = UserModel.where("username like :username", parameters).order("id desc, username asc").list();
    	for(UserModel entity : users){
    		System.out.println(entity);
    	}
    }
    
    @Test
    @Transactional
    public void testSelect(){
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	parameters.put("username", "%maker%");
    	List users = UserModel.select("id, username").list();
    	for(Object entity : users){
    		System.out.println(entity);
    	}
    }
    
    @Test
    @Transactional
    public void testGroup(){
    	List users = UserModel.select("username, count(*)").group("username").list();
    	for(Object entity : users){
    		System.out.println(entity);
    	}
    }
}
