/*
 * @(#)FormatHandlerMethodReturnValueHandler.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.mvc.method.annotation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.makersoft.mvc.annotation.Format;
import org.makersoft.mvc.json.JSONResult;
import org.makersoft.mvc.json.JSONWriter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义结果集转换Handler.
 * 
 * @see Format
 * 
 * @version 2013-1-31 下午5:59:39
 * @author Feng Kuok
 */
public class FormatHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler{
	
	private String encoding = "UTF-8";
    private boolean wrapWithComments = false;
    private boolean prefix = false;
    private boolean enableGZIP = false;
    
//    /*
//     * whether to ignore properties defined on base classes of the root object 
//     */
//    private boolean ignoreHierarchy = true;
    
    private boolean enumAsBean = JSONWriter.ENUM_AS_BEAN_DEFAULT;
    
    private boolean noCache = false;
    
    private boolean excludeNullProperties = false;
    
    private String callbackParameter;
    
    private String wrapPrefix;
    
    private String wrapSuffix;

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.getMethodAnnotation(Format.class) != null;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
		
		mavContainer.setRequestHandled(true);
		if (returnValue != null) {
			writeWithJSONSerialize(returnValue, returnType, webRequest);
		}
		
	}
	
	protected <T extends Object> void writeWithJSONSerialize(T returnValue, MethodParameter returnType, NativeWebRequest webRequest) throws IOException ,HttpMediaTypeNotAcceptableException {
		ServletServerHttpRequest inputMessage = this.createInputMessage(webRequest);
		ServletServerHttpResponse outputMessage = this.createOutputMessage(webRequest);
		
		Format format = returnType.getMethodAnnotation(Format.class);
		JSONResult result = new JSONResult();
		
		if(format.excludes().length > 0){
			result.setExcludeProperties(StringUtils.join(format.excludes(), ","));
		}
		
		if(format.includes().length > 0){
			result.setIncludeProperties(StringUtils.join(format.includes(), ","));
		}
		
		// default false
		result.setIgnoreHierarchy(format.ignoreHierarchy());
		
		//settings 
		result.setEncoding(encoding);
		result.setWrapWithComments(wrapWithComments);
		result.setPrefix(prefix);
		result.setEnableGZIP(enableGZIP);
		result.setEnumAsBean(enumAsBean);
		result.setNoCache(noCache);
		result.setExcludeNullProperties(excludeNullProperties);
		result.setCallbackParameter(callbackParameter);
		result.setWrapPrefix(wrapPrefix);
		result.setWrapSuffix(wrapSuffix);
		
		try {
			result.execute(inputMessage.getServletRequest(), outputMessage.getServletResponse(), returnValue);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		return new ServletServerHttpResponse(response);
	}
	
	/**
	 * Creates a new {@link HttpInputMessage} from the given {@link NativeWebRequest}.
	 *
	 * @param webRequest the web request to create an input message from
	 * @return the input message
	 */
	protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		return new ServletServerHttpRequest(servletRequest);
	}

	//setter methods
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setWrapWithComments(boolean wrapWithComments) {
		this.wrapWithComments = wrapWithComments;
	}

	public void setPrefix(boolean prefix) {
		this.prefix = prefix;
	}

	public void setEnableGZIP(boolean enableGZIP) {
		this.enableGZIP = enableGZIP;
	}

//	public void setIgnoreHierarchy(boolean ignoreHierarchy) {
//		this.ignoreHierarchy = ignoreHierarchy;
//	}

	public void setEnumAsBean(boolean enumAsBean) {
		this.enumAsBean = enumAsBean;
	}

	public void setNoCache(boolean noCache) {
		this.noCache = noCache;
	}

	public void setExcludeNullProperties(boolean excludeNullProperties) {
		this.excludeNullProperties = excludeNullProperties;
	}

	public void setCallbackParameter(String callbackParameter) {
		this.callbackParameter = callbackParameter;
	}

	public void setWrapPrefix(String wrapPrefix) {
		this.wrapPrefix = wrapPrefix;
	}

	public void setWrapSuffix(String wrapSuffix) {
		this.wrapSuffix = wrapSuffix;
	}
	
}
