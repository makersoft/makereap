/*
 * @(#)PersistenceEnhancer.java 2012-9-24 下午1:27:41
 *
 * Copyright (c) 2011-2012 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.enhancer;

import java.io.InputStream;

import javassist.CtClass;
import javassist.CtMethod;

/**
 * Class description goes here.
 * 
 * @version 2012-9-24 下午1:27:41
 * @author Feng Kuok
 */
public class PersistenceEnhancer extends Enhancer{

	@Override
	public void enhanceThisClass(InputStream inputStream) throws Exception {
		CtClass ctClass = super.makeClass(inputStream);
		
		
        if (!ctClass.subtypeOf(classPool.get("org.makersoft.activerecord.ActiveRecord"))) {
            return;
        }
        
        String entityName = ctClass.getName();
        
        // Enhance only JPA entities
        if (!hasAnnotation(ctClass, "javax.persistence.Entity")) {
            return;
        }

        // count
        CtMethod count = CtMethod.make("public static long count() { return org.makersoft.activerecord.jpa.JPQL.count("+entityName+".class); }", ctClass);
        ctClass.addMethod(count);

        // findAll
        CtMethod findAll = CtMethod.make("public static java.util.List findAll() { return  org.makersoft.activerecord.jpa.JPQL.findAll(\"" + entityName + "\"); }", ctClass);
        ctClass.addMethod(findAll);

        // findById
        CtMethod findById = CtMethod.make("public static Object findById(java.lang.Long id) { return  org.makersoft.activerecord.jpa.JPQL.findById("+entityName+".class, id); }", ctClass);
        ctClass.addMethod(findById);
//
//        // find
//        CtMethod find = CtMethod.make("public static play.db.jpa.GenericModel.JPAQuery find(String query, Object[] params) { return  getJPAConfig("+entityName+".class).jpql.find(\"" + entityName + "\", query, params); }", ctClass);
//        ctClass.addMethod(find);
//
//        // find
//        CtMethod find2 = CtMethod.make("public static play.db.jpa.GenericModel.JPAQuery find() { return  getJPAConfig("+entityName+".class).jpql.find(\"" + entityName + "\"); }", ctClass);
//        ctClass.addMethod(find2);

        // all
        CtMethod all = CtMethod.make("public static org.makersoft.activerecord.Querying all() { return  org.makersoft.activerecord.jpa.JPQL.all(\"" + entityName + "\"); }", ctClass);
        ctClass.addMethod(all);

//
//        // deleteAll
//        CtMethod deleteAll = CtMethod.make("public static int deleteAll() { return  getJPAConfig("+entityName+".class).jpql.deleteAll(\"" + entityName + "\"); }", ctClass);
//        ctClass.addMethod(deleteAll);
//
//        // findOneBy
//        CtMethod findOneBy = CtMethod.make("public static play.db.jpa.JPABase findOneBy(String query, Object[] params) { return  getJPAConfig("+entityName+".class).jpql.findOneBy(\"" + entityName + "\", query, params); }", ctClass);
//        ctClass.addMethod(findOneBy);
//
//        // create
//        CtMethod create = CtMethod.make("public static play.db.jpa.JPABase create(String name, play.mvc.Scope.Params params) { return  getJPAConfig("+entityName+".class).jpql.create(\"" + entityName + "\", name, params); }", ctClass);
//        ctClass.addMethod(create);
        
        // save
        CtMethod save = CtMethod.make("public boolean save() { org.makersoft.activerecord.jpa.JPA.em().persist(this);  return  true; }", ctClass);
        ctClass.addMethod(save);
        
        // update
        CtMethod update = CtMethod.make("public boolean update() { org.makersoft.activerecord.jpa.JPA.em().persist(this);  return  true; }", ctClass);
        ctClass.addMethod(update);
        
        // delete 
        CtMethod delete = CtMethod.make("public static boolean delete(java.lang.Long id) { org.makersoft.activerecord.jpa.JPA.em().remove(org.makersoft.activerecord.jpa.JPA.em().find("+entityName+".class, id)); return true; }", ctClass);
        ctClass.addMethod(delete);
        
        // merge
        CtMethod merge = CtMethod.make("public void merge() { org.makersoft.activerecord.jpa.JPA.em().merge(this); }", ctClass);
        ctClass.addMethod(merge);
        
        //where
        CtMethod where = CtMethod.make("public static org.makersoft.activerecord.jpa.JPQL where(java.lang.String hql, java.util.Map parameters) { return org.makersoft.activerecord.jpa.JPQL.newInstance("+entityName+".class).where(hql, parameters); }", ctClass);
        ctClass.addMethod(where);
        
        //select
        CtMethod select = CtMethod.make("public static org.makersoft.activerecord.jpa.JPQL select(java.lang.String select) { return org.makersoft.activerecord.jpa.JPQL.newInstance("+entityName+".class).select(select); }", ctClass);
        ctClass.addMethod(select);
        
        //group
        CtMethod group = CtMethod.make("public static org.makersoft.activerecord.jpa.JPQL group(java.lang.String group) { return org.makersoft.activerecord.jpa.JPQL.newInstance("+entityName+".class).group(group); }", ctClass);
        ctClass.addMethod(group);
        

        // Done.
        ctClass.defrost();
        
        this.getClass().getClassLoader().loadClass(ctClass.toClass().getName());
	}
	
}
