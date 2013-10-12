/*
 * @(#)Bootstrap.java 2013-2-22 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.orm.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.makersoft.orm.enhancer.PersistenceEnhancer;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.archiveiterator.Filter;
import org.scannotation.archiveiterator.IteratorFactory;
import org.scannotation.archiveiterator.StreamIterator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Class description goes here.
 * 
 * @version 2013-2-22 下午6:16:28
 * @author Feng Kuok
 */
public class Bootstrap implements BeanDefinitionRegistryPostProcessor,InitializingBean, ApplicationContextAware{
	private String basePackage = "org.makersoft.activerecord.model";

	private ApplicationContext applicationContext;
	private boolean init;

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.basePackage, "Property 'basePackage' is required");
		init();
	}

	private void init() {
		init = true;
		List<InputStream> inputStreams = new ArrayList<InputStream>();
		URL[] urls = getPackagePaths();

		Filter filter = new Filter() {
			public boolean accepts(String filename) {
				if (filename.endsWith(".class")) {
					return true;
				}

				return false;
			}
		};

		for (URL url : urls) {
			try {
				StreamIterator iterator = IteratorFactory.create(url, filter);

				InputStream stream;
				while ((stream = iterator.next()) != null) {
					inputStreams.add(stream);
				}
			} catch (IOException e) {
				
				//throw new RuntimeException(e);
			}
		}

		PersistenceEnhancer enhancer = new PersistenceEnhancer();
		for (InputStream inputStream : inputStreams) {
			try {
				enhancer.enhanceThisClass(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private URL[] getPackagePaths() {
		URL classpathUrl = ClasspathUrlFinder.findClassBase(this.getClass());
		try {
			String classpath = classpathUrl.getPath();
			String testClasspath = classpath.substring(0, classpath.lastIndexOf("classes"))
					+ "test-classes/";

			return new URL[] {
					new URL("file:" + classpath + basePackage.replaceAll("\\.", "/") + "/"),
					new URL("file:" + testClasspath + basePackage.replaceAll("\\.", "/") + "/") };

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
			throws BeansException {

		Scanner scanner = new Scanner(registry);
		scanner.setResourceLoader(this.applicationContext);

		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));

	}

	private final class Scanner extends ClassPathBeanDefinitionScanner {

		public Scanner(BeanDefinitionRegistry registry) {
			super(registry);
		}

		/**
		 * Configures parent scanner to search for the right bean. It can search for all class or
		 * just for those that implements ShardStrategy or/and those annotated with the
		 * annotationClass
		 */
		@Override
		protected void registerDefaultFilters() {

			// if specified, use the given annotation and / or marker interface
			// super.addIncludeFilter(new AnnotationTypeFilter(ShardStrategy.class));

			// exclude package-info.java
			super.addExcludeFilter(new TypeFilter() {
				public boolean match(MetadataReader metadataReader,
						MetadataReaderFactory metadataReaderFactory) throws IOException {
					String className = metadataReader.getClassMetadata().getClassName();
					return className.endsWith("package-info");
				}
			});
		}

		/**
		 * Calls the parent search that will search and register all the candidates. Then the
		 * registered objects are post processed to set them as ShardStrategy.
		 */
		@Override
		protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
			Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

//			if (beanDefinitions.isEmpty()) {
//				logger.warn("No Shard Strategy was found in '"
//						+ ApplicationConfigurer.this.basePackage
//						+ "' package. Please check your configuration.");
//			} else {
				if(init == false){
					init();
				}
//				 GenericBeanDefinition beanDef = new GenericBeanDefinition();
//				 beanDef.setBeanClass(PersistenceHelper.class);
//				 
//				 SqlSessionTemplate sessionTemplate = applicationContext.getBean(SqlSessionTemplate.class);
//				 beanDef.getPropertyValues().add("sqlSessionTemplate", sessionTemplate);
//
//				 BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDef,"persistenceHelper");
//				 super.registerBeanDefinition(definitionHolder, super.getRegistry());
				// ShardReduceStrategyContext reduceStrategyContext = applicationContext
				// .getBean(ShardReduceStrategyContext.class);
				for (BeanDefinitionHolder holder : beanDefinitions) {
					GenericBeanDefinition definition = (GenericBeanDefinition) holder
							.getBeanDefinition();

					if (logger.isDebugEnabled()) {
						logger.debug("Creating Strategy with name '" + holder.getBeanName()
								+ "' and '" + definition.getBeanClassName() + "'.");
					}

					// ShardReduceStrategy reduceStrategy = (ShardReduceStrategy) applicationContext
					// .getBean(holder.getBeanName());
					// ShardedReducer reducer = reduceStrategy.getClass().getAnnotation(
					// ShardedReducer.class);
					//
					// reduceStrategyContext.put(reducer.mapper(), reducer.statements(),
					// reduceStrategy);

				}
//			}

			return beanDefinitions;
		}

		@Override
		protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
			return (!beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata()
					.isIndependent());
		}

		@Override
		protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition)
				throws IllegalStateException {
			if (super.checkCandidate(beanName, beanDefinition)) {
				return true;
			} else {
//				logger.warn("Skipping strategy bean  with name '" + beanName + "' and '"
//						+ beanDefinition.getBeanClassName()
//						+ "'. Bean already defined with the same name!");
				return false;
			}
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
			throws BeansException {

	}
}
