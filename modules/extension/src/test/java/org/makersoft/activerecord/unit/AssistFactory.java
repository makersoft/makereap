/*
 * @(#)AssistFactory.java 2013-4-1 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.unit;

import java.util.concurrent.atomic.AtomicInteger;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import org.junit.Test;
import org.makersoft.activerecord.model.Person;

/**
 * Class description goes here.
 * 
 * @version 2013-4-1 下午1:31:18
 * @author Feng Kuok
 */
public class AssistFactory {
	 // Class载入器  
    private static ClassPool     pool;  
    // 原子计数器  
    private static AtomicInteger number = new AtomicInteger(1);  
    static {  
        pool = ClassPool.getDefault();  
    }  
  
    public void compileAndExe(String body) throws Exception {  
        String name = Person.class.getName();  
        // 新定义的子类，可以修改  
        CtClass cc = pool.makeClass(name + "$" + number.incrementAndGet());  
        // 父类  
        cc.setSuperclass(pool.get(name));  
        // 复写父类方法  
        String method = "public String getName(){ System.out.println(super.getName()+\" is %s!\"); return super.getName(); }";  
          
        method=String.format(method, body);  
        // 将新方法添加到类中  
        cc.addMethod(CtMethod.make(method, cc));  
  
        // 类模板  
        Class<?> c = cc.toClass();  
        cc.detach();  
  
        // 实例化对象  
        Person p = (Person) c.newInstance();  
        p.setName("onlyone");  
  
        p.getName();  
    }  
  
    public static void main(String[] args) throws Exception {  
//        new AssistFactory().compileAndExe("sign");  
    	
    	AssistFactory test = new AssistFactory();
    	System.out.println(test.findByToJPQL("findByUsernameAndPassword"));
    }  
    
    
    protected static String extractProp(String part, String end) {
        String prop = part.substring(0, part.length() - end.length());
        prop = (prop.charAt(0) + "").toLowerCase() + prop.substring(1);
        return prop;
    }
    
    private boolean isHSQL() {
//        String db = Play.configuration.getProperty("db");
//        return ("mem".equals(db) || "fs".equals(db) || "org.hsqldb.jdbcDriver".equals(Play.configuration.getProperty("db.driver")));
    	return true;
    }
    
    public String findByToJPQL(String findBy) {
        findBy = findBy.substring(6);
        StringBuffer jpql = new StringBuffer();
        String[] parts = findBy.split("And");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.endsWith("NotEqual")) {
                String prop = extractProp(part, "NotEqual");
                jpql.append(prop + " <> ?");
            } else if (part.endsWith("Equal")) {
                String prop = extractProp(part, "Equal");
                jpql.append(prop + " = ?");
            } else if (part.endsWith("IsNotNull")) {
                String prop = extractProp(part, "IsNotNull");
                jpql.append(prop + " is not null");
            } else if (part.endsWith("IsNull")) {
                String prop = extractProp(part, "IsNull");
                jpql.append(prop + " is null");
            } else if (part.endsWith("LessThan")) {
                String prop = extractProp(part, "LessThan");
                jpql.append(prop + " < ?");
            } else if (part.endsWith("LessThanEquals")) {
                String prop = extractProp(part, "LessThanEquals");
                jpql.append(prop + " <= ?");
            } else if (part.endsWith("GreaterThan")) {
                String prop = extractProp(part, "GreaterThan");
                jpql.append(prop + " > ?");
            } else if (part.endsWith("GreaterThanEquals")) {
                String prop = extractProp(part, "GreaterThanEquals");
                jpql.append(prop + " >= ?");
            } else if (part.endsWith("Between")) {
                String prop = extractProp(part, "Between");
                jpql.append(prop + " < ? AND " + prop + " > ?");
            } else if (part.endsWith("Like")) {
                String prop = extractProp(part, "Like");
                // HSQL -> LCASE, all other dbs lower
                if (isHSQL()) {
                    jpql.append("LCASE(" + prop + ") like ?");
                } else {
                    jpql.append("LOWER(" + prop + ") like ?");
                }
            } else if (part.endsWith("Ilike")) {
                String prop = extractProp(part, "Ilike");
                 if (isHSQL()) {
                    jpql.append("LCASE(" + prop + ") like LCASE(?)");
                 } else {
                    jpql.append("LOWER(" + prop + ") like LOWER(?)");
                 }
            } else if (part.endsWith("Elike")) {
                String prop = extractProp(part, "Elike");
                jpql.append(prop + " like ?");
            } else {
                String prop = extractProp(part, "");
                jpql.append(prop + " = ?");
            }
            if (i < parts.length - 1) {
                jpql.append(" AND ");
            }
        }
        return jpql.toString();
    }
}
