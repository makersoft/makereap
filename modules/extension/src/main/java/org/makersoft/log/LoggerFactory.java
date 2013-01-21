/*
 * @(#)LoggerFactory.java 2013-1-21 下午3:08:43
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Creates loggers.  Static accessor will lazily try to decide on the best factory if none specified.
 */
public abstract class LoggerFactory {
    
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static LoggerFactory factory;
    
    public static void setLoggerFactory(LoggerFactory factory) {
        lock.writeLock().lock();
        try {
            LoggerFactory.factory = factory;
        } finally {
            lock.writeLock().unlock();
        }
            
    }
    
    public static Log getLogger(Class<?> cls) {
        return getLoggerFactory().getLoggerImpl(cls);
    }
    
    public static Log getLogger(String name) {
        return getLoggerFactory().getLoggerImpl(name);
    }
    
    protected static LoggerFactory getLoggerFactory() {
        lock.readLock().lock();
        try {
            if (factory != null) {
                return factory;
            }
        } finally {
            lock.readLock().unlock();
        }
        lock.writeLock().lock();
        try {
            if (factory == null) {
                try {
//                    Class.forName("org.apache.commons.logging.LogFactory");
//                    factory = new CommonsLoggerFactory();
                	
                	Class.forName("org.slf4j.LoggerFactory");
//                	factory = new Sl4jLoggerFactory();
                } catch (ClassNotFoundException ex) {
                    // commons logging not found, falling back to jdk logging
                }
            }
            return factory;
        }
        finally {
            lock.writeLock().unlock();
        }
    }
    
    protected abstract Log getLoggerImpl(Class<?> cls);
    
    protected abstract Log getLoggerImpl(String name); 

}

