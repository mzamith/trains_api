package org.feup.trains.security;

import org.feup.trains.security.AccountAuthenticationProvider;
import org.feup.trains.security.jwt.JWTAuthenticationFilter;
import org.feup.trains.security.jwt.JWTLoginFilter;
import org.feup.trains.security.jwt.TokenAuthenticationService;
import org.feup.trains.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String LOGIN_ENTRY_POINT_USER = "/login";
	public static final String LOGIN_ENTRY_POINT_INSPECTOR = "/logininspector";
	public static final String ENTRY_POINT_INSPECTOR = "/inspector/**";
	public static final String ENTRY_POINT_USER = "/api/**";
	public static final String ENTRY_POINT_REGISTRATION = "/register";
	
	 /**
     * The AccountAuthenticationProvider security component.
     */
    @Autowired
    private AccountAuthenticationProvider accountAuthenticationProvider;

	private final UserService userService;
	
	private final TokenAuthenticationService tokenAuthenticationService;

	public WebSecurityConfig() {
		
		super(true);
		this.userService = UserService.getInstance();
		tokenAuthenticationService = new TokenAuthenticationService(userService);
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable caching
		http.headers().cacheControl();

		http.csrf().disable() // disable csrf for our requests.
				.exceptionHandling()
				
				.and()
					.authorizeRequests()
						.antMatchers("/").permitAll()
						.antMatchers(HttpMethod.POST, LOGIN_ENTRY_POINT_USER).permitAll()
						.antMatchers(HttpMethod.POST, LOGIN_ENTRY_POINT_INSPECTOR).permitAll()
						.antMatchers(HttpMethod.POST, ENTRY_POINT_REGISTRATION).permitAll()
						
						.and()
						.authorizeRequests().anyRequest().authenticated().and()
				
				.addFilterBefore(new JWTLoginFilter(LOGIN_ENTRY_POINT_INSPECTOR, authenticationManager(), tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class)
			
				// We filter the api/login requests
				.addFilterBefore(new JWTLoginFilter(LOGIN_ENTRY_POINT_USER, authenticationManager(), tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class)
				
				// And filter other requests to check the presence of JWT in
				// header
				.addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService, accountAuthenticationProvider),
						UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(HttpMethod.POST, ENTRY_POINT_REGISTRATION);
    }


    /**
     * This method builds the AuthenticationProvider used by the system to
     * process authentication requests.
     * 
     * @param auth An AuthenticationManagerBuilder instance used to construct
     *        the AuthenticationProvider.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(accountAuthenticationProvider);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    /**
     * Supplies a PasswordEncoder instance to the Spring ApplicationContext. The
     * PasswordEncoder is used by the AuthenticationProvider to perform one-way
     * hash operations on passwords for credential comparison.
     * 
     * @return A PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}

	@Bean
	public TokenAuthenticationService userTokenAuthenticationService() {
		return tokenAuthenticationService;
	}
	



}