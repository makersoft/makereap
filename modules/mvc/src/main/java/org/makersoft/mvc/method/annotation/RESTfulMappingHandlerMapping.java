/*
 * @(#)RestfullMappingHandlerMapping.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.mvc.method.annotation;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.makersoft.log.Log;
import org.makersoft.log.LoggerFactory;
import org.makersoft.mvc.annotation.RESTfull;
import org.makersoft.mvc.builder.PackageBasedUrlPathBuilder;
import org.makersoft.mvc.mapping.Mapping;
import org.makersoft.mvc.mapping.RESTfulHelper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

/**
 * 适用于带有{@link RESTfull }注解的Controller类,用于构造RESTful请求路径.
 * @see RESTfull
 * 
 * @author Feng Kuok
 */
public class RESTfulMappingHandlerMapping  extends RequestMappingInfoHandlerMapping{
	
	private static final Log LOG = LoggerFactory.getLogger(RESTfulMappingHandlerMapping.class);

	private boolean useSuffixPatternMatch = true;

	private boolean useTrailingSlashMatch = true;
	
	private String[] packageLocators = new String[]{"web", "controllers", "actions", "action", "controller"};
	
	private String[] controllerPackages = null;
	
	private String controllerSuffix = "Controller";
	
	private String root = "application#index";
	
	//default constructor
	public RESTfulMappingHandlerMapping(){
		//set HandlerMapping order 
		super.setOrder(0);
		
		LOG.debug("Init RESTfulMappingHandlerMapping...");
	}
	
	/**
	 * Whether to use suffix pattern match (".*") when matching patterns to
	 * requests. If enabled a method mapped to "/users" also matches to "/users.*".
	 * <p>The default value is {@code true}. 
	 */
	public void setUseSuffixPatternMatch(boolean useSuffixPatternMatch) {
		this.useSuffixPatternMatch = useSuffixPatternMatch;
	}
	
	/**
	 * Whether to match to URLs irrespective of the presence of a trailing slash.
	 * If enabled a method mapped to "/users" also matches to "/users/".
	 * <p>The default value is {@code true}.
	 */
	public void setUseTrailingSlashMatch(boolean useTrailingSlashMatch) {
		this.useTrailingSlashMatch = useTrailingSlashMatch;
	}

	/**
	 * 用于指定Controller类的跟包名称
	 * 默认为"web", "controllers", "actions", "action", "controller" 
	 */
	public void setPackageLocators(String packageLocators) {
		this.packageLocators = StringUtils.split(packageLocators, ",");
	}

	/**
	 * 用于指定Controller所在包名称
	 */
	public void setControllerPackages(String controllerPackages) {
		this.controllerPackages = StringUtils.split(controllerPackages, ",");
	}

	/**
	 * 用于指定Controller类名称后缀,默认为Controller
	 */
	public void setControllerSuffix(String controllerSuffix) {
		this.controllerSuffix = controllerSuffix;
	}

	/**
	 * 指定访问根路径,默认为application#index
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * Whether to use suffix pattern matching.
	 */
	public boolean useSuffixPatternMatch() {
		return this.useSuffixPatternMatch;
	}
	/**
	 * Whether to match to URLs irrespective of the presence of a trailing  slash.
	 */
	public boolean useTrailingSlashMatch() {
		return this.useTrailingSlashMatch;
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
	}

	/**
	 * {@inheritDoc} 
	 * Expects a handler to have a type-level @{@link Controller} annotation.
	 */
	@Override
	protected boolean isHandler(Class<?> beanType) {
		return AnnotationUtils.findAnnotation(beanType, Controller.class) != null;
	}

	/**
	 * Uses method and type-level @{@link RequestMapping} annotations to create
	 * the RequestMappingInfo.
	 * 
	 * @return the created RequestMappingInfo, or {@code null} if the method
	 * does not have a {@code @RequestMapping} annotation.
	 * 
	 * @see #getCustomMethodCondition(Method)
	 * @see #getCustomTypeCondition(Class)
	 */
	@Override
	protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
		RequestMappingInfo info = null;
		RESTfull clazzAnnotation = AnnotationUtils.findAnnotation(handlerType, RESTfull.class);
		if (clazzAnnotation != null) {
			info = createRequestMappingInfo(method, clazzAnnotation, handlerType);
//			RequestMapping typeAnnotation = AnnotationUtils.findAnnotation(handlerType, RequestMapping.class);
//			if (typeAnnotation != null) {
//				RequestCondition<?> typeCondition = getCustomTypeCondition(handlerType);
//				info = createRequestMappingInfo(typeAnnotation, typeCondition).combine(info);
//			}
			
		}
		return info;
	}

	/**
	 * Provide a custom method-level request condition.
	 * The custom {@link RequestCondition} can be of any type so long as the 
	 * same condition type is returned from all calls to this method in order
	 * to ensure custom request conditions can be combined and compared. 
	 * @param method the handler method for which to create the condition
	 * @return the condition, or {@code null}
	 */
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		return null;
	}
	
	/**
	 * Provide a custom type-level request condition.
	 * The custom {@link RequestCondition} can be of any type so long as the 
	 * same condition type is returned from all calls to this method in order
	 * to ensure custom request conditions can be combined and compared. 
	 * @param method the handler method for which to create the condition
	 * @return the condition, or {@code null}
	 */
	protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
		return null;
	}

	/**
	 * Created a RequestMappingInfo from a RequestMapping annotation.
	 */
	private RequestMappingInfo createRequestMappingInfo(Method method, RESTfull annotation, Class<?> controllerClass) {
		
		Mapping mapping = RESTfulHelper.matchMapping(method.getName());
		
		if(mapping != null){
			PackageBasedUrlPathBuilder builder = new PackageBasedUrlPathBuilder(packageLocators, controllerPackages,
					controllerSuffix, root);
			
			String[] namespaceString = builder.buildUrlPath(controllerClass, mapping.getMethodName(), mapping.getValues());
			if(namespaceString != null){
				return new RequestMappingInfo(
						new PatternsRequestCondition(namespaceString, 
								getUrlPathHelper(), getPathMatcher(), this.useSuffixPatternMatch, this.useTrailingSlashMatch),
						new RequestMethodsRequestCondition(mapping.getRequestMethods()),
						new ParamsRequestCondition(mapping.getParams()),
						new HeadersRequestCondition(mapping.getHeaders()),
						new ConsumesRequestCondition(mapping.getConsumes(), mapping.getHeaders()),
						new ProducesRequestCondition(mapping.getProduces(), mapping.getHeaders()), 
						getCustomMethodCondition(method));
			}
			
		}
		
		return null;
	}

}