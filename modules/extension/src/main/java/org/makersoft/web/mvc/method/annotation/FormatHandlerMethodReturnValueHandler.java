/*
 * @(#)FormatHandlerMethodReturnValueHandler.java 2013-1-31 下午23:33:33
 *
 * Copyright (c) 2011-2013 Makersoft.org all rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 */
package org.makersoft.web.mvc.method.annotation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.makersoft.web.mvc.annotation.Format;
import org.makersoft.web.mvc.json.JSONResult;
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

}
