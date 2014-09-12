package org.jar.invent.config.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @author zero
 * custom empty interceptor
 * @see WebSecurityExpressionRoot
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter
{
	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
    	//log.info("RESPONSE STATUS: " + response.getStatus());
    }
    @Override
    public boolean preHandle(HttpServletRequest request,
    		HttpServletResponse response, Object handler) throws Exception {

        //log.info("REQUEST AUTH TYPE: " + request.getRemoteAddr()+", REMOTE USER: "+request.getRemoteUser());
    	return super.preHandle(request, response, handler);

    }
}