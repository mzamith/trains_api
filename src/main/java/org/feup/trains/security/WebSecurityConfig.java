package org.feup.trains.security;

import org.feup.trains.security.jwt.JWTAuthenticationFilter;
import org.feup.trains.security.jwt.JWTLoginFilter;
import org.feup.trains.security.jwt.TokenAuthenticationService;
import org.feup.trains.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserService userService;
	private final TokenAuthenticationService tokenAuthenticationService;

	public WebSecurityConfig() {
		super(true);
		this.userService = new UserService();
		tokenAuthenticationService = new TokenAuthenticationService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable caching
		http.headers().cacheControl();

		http.csrf().disable() // disable csrf for our requests.
				.authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated().and()
				// We filter the api/login requests
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager(), tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class)
				// And filter other requests to check the presence of JWT in
				// header
				.addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService, userService), UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception{ // Create a default account
	 * auth.inMemoryAuthentication() .withUser("admin") .password("password")
	 * .roles("ADMIN"); }
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return tokenAuthenticationService;
	}

}