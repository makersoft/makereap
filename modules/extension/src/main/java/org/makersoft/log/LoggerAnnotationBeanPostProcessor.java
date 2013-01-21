/*
 * @(#)LoggerAnnotationBeanPostProcessor.java 2013-1-21 下午3:19:03
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.log;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.PriorityOrdered;

/**
 * Logger注入.
 */
public class LoggerAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter
		implements MergedBeanDefinitionPostProcessor, PriorityOrdered, BeanFactoryAware {

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition,
			Class<?> beanType, String beanName) {
		if (beanType != null) {
//			InjectionMetadata metadata = findAutowiringMetadata(beanType);
//			metadata.checkConfigMembers(beanDefinition);
		}

	}

}
