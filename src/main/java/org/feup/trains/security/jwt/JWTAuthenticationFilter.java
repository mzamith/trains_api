package org.feup.trains.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.feup.trains.security.AccountAuthenticationProvider;
import org.feup.trains.security.WebSecurityConfig;
import org.feup.trains.service.UserService;
import org.feup.trains.util.RequestContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends GenericFilterBean {

	private final TokenAuthenticationService authenticationService;
	private final AccountAuthenticationProvider authenticationProvider;

	public JWTAuthenticationFilter(TokenAuthenticationService authenticationService,
			AccountAuthenticationProvider authenticationProvider) {

		this.authenticationService = authenticationService;
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURL().toString();

		// if (!url.contains(WebSecurityConfig.ENTRY_POINT_REGISTRATION)) {

		try {
			Authentication authentication = authenticationService.getAuthentication((HttpServletRequest) request);

			User user = (User) authenticationService.getAuthenticatedUser((HttpServletRequest) request);

			if (user != null) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
						user.getPassword());
				this.authenticationProvider.additionalAuthenticationChecks(user, token);
			}

			if (authentication != null && url.contains("/inspector")) {
				if (!authenticationService.isValidInspector(authentication)) {
					res.sendError(401, "You are not a valid Inspector");
				}
			}

			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
			
			//Added condition to verify if token has already expired;
		} catch (ExpiredJwtException eje) {
			res.sendError(401, eje.getLocalizedMessage());
		} catch (UsernameNotFoundException unfe){
			res.sendError(401, unfe.getLocalizedMessage());
		}
	}
	// }
}
