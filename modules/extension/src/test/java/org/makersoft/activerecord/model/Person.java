/*
 * @(#)Person.java 2013-4-1 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.activerecord.model;

/**
 * Class description goes here.
 * 
 * @version 2013-4-1 下午1:32:52
 * @author Feng Kuok
 */
public class Person {
	private String name;  
    private String age;  
    private String address;  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getAge() {  
        return age;  
    }  
  
    public void setAge(String age) {  
        this.age = age;  
    }  
  
    public String getAddress() {  
        return address;  
    }  
  
    public void setAddress(String address) {  
        this.address = address;  
    }  
}
