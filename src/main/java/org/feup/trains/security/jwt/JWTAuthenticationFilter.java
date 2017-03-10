package org.feup.trains.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.feup.trains.security.WebSecurityConfig;
import org.feup.trains.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {
	
    private final TokenAuthenticationService authenticationService;
    private String requestedEndpoint;
	
    public JWTAuthenticationFilter(TokenAuthenticationService authenticationService, String url) {
        
    	this.authenticationService = authenticationService;
    	this.requestedEndpoint = url;
    }	

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication =  authenticationService.getAuthentication((HttpServletRequest)request);
        
        HttpServletResponse res = (HttpServletResponse) response;
        
        if (authentication != null ){
        	if (requestedEndpoint.equals(WebSecurityConfig.ENTRY_POINT_INSPECTOR)){
        		if (!authenticationService.isValidInspector(authentication)){
        			res.sendError(401);
        		}
        	}
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
