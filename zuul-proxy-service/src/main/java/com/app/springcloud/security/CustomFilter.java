package com.app.springcloud.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class CustomFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(ZuulFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		logger.info("<=========================================== Executing CustomFilter ===========================================>");
		RequestContext requestContext = null;
		HttpServletRequest httpServletRequest = null;
		
		try {
			requestContext = RequestContext.getCurrentContext();
			httpServletRequest = requestContext.getRequest();
			logger.info(String.format("<========================= %s Request to %s", httpServletRequest.getMethod(), httpServletRequest.getRequestURL()) + " =========================>");
		} catch(Exception exception) {
			
		}
		return "Fileter-Execution Completed";
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
