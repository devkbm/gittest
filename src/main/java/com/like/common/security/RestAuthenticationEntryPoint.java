package com.like.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private static final Logger log = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
						
		/*Enumeration<String> params = request.getParameterNames();
		
		while(params.hasMoreElements()){
		  String names = (String)params.nextElement();
		  log.info(names);
		 }*/
		
		// 인증 없이 Request 수신시 "Unauthorized(401)" 응답
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());		
	}

}
